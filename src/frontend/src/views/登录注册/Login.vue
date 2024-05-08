<template>
  <div>
  <h2 style="color: #333; font-size: 24px; text-align: center; margin-bottom: 20px;">老年人的智能陪伴交互系统</h2>
  <el-form ref="loginForm" :model="loginForm" label-width="100px">
    <el-form-item label="用户名">
      <el-input v-model="loginForm.username"></el-input>
    </el-form-item>
    <el-form-item label="密码">
      <el-input type="password" v-model="loginForm.password"></el-input>
    </el-form-item>
    <el-form-item>
      <el-button type="primary" @click="submitForm">登录</el-button>
      <el-button type="primary" @click="registerForm">注册</el-button>
    </el-form-item>
  </el-form>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      loginForm: {
        username: '',
        password: '',
      },
    };
  },
  methods: {
    submitForm() {
      axios.post('/api/user/login', this.loginForm)
        .then(response => {
          if(response.data.success) {
            this.$message.success('登录成功');
            // 这里可以进行路由跳转或其他操作
            // 保存token
            localStorage.setItem('token', response.data.token);

            // this.$router.push('/todolist'); // 使用 Vue Router 进行跳转
          } else {
            this.$message.error(response.data.message);
          }
        })
        .catch(error => {
          this.$message.error('登录失败');
        });
    },
    registerForm(){
      this.$router.push('/register'); 
    }
  },
};
</script>
