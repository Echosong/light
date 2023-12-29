<template>
    <Dialog v-model="isShow" :title="title" maxHeight="80%">
        <el-form v-if="m" ref="ruleForm" :rules="rules" :model="m" class="demo-ruleForm"
                 label-width="120px">
            <el-form-item label="图标">
 <FileImage @onremove="m.ico = ''" @onSuccess="m.ico = $event" :file="m.ico" /></el-form-item>
<el-form-item label="文件名">
 <File @onremove="m.fileName = ''" @onSuccess="m.fileName = $event" :file="m.fileName" /></el-form-item>
<el-form-item label="文件扩展名："  prop="extend" >
            <el-input v-model="m.extend"></el-input>
          </el-form-item>
<el-form-item label="文件存储路径："  prop="filePath" >
            <el-input v-model="m.filePath"></el-input>
          </el-form-item>
<el-form-item label="文件类型">
                    <input-enum
                        enumName="fileTypeEnum"
                        v-model="m.fileType"
                    ></input-enum>
                </el-form-item>
<el-form-item label="文件大小："  prop="fileSize" >
            <el-input v-model="m.fileSize"></el-input>
          </el-form-item>
<el-form-item label="相对路径："  prop="urlPath" >
            <el-input v-model="m.urlPath"></el-input>
          </el-form-item>
<el-form-item label="文件唯一标识："  prop="uuid" >
            <el-input v-model="m.uuid"></el-input>
          </el-form-item>
        </el-form>
        <template #footer>
            <span class="dialog-footer">
                <el-button type="primary" @click="ok($parent)">
                    确定
                </el-button>
                <el-button @click="isShow = false">取消</el-button>
            </span>
        </template>
    </Dialog>
</template>

<script setup>
import Dialog from "@/components/dialog/index.vue";
import {inject, ref} from "vue";
import FileImage from "@/components/file/fileImage.vue";
import File from "@/components/file/file.vue";
import InputEnum from "@/components/enum/InputEnum.vue";

const props = defineProps(["params"]);
const m = ref({});
const title = ref("");
const isShow = ref(false);
const rules = {ico:[],
fileName:[ {required: true, message: '请输入文件名', trigger: 'blur' },],
extend:[],
filePath:[],
fileType:[],
fileSize:[],
urlPath:[],
uuid:[],
}
const sa = inject('sa')
const ruleForm = ref();


function open(data) {
    isShow.value = true;
    if (data) {
        title.value = "修改 文件管理";
        m.value = data;
    } else {
        m.value = {ico:'',
fileName:'',
extend:'',
filePath:'',
fileType:0,
fileSize:0,
urlPath:'',
uuid:''}
        title.value = "添加 文件管理";
    }
}

//提交文件管理信息
function ok(parent) {
    ruleForm.value.validate((valid) => {
        if (valid) {
            
            sa.post("/file/save", m.value).then((res) => {
                parent.f5();
                isShow.value = false;
            });
        } else {
            console.log("error submit!!");
            return false;
        }
    });
}

defineExpose({
    open
})

</script>