<template>
  <el-card class="user-card" shadow="hover">
    <div slot="header" class="clearfix">
      <span>编辑个人信息</span>
    </div>
    <el-form :model="userForm" label-position="left" label-width="80px">
      <el-form-item label="姓名">
        <el-input v-model="userForm.name"></el-input>
      </el-form-item>
      <el-form-item label="性别">
        <el-radio-group v-model="userForm.gender">
          <el-radio label="男">男</el-radio>
          <el-radio label="女">女</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="出生日期">
        <el-date-picker v-model="userForm.birthDate" type="date" placeholder="选择日期"></el-date-picker>
      </el-form-item>
      <el-form-item label="个人描述">
        <el-input v-model="userForm.description"></el-input>
      </el-form-item>
    </el-form>
    <div class="button-group">
      <el-button type="primary" @click="saveForm">Save</el-button>
      <el-button @click="cancelForm">Cancel</el-button>
    </div>
  </el-card>
</template>

<script>
import axios from 'axios';
import {getInfo} from '@/api/login';
import {save} from '@/api/editProfile';
export default {
  data() {
    return {
      userForm: {
        name: '',
        gender: '',
        birthDate: '',
        description:''
      }
    };
  },
  created() {
    this.fetchUserInfo();
  },
  methods: {
    fetchUserInfo() {
      getInfo()
      .then(response => {
        const data = response.data;
        this.userForm = {
          name: data.name,
          gender: data.gender,
          birthDate: data.birthDate,
          description:data.description
        };
      })
      .catch(error => {
        console.error('Error fetching user info:', error);
      });
    },
    saveForm() {
      // const token = localStorage.getItem('token');
      // axios.post('/api/user/update', this.userForm, {
      //   headers: {
      //     'Authorization': token
      //   }
      // })
      save(this.userForm)
      .then(response => {
        console.log('Form submitted successfully:', response.data);
        this.$router.push('/profile/info');
      })
      .catch(error => {
        console.error('Error submitting form:', error);
      });
    },
    cancelForm() {
      this.$router.go(-1);
    }
  }
};
</script>

<style scoped>
.user-card {
  max-width: 600px;
  margin: 0 auto;
}
.button-group {
  margin-top: 20px;
  text-align: right;
}
</style>
