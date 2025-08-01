package com.yihuobao.yhb.pitayafruit.service;

import com.alibaba.fastjson2.JSON;
import com.yihuobao.yhb.pitayafruit.req.DifyRequestBody;
import com.yihuobao.yhb.pitayafruit.resp.BlockResponse;
import com.yihuobao.yhb.pitayafruit.resp.StreamResponse;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import lombok.RequiredArgsConstructor;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;


@Service
@RequiredArgsConstructor
public class DifyService {

    @Value("${dify.url}")
    private String url;

    private final RestTemplate restTemplate;

    private final WebClient webClient;



    /**
     * 流式调用dify.
     *
     * @param query  查询文本
     * @param userId 用户id
     * @param apiKey apiKey 通过 apiKey 获取权限并区分不同的 dify 应用
     * @return Flux 响应流
     */
    public Flux<StreamResponse> streamingMessage(String query, String userId, String apiKey) {
        //1.设置请求体
        DifyRequestBody body = new DifyRequestBody();
        body.setInputs(new HashMap<>());
        body.setQuery(query);
        body.setResponseMode("streaming");
        body.setConversationId("");
        body.setUser(userId);
        //2.使用webclient发送post请求
        return webClient.post()
                .uri(url)
                .headers(httpHeaders -> {
                    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
                    httpHeaders.setBearerAuth(apiKey);
                })
                .bodyValue(JSON.toJSONString(body))
                .retrieve()
                .bodyToFlux(StreamResponse.class);
    }


    /**
     * 阻塞式调用dify.
     *
     * @param query 查询文本
     * @param userId 用户id
     * @param apiKey apiKey 通过 apiKey 获取权限并区分不同的 dify 应用
     * @return BlockResponse
     */
    public BlockResponse blockingMessage(String query, String userId, String apiKey) {
        //1.设置请求体
        DifyRequestBody body = new DifyRequestBody();
        body.setInputs(new HashMap<>());
        body.setQuery(query);
        body.setResponseMode("blocking");
        body.setConversationId("");
        body.setUser(userId);
        //2.设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.setBearerAuth(apiKey);
        //3.封装请求体和请求头
        String jsonString = JSON.toJSONString(body);
        HttpEntity<String> entity = new HttpEntity<>(jsonString, headers);
        //4.发送post请求，阻塞式
        ResponseEntity<BlockResponse> stringResponseEntity =
                restTemplate.postForEntity(url, entity, BlockResponse.class);
        //5.返回响应体
        return stringResponseEntity.getBody();
    }

    /**
     * 阻塞式调用dify，仅返回调用成功或失败状态
     *
     * @param query  查询文本
     * @param userId 用户id
     * @param apiKey apiKey 通过 apiKey 获取权限并区分不同的 dify 应用
     * @return 调用成功返回true，失败返回false
     */
    public boolean blockingMessageresult(String query, String userId, String apiKey) {
        //1.设置请求体
        DifyRequestBody body = new DifyRequestBody();
        body.setInputs(new HashMap<>());
        body.setQuery(query);
        body.setResponseMode("blocking");
        body.setConversationId("");
        body.setUser(userId);

        //2.设置请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        headers.setBearerAuth(apiKey);

        //3.封装请求体和请求头
        String jsonString = JSON.toJSONString(body);
        HttpEntity<String> entity = new HttpEntity<>(jsonString, headers);

        try {
            //4.发送post请求，只关注状态码
            ResponseEntity<Void> response = restTemplate.postForEntity(url, entity, Void.class);

            //5.检查状态码是否成功（2xx系列）
            return response.getStatusCode().is2xxSuccessful();
        } catch (Exception e) {
            // 处理异常情况（如网络错误、服务器错误等）
            e.printStackTrace(); // 可以根据需要记录日志
            return false;
        }
    }


}
