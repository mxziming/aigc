<template>
    <div class="relative-page">
      <h2>亲人信息</h2>
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
      <div class="button-group">
        <el-button type="primary" @click="addRelative">添加亲人信息</el-button>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        relatives: [
          // 初始亲人信息为空数组，实际数据应通过接口或其他方式获取
        ]
      };
    },
    methods: {
      addRelative() {
        // 跳转或显示添加亲人信息的表单
        this.$router.push('/addRelative');
        console.log("添加亲人信息");
      },
      editRelative(relative) {
        // 跳转或显示编辑亲人信息的表单，传递当前选中的亲人信息对象
        this.$router.push('/editRelative');
        console.log("编辑亲人信息", relative);
      }
    }
  };
  </script>
  
  <style scoped>
  .relative-page {
    padding: 20px;
  }
  .relative-card {
    margin-bottom: 20px;
  }
  .edit-button {
    float: right;
  }
  .relative-info {
    padding: 10px;
  }
  .button-group {
    margin-top: 20px;
    text-align: center;
  }
  </style>
  