package com.example.backend.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public String addUser(@RequestBody User user) {
        userService.addUser(user);
        return "用户添加成功";
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody User loginUser) {
        Map<String, Object> result = new HashMap<>();boolean success = userService.loginUser(loginUser.username, loginUser.password);
        if (success) {
            String token = JwtUtil.generateToken(loginUser.username);
            result.put("success", true);
            result.put("message", "登录成功");
            result.put("token", token);
        } else {
            result.put("success", false);
            result.put("message", "用户名或密码错误");
        }

        return result;
    }

    @GetMapping("/info")
    public ResponseEntity<?> getUserInfo(@RequestHeader("Authorization") String token) {
        try {
            User userInfo = userService.getUserInfoByToken(token);
            //System.out.println(userInfo.getName());
            return ResponseEntity.ok(userInfo);
        } catch (Exception e) {
            //System.out.println("not success");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }

    @PostMapping("/update")
    public String update(@RequestHeader("Authorization") String token, @RequestBody User user) {
        try {
            User userInfo = userService.getUserInfoByToken(token);
            user.username = userInfo.getUsername();
            userService.update(user);
            return "信息修改成功";
        } catch (Exception e) {
            return "信息修改失败";
        }
    }
}