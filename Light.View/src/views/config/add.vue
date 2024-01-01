<template>
    <Dialog v-model="isShow" :title="title" maxHeight="80%">
        <el-form v-if="m" ref="ruleForm" :rules="rules" :model="m" class="demo-ruleForm"
                 label-width="120px">
            <el-form-item label="配置字段名：" prop="key">
                <el-input v-model="m.key"></el-input>
            </el-form-item>
            <el-form-item label="配置说明：" prop="name">
                <el-input v-model="m.name"></el-input>
            </el-form-item>
            <el-form-item label="配置分组：" prop="group">
                <el-input v-model="m.group"></el-input>
            </el-form-item>
            <el-form-item label="配置值：" prop="value">
                <el-input v-model="m.value"></el-input>
            </el-form-item>
            <el-form-item label="类型：" prop="type">
                <el-input v-model="m.type"></el-input>
            </el-form-item>
            <el-form-item label="描述：" prop="description">
                <el-input v-model="m.description"></el-input>
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


const props = defineProps(["params"]);
const m = ref({});
const title = ref("");
const isShow = ref(false);
const rules = {
    key: [],
    name: [],
    group: [{pattern: /^\d{0,}$/, message: '配置分组必须为数字类型', trigger: 'blur'},],
    value: [],
    type: [{pattern: /^\d{0,}$/, message: '类型必须为数字类型', trigger: 'blur'},],
    description: [{pattern: /\s{2,}/, message: '', trigger: 'blur'}],
}
const sa = inject('sa')
const ruleForm = ref();


function open(data) {
    isShow.value = true;
    if (data) {
        title.value = "修改 系统配置";
        m.value = data;
    } else {
        m.value = {
            key: '',
            name: '',
            group: 0,
            value: '',
            type: 0,
            description: ''
        }
        title.value = "添加 系统配置";
    }
}

//提交系统配置信息
function ok(parent) {
    ruleForm.value.validate(async (valid) => {
        if (valid) {
            await sa.post("/config/save", m.value);
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