package com.example.backend.chat;

import com.example.backend.CommonResult;
import com.example.backend.chat.SignatureUtil;
import com.example.backend.user.JwtUtil;
import com.example.backend.user.User;
import com.example.backend.user.UserMapper;
import com.example.backend.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {
//    @Autowired
//    private WebClient webClient;
//    @Autowired
//    private SignatureUtil signatureUtil;
//    @Autowired
//    private ChatService chatService;
    @Autowired
    private UserService userService;
    @Autowired
    private  ChatMapper chatMapper;

    @GetMapping("/history")
    public CommonResult<?> fetchHistory(@RequestHeader("Authorization") String accessToken) {
        try {
            System.out.println("history");
            String token = accessToken.substring(7);
            User user = userService.getUserInfoByToken(token);
//            System.out.println("正在获取");
            List<Integer> sessions = chatMapper.getChatHistory(user.getId());
//            System.out.println("获取成功");
            return CommonResult.success(sessions);
        } catch (Exception e) {
            return CommonResult.error(400,"获取sessions出现错误");
        }
    }

    @GetMapping("/{sessionid}")
    public CommonResult<?> fetchChat(@PathVariable int sessionid, @RequestHeader("Authorization") String accessToken) {
        try {
            System.out.println("sessionid");
            String token = accessToken.substring(7);
            User user = userService.getUserInfoByToken(token);
            System.out.println("获取中");
            List<Chat> chats = chatMapper.getChats(user.getId(),sessionid);
            System.out.println("获取成功");
            return CommonResult.success(chats);
        } catch (Exception e) {
            return CommonResult.error(400,"历史聊天获取错误");
        }
    }

    @PostMapping("/conchat")//带sessionid的提问
    public CommonResult<?> sendQuestion(@RequestBody Chat chatRequest, @RequestHeader("Authorization") String accessToken) {
        try {
            String token = accessToken.substring(7);
            User user = userService.getUserInfoByToken(token);
            chatRequest.setUid(user.getId());
            chatRequest.setDate(new Date());

            String question = chatRequest.getQuestion();
            String jsonResponse = vivogpt.vivogpt(question);
            ParselContent parselContent = new ParselContent();
            CommonResult commonResult = parselContent.parsel(jsonResponse);
            int code = commonResult.getCode();
            if(code==200){
                String answer = commonResult.getData().toString();
                System.out.println("answer:"+answer);
                chatRequest.setUid(user.getId());
                chatRequest.setQuestion(question);
                chatRequest.setAnswer(answer);
                chatRequest.setDate(new Date());
                chatMapper.insertChat(chatRequest);
                System.out.println("插入成功");
                return CommonResult.success(chatRequest);
            }else{
                return CommonResult.error(code,commonResult.getMsg());
            }
        } catch (Exception e) {
            return CommonResult.error(400,"响应出现错误");
        }
    }

    @PostMapping("/newchat")//不带sessionid的提问
    public CommonResult<?> newChat(@RequestBody String question, @RequestHeader("Authorization") String accessToken) {
        try {
            String token = accessToken.substring(7);
            User user = userService.getUserInfoByToken(token);
            ChatHistory chatHistory = new ChatHistory();
            chatHistory.setUid(user.getId());
            chatHistory.setDate(new Date());
            chatMapper.insertChatHistory(chatHistory);
            int sessionid = chatHistory.getSessionid();

            Chat chat = new Chat();
            String jsonResponse = vivogpt.vivogpt(question);
            ParselContent parselContent = new ParselContent();
            CommonResult commonResult = parselContent.parsel(jsonResponse);
            int code = commonResult.getCode();
            if(code==200){
                String answer = commonResult.getData().toString();
                System.out.println("answer:"+answer);
                chat.setUid(user.getId());
                chat.setSessionid(sessionid);
                chat.setQuestion(question);
                chat.setAnswer(answer);
                chat.setDate(new Date());
                chatMapper.insertChat(chat);
                System.out.println("插入成功");
                return CommonResult.success(chat);
            }else{
                return CommonResult.error(code,commonResult.getMsg());
            }
        } catch (Exception e) {
            return CommonResult.error(400,"出现错误");
        }
    }
}