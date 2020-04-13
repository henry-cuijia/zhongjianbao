package com.zhongjianbaoapi.config.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
//import com.zhongjianbaoapi.dao.OrCgrUserDao;
//import com.zhongjianbaoapi.dao.OrPsrUserDao;
//import com.zhongjianbaoapi.entity.OrCgrUser;
//import com.zhongjianbaoapi.entity.OrPsrUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class AuthenticationInterceptor implements HandlerInterceptor {
    //日志
    private static Logger logger = LoggerFactory.getLogger(AuthenticationInterceptor.class);

//    @Autowired
//    OrCgrUserDao orCgrUserDao;
//
//    @Autowired
//    OrPsrUserDao orPsrUserDao;
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
//        String token = httpServletRequest.getHeader("token");// 从 http 请求头中取出 token
//        // 如果不是映射到方法直接通过
//        if(!(object instanceof HandlerMethod)){
//            return true;
//        }
//        HandlerMethod handlerMethod=(HandlerMethod)object;
//        Method method=handlerMethod.getMethod();
//        //检查是否有passtoken注释，有则跳过认证
//        if (method.isAnnotationPresent(PassToken.class)) {
//            PassToken passToken = method.getAnnotation(PassToken.class);
//            if (passToken.required()) {
//                return true;
//            }
//        }
//        //检查有没有需要用户权限的注解
//        if (method.isAnnotationPresent(UserLoginToken.class)) {
//            UserLoginToken userLoginToken = method.getAnnotation(UserLoginToken.class);
//            if (userLoginToken.required()) {
//                // 执行认证
//                if (token == null) {
//                    logger.info("token为空");
//                    httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//                    return false;
//                }
//                // 获取 token 中的 user id
//                String use_id = "";
//                try {
//                    use_id = JWT.decode(token).getClaim("userId").asString();
//                }catch (JWTDecodeException j){
//                    logger.info("token錯誤");
//                    httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//                    return false;
//                }
//
//                String userType = JWT.decode(token).getClaim("userType").asString();
//                if("client".equals(userType)){    //客户端
//                    OrCgrUser user = orCgrUserDao.selectByPrimaryKey(use_id);
//
//                    if(user == null){
//                        logger.info("用户不存在");
//                        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//                        return false;
//                    }
//
//                    if(!token.equals(user.getUserToken())){
//                        logger.info("token错误");
//                        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//                        return false;
//                    }
//
//                    // 验证 token
//                    JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(String.valueOf(user.getUserId()))).build();
//                    try {
//                        jwtVerifier.verify(token);
//                    } catch (JWTVerificationException e) {
//                        logger.info("token过时" + e.getMessage());
//                        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//                        return false;
//                    }
//                    return true;
//                }else if("delivery".equals(userType)){   //配送端
//                    //
//                   OrPsrUser user =  orPsrUserDao.selectByPrimaryKey(use_id);
//                    if(user == null){
//                        logger.info("用户不存在");
//                        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//                        return false;
//                    }
//
//                    if(!token.equals(user.getUserToken())){
//                        logger.info("token错误");
//                        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//                        return false;
//                    }
//
//                    // 验证 token
//                    JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(String.valueOf(user.getPsrId()))).build();
//                    try {
//                        jwtVerifier.verify(token);
//                    } catch (JWTVerificationException e) {
//                        logger.info("token过时" + e.getMessage());
//                        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
//                        return false;
//                    }
//                    return true;
//                }
//
//
//
//
//                return false;
//            }
//        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
