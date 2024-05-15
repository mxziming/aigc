<template>
    <div class="container">
      <h1 class="mt-4">养生知识</h1>
      <el-alert
        v-if="healthKnowledges.length === 0"
        title="暂无养生知识推送"
        type="warning"
        class="mt-4"
      ></el-alert>
      <div v-else>
        <el-card
          v-for="(knowledge, index) in healthKnowledges"
          :key="index"
          class="mt-4"
        >
          <div slot="header" class="clearfix">
            <span>{{ knowledge.title }}</span>
          </div>
          <div>{{ knowledge.content }}</div>
          <div slot="footer">
            <span class="date">发布日期：{{ formatDate(knowledge.date) }}</span>
          </div>
        </el-card>
      </div>
    </div>
  </template>
  
  <script>
  export default {
    data() {
      return {
        healthKnowledges: [
          // 养生知识数据
          {
            title: "标题1",
            content: "养生知识内容1",
            date: "2024-05-15"
          },
          {
            title: "标题2",
            content: "养生知识内容2",
            date: "2024-05-14"
          },
          // 可以继续添加更多养生知识数据
        ]
      };
    },
    methods: {
      formatDate(date) {
        // 格式化日期函数，这里简单处理为YYYY-MM-DD格式
        return new Date(date).toLocaleDateString();
      }
    }
  };
  </script>
  
  <style scoped>
  /* 可以添加自定义样式 */
  </style>
  