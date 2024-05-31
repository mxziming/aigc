<template>
    <div class="add-video-page">
      <h2>上传视频</h2>
      <VideoUploader />
      <el-form ref="videoForm" :model="videoForm" label-width="100px" style="margin-top: 20px;">
        <el-form-item label="描述">
          <el-input v-model="videoForm.description"></el-input>
        </el-form-item>
        <el-form-item>
        <el-button type="primary" @click="submitForm">确认上传</el-button>
        <el-button @click="goBack">返回</el-button>
      </el-form-item>
      </el-form>
    </div>
  </template>
  
  <script>
   import VideoUploader from '@/components/videoUploader/index.vue';
  export default {
    name: 'VideoDisplay',  // 视图的名称
    components: {
    VideoUploader 
    },
    data() {
      return {
        videoForm: {
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
        console.log('确认上传', this.videoForm);
        // 在这里执行上传逻辑，可以使用axios等方式发送到后端
      },
      goBack() {
        this.$router.go(-1);
      }
    }
  };
  </script>
  
  <style scoped>
  .add-video-page {
    padding: 20px;
  }
  .upload-demo {
    margin-bottom: 20px;
  }
  </style>
  