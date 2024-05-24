<!-- <template>
    <el-container style="height: 100vh;">

      <el-scrollbar class="history-container">
        <el-button @click="createNewChat" class="newchat">New Chat</el-button>
          <el-menu :default-active="currentSessionId" @select="handleSelect" class="history-menu">
            <el-menu-item v-for="history in chatHistories" :key="history.sessionid" :index="history.sessionid">
              {{ history.sessionid }}
            </el-menu-item>
          </el-menu>
      </el-scrollbar>

      <el-container>
        <el-main class="chat-main">
          <div v-if="chats.length" class="chat-container">
            <div v-for="chat in chats" :key="chat.cid" class="chat-item">
              <p class="user-message"><strong>User:</strong> {{ chat.question }}</p>
              <p class="ai-message"><strong>AI:</strong> {{ chat.answer }}</p>
            </div>
          </div>
          <div v-else class="no-chat">
            <p>欢迎与蓝心大模型对话</p>
          </div>
        </el-main>
  
        <el-footer class="chat-footer">
          <el-input v-model="newQuestion" placeholder="Enter your message" @keyup.enter="sendChat" class="chat-input">
            <el-button slot="prepend" icon="el-icon-microphone" @click="startVoiceInput"></el-button>
            <el-button slot="append" @click="sendChat">发送</el-button>
          </el-input>
        </el-footer>
  
      </el-container>
    </el-container>
  </template>
  
  <script>
  import axios from 'axios';
  import { fetchhistory } from '@/api/chat';
  import { fetchchat } from '@/api/chat';
  export default {
    // data() {
    //   return {
    //     chatHistories: [],
    //     chats: [],
    //     currentSessionId: '',
    //     newQuestion: '',
    //   };
    // },
    data() {
  return {
    // 示例会话列表数据
    chatHistories: [
      { sessionid: 1 },
      { sessionid: 2 },
      { sessionid: 3 },
      { sessionid: 1 },
      { sessionid: 2 },
      { sessionid: 3 },
      { sessionid: 1 },
      { sessionid: 2 },
      { sessionid: 3 },
      { sessionid: 1 },
      { sessionid: 2 },
      { sessionid: 3 },
      { sessionid: 1 },
      { sessionid: 2 },
      { sessionid: 3 },
      { sessionid: 1 },
      { sessionid: 2 },
      { sessionid: 4 },
    ],
    // 示例聊天内容数据
    chats: [
      { cid: 1, question: "你好", answer: "你好，有什么可以帮助你的吗？" },
      { cid: 2, question: "天气怎么样？", answer: "今天天气晴朗，温度适宜。" },
      { cid: 3, question: "谢谢！", answer: "不客气，有其他问题可以随时问我。" }
    ],
    // 当前选定的会话id
    currentSessionId: 1,
    // 新消息输入框内容
    newQuestion: 'sadfs'
  };
},

    methods: {
      async fetchChatHistories() {
        fetchhistory()
            .then(response =>{
            this.chatHistories = response.data;
            })
            .catch(error => {
                console.error(error)
            })
      },
  
      async fetchChats(sessionid) {
        fetchchat(sessionid)
            .then(response =>{
                this.chats = response.data
            })
            .catch(error =>{
                console.error(error)
            })
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
      },
      async createNewChat() {
        try {
          const response = await axios.get('/chat/newchat');
          const sessionid = response.data.sessionid;
          this.currentSessionId = sessionid;
          this.fetchChats(sessionid);
          // Redirect to the new chat page
          this.$router.push(`/ai/chat/${sessionid}`);
        } catch (error) {
          console.error('Error creating new chat:', error);
        }
      }
    },
    mounted() {
      this.fetchChatHistories();
    }
  };
  </script>

<style>
.el-scrollerbar{
  scrollbar-width:thin;
}

.el-aside {
  background: #f0f2f5;
}

.chat-main {
  display: flex;
  flex-direction: column;
  height: 100%;

}
.el-aside{
    position: fixed;
}
.history-container {
    /* position: fixed; */
    /* width:collapse; */
  /* width: 200px; */
  flex: 1;
  /* overflow-y: auto; */
}

.history-menu {
  padding-bottom: 40px; /* 让下方给 New Chat 留出空间 */
}

.newchat {
  position: fixed;
  top: 0;
  z-index: 2; /* 确保在最顶部 */
  background-color: rgba(0, 157, 255, 0.491); /* 背景色与左侧栏一致 */
}

.chat-container {
  flex: 1;
  padding: 20px;
  overflow-y: auto;
}

.chat-item {
  margin-bottom: 15px;
}

.user-message {
  margin-left: 20px; /* 缩进用户消息，以区分 */
}

.ai-message {
  margin-left: 0; /* AI 消息不缩进 */
}

.no-chat {
  display: flex;
  justify-content: center;
  align-items: center;
  flex: 1;
  color: #aaa;
}

.chat-footer {
  position: fixed;
  bottom: 0;
  width: calc(90% - 300px);
  padding: 10px 20px;
  background: #fff;
  border-top: 1px solid #ebeef5;
  box-shadow: 0 -2px 12px rgba(0, 0, 0, 0.1);
  z-index: 1000;
}
</style> -->


