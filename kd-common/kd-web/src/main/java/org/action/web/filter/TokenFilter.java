package org.action.web.filter;
import org.action.web.constant.Constant;
import org.action.web.utils.JwtUtils;
import org.action.web.vo.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author gzw
 * @create 2024/9/30 16:16
 */
public class TokenFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(TokenFilter.class);




    @Override
    public void init(FilterConfig filterConfig) {
        // 过滤器初始化，可选实现
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
            HttpServletRequest httpRequest = (HttpServletRequest) request;
            HttpServletResponse httpResponse = (HttpServletResponse) response;

            // 从请求头中获取Token

            String token = JwtUtils.getJwtToken(httpRequest);

            if (token == null || "null".equals(token) || "undefined".equals(token)) {
                httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                httpResponse.getWriter().write("No Token Found ...");
                logger.error("no token found in header , pls check!");
                return;
            }

            // 校验Token的有效性
        Result<?> result = JwtUtils.verifyToken(token, Constant.TOKEN_MY);

        if (!result.getSuccess()) {
                httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                httpResponse.getWriter().write("Invalid or expired token");
                logger.error("token validate failed , pls check!");
                return;
            }
            // Token有效，继续执行其他过滤器链
            filterChain.doFilter(request, response);
    }
}
