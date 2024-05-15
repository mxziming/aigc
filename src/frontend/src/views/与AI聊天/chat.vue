<template>
    <el-container>
      <el-header>与蓝心大模型聊天</el-header>
      <el-main>
        <div class="chat-history">
          <div v-for="msg in messages" :key="msg.id" class="message-box">
            <div v-if="msg.user === 'user'" class="user-message">
              <div class="message-content">{{ msg.content }}</div>
              <div class="message-time">{{ msg.time }}</div>
            </div>
            <div v-else class="bot-message">
              <div class="message-content">{{ msg.content }}</div>
              <div class="message- time">{{ msg.time }}</div>
            </div>
          </div>
        </div>
        <div class="input-box">
          <el-input
              type="textarea"
              v-model="message"
              placeholder="请输入你的问题或内容...">
          </el-input>
          <el-button type="primary" @click="sendMessage">发送</el-button>
        </div>
      </el-main>
    </el-container>
</template> 
  
  <script>
  import axios from 'axios';
  import { v4 as uuidv4 } from 'uuid';
  
  export default {
    data() {
      return {
        message: '',
        messages: [],
        sessionId: uuidv4() // 在页面加载时生成一个唯一的sessionId
      };
    },
    computed: {
      time() {
        const now = new Date();
        const year = now.getFullYear();
        const month = ('0' + (now.getMonth() + 1)).slice(-2);
        const day = ('0' + now.getDate()).slice(-2);
        const hour = ('0' + now.getHours()).slice(-2);
        const minute = ('0' + now.getMinutes()).slice(-2);
        const second = ('0' + now.getSeconds()).slice(-2);
        return `${year}-${month}-${day} ${hour}:${minute}:${second}`;
      }
    },
    methods: {
      sendMessage() {
        if (!this.message.trim()) {
          return; // 阻止发送空消息
        }
        const requestBody = {
          content: this.message,
          sessionId: this.sessionId // 包含sessionId
        };
        axios.post('/api/chat/send', requestBody)
            .then(response => {
              const responseData = response.data;
              if (responseData && responseData.data && responseData.code === 0) {
                this.messages.push({
                  id: this.messages.length + 1,
                  content: responseData.data.content,
                  time: this.time,
                  user: 'bot' // 响应内容由后台生成，用户为'bot'
                });
                this.message = ''; // 发送后清空输入框
              } else {
                console.error('接收到错误或格式无效:', responseData);
              }
            })
            .catch(error => {
              console.error('发送消息错误:', error);
              this.messages.push({
                id: this.messages.length + 1,
                content: `错误：${error.message || "与服务器通信失败"}`,
                time: this.time,
                user: 'user' // 错误信息由用户输入，用户为'user'
              });
            });
      }
    }
  }
  </script>
  <style>
  .input-box {
    margin-bottom: 10px;
  }
  
  .chat-history {
    padding: 10px;
    border-radius: 5px;
    background-color: #f2f2f2;
  }
  
  .message-box {
    margin-bottom: 10px;
    padding: 10px;
    border-radius: 5px;
    background-color: #fff;
  }
  
  .user-message {
    display: flex;
    align-items: flex-end;
    justify-content: flex-end;
    background-color: #409eff;
    color: #fff;
  }
  
  .bot-message {
    display: flex;
    align-items: flex-start;
    justify-content: flex-start;
    background-color: #f2f2f2;
    color: #333;
  }
  
  .message-content {
    font-size: 16px;
    line-height: 24px;
  }
  
  .message-time {
    font-size: 12px;
    color: #999;
  }
  </style>