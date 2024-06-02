<template>
  <div class="resource-page">
    <div class="resource-type-selector">
      <el-button type="primary" >图片资源</el-button>
      <el-button @click="redirectToVideoPage">视频资源</el-button>
    </div>
    <div class="resource-list">
      <h2>图片资源</h2>
      <div class="image-list">
        <div v-for="(url, index) in imageResources" :key="index" class="image-item">
          <img :src="url" alt="Image" @click="viewImage(url)">
        </div>
      </div>
      <!-- <img src="https:\\\/img0.baidu.com/\\it\u=2392209312,2985640217&fm=253&fmt=auto&app=120&f=JPEG?w=500&h=667"> -->
      <!-- <img src="https://img2.baidu.com/it/u=2074916572,2932976242&fm=253&fmt=auto&app=120&f=JPEG?w=500&h=500"> -->
      <!-- <img src="http://localhost:8080/C:/Users/12952/Pictures/AIGC/picture/7d64b5ef-448b-4920-85c8-1461b55a142e_image (2).png"> -->
      <el-button class="fixed-add-button" type="primary" @click="addImage">添加图片</el-button>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import { getAccessToken } from "@/utils/auth";

export default {
  data() {
    return {
      imageResources: []
    };
  },
  created() {
    this.fetchImageResources(); // 页面加载时自动调用获取图片资源的方法
  },
  methods: {
    fetchImageResources() {
      axios.get('/api/source/images',{
        headers:{
          'Authorization': 'Bearer ' + getAccessToken(),
        }
      })
        .then(response => {
          console.log(response.data.data)
          const baseURL = 'https://139.224.204.66';
          for (let i = 0; i < response.data.data.length; i++) {
              response.data.data[i] = baseURL + response.data.data[i];
          }
          this.imageResources = response.data.data;
        })
        .catch(error => {
          console.error('Error fetching image resources:', error);
        });
    },
    getLocalFileURL(path) {
      // 如果浏览器支持 File API，则使用 createObjectURL 将本地文件路径转换为 URL
      if (window.URL && window.URL.createObjectURL) {
        return window.URL.createObjectURL(new File([path], ''));
      } else {
        // 否则，返回空字符串或者默认的图片地址
        return '';
      }
    },
    addImage() {
      this.$router.push('/source/addPicture');
    },
    viewImage(url) {
      console.log('查看图片:', url);
      // 在这里你可以实现点击图片后的预览或其他操作
    },
    redirectToVideoPage() {
      this.$router.push('/source/video');
    }
  }
};
</script>

<style scoped>
.resource-page {
  padding: 20px;
}
.resource-type-selector {
  margin-bottom: 20px;
}
.resource-list {
  border: 1px solid #ccc;
  padding: 20px;
  padding-top: 40px;
  padding-bottom: 60px;
  position: relative;
}
.image-list {
  display: flex;
  flex-wrap: wrap;
}
.image-item {
  margin-right: 10px;
  margin-bottom: 10px;
  cursor: pointer;
}
.fixed-add-button {
  position: absolute;
  top: 20px;
  right: 20px;
}
</style>
