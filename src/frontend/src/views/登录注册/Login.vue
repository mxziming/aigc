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
import {login} from '@/api/login.js'
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
      if (!this.loginForm.username || !this.loginForm.password) {
        this.$message.error('请输入用户名和密码');
        return;
      }
      login(this.loginForm.username, this.loginForm.password)
      .then(response => {
      if(response.success === true){
        localStorage.setItem('ACCESS_TOKEN', response.token);
        this.$message.success('登录成功');
        this.$router.push('/profile');}
      else
        this.$message.error('用户名或密码错误');
      })
      .catch(error => {
      this.$message.error('网络出现问题');
    });
    },
    registerForm(){
      this.$router.push('/register'); 
    }
  },
};
</script>
