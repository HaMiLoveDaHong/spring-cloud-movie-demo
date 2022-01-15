package com.zhw.cloud.microservicegatewayzuulfilter.fallback;

import org.springframework.cloud.netflix.zuul.filters.route.ZuulFallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * 服务回退设置
 * @Author zhw
 * @since 2022/1/12
 */
public class UserFallbackProvider implements ZuulFallbackProvider {
    /**
     * 指定那个微服务提供服务
     * @return
     */
    @Override
    public String getRoute() {
        return "microservice-simple-provider-user";
    }

    @Override
    public ClientHttpResponse fallbackResponse() {
        return new ClientHttpResponse() {

            /**
             * fallback 返回状态码
             * @return
             * @throws IOException
             */
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return HttpStatus.OK;
            }

            /**
             * 数字类型状态码
             * @return
             * @throws IOException
             */
            @Override
            public int getRawStatusCode() throws IOException {
                return this.getStatusCode().value();
            }

            @Override
            public String getStatusText() throws IOException {
                return this.getStatusCode().getReasonPhrase();
            }

            @Override
            public void close() {

            }

            /**
             * 响应体
             * @return
             * @throws IOException
             */
            @Override
            public InputStream getBody() throws IOException {
                return new ByteArrayInputStream("用户服务不可用，请稍候再请求".getBytes());
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                MediaType mt = new MediaType("application","json", Charset.forName("utf-8"));
                headers.setContentType(mt);
                return headers;
            }
        };
    }
}
