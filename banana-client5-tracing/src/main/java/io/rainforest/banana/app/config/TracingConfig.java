package io.rainforest.banana.app.config;

import io.micrometer.tracing.SpanCustomizer;
import org.springframework.context.annotation.Configuration;
import zipkin2.reporter.Sender;

@Configuration
public class TracingConfig {

//    @Value("${spring.zipkin.baseUrl}")
//    private String zipkinBaseUrl;
//
//    @Bean
//    public ZipkinSender zipkinSender() {
//        Sender sender = OkHttpSender.create(zipkinBaseUrl + "/api/v2/spans");
//        return ZipkinSender.create(sender);
//    }
//
//    @Bean
//    public SpanCustomizer spanCustomizer(ZipkinSender zipkinSender) {
//        return ZipkinV2Reporter.create(zipkinSender);
//    }
}
