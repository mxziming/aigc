<template>
  <div class="add-image-page">
    <!-- <h2>上传图片</h2>
    <ImageUploader/> -->

    <!-- <el-upload
  class="upload-demo"
  action="http://localhost/source/uploadImage"
  drag
  multiple>
  <i class="el-icon-upload"></i>
  <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
  <div class="el-upload__tip" slot="tip">只能上传jpg/png文件，且不超过500kb</div>
</el-upload> -->

<el-upload
  class="upload-demo"
   action="/api/source/uploadImage"
  :on-preview="handlePreview"
  :on-remove="handleRemove"
  :file-list="fileList"
  list-type="picture"
  name="file"
  :headers="headers">
  <el-button size="small" type="primary">点击上传</el-button>
  <div slot="tip" class="el-upload__tip">只能上传jpg/png文件，
  且不超过500kb</div>
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
import { getAccessToken } from '@/utils/auth';
import ImageUploader from '@/components/imageUploader/index.vue';
import axios from 'axios';
const accesstoken = 'Bearer ' + getAccessToken()
export default {
  name: 'ImageDisplay',
  components: {
    ImageUploader
  },
  data() {
    return {
      imageForm: {
        description: '',
        files: []
      },
      headers: {
        Authorization: localStorage.getItem("token")
      },
    };
  },
  methods: {
    submitForm() {
    // 构造 FormData 对象
    const formData = new FormData();
    this.imageForm.files.forEach(file => {
      formData.append('files', file); // 假设 imageForm.files 是一个包含文件的数组
    });
    formData.append('description', this.imageForm.description); // 假设你有一个变量来存储描述信息

    // 发送 POST 请求
      axios.post('/api/source/uploadImage', this.files, this.description,{
        headers: {
          'Authorization': 'Bearer ' + localStorage.getAccessToken(),
          // 'Content-Type': 'multipart/form-data'
        }
      })
      .then(response => {
        this.$message.success("图片上传成功")
        console.log('上传成功', response);
      }).catch(error => {
        this.$message.error("图片上传失败")
        console.error('上传失败', error);
      });
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