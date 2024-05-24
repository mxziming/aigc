package com.example.backend.chat;

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


    @GetMapping("/history")
    public String fetchHistory(@RequestHeader("Authorization") String accessToken) {
        // 处理获取聊天历史记录的逻辑
        return "Chat history"; // 示例返回值
    }

    @GetMapping("/{sessionid}")
    public String fetchChat(@PathVariable int sessionid, @RequestHeader("Authorization") String accessToken) {
        // 处理获取特定会话聊天记录的逻辑
        return "Chat with session ID: " + sessionid; // 示例返回值
    }

    @PostMapping("/chat")
    public String sendQuestion(@RequestBody String question, @RequestHeader("Authorization") String accessToken) {
        // 处理发送问题的逻辑
        return "Question sent"; // 示例返回值
    }

    @GetMapping("/newchat")
    public String newChat(@RequestHeader("Authorization") String accessToken) {
        // 处理创建新会话的逻辑
        return "New chat"; // 示例返回值
    }
//    @GetMapping("/{sessionid}")
//    public List<Chat> getChatsBySessionId(@PathVariable String sessionid) {
//        return chatService.getChatsBySessionId(sessionid);
//    }
//
//    @PostMapping("/send")
//    public String sendChat(@RequestBody Chat chat) {
//        chat.setDate(new Date());
//        chatService.insertChat(chat);
//        // 返回 answer（模拟回答逻辑）
//        String answer = "这是一个示例回答";
//        chat.setAnswer(answer);
//        return answer;
//    }
//
//    @GetMapping("/chathistory")
//    public List<ChatHistory> getChatHistoriesByUid(@RequestParam int uid) {
//        return chatService.getChatHistoriesByUid(uid);
//    }
}
