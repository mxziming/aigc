<template>
    <div class="add-image-page">
      <h2>上传图片</h2>
      <el-upload
        class="upload-demo"
        action="/upload"
        :show-file-list="false"
        :on-success="handleSuccess"
        :on-error="handleError">
        <el-button size="small" type="primary">点击上传照片</el-button>
      </el-upload>
      <el-form ref="imageForm" :model="imageForm" label-width="100px" style="margin-top: 20px;">
        <el-form-item label="描述">
          <el-input v-model="imageForm.description"></el-input>
        </el-form-item>
        <el-form-item>
        <el-button type="primary" @click="submitForm">确认上传</el-button>
        <el-button @click="goBack">返回</el-button>
      </el-form-item>
      </el-form>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        imageForm: {
          description: ''
        }
      };
    },
    methods: {
      handleSuccess(response, file, fileList) {
        console.log('上传成功', response, file, fileList);
      },
      handleError(err, file, fileList) {
        console.error('上传失败', err, file, fileList);
      },
      submitForm() {
        console.log('确认上传', this.imageForm);
        // 在这里执行上传逻辑，可以使用axios等方式发送到后端
      },
      goBack() {
        this.$router.go(-1);
      }
    }
  };
  </script>
  
  <style scoped>
  .add-image-page {
    padding: 20px;
  }
  .upload-demo {
    margin-bottom: 20px;
  }
  </style>
  