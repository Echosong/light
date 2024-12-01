<template>
    <!---------- 查询表单form begin ----------->
    <a-form class="smart-query-form">
        <a-row class="smart-query-form-row">
            #{a-form-item}#
            <a-form-item class="smart-query-form-item">
                <a-button-group>
                    <a-button type="primary" @click="f5">
                        <template #icon>
                            <SearchOutlined/>
                        </template>
                        查询
                    </a-button>
                    <a-button @click="resetQuery" class="smart-margin-left10">
                        <template #icon>
                            <ReloadOutlined/>
                        </template>
                        重置
                    </a-button>
                </a-button-group>
            </a-form-item>
        </a-row>
    </a-form>
    <!---------- 查询表单form end ----------->

    <a-card size="small" :bordered="false" :hoverable="true">
        <!---------- 表格操作行 begin ----------->
        <a-row class="smart-table-btn-block">
            <div class="smart-table-operate-block">
                <a-button @click="add" type="primary">
                    <template #icon>
                        <PlusOutlined/>
                    </template>
                    新建
                </a-button>
                <a-button @click="exportFile" type="primary">
                    <template #icon>
                        <ArrowDownOutlined/>
                    </template>
                    导出
                </a-button>
            </div>
            <div class="smart-table-setting-block">
                <TableOperator v-model="columns" tableId="log" :refresh="f5"/>
            </div>
        </a-row>
        <!---------- 表格操作行 end ----------->

        <!---------- 表格 begin ----------->
        <a-table
            size="small"
            :dataSource="tableData"
            :columns="columns"
            rowKey="positionId"
            bordered
            :loading="tableLoading"
            :pagination="false"

        >
            <template #bodyCell="{ text, record, column }">
                <template v-if="column.dataIndex === 'action'">
                    <div class="smart-table-operate">
                        <a-button @click="update(record)" type="link">编辑</a-button>
                        <a-button @click="del(record)" danger type="link">删除</a-button>
                    </div>
                </template>
            </template>
        </a-table>
        <!---------- 表格 end ----------->

        <Pagination :p="p" @f5="f5" :total="total"></Pagination>
        <addOrUpdate ref="addUpdate" @reloadList="f5"/>

    </a-card>
</template>
<script setup>
import addOrUpdate from './add.vue';
import {reactive, ref, onMounted} from 'vue';
import {base} from '/@/utils/base';
import Pagination from "/@/components/framework/base-page/index.vue"
import {smartSentry} from '/@/lib/smart-sentry';
import TableOperator from '/@/components/support/table-operator/index.vue';
import {useRouter} from "vue-router";
//importFiles
// ---------------------------- 表格列 ----------------------------

const columns = ref(#{listColumns}#);

// ---------------------------- 查询数据表单和方法 ----------------------------

const params = #{queryPageParams}#
// 查询表单form
let p = reactive({...params});
// 表格加载loading
const tableLoading = ref(false);
// 表格数据
const tableData = ref([]);
// 总数
const total = ref(0);
const addUpdate = ref()
const query = ref({})

// 重置查询条件
function resetQuery() {
    let pageSize = p.pageSize;
    Object.assign(p, params);
    p.pageSize = pageSize;
    f5();
}

// 查询数据
async function f5() {
    tableLoading.value = true;
    try {
        let queryResult = await base.put('/#(EntityName)/listPage', p);
        tableData.value = queryResult.data.content;
        total.value = queryResult.data.totalElements;
    } catch (e) {
        smartSentry.captureError(e);
    } finally {
        tableLoading.value = false;
    }
}

const router = useRouter();

onMounted(() => {
    query.value = router.currentRoute.value.query;
    f5()
})

function exportFile() {
    base.download("/#(EntityName)/export", p)
}

// ---------------------------- 添加/修改 ----------------------------
function update(row) {
    addUpdate.value.open(row, query.value);
}

function add() {
    addUpdate.value.open(null, query.value);
}

// ---------------------------- 单个删除 ----------------------------
//确认删除
function del(data) {
    base.confirm('是否删除，此操作不可撤销', async function () {
        let res = await base.delete("/#(EntityName)/delete/" + data.id);
        base.success(res.message);
        f5();
    }.bind(this));
}

function shortChange(e) {
    console.log('排序接受', e)
    p.direction = e.order === 'ascending';
    p.sortCol = e.prop;
    f5();
}

async function updateSwitch(row) {
    await base.post('/#(EntityName)/save', row);
    base.success("更新成功")
}


// 选择表格行
const selectedRowKeyList = ref([]);

function onSelectChange(selectedRowKeys) {
    selectedRowKeyList.value = selectedRowKeys;
}

defineExpose({
    f5
})
</script>
