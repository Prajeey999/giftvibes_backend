package com.giftvibes.prajeetkumar.utils;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

public class MyConstant {
        // Request Mappings
        public static final String AUTH = "/api/v1/auth";
        public static final String USER = "/api/v1/user";

        // End Points
        public static final String GET = "/get";
        public static final String LOGIN = "/login";
        public static final String REGISTER = "/register";
        public static final String REFRESR_TOKEN = "/refresh-token";
        public static final String LOGOUT = "/logout";
        public static final String FORGOT_PASSWORD = "/forgot-password";

        // Web Security
        public static final String[] WHITELIST_URL = {
                        "/api/v1/auth/**",
                        "/swagger-ui/**",
                        "/swagger-ui.html/",
                        "/v3/api-docs/**"
        };
        
        public static final List<String> ORIGINS = Arrays.asList("http://localhost:4000","http://localhost:5173");
        public static final List<String> HEADERS = Arrays.asList(HttpHeaders.AUTHORIZATION,HttpHeaders.CONTENT_TYPE);
        public static final List<String> METHODS = Arrays.asList(HttpMethod.GET.name(),
        HttpMethod.POST.name(),HttpMethod.PUT.name(),HttpMethod.PATCH.name(),HttpMethod.DELETE.name(),
        HttpMethod.HEAD.name(),HttpMethod.OPTIONS.name());


        // Swagger Info
        public static final String SWAGGER_INFO_TITLE = "Giftvibes API Documentation";
        public static final String SWAGGER_INFO_DESCRIPTION = "This is a Giftvibes API documentation";
        public static final String SWAGGER_INFO_VERSION = "1.0.0";
        public static final String SWAGGER_INFO_CONTACT_NAME = "Prajeet Kumar K";
        public static final String SWAGGER_INFO_CONTACT_EMAIL = "727721eucs101@skcet.ac.in";
        public static final String SWAGGER_INFO_CONTACT_URL = "https://giftvibes.com";
        public static final String SWAGGER_INFO_LISENCE_NAME = "Apache 2.0";
        public static final String SWAGGER_INFO_LISENCE_URL = "https://www.apache.org/licenses/LICENSE-2.0.html";


        // JsonWebToken
        public static final String JWT_LOCALHOST_URL = "http://localhost:8181";
        public static final String JWT_SECURITY_SCHEME_NAME = "bearerAuth";
        public static final String JWT_SCHEME = "bearer";
        public static final String JWT_DESCRIPTION = "Provide the JWT token.";
        public static final String JWT_BEARER_FORMAT = "JWT";
}