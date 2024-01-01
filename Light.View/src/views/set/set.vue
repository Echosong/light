<template>
    <div class="app-container">
        <el-tabs v-model="activeName" class="demo-tabs">
            <el-tab-pane label="系统配置" name="first">
                <div>
                    <el-form label-position="right" label-width="200" ref="ruleFormRef"
                             style="max-width: 70%">

                        <template v-for="(item, index) in configList">
                            <el-form-item :label="item.name + ':'">
                                <el-input v-if="item.type === 0" v-model="item.value"
                                          :placeholder="item.description"></el-input>

                                <el-select v-if="item.type === 1" v-model="item.value">
                                    <el-option label="请选择" value=""></el-option>
                                    <el-option v-for="op in item.options" :key="op.v" :label="op.n" :value="op.n">
                                        {{ op.n }}
                                    </el-option>
                                </el-select>

                                <ESwitch v-if="item.type === 2" v-model="item.value" size="default"></ESwitch>

                                <el-input v-if="item.type === 5" type="textarea" :rows="4" style="width: 60%"
                                          :placeholder="item.description" v-model="item.value">
                                </el-input>
                                <FileImage v-if="item.type === 4" @onremove="item.value = ''" @onSuccess="item.value = $event" :file="item.value"/>
                                <File v-if="item.type === 6" @onremove="item.value = ''" @onSuccess="item.value = $event" :file="item.value"/>
                                <template v-if="item.type === 3">
                                    <el-checkbox-group v-model="item.values">
                                        <el-checkbox v-for="op in item.options" :key="op.v" :label="op.n">{{ op.n }}
                                        </el-checkbox>
                                    </el-checkbox-group>
                                </template>
                            </el-form-item>
                        </template>
                        <el-form-item>
                            <el-button type="primary" @click="submitForm()">修改配置</el-button>
                        </el-form-item>
                    </el-form>
                </div>
            </el-tab-pane>
        </el-tabs>

    </div>
</template>
<script>
export default {
    name: "set"
}
</script>
<script setup>
import {onMounted, ref} from "vue";
import {ElMessage} from "element-plus";
import {getConfiglist, saveConfigList} from "@/api/api";
import File from "@/components/file/file.vue";
import FileImage from "@/components/file/fileImage.vue";
import ESwitch from "@/components/ESwitch/ESwitch.vue";

const activeName = ref('first')
const name = ref("系统配置");
const configList = ref([])

onMounted(() => {
    init()
})

const init = async ()=>{
    const { data } = await getConfiglist({});
    configList.value = data;
}

const  submitForm = async ()=>{
    await saveConfigList(configList.value);
    ElMessage.success("修改配置成功！！")
}

</script>

