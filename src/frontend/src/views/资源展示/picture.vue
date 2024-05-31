<template>
  <div class="resource-page">
    <div class="resource-type-selector">
      <el-button type="primary" @click="redirectToImagePage">图片资源</el-button>
      <el-button @click="redirectToVideoPage">视频资源</el-button>
    </div>
    <div class="resource-list">
      <h2>图片资源</h2>
      <div class="image-list">
        <div v-for="resource in imageResources" :key="resource.sid" class="image-item" @click="viewResource('image', resource.sid)">
          <img :src="resource.url" alt="Image">
        </div>
      </div>
      <el-button class="fixed-add-button" type="primary" @click="addImage">添加图片</el-button>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      imageResources: []
    };
  },
  created() {
    // this.fetchImageResources();    //暂时不调用获取方法
  },
  methods: {
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
      this.$router.push('/source/addPicture');
    },
    viewResource(type, resourceId) {
      console.log('查看资源', type, resourceId);
      // 跳转到查看资源的页面，传递当前选中的资源类型和资源ID
      // this.$router.push({ path: '/viewResource', query: { type, id: resourceId }});
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
  /* border: 1px solid #ccc;
  padding: 20px;
  position: relative; //为了定位固定按钮 */
  border: 1px solid #ccc;
  padding: 20px;
  padding-top: 40px; /* 增加上内边距 */
  padding-bottom: 60px; /* 增加下内边距 */
  position: relative; /* 为了定位固定按钮 */
}
.image-list {
  display: flex;
  flex-wrap: wrap;
  max-height: 10000px; /* 固定高度 */
  overflow-y: auto; /* 超出部分滚动 */
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
