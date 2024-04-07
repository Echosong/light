<template>
    <Dialog v-model="isShow" :title="title" maxHeight="700px">
        <el-form v-if="m" ref="ruleForm" :rules="rules" :model="m" class="demo-ruleForm"
                 label-width="120px">
            <el-form-item label="标题" prop="title" v-if="!query.title">
                <el-input v-model="m.title"></el-input>
            </el-form-item>
            <el-form-item label="类别" v-if="!query.type">
                <select-data v-model="m.type" routeName="resourceCategory"></select-data>
            </el-form-item>
            <el-form-item label="文件路径" v-if="!query.path">
                <File @onremove="m.path = ''" @onSuccess="m.path = $event" :file="m.path"/>
            </el-form-item>
            <el-form-item label="说明" v-if="!query.info">
                <el-input type="textarea" rows="2" placeholder="说明" v-model="m.info"></el-input>
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
import File from "@/components/file/file.vue";
import selectData from '@/components/SelectData/index.vue'

const props = defineProps(["params"]);
const m = ref({});
const title = ref("");
const isShow = ref(false);
const rules = {
    title: [],
    type: [],
    path: [],
    info: [],
}
const sa = inject('sa')
const ruleForm = ref();
const query = ref({});

async function open(data, parmas) {
    isShow.value = true;
    if (data) {
        title.value = "修改 办公资源文件存在";
        let one = await sa.get("/resource/find/" + data.id);
        m.value = one.data;
        m.value = data;
    } else {
        let mdata = {
            title: '',
            type: '',
            path: '',
            info: ''
        }
        query.value = parmas || {};
        m.value = {...mdata, ...parmas}
        title.value = "添加 办公资源文件存在";
    }
}

//提交办公资源文件存在信息
function ok(parent) {
    ruleForm.value.validate(async (valid) => {
        if (valid) {

            await sa.post("/resource/save", m.value);
            parent.f5();
            isShow.value = false;
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