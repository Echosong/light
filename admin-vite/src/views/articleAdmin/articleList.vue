<template>
  <div class="app-container">
    <el-form v-show="show" :inline="true" :model="formInline" class="demo-form-inline">
      <el-form-item>
        <el-input v-model="formInline.title" placeholder="请输入描述" />
      </el-form-item>
      <!-- <el-form-item>
        <el-date-picker @change="changeFn" start-placeholder="开始时间" end-placeholder="结束时间" v-model="date"
          type="daterange" />
      </el-form-item> -->
      <el-form-item>
        <el-button type="primary" :icon="Search" @click="onSubmit">
          搜索
        </el-button>
        <el-button type="danger" :icon="Refresh" @click="resetting">
          重置
        </el-button>
      </el-form-item>
    </el-form>

    <FunNavigation @addFrom="$router.push({ name: 'addArticle' })" @showFn="show = !show" @refreshFn="listFn">
    </FunNavigation>

    <el-table header-cell-class-name="tableBackground" :data="list" stripe style="width: 100%">
      <el-table-column prop="title" label="标题" />

      <!-- <el-table-column prop="content" label="内容">
        <template #default="scope">
          <el-scrollbar max-height="200">
            {{ scope.row.content }}
          </el-scrollbar>
        </template>
      </el-table-column> -->
      <el-table-column width="180" prop="createTime" label="添加时间">
        <template #default="scope">
          {{ parseTime(new Date(scope.row.createTime).getTime()) }}
        </template>
      </el-table-column>

      <el-table-column width="180" fixed="right"  label="操作">
        <template #default="scope">
          <el-button  v-if="permission('edit')"  @click="$router.push({name:'addArticle',query:{id:scope.row.id}})" :icon="Edit" type="warning" size="small">修改</el-button>
          <el-popconfirm @confirm="deleteFn(scope.row.id)" title="您确定要删除这个公告？">
            <template #reference>
              <el-button  v-if="permission('delete')" icon="Delete" type="danger" size="small">删除</el-button>
            </template> 
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <div class="pages">
      <el-pagination background v-model:current-page="formInline.pageNum" v-model:page-size="formInline.pageSize"
        layout=" total,sizes, prev, pager, next" :total="total" @size-change="handleSizeChange"
        @current-change="handleCurrentChange" />
    </div>
  </div>
</template>
<script>
export default {
  name: 'articleList',
}
</script>
<script setup>
import { articleListPage,articleDelete } from '@/api/api'
import FunNavigation from '@/components/funNavigation/funNavigation.vue'
import { parseTime } from '@/utils'
import { Search, Refresh,Edit } from '@element-plus/icons-vue'
import { onMounted, reactive, ref } from 'vue'
import { permission } from '@/hooks/permission';
import { ElMessage } from 'element-plus'
const ProdGroupsPopRef = ref()

onMounted(() => {
  listFn()
})

function prodFn(id) {
  ProdGroupsPopRef.value.dialogVisible = true
  ProdGroupsPopRef.value.createUnrefFn(id)
}
const date = ref('')
const show = ref(true)
const formInline = reactive({
  pageSize: 10,
  pageNum: 1,
  title: '',
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
  const { data } = await articleListPage({
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

async function deleteFn(id){
  await articleDelete({
    id
  })
  ElMessage.success('删除成功！')
  listFn()
}
</script>

<style>
.tableBackground {
  background: red;
}
</style>
