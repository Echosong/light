<template>
  <div class="app-container">
    <el-form
      v-show="show"
      :inline="true"
      :model="formInline"
      class="demo-form-inline"
    >
      <el-form-item>
        <el-input v-model="formInline.description" placeholder="请输入描述" />
      </el-form-item>
      <el-form-item>
        <el-date-picker
          @change="changeFn"
          start-placeholder="开始时间"
          end-placeholder="结束时间"
          v-model="date"
          type="daterange"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" :icon="Search" @click="onSubmit">
          搜索
        </el-button>
        <el-button type="danger" :icon="Refresh" @click="resetting">
          重置
        </el-button>
      </el-form-item>
    </el-form>

    <FunNavigation
      :isAdd="false"
      @addFrom="$router.push({ name: 'userAdd' })"
      @showFn="show = !show"
      @refreshFn="listFn"
    ></FunNavigation>

    <el-table
      header-cell-class-name="tableBackground"
      :data="list"
      stripe
      style="width: 100%"
    >
      <el-table-column prop="userName" label="用户名" />
      <el-table-column prop="requestIp" label="请求ip" />
      <el-table-column width="240" prop="browser" label="浏览器">
        <template #default="scope">
          <el-scrollbar max-height="100">
            {{scope.row.browser}}
          </el-scrollbar>
        </template>
      </el-table-column>
      <el-table-column prop="address" label="地址" />
      <el-table-column prop="description" label="描述" />
      <el-table-column prop="exceptionDetail" label="异常详情">
        <template #default="scope">
          <el-scrollbar max-height="100">
            {{scope.row.exceptionDetail}}
          </el-scrollbar>
        </template>
      </el-table-column>
      <el-table-column prop="logType" label="日志类型" />
      <el-table-column prop="method" label="方法名">
        <template #default="scope">
          <el-scrollbar max-height="100">
            {{scope.row.method}}
          </el-scrollbar>
        </template>
      </el-table-column>
      <el-table-column prop="params" label="参数">
        <template #default="scope">
          <el-scrollbar max-height="100">
            {{scope.row.params}}
          </el-scrollbar>
        </template>
      </el-table-column>
      <el-table-column prop="time" label="请求耗时" />
      <el-table-column width="180" prop="createTime" label="添加时间">
        <template #default="scope">
          {{ parseTime(new Date(scope.row.createTime).getTime()) }}
        </template>
      </el-table-column>
      
    </el-table>
    <div class="pages">
      <el-pagination
        background
        v-model:current-page="formInline.pageNum"
        v-model:page-size="formInline.pageSize"
        layout=" total,sizes, prev, pager, next"
        :total="total"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
      />
    </div>
  </div>
</template>
<script>
export default {
  name: 'userList',
}
</script>
<script setup>
import { logListPages, queryUserLevelList } from '@/api/api'
import FunNavigation from '@/components/funNavigation/funNavigation.vue'
import { statusmark } from '@/pinia/modules/statusmark'
import { parseTime } from '@/utils'
import { Search, Refresh } from '@element-plus/icons-vue'
import { onMounted, reactive, ref,computed } from 'vue'
const g_statusmark = computed(() => statusmark().g_statusmark);

const ProdGroupsPopRef = ref()

onMounted(() => {
  listFn()
})

function prodFn(id) {
  ProdGroupsPopRef.value.dialogVisible = true
  ProdGroupsPopRef.value.createUnrefFn(id)
}

const date = ref('')
const id = ref(0)
const show = ref(true)
const formInline = reactive({
  pageSize: 10,
  pageNum: 1,
  description: '',
  startCreateTime: '',
  endCreateTime: '',
})

const list = ref([])
const total = ref(0)
const handleSizeChange = val => {
  formInline.pageSize = val
  listFn()
}

const handleCurrentChange = val => {
  formInline.pageNum = val
  listFn()
}

async function listFn() {
  const { data } = await logListPages({
    ...formInline,
  })
  console.log(data)
  list.value = data.dataList
  total.value = data.total
}
const changeFn = val => {
  if (val) {
    formInline.startCreateTime = parseTime(val[0].getTime(), '{y}-{m}-{d}')
    formInline.endCreateTime = parseTime(val[1].getTime(), '{y}-{m}-{d}')
  } else {
    formInline.startCreateTime = ''
    formInline.endCreateTime = ''
  }
}

const onSubmit = () => {
  formInline.pageNum = 1
  listFn()
}

const resetting = () => {
  formInline.endCreateTime = ''
  formInline.startCreateTime = ''
  date.value = ''
  formInline.description = ''
}

const levelList = ref([])
let { data } = await queryUserLevelList({})
levelList.value = data
</script>

<style>
.tableBackground {
  background: red;
}
</style>
