package org.action.rpc.kdCade;
import com.alibaba.fastjson2.JSON;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import javax.validation.ValidationException;
import lombok.extern.slf4j.Slf4j;
import org.action.enums.RepoErrorCode;
import org.action.exception.BizException;
import org.action.exception.SystemException;
import org.action.response.BaseResponse;
import org.action.utils.BeanValidator;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.StopWatch;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

/**
 * @author gzw
 * @description： KdCade的切面处理类，统一统计进行参数校验及异常捕获
 * @since：2024/8/2 23:29
 */
@Slf4j
@Aspect
@Component
public class KdCadeAspect {

    @Around("@annotation(org.action.rpc.kdCade.KdCade)")
    public Object facade(ProceedingJoinPoint pjp) throws Exception {

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        Method method = ((MethodSignature) pjp.getSignature()).getMethod();
        Object[] args = pjp.getArgs();
        log.info("start to execute , method  {}  , args {}" , method.getName() , JSON.toJSONString(args));

        Class<?> returnType = ((MethodSignature) pjp.getSignature()).getMethod().getReturnType();

        //循环遍历所有参数，进行参数校验
        for (Object parameter : args) {
            try {
                BeanValidator.validateObject(parameter);
            } catch (ValidationException e) {
                printLog(stopWatch, method, args, "failed to validate", null, e);
                return getFailedResponse(returnType, e);
            }
        }

        try {
            // 目标方法执行
            Object response = pjp.proceed();
            enrichObject(response);
            printLog(stopWatch, method, args, "end to execute", response, null);
            return response;
        } catch (Throwable throwable) {
            // 如果执行异常，则返回一个失败的response
            printLog(stopWatch, method, args, "failed to execute", null, throwable);
            return getFailedResponse(returnType, throwable);
        }
    }

    /**
     * 日志打印
     */
    private void printLog(StopWatch stopWatch, Method method, Object[] args, String action, Object response,
                          Throwable throwable) {
        try {
            log.info(getInfoMessage(action, stopWatch, method, args, response, throwable), throwable);
        } catch (Exception e1) {
            log.error("log failed", e1);
        }
    }

    /**
     * 统一格式输出，方便做日志统计
     * <p>
     * *** 如果调整此处的格式，需要同步调整日志监控 ***
     *
     * @param action    行为
     * @param stopWatch 耗时
     * @param method    方法
     * @param args      参数
     * @param response  响应
     * @return 拼接后的字符串
     */
    private String getInfoMessage(String action, StopWatch stopWatch, Method method, Object[] args, Object response,
                                  Throwable exception) {

        StringBuilder stringBuilder = new StringBuilder(action);
        stringBuilder.append(" ,method = ");
        stringBuilder.append(method.getName());
        stringBuilder.append(" ,cost = ");
        stringBuilder.append(stopWatch.getTime()).append(" ms");
        if (response instanceof BaseResponse) {
            stringBuilder.append(" ,success = ");
            stringBuilder.append(((BaseResponse) response).getSuccess());
        }
        if (exception != null) {
            stringBuilder.append(" ,success = ");
            stringBuilder.append(false);
        }
        stringBuilder.append(" ,args = ");
        stringBuilder.append(JSON.toJSONString(Arrays.toString(args)));

        if (response != null) {
            stringBuilder.append(" ,resp = ");
            stringBuilder.append(JSON.toJSONString(response));
        }

        if (exception != null) {
            stringBuilder.append(" ,exception = ");
            stringBuilder.append(exception.getMessage());
        }

        if (response instanceof BaseResponse) {
            BaseResponse baseResponse = (BaseResponse) response;
            if (Boolean.FALSE.equals(baseResponse.getSuccess())) {
                stringBuilder.append(" , execute_failed");
            }
        }

        return stringBuilder.toString();
    }

    /**
     * 将response的信息补全，主要是code和message
     *
     * @param response resp
     */
    private void enrichObject(Object response) {
        if (response instanceof BaseResponse) {
            if (Boolean.TRUE.equals(((BaseResponse) response).getSuccess())) {
                //如果状态是成功的，需要将未设置的responseCode设置成SUCCESS
                if (StringUtils.isEmpty(((BaseResponse) response).getCode())) {
                    ((BaseResponse) response).setCode(RepoErrorCode.SUCCESS.name());
                }
            } else {
                //如果状态是成功的，需要将未设置的responseCode设置成BIZ_ERROR
                if (StringUtils.isEmpty(((BaseResponse) response).getCode())) {
                    ((BaseResponse) response).setCode(RepoErrorCode.UNKNOWN_ERROR.name());
                }
            }
        }
    }

    /**
     * 定义并返回一个通用的失败响应
     */
    private Object getFailedResponse(Class<?> returnType, Throwable throwable)
            throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        //如果返回值的类型为BaseResponse 的子类，则创建一个通用的失败响应
        if (returnType.getDeclaredConstructor().newInstance() instanceof BaseResponse) {
            BaseResponse response = (BaseResponse) returnType.getDeclaredConstructor().newInstance();
            response.setSuccess(false);
            if (throwable instanceof BizException) {
                BizException bizException = (BizException) throwable;
                response.setMsg(bizException.getErrorCode().getMessage());
                response.setCode(bizException.getErrorCode().getCode());
            } else if (throwable instanceof SystemException) {
                SystemException systemException = (SystemException) throwable;
                response.setMsg(systemException.getErrorCode().getMessage());
                response.setCode(systemException.getErrorCode().getCode());
            } else {
                response.setMsg(throwable.toString());
                response.setCode(RepoErrorCode.UNKNOWN_ERROR.name());
            }

            return response;
        }

        log.error(
                "failed to getFailedResponse , returnType ({}) is not instanceof BaseResponse",returnType);
        return null;
    }
}
