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
  </div>
</template>

<script>
import axios from 'axios';
import { getAccessToken } from '@/utils/auth';
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
  mounted() {
    this.fetchUserInfo();
  },
  methods: {
    fetchUserInfo() {
      const token = localStorage.getItem('token');
      // const token = getAccessToken();  //使用getAccessToken()无法获取token？？？？？？？？？？？？？？
      axios.get('/api/user/info', {
         headers: {
    'Authorization':  token// 请替换 'token' 为实际的认证令牌
  }
})
        .then(response => {
          const data = response.data;
          this.userInfo = {
            name: data.name,
            age: this.calculateAge(data.birthDate), // Assuming `birthDate` is the key for the birth date in the response
            gender: data.gender,
            description: data.description
          };
        })
        .catch(error => {
          console.error('Error fetching user info:', error);
        });
    },
    calculateAge(birthDate) {
      const birth = new Date(birthDate);
      const today = new Date();
      let age = today.getFullYear() - birth.getFullYear();
      const monthDiff = today.getMonth() - birth.getMonth();
      if (monthDiff < 0 || (monthDiff === 0 && today.getDate() < birth.getDate())) {
        age--;
      }
      return age;
    },
    editInfo() {
      // 跳转到编辑信息页面的逻辑
      this.$router.push('/profile/editProfile');
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