<template>
  <el-container style="height: 100vh;">
    <el-container class="history-container">
    <el-scrollbar >
      <el-button @click="createNewChat" class="newchat">New Chat</el-button>
        <el-menu :default-active="currentSessionId" @select="handleSelect" class="history-menu">
          <el-menu-item v-for="history in chatHistories" :key="history.sessionid" :index="history.sessionid">
            {{ history.sessionid }}
          </el-menu-item>
        </el-menu>
    </el-scrollbar>
  </el-container>

    <el-container>
      <el-main class="chat-main">
        <div v-if="chats.length" class="chat-container">
          <div v-for="chat in chats" :key="chat.cid" class="chat-item">
            <p class="user-message"><strong>User:</strong> {{ chat.question }}</p>
            <p class="ai-message"><strong>AI:</strong> {{ chat.answer }}</p>
          </div>
        </div>
        <div v-else class="no-chat">
          <p>欢迎与蓝心大模型对话</p>
        </div>
      </el-main>

      <el-footer class="chat-footer">
        <el-input v-model="newQuestion" placeholder="Enter your message" @keyup.enter="sendChat" class="chat-input">
          <el-button slot="prepend" icon="el-icon-microphone" @click="startVoiceInput"></el-button>
          <el-button slot="append" @click="sendChat">发送</el-button>
        </el-input>
      </el-footer>
    </el-container>
  </el-container>
</template>

<script>
import axios from 'axios';
import { fetchhistory } from '@/api/chat';
import { fetchchat } from '@/api/chat';
export default {
  // data() {
  //   return {
  //     chatHistories: [],
  //     chats: [],
  //     currentSessionId: '',
  //     newQuestion: '',
  //   };
  // },
  data() {
return {
  // 示例会话列表数据
  chatHistories: [
    { sessionid: 1 },
    { sessionid: 2 },
    { sessionid: 3 },
    { sessionid: 1 },
    { sessionid: 2 },
    { sessionid: 3 },
    { sessionid: 1 },
    { sessionid: 2 },
    { sessionid: 3 },
    { sessionid: 1 },
    { sessionid: 2 },
    { sessionid: 3 },
    { sessionid: 1 },
    { sessionid: 2 },
    { sessionid: 3 },
    { sessionid: 1 },
    { sessionid: 2 },
    { sessionid: 4 },
  ],
  // 示例聊天内容数据
  chats: [
    { cid: 1, question: "你好", answer: "你好，有什么可以帮助你的吗？" },
    { cid: 2, question: "天气怎么样？", answer: "今天天气晴朗，温度适宜。" },
    { cid: 3, question: "谢谢！", answer: "不客气，有其他问题可以随时问我。" }
  ],
  // 当前选定的会话id
  currentSessionId: 1,
  // 新消息输入框内容
  newQuestion: 'sadfs'
};
},

  methods: {
    async fetchChatHistories() {
      fetchhistory()
          .then(response =>{
          this.chatHistories = response.data;
          })
          .catch(error => {
              console.error(error)
          })
    },

    async fetchChats(sessionid) {
      fetchchat(sessionid)
          .then(response =>{
              this.chats = response.data
          })
          .catch(error =>{
              console.error(error)
          })
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
    },
    async createNewChat() {
      try {
        const response = await axios.get('/chat/newchat');
        const sessionid = response.data.sessionid;
        this.currentSessionId = sessionid;
        this.fetchChats(sessionid);
        // Redirect to the new chat page
        this.$router.push(`/ai/chat/${sessionid}`);
      } catch (error) {
        console.error('Error creating new chat:', error);
      }
    }
  },
  mounted() {
    this.fetchChatHistories();
  }
};
</script>

<style>
.el-scrollerbar{
scrollbar-width:thin;
}

.el-aside {
background: #f0f2f5;
}

.chat-main {
display: flex;
flex-direction: column;
height: 100%;

}
.el-aside{
  position: fixed;
}
.history-container {
  flex: 0 0 250px; /* Set a fixed width for the sidebar */
  overflow-y: auto;
}


.history-menu {
padding-bottom: 40px; /* 让下方给 New Chat 留出空间 */
}

.newchat {
  position: absolute; /* Change to absolute positioning */
  top: 20px; /* Adjust top positioning */
  right: 20px; /* Adjust right positioning */
  background-color: #007bff; /* Change background color */
  color: #fff; /* Change text color */
  border: none; /* Remove border */
  border-radius: 5px; /* Add border radius for better appearance */
  padding: 10px 20px; /* Add padding for better spacing */
  cursor: pointer; /* Add cursor pointer */
}


.chat-container {
flex: 1;
padding: 20px;
overflow-y: auto;
}

.chat-item {
margin-bottom: 15px;
}

.user-message {
margin-left: 20px; /* 缩进用户消息，以区分 */
}

.ai-message {
margin-left: 0; /* AI 消息不缩进 */
}

.no-chat {
display: flex;
justify-content: center;
align-items: center;
flex: 1;
color: #aaa;
}

.chat-footer {
position: fixed;
bottom: 0;
width: calc(100% - 250px);
padding: 10px 20px;
background: #fff;
border-top: 1px solid #ebeef5;
box-shadow: 0 -2px 12px rgba(0, 0, 0, 0.1);
z-index: 1000;


}

</style>