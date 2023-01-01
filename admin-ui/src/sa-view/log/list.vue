<template>
    <div class="vue-box">
        <div class="c-panel">
            <!-- 参数栏 -->
            <el-form :inline="true" size="mini">
                <el-form-item label="描述：">
                    <el-input v-model="p.description" placeholder="模糊查询"></el-input>
                </el-form-item>
                <el-form-item label="创建时间：">
                    <el-date-picker v-model="p.startCreateTime" type="datetime" value-format="yyyy-MM-dd HH:mm:ss"
                        placeholder="开始日期"></el-date-picker>
                    -
                    <el-date-picker v-model="p.endCreateTime" type="datetime" value-format="yyyy-MM-dd HH:mm:ss"
                        placeholder="结束日期"></el-date-picker>
                </el-form-item>
                <el-form-item style="min-width: 0px">
                    <el-button type="primary" icon="el-icon-search" @click="f5();">查询 </el-button>
                </el-form-item>
            </el-form>
            <!-- <div class="c-title">数据列表</div> -->
            <el-table :data="dataList" size="mini">
                <el-table-column label="方法名" prop="method" min-width="100" :show-overflow-tooltip="true">
                </el-table-column>
                <el-table-column label="用户" prop="creatorName">
                </el-table-column>
                <el-table-column label="请求ip" prop="requestIp"></el-table-column>
                <el-table-column label="描述" prop="description"></el-table-column>
                <el-table-column label="创建时间" prop="createTime"></el-table-column>
                <el-table-column label="浏览器" prop="browser" min-width="100" :show-overflow-tooltip="true">
                </el-table-column>
                <el-table-column label="请求耗时" prop="time"></el-table-column>
                <el-table-column label="参数" prop="params" min-width="180" :show-overflow-tooltip="true">
                </el-table-column>
            </el-table>
            <!-- 分页 -->
            <div class="page-box">
                <el-pagination background layout="total, prev, pager, next, sizes, jumper" :current-page.sync="p.page"
                    :page-size.sync="p.pageSize" :total="dataCount" :page-sizes="[1, 10, 20, 30, 40, 50, 100]"
                    @current-change="f5(true)" @size-change="f5(true)">
                </el-pagination>
            </div>
        </div>
        <!-- 增改组件 -->
        <add-or-update ref="add-or-update"></add-or-update>
    </div>
</template>

<script>
//import inputEnum from "../../sa-resources/com-view/input-enum.vue";
import addOrUpdate from './add.vue';
export default {
    components: {
        addOrUpdate,
        //inputEnum,
    },
    data() {
        return {
            p: { pageSize: 10, page: 1, description: '', startCreateTime: '', endCreateTime: '' },
            dataCount: 0,
            dataList: [],
        }
    },
    methods: {
        // 数据刷新
        f5: function () {
            this.sa.put("/log/listPage", this.p).then(res => {
                this.dataList = res.data.content.map((item) => {

                    return item;
                });;
                this.dataCount = res.data.totalElements;
            });
        },
        // 删除
        del: function (data) {
            this.sa.confirm('是否删除，此操作不可撤销', function () {
                this.sa.delete("/log/delete/" + data.id).then(res => {
                    console.log(res)
                    this.sa.arrayDelete(this.dataList, data);
                    this.sa.ok(res.message);
                });

            }.bind(this));
        },
    },
    created: function () {
        this.f5();
    }
}

</script>

<style>
</style>
