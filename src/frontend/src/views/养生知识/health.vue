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
      <el-row class="mt-4" v-for="(knowledge, index) in healthKnowledges" :key="index">
        <el-col :span="4" class="index">{{ index + 1 }}</el-col>
        <el-col :span="20" class="content">{{ knowledge }}</el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      healthKnowledges: [
        "每天保持足够的睡眠时间，有助于身体恢复和健康维护。",
        "饮食要均衡，摄入足够的蛋白质、碳水化合物、脂肪、维生素和矿物质。",
        "多喝水，保持身体水分平衡，有利于排泄代谢废物。",
        "坚持适量的有氧运动，如散步、慢跑、游泳等，可以提高心肺功能和身体素质。",
        "避免吸烟和过量饮酒，这些不良习惯会对身体造成严重损害。",
        "保持良好的心理状态，积极应对生活中的压力和挑战，避免过度焦虑和抑郁。",
        "注意个人卫生，勤洗手、勤洗澡，保持身体清洁。",
        "避免长时间久坐，每隔一段时间起身活动一下，可以缓解身体疲劳和不适。",
        "定期体检，及时发现并治疗疾病，可以预防疾病的发生和发展。",
        "保持良好的社交关系，多与家人和朋友交流沟通，可以缓解压力和改善情绪状态。"
      ]
    };
  },
};
</script>

<style scoped>
.container {
  padding: 20px;
}

.index {
  text-align: center;
  line-height: 40px;
  font-size: 16px;
  font-weight: bold;
  border: 1px solid #e4e7ed;
  border-right: none;
  background-color: #f5f7fa;
}

.content {
  padding-left: 20px;
  line-height: 40px;
  border: 1px solid #e4e7ed;
  background-color: #fff;
}
</style>
