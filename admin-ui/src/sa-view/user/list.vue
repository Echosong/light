<template>
    <div class="vue-box">
        <div class="c-panel">
            <!-- 参数栏 -->
            <el-form :inline="true" size="mini">
                <el-form-item label="用户昵称：">
                    <el-input v-model="p.username" placeholder="模糊查询"></el-input>
                </el-form-item>
                <el-form-item label="注册日期：">
                    <el-date-picker v-model="p.startDate" type="date" value-format="yyyy-MM-dd" placeholder="开始日期">
                    </el-date-picker>
                    -
                    <el-date-picker v-model="p.endDate" type="date" value-format="yyyy-MM-dd" placeholder="结束日期">
                    </el-date-picker>
                </el-form-item>
                <el-form-item style="min-width: 0px">
                    <el-button type="primary" icon="el-icon-search" @click="
              p.pageNo = 1;
              f5();
            ">查询</el-button>
                    <el-button type="primary" icon="el-icon-plus" @click="add">增加</el-button>
                </el-form-item>
            </el-form>
            <!-- <div class="c-title">数据列表</div> -->
            <el-table :data="dataList" size="mini">
                <el-table-column label="编号" prop="id" width="70px"> </el-table-column>
                <el-table-column label="姓名" prop="name"> </el-table-column>
                <el-table-column label="账号" prop="username"></el-table-column>
                <el-table-column label="邮箱" prop="email" width="150px"></el-table-column>
                <el-table-column label="性别" prop="sexEnum"> </el-table-column>
                <el-table-column label="登录ip" prop="loginIp"></el-table-column>
                <el-table-column label="注册ip" prop="regIp"></el-table-column>
                <el-table-column label="创建时间" width="150px" prop="createTime"></el-table-column>
                <el-table-column label="部门" prop="roleName"></el-table-column>
                <el-table-column label="状态" prop="stateEnum">
                    <template slot-scope="s">
                        <el-button title="点击切换状态" :style="{ color: colors[s.row.state] }" @click="updateState(s.row)" type="text">{{ s.row.stateEnum }}</el-button>
                    </template>
                </el-table-column>
                <el-table-column prop="address" label="操作" width="220px">
                    <template slot-scope="s">
                        <el-button class="c-btn" type="primary" icon="el-icon-edit" @click="update(s.row)">修改
                        </el-button>
                        <el-button class="c-btn" type="danger" icon="el-icon-delete" @click="del(s.row)">删除</el-button>
                        <el-button type="info" class="c-btn" icon="el-icon-setting" @click="reset(s.row)">重置</el-button>
                    </template>
                </el-table-column>
            </el-table>

            <!-- 分页 -->
            <div class="page-box">
                <el-pagination background layout="total, prev, pager, next, sizes, jumper" :current-page.sync="p.page" :page-size.sync="p.pageSize" :total="dataCount" :page-sizes="[10, 20, 30, 40, 50, 100]" @current-change="f5(true)" @size-change="f5(true)">
                </el-pagination>
            </div>
        </div>

        <!-- 增改组件 -->
        <add-or-update ref="add-or-update"></add-or-update>
    </div>
</template>

<script>
//import mockData from './data-list.js';
import addOrUpdate from "./user-add.vue";
export default {
    components: {
        addOrUpdate
    },
    data() {
        return {
            p: {
                // 查询参数
                username: "",
                startDate: "", // 本月一号
                endDate: "", // 本月当日
                page: 1,
                pageSize: 10
            },
            dataCount: 1422,
            dataList: [],
            colors: ["#606266", "#409EFF", "#F56C6C"]
        };
    },
    methods: {
        // 数据刷新
        f5: function() {
            this.sa.put("/user/list", this.p).then(res => {
                this.dataList = res.data.content;
                this.dataCount = res.data.totalElements;
            });
        },
        // 删除
        del: function(data) {
            this.sa.confirm(
                "是否删除，此操作不可撤销",
                function() {
                    this.sa.delete("/user/delete/" + data.id).then(res => {
                        console.log(res);
                        this.sa.arrayDelete(this.dataList, data);
                        this.sa.ok(res.message);
                    });
                }.bind(this)
            );
        },
        

        updateState(row) {
            console.log(999, row.state);
            if (row.state == 0 || row.state == 2) {
                row.state = 1;
            } else {
                row.state = 2;
            }
            this.sa.put("/user/update", row).then(res => {
                console.log(res);
                this.f5();
                this.sa.ok("更新数据成功");
            });
        },

        update(row) {
            this.$refs["add-or-update"].open(row);
        },
        /**
         * 重置密码
         */
        reset(row) {
            this.sa.confirm(
                "是否确认重置密码，此操作不可撤销",
                function() {
                    this.sa.get("/user/resetPassword/" + row.id).then(res => {
                        console.log(res);
                        this.sa.ok("密码重置成功");
                    });
                }.bind(this)
            );
        },

        // 查看
        get: function(data) {
            var str = "<div>";
            str += "<p>编号：" + data.id + "</p>";
            str += "<p>姓名：" + data.name + "</p>";
            str += "<p>性别：" + data.sexEnum + "</p>";
            str += "<p>当前状态：<b>" + data.stateEnum + "</b></p>";
            str += "<p>注册ip：" + data.regIp + "</p>";
            str += "<p>注册时间：" + data.createTime + "</p>";
            str += "</div>";
            this.sa.alert(str);
        },
        add: function() {
            this.$refs["add-or-update"].open(0);
        }
    },
    created: function() {
        this.f5();
    }
};
</script>

<style>
</style>
