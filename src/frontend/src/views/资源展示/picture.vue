<template>
    <div class="resource-page">
      <div class="resource-type-selector">
        <el-button type="primary" @click="showImageResources">图片资源</el-button>
        <el-button @click="showVideoResources">视频资源</el-button>
      </div>
      <div class="resource-list">
        <div v-if="currentType === 'image'">
          <h2>图片资源</h2>
          <div class="image-list">
            <div v-for="resource in imageResources" :key="resource.sid" class="image-item">
              <img :src="resource.url" alt="Image">
            </div>
          </div>
          <el-button type="primary" @click="addImage">添加图片</el-button>
        </div>
        <div v-else>
          <h2>视频资源</h2>
          <div class="video-list">
            <div v-for="resource in videoResources" :key="resource.sid" class="video-item">
              <!-- 在这里展示视频 -->
            </div>
          </div>
          <el-button type="primary" @click="addVideo">添加视频</el-button>
        </div>
      </div>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  
  export default {
    data() {
      return {
        currentType: 'image', // 默认展示图片资源
        imageResources: [],
        videoResources: []
      };
    },
    created() {
      this.fetchImageResources();
    },
    methods: {
      showImageResources() {
        this.currentType = 'image';
        this.fetchImageResources();
      },
      showVideoResources() {
        this.currentType = 'video';
        // 如果需要，在这里调用获取视频资源的方法
      },
      fetchImageResources() {
        axios.get('/api/resources?type=image')
          .then(response => {
            this.imageResources = response.data;
          })
          .catch(error => {
            console.error('Error fetching image resources:', error);
          });
      },
      addImage() {
        // 添加图片资源的逻辑
        console.log('添加图片');
      },
      addVideo() {
        // 添加视频资源的逻辑
        console.log('添加视频');
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
  }
  .image-list {
    display: flex;
    flex-wrap: wrap;
  }
  .image-item {
    margin-right: 10px;
    margin-bottom: 10px;
  }
  .video-list {
    /* 在这里设置视频列表的样式 */
  }
  .video-item {
    /* 在这里设置视频项的样式 */
  }
  </style>
  