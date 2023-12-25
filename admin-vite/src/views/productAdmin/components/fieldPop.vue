<template>
    <el-dialog v-model="dialogVisible" title="投保字段" width="30%">
        <el-form label-position="right" ref="ruleFormRef">
            <el-form-item v-for="item in groupbyField(list, 'fieldcategoryid')" style="margin-top: 10px"
                :label="item[0].fieldTypeName">
                <el-checkbox-group v-model="checkList">
                    <el-checkbox v-for="items in item" :label="items" :key="items.id">
                        {{ items.fieldname }}
                    </el-checkbox>
                </el-checkbox-group>
            </el-form-item>
        </el-form>
        <template #footer>
            <span class="dialog-footer">
                <el-button type="primary" @click="submitForm">确定</el-button>
                <el-button @click="dialogVisible = false">取消</el-button>
            </span>
        </template>
    </el-dialog>
</template>
  
<script  setup>
import { ref, reactive ,defineEmits} from 'vue'
import { groupbyField } from '@/utils'
const emit = defineEmits(["onSucces"])
const checkList = ref([])
const dialogVisible = ref(false)
const props = defineProps({
    list: Array
})
const submitForm = () => {
    dialogVisible.value = false
    emit("onSucces",checkList.value)
    checkList.value = []
}

//把数据导出
defineExpose({
    dialogVisible,
})

</script>
<style scoped>
.dialog-footer button:first-child {
    margin-right: 10px;
}
</style>