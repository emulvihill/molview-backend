   package com.snazzyrobot.molviewbackend.security;

   import jakarta.servlet.http.HttpServletRequest;
   import jakarta.servlet.http.HttpServletResponse;
   import org.springframework.beans.factory.annotation.Autowired;
   import org.springframework.stereotype.Component;
   import org.springframework.web.servlet.HandlerInterceptor;

   @Component
   public class JwtInterceptor implements HandlerInterceptor {

       @Autowired
       private JwtUtil jwtUtil;

       @Override
       public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
           String token = request.getHeader("Authorization");

           if (token != null && token.startsWith("Bearer ")) {
               try {
                   token = token.substring(7);
                   jwtUtil.extractClaims(token);
               } catch (Exception e) {
                   throw new RuntimeException("Invalid JWT Token", e);
               }
           } else {
               throw new RuntimeException("Missing or invalid JWT Token");
           }
           return true;
       }
   }