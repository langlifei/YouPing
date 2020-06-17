package com.youping.test.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

public class JwtUtil {

    private static final String SECRET = "d46baea9-924b-472b-97f6-b8bc577d6a08";//由UUID随机生成
    private static final long EXPIRATION = 15*60*1000;//设置时间为15分钟
    public static final String TOKEN_HEADER="Authorization";
    public static final String TOKEN_PREFIX="Bearer";
    private static final String REFRESH_TOKEN="REFRESH_TOKEN:";
    private static final long REFRESH_TOKEN_EXPIRATION=3*60*60;//设为3个小时,单位为秒

    public static String getRefreshToken() {
        return REFRESH_TOKEN;
    }

    public static long getRefreshTokenExpiration() {
        return REFRESH_TOKEN_EXPIRATION;
    }


    public static String getToken(HttpServletRequest request){
        String token = request.getHeader(TOKEN_HEADER);
        if(token!=null){
            token = token.replace(TOKEN_PREFIX,"").trim();
        }
        return token;
    }

    /**
     * 对用户信息进行签名
     * @param
     * @return
     */
    public static String sign(Map<String,String> claims){
        //设置token过期时间
        Date date = new Date(System.currentTimeMillis()+EXPIRATION);
        JWTCreator.Builder jwt = JWT.create().withExpiresAt(date);
        //将Playload部分的信息加入jwt
        for (Map.Entry<String,String> entry:claims.entrySet()){
            jwt.withClaim(entry.getKey(),entry.getValue());
        }
        //结合Signature整合为jwt
        return jwt.sign(Algorithm.HMAC256(SECRET));
    }

    /**
     * 校验token并解析token
     * @param token
     * @return
     */
    public static boolean verifyToken(String token){
        try {
            DecodedJWT jwt = JWT.require(Algorithm.HMAC256(SECRET)).build().verify(token);
            return true;
        }catch (JWTVerificationException e){
            Throwable throwable = e.getCause();
            if (throwable instanceof TokenExpiredException)
                throw new TokenExpiredException("Token过期");
            else
                throw e;
        }
    }

    /**
     *
     * @param token jwt
     * @param claim token中存储信息的键
     * @return 键对应的值
     */
    public static String getClaim(String token,String claim){
        //进行解码
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getClaim(claim).asString();
    }
}
