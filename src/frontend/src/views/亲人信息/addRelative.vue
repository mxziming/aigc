<template>
    <div class="add-relative-page">
      <h2>添加亲人信息</h2>
      <el-form :model="relativeForm" ref="relativeForm" label-width="100px">
        <el-form-item label="姓名" prop="name">
          <el-input v-model="relativeForm.name"></el-input>
        </el-form-item>
        <el-form-item label="年龄" prop="age">
          <el-input v-model.number="relativeForm.age"></el-input>
        </el-form-item>
        <el-form-item label="性别" prop="gender">
          <el-radio-group v-model="relativeForm.gender">
            <el-radio label="男">男</el-radio>
            <el-radio label="女">女</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="关系" prop="relation">
          <el-input v-model="relativeForm.relation"></el-input>
        </el-form-item>
        <el-form-item label="职业" prop="job">
          <el-input v-model="relativeForm.job"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm">提交</el-button>
          <el-button @click="resetForm">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        relativeForm: {
          name: '',
          age: null,
          gender: '',
          relation: '',
          job: ''
        }
      };
    },
    methods: {
      submitForm() {
        this.$refs.relativeForm.validate(valid => {
          if (valid) {
            // 提交表单逻辑
            console.log('Form submitted!');
          } else {
            console.log('Form validation failed');
            return false;
          }
        });
      },
      resetForm() {
        this.$refs.relativeForm.resetFields();
      }
    }
  };
  </script>
  
  <style scoped>
  .add-relative-page {
    padding: 20px;
  }
  </style>
  