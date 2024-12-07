<template>
    <a-drawer
        :title="title"
        :width="700"
        :open="isShow"
        :body-style="{ paddingBottom: '80px' }"
        @close="isShow = false"
        destroyOnClose
    >
        <a-form v-if="m" ref="formRef" :rules="rules" :model="m" :label-col="{ span: 4 }" >
            #{a-form-item}#
        </a-form>
        <template #footer>
            <a-space>
                <a-button @click="isShow= false">取消</a-button>
                <a-button type="primary" @click="handleSubmit">保存</a-button>
            </a-space>
        </template>
    </a-drawer>
</template>

<script setup>
import {ref} from "vue";
import {base} from "/@/utils/base"
//import_file
//create_editor
const emits = defineEmits(['reloadList']);
const props = defineProps(["params"]);

const m = ref({});
const title = ref("");
const isShow = ref(false);
const rules = {//rule_fields
}
const formRef = ref();
const  query = ref({});

async function openDrawer(data, parmas)  {
    isShow.value = true;
    if (data) {
        title.value = "修改 #(tableInfo)";
        let response = await base.get("/#(EntityName)/find/"+data.id);
        m.value = response.data;
    } else {
        let initialData  = //data_init
        query.value = parmas || {};
        m.value ={...initialData, ...parmas}
        title.value = "添加 #(tableInfo)";
    }
}

//提交#(tableInfo)信息
async function handleSubmit() {
    try {
        await formRef.value.validateFields();
        //replace_editor
        base.post("/#(EntityName)/save", m.value).then(() => {
            emits('reloadList');
            isShow.value = false;
        });
    } catch (err) {
        base.error('参数验证错误，请仔细填写表单数据!'+ err.message);
    }
}

defineExpose({
    open: openDrawer
})

</script>