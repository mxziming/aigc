<template>
    <div>
      <el-upload
        ref="upload"
        multiple
        list-type="picture-card"
        :before-upload="handleBeforeUpload"
        :limit="limit"
        :file-list="fileList"
        @remove="handleRemove"
      >
        <i class="el-icon-plus"></i>
      </el-upload>
      <el-input
        v-model="description"
        placeholder="Description"
        class="description-input"
      ></el-input>
      <el-button type="primary" @click="submitForm" class="send-button">
        Send
      </el-button>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  import { getAccessToken } from "@/utils/auth";
  import { updateImage } from '@/api/source';
  export default {
    props: {
      limit: {
        type: Number,
        default: 5,
      },
    },
    data() {
      return {
        fileList: [],
        description: '',
      };
    },
    methods: {
        submitForm() {
    const formData = new FormData();
    this.fileList.forEach(file => {
      formData.append('files', file);
    });
    formData.append('description', this.description);

    axios.post('/api/source/uploadImage', formData, {
      headers: {
        'Authorization': 'Bearer ' + getAccessToken(),
        'Content-Type': 'multipart/form-data'
      }
    })
        .then(response => {
          this.$message.success("Images uploaded successfully");
          console.log('Upload successful', response);
          // Clear description after successful upload
          this.description = '';
        }).catch(error => {
          this.$message.error("Failed to upload images");
          console.error('Upload failed', error);
        });
      },
      handleBeforeUpload(file) {
        // Perform file type and size validation if needed
        this.fileList.push(file);
        return false; // Prevent automatic upload
      },
      handleRemove(file, fileList) {
        // Handle file removal from the fileList
        const index = fileList.indexOf(file);
        if (index !== -1) {
          fileList.splice(index, 1);
        }
      },

    }
  };
  </script>
  
  <style scoped>
  .description-input {
    margin-top: 10px;
  }
  .send-button {
    margin-top: 10px;
  }
  </style>
  