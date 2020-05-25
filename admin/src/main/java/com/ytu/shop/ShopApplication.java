package com.ytu.shop;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(value = "com.ytu.shop.mapper")
public class ShopApplication {

//    @Bean
//    public ServletWebServerFactory servletContainer() {
//        TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
//            @Override
//            protected void postProcessContext(Context context) {
//                SecurityConstraint securityConstraint = new SecurityConstraint();
//                securityConstraint.setUserConstraint("CONFIDENTIAL");
//                SecurityCollection collection = new SecurityCollection();
//                collection.addPattern("/*");
//                securityConstraint.addCollection(collection);
//                context.addConstraint(securityConstraint);
//            }
//        };
//        tomcat.addAdditionalTomcatConnectors(redirectConnector());
//        return tomcat;
//    }
//
//    private Connector redirectConnector() {
//        Connector connector = new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
//        connector.setScheme("http");
//        connector.setPort(80);
//        connector.setSecure(false);
//        connector.setRedirectPort(443);
//        return connector;
//    }

    public static void main(String[] args) {
        SpringApplication.run(ShopApplication.class, args);
    }

}
