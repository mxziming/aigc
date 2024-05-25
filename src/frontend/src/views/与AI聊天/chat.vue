<template>
  <el-container style="height: 100vh;">
    <el-container class="history-container">
      <el-scrollbar>
        <el-button @click="createNewChat" class="newchat">New Chat</el-button>
        <el-menu :default-active="currentSessionId" @select="handleSelect" class="history-menu">
          <el-menu-item v-for="history in chatHistories" :key="history" :index="history">
            {{ history }}
          </el-menu-item>
        </el-menu>
      </el-scrollbar>
    </el-container>

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
          <el-button slot="append" @click="sendChat">发送</el-button>
        </el-input>
      </el-footer>
    </el-container>
  </el-container>
</template>

<script>
import { fetchhistory, fetchchat, sendquestion, newchat } from "@/api/chat"; // Change the path accordingly
import { create } from "core-js/core/object";

export default {
  data() {
    return {
      chatHistories: [],
      chats: [],
      currentSessionId: null,
      newQuestion: "",
      exampleData: "This is an example data."
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
      //有sid，push到新页面；没有sid，不变
      if (this.$route.query.sessionid){
        console.log(this.$route.query.sessionid)
        this.$router.push('/ai/chat');
        this.chats = [];
        fetchhistory();
      }
    },
    async sendChat() {
      if (!this.newQuestion.trim()) return;
      try {
        let response;
        if (this.$route.query.sessionid) {
          response = await sendquestion(this.newQuestion, this.currentSessionId);
          fetchchat(this.currentSessionId)
        } else {
          newchat(this.newQuestion)
            .then(response => {
              console.log(response)
              console.log(response.data)
              this.currentSessionId = response.data.sessionid
              console.log(this.currentSessionId)
              this.$router.push({path: '/ai/chat/',query: {sessionid: this.currentSessionId}})
              this.newQuestion = "";
              handleInitialPageLoad()
            })
            .catch(error => {
              console.error(error)
            })
        }
        // this.chats.push({
        //   cid: response.data.cid,
        //   question: this.newQuestion,
        //   answer: response.data.answer
        // });

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
/* Add your custom styles here */
.history-container {
  flex: 0 0 25%;
  overflow-y: auto;
}
.chat-main {
  flex: 1;
  overflow-y: auto;
}
.chat-footer {
  flex: 0 0 auto;
}
.chat-input {
  width: 100%;
}
.no-chat {
  text-align: center;
  margin-top: 20px;
}
</style>
