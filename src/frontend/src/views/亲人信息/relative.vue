<template>
  <div class="relative-page">
    <h2>亲人信息</h2>
    <div class="relative-cards">
      <el-card v-for="relative in relatives" :key="relative.rid" class="relative-card" shadow="hover">
        <div slot="header" class="clearfix">
          <span>{{ relative.relation }}</span>
          <el-button type="text" class="edit-button" icon="el-icon-edit" @click="editRelative(relative)">编辑</el-button>
        </div>
        <div class="relative-info">
          <p><strong>姓名：</strong>{{ relative.name }}</p>
          <p><strong>年龄：</strong>{{ relative.age }}</p>
          <p><strong>性别：</strong>{{ relative.gender }}</p>
          <p><strong>关系：</strong>{{ relative.relation }}</p>
          <p><strong>职业：</strong>{{ relative.job }}</p>
        </div>
      </el-card>
    </div>
    <div class="button-group">
      <el-button type="primary" @click="addRelative">添加亲人信息</el-button>
    </div>
  </div>
</template>
  
  <script>
import { getAccessToken } from '@/utils/auth';
import axios from 'axios';

export default {
  data() {
    return {
      relatives: []
    };
  },  
  mounted() {
    this.fetchRelativeInfo(); // 在组件挂载后调用 API 获取信息
  },
  methods: {
    fetchRelativeInfo() {
      const token = getAccessToken()
      axios.get('/api/relative/info', {
        headers: {
          'Authorization': token
        }
      })
        .then(response => {
          this.relatives = response.data;
        })
        .catch(error => {
          console.error('获取亲人信息失败:', error);
        });
    },
    addRelative() {
      // 跳转或显示添加亲人信息的表单
      this.$router.push('/relative/addRelative');
      console.log("添加亲人信息");
    },
    editRelative(relative) {
      // 跳转或显示编辑亲人信息的表单，传递当前选中的亲人信息对象
      this.$router.push({ path: '/relative/editRelative', query: { rid: relative.rid } });
      console.log("编辑亲人信息", relative);
    }
  }
};
  </script>
  
  <style scoped>
  .relative-page {
    padding: 20px;
  }
  
  .relative-cards {
    display: flex;
    flex-wrap: wrap;
    justify-content: space-between;
  }
  
  .relative-card {
    width: calc(50% - 10px); /* 每个卡片宽度为页面宽度的一半减去外边距 */
    margin-bottom: 20px;
  }
  
  .button-group {
  position: fixed; /* 按钮固定定位 */
  top: 50px; /* 距离底部 20px */
  right: 50px; /* 距离右侧 20px */
}
  </style>