<template>
  <el-container style="height: 100vh;">
    <el-aside width="250px" class="history-container">
      <el-scrollbar>
        <el-button @click="createNewChat" type="primary" class="newchat">开始新聊天</el-button>
        <el-menu :default-active="currentSessionId" @select="handleSelect" class="history-menu">
          <el-menu-item v-for="history in chatHistories" :key="history" :index="history">
            {{ history }}
          </el-menu-item>
        </el-menu>
      </el-scrollbar>
    </el-aside>

    <el-container>
      <el-main class="chat-main">
        <div v-if="chats.length" class="chat-container">
          <div v-for="chat in chats" :key="chat.cid" class="chat-item">
            <p class="user-message"><strong>User:</strong> {{ chat.question }}</p>
            <p class="ai-message" v-if="chat.answer"><strong>AI:</strong> {{ chat.answer }}</p>
          </div>
        </div>
        <div v-else class="no-chat">
          <p>欢迎与BlueLM对话</p>
        </div>
      </el-main>

      <el-footer class="chat-footer">
        <el-input v-model="newQuestion" placeholder="输入你的消息" @keyup.enter="sendChat" class="chat-input">
          <el-button slot="prepend" icon="el-icon-microphone" @click="startVoiceInput"></el-button>
          <el-button slot="append" type="primary" @click="sendChat">发送</el-button>
        </el-input>
      </el-footer>
    </el-container>
  </el-container>
</template>

<script>
import { fetchhistory, fetchchat, sendquestion, newchat } from "@/api/chat"; // Change the path accordingly

export default {
  data() {
    return {
      chatHistories: [],
      chats: [],
      currentSessionId: null,
      newQuestion: "",
    };
  },
  mounted() {
    this.handleInitialPageLoad();
  },
  methods: {
    async handleInitialPageLoad() {
      const sessionIdFromURL = this.$route.query.sessionid;
      if (sessionIdFromURL) {
        this.currentSessionId = sessionIdFromURL;
        await this.loadChatHistories();
        await this.loadChat(this.currentSessionId);
      } else {
        await this.loadChatHistories();
      }
    },
    async loadChatHistories() {
      try {
        const response = await fetchhistory();
        this.chatHistories = response.data;
      } catch (error) {
        console.error("Error fetching chat histories:", error);
      }
    },
    async loadChat(sessionid) {
      try {
        const response = await fetchchat(sessionid);
        this.chats = response.data;
      } catch (error) {
        console.error("Error fetching chat:", error);
      }
    },
    async createNewChat() {
      if (this.$route.query.sessionid) {
        this.$router.push('/ai/chat');
        this.chats = [];
        this.currentSessionId = null
        await this.loadChatHistories();
      }
    },
    async sendChat() {
      if (!this.newQuestion.trim()) return;
      try {
        if (this.currentSessionId) {
          await sendquestion(this.newQuestion, this.currentSessionId);
          this.newQuestion = '';
          await this.loadChat(this.currentSessionId);
        } else {
          const response = await newchat(this.newQuestion);
          this.currentSessionId = response.data.sessionid;
          this.$router.push({ path: '/ai/chat/', query: { sessionid: this.currentSessionId } });
          this.newQuestion = '';
          await this.handleInitialPageLoad();
        }
      } catch (error) {
        console.error("Error sending chat:", error);
      }
    },
    async startVoiceInput() {
      // Implement voice input functionality if needed
    },
    handleSelect(sessionid) {
      this.currentSessionId = sessionid;
      this.$router.push({ path: '/ai/chat', query: { sessionid } });
      this.loadChat(sessionid);
    }
  }
};
</script>

<style scoped>
.history-container {
  background-color: #f5f5f5;
  padding: 20px;
}

.newchat {
  width: 100%;
  margin-bottom: 20px;
}

.history-menu {
  margin-top: 20px;
}

.chat-main {
  padding: 20px;
  background-color: #fff;
  overflow-y: auto;
}

.chat-container {
  display: flex;
  flex-direction: column;
}

.chat-item {
  margin-bottom: 15px;
}

.user-message {
  background-color: #f0f8ff;
  padding: 10px;
  border-radius: 5px;
}

.ai-message {
  background-color: #f5f5f5;
  padding: 10px;
  border-radius: 5px;
  margin-top: 5px;
}

.no-chat {
  text-align: center;
  margin-top: 20px;
  color: #999;
}

.chat-footer {
  background-color: #f5f5f5;
  padding: 10px 20px;
}

.chat-input {
  width: 100%;
}
</style>

