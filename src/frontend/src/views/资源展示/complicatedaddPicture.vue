<template>
    <div class="component-upload-image">
      <el-upload
          multiple
          :action="uploadFileUrl"
          list-type="picture-card"
          :on-success="handleUploadSuccess"
          :before-upload="handleBeforeUpload"
          :limit="limit"
          :on-error="handleUploadError"
          :on-exceed="handleExceed"
          name="file"
          :on-remove="handleRemove"
          :show-file-list="true"
          :headers="headers"
          :file-list="fileList"
          :on-preview="handlePictureCardPreview"
          :class="{hide: this.fileList.length >= this.limit}"
      >
        <i class="el-icon-plus"></i>
      </el-upload>
  
      <!-- 上传提示 -->
      <div class="el-upload__tip" slot="tip" v-if="showTip">
        请上传
        <template v-if="fileSize"> 大小不超过 <b style="color: #f56c6c">{{ fileSize }}MB</b> </template>
        <template v-if="fileType"> 格式为 <b style="color: #f56c6c">{{ fileType.join("/") }}</b> </template>
        的文件
      </div>
  
      <el-dialog
          :visible.sync="dialogVisible"
          title="预览"
          width="800"
          append-to-body
      >
        <img
            :src="dialogImageUrl"
            style="display: block; max-width: 100%; margin: 0 auto"
        />
      </el-dialog>
      <el-form ref="imageForm" :model="imageForm" label-width="100px" style="margin-top: 20px;">
        <el-form-item label="描述">
          <el-input v-model="imageForm.description"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm">确认上传</el-button>
          <el-button @click="goBack">返回</el-button>
        </el-form-item>
      </el-form>
    </div>
  </template>

<script>
import axios from 'axios'; // Ensure Axios is imported
import { getAccessToken } from "@/utils/auth";

export default {
  props: {
    value: [String, Object, Array],
    limit: {
      type: Number,
      default: 5,
    },
    fileSize: {
      type: Number,
      default: 5,
    },
    fileType: {
      type: Array,
      default: () => ["png", "jpg", "jpeg"],
    },
    isShowTip: {
      type: Boolean,
      default: true
    }
  },
  data() {
    return {
      number: 0,
      uploadList: [],
      dialogImageUrl: "",
      dialogVisible: false,
      hideUpload: false,
      uploadFileUrl: "/api/source/uploadImage",
      headers:  {
        Authorization: 'Bearer ' + getAccessToken()
      },
      fileList: [],
      imageForm: {
        description: ''
      }
    };
  },
  watch: {
    fileList: {
      handler(newVal) {
        this.$emit('update', newVal);
      },
      deep: true
    },
    value: {
      handler(val) {
        if (val) {
          const list = Array.isArray(val) ? val : Array.isArray(this.value.split(',')) ? this.value.split(',') : Array.of(this.value);
          this.fileList = list.map(item => {
            if (typeof item === "string") {
              item = { name: item, url: item };
            }
            return item;
          });
        } else {
          this.fileList = [];
        }
      },
      deep: true,
      immediate: true
    }
  },
  computed: {
    showTip() {
      return this.isShowTip && (this.fileType || this.fileSize);
    },
  },
  methods: {
    handleRemove(file, fileList) {
      const findex = this.fileList.map(f => f.name).indexOf(file.name);
      if(findex > -1) {
        this.fileList.splice(findex, 1);
        this.$emit("input", this.listToString(this.fileList));
      }
    },
    handleUploadSuccess(res) {
      this.uploadList.push({ name: res.data, url: res.data });
      if (this.uploadList.length === this.number) {
        this.fileList = this.fileList.concat(this.uploadList);
        this.uploadList = [];
        this.number = 0;
        this.$emit("input", this.listToString(this.fileList));
        this.$modal.closeLoading();
      }
    },
    handleBeforeUpload(file) {
      let isImg = false;
      if (this.fileType.length) {
        let fileExtension = "";
        if (file.name.lastIndexOf(".") > -1) {
          fileExtension = file.name.slice(file.name.lastIndexOf(".") + 1);
        }
        isImg = this.fileType.some(type => {
          if (file.type.indexOf(type) > -1) return true;
          return !!(fileExtension && fileExtension.indexOf(type) > -1);
        });
      } else {
        isImg = file.type.indexOf("image") > -1;
      }

      if (!isImg) {
        this.$modal.msgError(`文件格式不正确, 请上传${this.fileType.join("/")}图片格式文件!`);
        return false;
      }
      if (this.fileSize) {
        const isLt = file.size / 1024 / 1024 < this.fileSize;
        if (!isLt) {
          this.$modal.msgError(`上传头像图片大小不能超过 ${this.fileSize} MB!`);
          return false;
        }
      }
      this.$modal.loading("正在上传图片，请稍候...");
      this.number++;
    },
    handleExceed() {
      this.$modal.msgError(`上传文件数量不能超过 ${this.limit} 个!`);
    },
    handleUploadError() {
      this.$modal.msgError("上传图片失败，请重试");
      this.$modal.closeLoading();
    },
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
    },
    listToString(list, separator) {
      let strs = "";
      separator = separator || ",";
      for (let i in list) {
        strs += list[i].url.replace(this.baseUrl, "") + separator;
      }
      return strs !== '' ? strs.substr(0, strs.length - 1) : '';
    },
    submitForm() {
    const formData = new FormData();
    formData.append('description', this.imageForm.description);

    // Append each file object to the formData
    this.fileList.forEach(file => {
      // Assuming each file object has a raw file associated with it
      formData.append('files', file.raw);
    });

    axios.post(this.uploadFileUrl, this.imageForm.description, this.fileList, {headers:{
        'Authorization': 'Bearer ' + getAccessToken()
    }})

//     axios.post(this.uploadFileUrl, formData, {
//   headers: {
//     'Authorization': 'Bearer ' + getAccessToken(),
//     'Content-Type': 'multipart/form-data'
//   }
// })
    .then(response => {
      this.$message.success("图片上传成功");
      console.log('上传成功', response);
    }).catch(error => {
      this.$message.error("图片上传失败");
      console.error('上传失败', error);
    });
  },
    goBack() {
      this.$router.go(-1);
    }
  }
};
</script>

<style scoped lang="scss">
:deep(.hide .el-upload--picture-card) {
  display: none;
}
:deep(.el-list-enter-active, .el-list-leave-active) {
  transition: all 0s;
}

:deep(.el-list-enter, .el-list-leave-active) {
  opacity: 0;
  transform: translateY(0);
}
</style>
