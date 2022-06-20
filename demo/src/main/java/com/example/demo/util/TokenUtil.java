package com.example.demo.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@Component
public class TokenUtil {

    //加密secret
    private static final String SECRET = "jiuZhou-token";

    //过期时间
    private static final Integer TIME_OUT_DAY = 30;

    //需要重新生成的天数 如果token的时间超过这个 则重新生成token
    private static final Integer NEED_CREATE_DAY = 7;

    public static final String USER_ID = "userid";
    public static final String USER_NAME = "username";

    /**
     * 签名生成
     */
    public static String sign(String userName, String userId) {

        String token = null;
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DATE, TIME_OUT_DAY);
            token = JWT.create()
                    .withIssuer("auth0")
                    .withClaim(USER_NAME, userName)
                    .withClaim(USER_ID, userId)
                    .withExpiresAt(calendar.getTime())
                    .sign(Algorithm.HMAC256(SECRET));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return token;

    }

    /**
     * 签名验证
     */
    public static boolean verify(String token) {

        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(SECRET)).withIssuer("auth0").build();
            DecodedJWT jwt = verifier.verify(token);
            System.out.println("认证通过：");
            System.out.println("issuer:：" + jwt.getIssuer());
            System.out.println("username：" + jwt.getClaim(USER_NAME).asString());
            System.out.println("userid：" + jwt.getClaim(USER_ID).asString());
            System.out.println("过期时间：" + jwt.getExpiresAt());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
