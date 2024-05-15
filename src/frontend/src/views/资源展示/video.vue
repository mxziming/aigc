<template>
    <div class="resource-page">
      <div class="resource-type-selector">
        <el-button @click="redirectToImagePage">图片资源</el-button>
        <el-button type="primary" @click="redirectToVideoPage">视频资源</el-button>
      </div>
      <div class="resource-list">
        <h2>视频资源</h2>
        <div class="video-list">
          <div v-for="resource in videoResources" :key="resource.sid" class="video-item" @click="viewResource('video', resource.sid)">
            <!-- 在这里展示视频 -->
          </div>
        </div>
        <el-button type="primary" @click="addVideo">添加视频</el-button>
      </div>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  
  export default {
    data() {
      return {
        videoResources: []
      };
    },
    created() {
      //this.fetchVideoResources();   //暂时不调用获取方法
    },
    methods: {
      fetchVideoResources() {
        axios.get('/api/resources?type=video')
          .then(response => {
            this.videoResources = response.data;
          })
          .catch(error => {
            console.error('Error fetching video resources:', error);
          });
      },
      addVideo() {
        // 添加视频资源的逻辑
        this.$router.push('/source/addVideo');
      },
      viewResource(type, resourceId) {
        console.log('查看资源', type, resourceId);
        // 跳转到查看资源的页面，传递当前选中的资源类型和资源ID
        // this.$router.push({ path: '/viewResource', query: { type, id: resourceId }});
      },
      redirectToImagePage() {
        this.$router.push('/source/picture');
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
  .video-list {
    /* 在这里设置视频列表的样式 */
  }
  </style>
  