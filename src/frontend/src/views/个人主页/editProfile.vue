<template>
    <el-card class="user-card" shadow="hover">
      <div slot="header" class="clearfix">
        <span>编辑个人信息</span>
      </div>
      <el-form :model="formData" label-position="top" label-width="80px">
        <el-form-item label="Name">
          <el-input v-model="formData.name"></el-input>
        </el-form-item>
        <el-form-item label="Age">
          <el-input v-model.number="formData.age"></el-input>
        </el-form-item>
        <el-form-item label="Gender">
          <el-input v-model.number="formData.gender"></el-input>
        </el-form-item>
        <el-form-item label="Description">
          <el-input type="textarea" v-model="formData.description"></el-input>
        </el-form-item>
      </el-form>
      <div class="button-group">
        <el-button type="primary" @click="saveForm">Save</el-button>
        <el-button @click="cancelForm">Cancel</el-button>
        <el-button type="danger" @click="deleteItem">Delete</el-button>
        <el-button type="success" @click="submitForm">Submit</el-button>
      </div>
    </el-card>
  </template>
  
  <script>
  export default {
    data() {
      return {
        formData: {
          name: '',
          age: null,
          gender: null,
          description: ''
        }
      };
    },
    methods: {
      saveForm() {
        // 保存表单逻辑
      },
      cancelForm() {
        this.$router.go(-1);
      },
      deleteItem() {
        // 删除逻辑
      },
      submitForm() {
        // 提交表单逻辑
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
  