<!-- 
<template>
  <el-container style="height: 100vh;">
    <el-aside width="250px" class="history-container">
      <el-scrollbar>
        <el-button @click="createNewChat" type="primary" class="newchat">New Chat</el-button>
        <el-menu :default-active="currentSessionId" @select="handleSelect" class="history-menu">
          <el-menu-item v-for="history in chatHistories" :key="history" :index="history">
            {{ history }}
          </el-menu-item>
        </el-menu>
      </el-scrollbar>
    </el-aside>

    <el-container>
      <el-main class="chat-main">
        <div v-if="chats.length" class="chat-container">
          <div v-for="chat in chats" :key="chat.cid" class="chat-item">
            <p class="user-message"><strong>User:</strong> {{ chat.question }}</p>
            <p class="ai-message" v-if="chat.answer"><strong>AI:</strong> {{ chat.answer }}</p>
          </div>
        </div>
        <div v-else class="no-chat">
          <p>欢迎与蓝心大模型对话</p>
        </div>
      </el-main>

      <el-footer class="chat-footer">
        <el-input v-model="newQuestion" placeholder="Enter your message" @keyup.enter="sendChat" class="chat-input">
          <el-button slot="prepend" icon="el-icon-microphone" @click="startVoiceInput"></el-button>
          <el-button slot="append" type="primary" @click="sendChat">发送</el-button>
        </el-input>
      </el-footer>
    </el-container>
  </el-container>
</template>

<script>
import { fetchhistory, fetchchat, sendquestion, newchat } from "@/api/chat"; // Change the path accordingly

export default {
  data() {
    return {
      chatHistories: [],
      chats: [],
      currentSessionId: null,
      newQuestion: "",
    };
  },
  mounted() {
    this.handleInitialPageLoad();
  },
  methods: {
    async handleInitialPageLoad() {
      const sessionIdFromURL = this.$route.query.sessionid;
      if (sessionIdFromURL) {
        this.currentSessionId = sessionIdFromURL;
        await this.loadChatHistories();
        await this.loadChat(this.currentSessionId);
      } else {
        await this.loadChatHistories();
      }
    },
    async loadChatHistories() {
      try {
        const response = await fetchhistory();
        this.chatHistories = response.data;
      } catch (error) {
        console.error("Error fetching chat histories:", error);
      }
    },
    async loadChat(sessionid) {
      try {
        const response = await fetchchat(sessionid);
        this.chats = response.data;
      } catch (error) {
        console.error("Error fetching chat:", error);
      }
    },
    async createNewChat() {
      if (this.$route.query.sessionid) {
        this.$router.push('/ai/chat');
        this.chats = [];
        this.currentSessionId = null
        await this.loadChatHistories();
      }
    },
    async sendChat() {
      if (!this.newQuestion.trim()) return;
      try {
        if (this.currentSessionId) {
          await sendquestion(this.newQuestion, this.currentSessionId);
          this.newQuestion = '';
          await this.loadChat(this.currentSessionId);
        } else {
          const response = await newchat(this.newQuestion);
          this.currentSessionId = response.data.sessionid;
          this.$router.push({ path: '/ai/chat/', query: { sessionid: this.currentSessionId } });
          this.newQuestion = '';
          await this.handleInitialPageLoad();
        }
      } catch (error) {
        console.error("Error sending chat:", error);
      }
    },
    async startVoiceInput() {
      // Implement voice input functionality if needed
    },
    handleSelect(sessionid) {
      this.currentSessionId = sessionid;
      this.$router.push({ path: '/ai/chat', query: { sessionid } });
      this.loadChat(sessionid);
    }
  }
};
</script>

<style scoped>
.history-container {
  background-color: #f2f2f2;
  padding: 20px;
}

.newchat {
  width: 100%;
  margin-bottom: 20px;
}

.history-menu {
  margin-top: 20px;
}

.chat-main {
  padding: 20px;
  background-color: #fff;
  overflow-y: auto;
}

.chat-container {
  display: flex;
  flex-direction: column;
}

.chat-item {
  margin-bottom: 15px;
}

.user-message {
  background-color: #e6f7ff;
  padding: 10px;
  border-radius: 5px;
}

.ai-message {
  background-color: #f0f0f0;
  padding: 10px;
  border-radius: 5px;
  margin-top: 5px;
}

.no-chat {
  text-align: center;
  margin-top: 20px;
  color: #999;
}

.chat-footer {
  background-color: #f2f2f2;
  padding: 10px 20px;
}

.chat-input {
  width: 100%;
}
</style> -->
