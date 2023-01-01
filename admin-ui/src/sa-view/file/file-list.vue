<template>
    <div class="vue-box">
        <div class="c-panel">
            <!-- 参数栏 -->
            <el-form :inline="true" size="mini">
                <el-form-item label="文件名">
                    <el-input v-model="p.fileName" placeholder="搜索文件名"></el-input>
                </el-form-item>
                <el-form-item label="业务类型">
                    <input-enum enumName="fileTypeEnum" v-model="p.fileType"></input-enum>
                </el-form-item>
                <el-form-item style="min-width: 10px">
                    <el-button type="primary" icon="el-icon-search" @click="f5">查询</el-button>
                </el-form-item>
            </el-form>

            <el-table class="data-table" :data="dataList" size="mini">
                <el-table-column label="编号" prop="id"></el-table-column>
                <el-table-column label="文件名" prop="fileName">
                    <template slot-scope="s">
                        <el-link target="_blank" type="primary" :href="sa.cfg.api_url + '/u/' + s.row.urlPath">
                            {{ s.row.fileName }}</el-link>
                    </template>
                </el-table-column>
                <el-table-column label="业务类型" prop="fileTypeEnum"></el-table-column>
                <el-table-column label="文件大小" prop="fileSize"></el-table-column>
                <el-table-column label="创建人" prop="creatorName"></el-table-column>
                <el-table-column label="创建时间" prop="createTime"></el-table-column>
                <el-table-column prop="address" label="操作" width="150px">
                    <template slot-scope="s">
                        <el-button class="c-btn" type="danger" icon="el-icon-delete" @click="del(s.row)">删除</el-button>
                    </template>
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
    </div>
</template>

<script>
import inputEnum from "../../sa-resources/com-view/input-enum.vue";
export default {
    components: { inputEnum },
    data() {
        return {
            p: {
                fileName: "",
                fileType: null,
                page: 1,
                pageSize: 20
            },
            dataList: [],
            dataCount: 0
        };
    },

    methods: {
        f5() {
            this.sa.put("/file/list", this.p).then((res) => {
                this.dataList = res.data.content;
                this.dataCount = res.data.totalElements;
            });
        },
        del(data) {
            this.sa.confirm(
                "是否删除，此操作不可撤销",
                function () {
                    this.sa.delete("/file/delete/" + data.id).then((res) => {
                        console.log(res);
                        this.sa.arrayDelete(this.dataList, data);
                        this.sa.ok(res.message);
                    });
                }.bind(this)
            );
        },
    },

    created() {
        this.f5();
    },
};
</script>

<style>
</style>