<template>
    <div class="edit-relative">
      <el-card class="box-card">
        <div slot="header" class="clearfix">
          <span>编辑亲人信息</span>
        </div>
        <el-form :model="relativeForm" label-width="100px">
          <el-form-item label="姓名">
            <el-input v-model="relativeForm.name"></el-input>
          </el-form-item>
          <el-form-item label="年龄">
            <el-input v-model.number="relativeForm.age"></el-input>
          </el-form-item>
          <el-form-item label="性别">
            <el-radio-group v-model="relativeForm.gender">
              <el-radio label="男">男</el-radio>
              <el-radio label="女">女</el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="关系">
            <el-input v-model="relativeForm.relation"></el-input>
          </el-form-item>
          <el-form-item label="职业">
            <el-input v-model="relativeForm.job"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="saveRelative">保存</el-button>
            <el-button @click="cancel">取消</el-button>
          </el-form-item>
        </el-form>
      </el-card>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  
  export default {
    data() {
      return {
        relativeForm: {
          id: null,
          name: '',
          age: null,
          gender: '',
          relation: '',
          job: ''
        }
      };
    },
    created() {
      this.initializeRelative();
    },
    methods: {
      initializeRelative() {
        const relativeId = this.$route.query.relativeId;
        if (relativeId) {
          this.fetchRelative(relativeId);
        }
      },
      fetchRelative(relativeId) {
        // 假设接口地址为 '/api/relatives/{relativeId}'
        axios.get(`/api/relatives/${relativeId}`)
          .then(response => {
            this.relativeForm = response.data;  // 假设响应数据直接是亲人信息对象
          })
          .catch(error => {
            console.error('Error fetching relative:', error);
            // 处理错误
          });
      },
      saveRelative() {
        // 假设接口地址为 '/api/relatives/update'
        axios.post('/api/relatives/update', this.relativeForm)
          .then(() => {
            this.$message.success('亲人信息已更新');
            this.$router.push('/relatives'); // 保存成功后跳转到亲人信息列表页面
          })
          .catch(error => {
            console.error('Error saving relative:', error);
            this.$message.error('保存亲人信息失败');
          });
      },
      cancel() {
        this.$router.go(-1); // 返回上一页
      }
    }
  };
  </script>
  
  <style scoped>
  .edit-relative {
    display: flex;
    justify-content: center; 
  }
  .box-card {
    width: 400px;
  }
  </style>
  