package com.example.backend.newchat;

import com.example.backend.chat.SignatureUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {
    @Autowired
    private WebClient webClient;
    @Autowired
    private SignatureUtil signatureUtil;
    @Autowired
    private ChatService chatService;

    @GetMapping("/{sessionid}")
    public List<Chat> getChatsBySessionId(@PathVariable String sessionid) {
        return chatService.getChatsBySessionId(sessionid);
    }

    @PostMapping("/send")
    public String sendChat(@RequestBody Chat chat) {
        chat.setDate(new Date());
        chatService.insertChat(chat);
        // 返回 answer（模拟回答逻辑）
        String answer = "这是一个示例回答";
        chat.setAnswer(answer);
        return answer;
    }

    @GetMapping("/chathistory")
    public List<ChatHistory> getChatHistoriesByUid(@RequestParam int uid) {
        return chatService.getChatHistoriesByUid(uid);
    }
}
