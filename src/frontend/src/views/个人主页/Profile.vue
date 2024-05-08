   <!-- <template>
    <el-card class="user-card" shadow="hover">
      <div slot="header" class="clearfix">
        <span class="card-header">个人信息</span>
        <el-button type="text" class="edit-button" icon="el-icon-edit" @click="editInfo">编辑个人信息</el-button>
      </div>
      <div class="info-section">
        <el-descriptions :column="1" border>
          <el-descriptions-item label="姓名">{{ userInfo.name }}</el-descriptions-item>
          <el-descriptions-item label="年龄">{{ userInfo.age }}</el-descriptions-item>
          <el-descriptions-item label="性别">{{ userInfo.gender }}</el-descriptions-item>
          <el-descriptions-item label="个人描述">{{ userInfo.description }}</el-descriptions-item>
        </el-descriptions>
      </div>
      <div class="button-section">
      <el-button type="primary" @click="goToProfile">个人主页</el-button>
      <el-button type="primary" @click="goToRelative">亲人信息</el-button>
      <el-button type="primary" @click="goToSource">资源信息</el-button>
      <el-button type="primary" @click="goToAIChat">与AI聊天</el-button>
    </div>
    </el-card>
  </template>
  
  <script>
  export default {
    data() {
      return {
        userInfo: {
          name: '',
          age: null,
          gender: '',
          description: ''
        }
      };
    },
    methods: {
      editInfo() {
        // 跳转到编辑信息页面的逻辑
        this.$router.push('/editProfile');
      }
    }
  };
  </script>
  
  <style scoped>
  .user-card {
    max-width: 600px;
    margin: 0 auto;
  }
  .card-header {
    font-size: 18px;
  }
  .edit-button {
    float: right;
  }
  .info-section {
    margin-top: 20px;
  }
  </style>
   -->

   <template>
    <div>
      <el-card class="user-card" shadow="hover">
        <div slot="header" class="clearfix">
          <span class="card-header">个人信息</span>
          <el-button type="text" class="edit-button" icon="el-icon-edit" @click="editInfo">编辑个人信息</el-button>
        </div>
        <div class="info-section">
          <el-descriptions :column="1" border>
            <el-descriptions-item label="姓名">{{ userInfo.name }}</el-descriptions-item>
            <el-descriptions-item label="年龄">{{ userInfo.age }}</el-descriptions-item>
            <el-descriptions-item label="性别">{{ userInfo.gender }}</el-descriptions-item>
            <el-descriptions-item label="个人描述">{{ userInfo.description }}</el-descriptions-item>
          </el-descriptions>
        </div>
      </el-card>
      <div class="button-section">
        <el-button type="primary" @click="goToProfile">个人主页</el-button>
        <el-button type="primary" @click="goToRelative">亲人信息</el-button>
        <el-button type="primary" @click="goToSource">资源信息</el-button>
        <el-button type="primary" @click="goToAIChat">与AI聊天</el-button>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        userInfo: {
          name: '',
          age: null,
          gender: '',
          description: ''
        }
      };
    },
    methods: {
      editInfo() {
        // 跳转到编辑信息页面的逻辑
        this.$router.push('/editProfile');
      },
      goToProfile() {
        // 跳转到个人主页的逻辑
        this.$router.push('/profile');
      },
      goToRelative() {
        // 跳转到亲人信息的逻辑
        this.$router.push('/relative');
      },
      goToSource() {
        // 跳转到资源信息的逻辑
        this.$router.push('/source');
      },
      goToAIChat() {
        // 跳转到与AI聊天的逻辑
        this.$router.push('/aiChat');
      }
    }
  };
  </script>
  
  <style scoped>
  .user-card {
    max-width: 600px;
    margin: 0 auto;
  }
  .card-header {
    font-size: 18px;
  }
  .edit-button {
    float: right;
  }
  .info-section {
    margin-top: 20px;
  }
  .button-section {
    margin-top: 20px;
    text-align: center;
  }
  </style>
  