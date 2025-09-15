package com.yihuobao.yhb.pitayafruit.controller;

import com.yihuobao.yhb.pitayafruit.req.DifyRequestBody;
import com.yihuobao.yhb.pitayafruit.resp.StreamResponse;
import com.yihuobao.yhb.pitayafruit.service.ConversationService;
import com.yihuobao.yhb.pitayafruit.service.DifyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/assistant")
@RequiredArgsConstructor
public class AssistantController {
    //易货宝小助手apiKey
    private String apiKey = "app-IJzCuctR9pUHyBFw8gpdjXPE";

    @Autowired
    private final DifyService difyService;

    @Autowired
    private ConversationService conversationService;

    @PostMapping("/chat")
    public Flux<StreamResponse> chat(@RequestBody DifyRequestBody request){
        return difyService.streamingMessage(request.getQuery(),request.getUser(),apiKey);
    }

    @GetMapping("/history")
    public Map<String,Object> history(@RequestParam DifyRequestBody query){
        String conversationId=query.getConversationId();
        int limit = 20;
        Map<String, Object> result = new HashMap<>();
        result.put("success", true);
        result.put("history", conversationService.getConversationHistory(conversationId, limit));
        return result;
    }

}
