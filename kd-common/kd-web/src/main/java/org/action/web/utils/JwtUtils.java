package org.action.web.utils;
import cn.hutool.core.util.RandomUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.extern.slf4j.Slf4j;
import org.action.web.vo.Result;
import org.apache.commons.lang.StringUtils;
import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import static org.action.web.constant.Constant.TOKEN_HEADER_KEY;
import static org.action.web.constant.Constant.TOKEN_HEADER_VALUE_PREFIX;

/**
 * @author gzw
 * @create 2024/9/30 16:16
 */
@Slf4j
public class JwtUtils {

    private JwtUtils() {
    }

    public static String getToken(String appId, String secret, int amount, String issUser) {
        Calendar ca = Calendar.getInstance();
        ca.add(12, amount);
        String random = RandomUtil.randomString(24);
        String token = "";
        token = JWT.create().withSubject(appId).withIssuedAt(new Date(System.currentTimeMillis() - 60000L)).withExpiresAt(ca.getTime()).withClaim("ran", random).withIssuer(issUser).sign(getSecret(secret));
        return token;
    }

    public static String getJwtToken(HttpServletRequest request) {
        String authorization = request.getHeader(TOKEN_HEADER_KEY);
        return !StringUtils.isBlank(authorization) && authorization.startsWith(TOKEN_HEADER_VALUE_PREFIX) ? authorization.substring(7) : null;
    }

    public static Result<?> verifyToken(String token, String secret) {
        JWTVerifier jwtVerifier = JWT.require(getSecret(secret)).build();
        try {
            DecodedJWT verify = jwtVerifier.verify(token);
            Map<String, Claim> claims = verify.getClaims();
            return  Result.success(claims);
        } catch (TokenExpiredException var7) {
            log.error("jwtToken过期：{}，原因：{}", token, var7.getMessage());
            return  Result.error("4401","客户端身份认证失败");
        } catch (JWTVerificationException var8) {
            log.error("jwtToken无效：{}，原因：{}", token, var8.getMessage());
            return  Result.error("4401","客户端身份认证失败");
        }
    }

    public static Algorithm getSecret(String secret) {
        return Algorithm.HMAC256(secret);
    }

}
