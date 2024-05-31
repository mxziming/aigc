<template>
  <div class="resource-page">
    <div class="resource-type-selector">
      <el-button  @click="redirectToImagePage">图片资源</el-button>
      <el-button type="primary" @click="redirectToVideoPage">视频资源</el-button>
    </div>
    <div class="resource-list">
      <h2>视频资源</h2>
      <div class="video-list">
        <div v-for="resource in videoResources" :key="resource.sid" class="video-item" @click="viewResource('video', resource.sid)">
          <video :src="resource.url" controls alt="Video"></video>
        </div>
      </div>
      <el-button class="fixed-add-button" type="primary" @click="addVideo">添加视频</el-button>
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
    // this.fetchVideoResources();    //暂时不调用获取方法
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
  padding-top: 40px; /* 增加上内边距 */
  padding-bottom: 60px; /* 增加下内边距 */
  position: relative; /* 为了定位固定按钮 */
}
.video-list {
  display: flex;
  flex-wrap: wrap;
  max-height: 500px; /* 固定高度 */
  overflow-y: auto; /* 超出部分滚动 */
}
.video-item {
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
