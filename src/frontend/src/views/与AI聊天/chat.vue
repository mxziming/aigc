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
        axios.post('/api/chat/send', requestBody,{
          'Authorization' : 'Bear '+localStorage.getItem("ACCESS_TOKEN")
        })
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

  <!-- <template>
    <el-container>
      <el-aside width="300px">
        <el-menu :default-active="currentSessionId" @select="handleSelect">
          <el-menu-item v-for="history in chatHistories" :key="history.sessionid" :index="history.sessionid">
            {{ history.sessionid }}
          </el-menu-item>
        </el-menu>
      </el-aside>
      
      <el-container>
        <el-main>
          <div v-if="chats.length" class="chat-container">
            <div v-for="chat in chats" :key="chat.cid" class="chat-item">
              <p><strong>Question:</strong> {{ chat.question }}</p>
              <p><strong>Answer:</strong> {{ chat.answer }}</p>
            </div>
          </div>
          <div v-else class="no-chat">
            <p>Select a session to view the chat history</p>
          </div>
        </el-main>
        
        <el-footer>
          <el-input v-model="newQuestion" placeholder="Enter your message">
            <el-button slot="append" @click="sendChat">Send</el-button>
          </el-input>
        </el-footer>
      </el-container>
    </el-container>
  </template>
  
  <script>
  import axios from 'axios';
  
  export default {
    data() {
      return {
        chatHistories: [],
        chats: [],
        currentSessionId: '',
        newQuestion: '',
      };
    },
    methods: {
      async fetchChatHistories() {
        try {
          const response = await axios.get('/chat/chathistory', {
            params: { uid: 1 }  // Assuming uid is 1 for demonstration
          });
          this.chatHistories = response.data;
        } catch (error) {
          console.error('Error fetching chat histories:', error);
        }
      },
      async fetchChats(sessionid) {
        try {
          const response = await axios.get(`/chat/info/${sessionid}`);
          this.chats = response.data;
        } catch (error) {
          console.error('Error fetching chats:', error);
        }
      },
      handleSelect(sessionid) {
        this.currentSessionId = sessionid;
        this.fetchChats(sessionid);
      },
      async sendChat() {
        if (this.newQuestion.trim() === '') return;
        
        const newChat = {
          uid: 1,  // Assuming uid is 1 for demonstration
          sessionid: this.currentSessionId,
          question: this.newQuestion,
          answer: ''  // Answer will be populated by the server
        };
  
        try {
          const response = await axios.post('/chat/send', newChat);
          newChat.answer = response.data;
          this.chats.push(newChat);
          this.newQuestion = '';
        } catch (error) {
          console.error('Error sending chat:', error);
        }
      }
    },
    mounted() {
      this.fetchChatHistories();
    }
  };
  </script>
  
  <style>
  .el-aside {
    background: #f0f2f5;
  }
  
  .chat-container {
    max-height: 600px;
    overflow-y: auto;
    padding: 20px;
    background: #fff;
    border-radius: 5px;
    box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1);
  }
  
  .chat-item {
    margin-bottom: 15px;
  }
  
  .no-chat {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100%;
    color: #aaa;
  }
  
  .el-footer {
    padding: 20px;
    background: #fff;
    border-top: 1px solid #ebeef5;
  }
  </style>
   -->