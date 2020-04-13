package com.zhongjianbaoapi.config.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
//import com.zhongjianbaoapi.entity.vo.TokenUser;
import com.zhongjianbaoapi.entity.FaUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service("TokenService")
public class TokenService {
    //过期时间
    private Long EXPIRE_TIME = (long) 12 * 30 * 24 * 60 * 60 * 60 * 1000;
    //private String TOKEN_SECRET = "f26e587c28064d0e855e72c0a6a0e123";

    public String getToken(FaUser user) {
        String token="";
        try{
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(user.getId() + "");
            Map<String, Object> header = new HashMap<String, Object>();
            header.put("typ","JWT");
            header.put("alg","HS256");

            token= JWT.create()
                    .withHeader(header)
                    /*.withClaim("username",user.getAccount())*/
//                    .withClaim("userScale", user.getMobile())
                    .withClaim("userId",user.getId())
                    .withExpiresAt(date)
                    .sign(algorithm); // 以 userId 作为 token 的密钥
            return token;
        }catch(UnsupportedEncodingException e){
            return null;
        }
    }

    /**
     * token解码
     */
    public boolean verity(String token,String password) {
        try{
            JWTVerifier verity = JWT.require(Algorithm.HMAC256(password)).build();
            DecodedJWT jwt = verity.verify(token);
        }catch(Exception e){
            return false;
        }
        return true;
    }
}

