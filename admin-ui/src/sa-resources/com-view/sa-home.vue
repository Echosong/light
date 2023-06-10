<style scoped>
.vue-box {
    background-color: #eee;
    font-size: 14px;
    font-family: "Helvetica Neue", Helvetica, "PingFang SC", "Hiragino Sans GB",
        "Microsoft YaHei", "微软雅黑", Arial, sans-serif;
}
.content-box {
    margin: 1em;
    padding: 1em;
    background-color: #fff;
    color: #666;
    border-radius: 10px;

    margin-left: 20px;
}
.el-tag {
    border-radius: 1px;
}
/*800之下*/
@media (max-width: 800px) {
    .kapian {
        width: 100% !important;
        margin-right: 0px !important;
    }
    .saoma {
        display: none;
    }
}
/* markdown格式 */
.md-contetn {
    padding-left: 1.5em;
    line-height: 26px;
}
</style>
<style>
/* 多个ul时，切换那个啥 */
.md-contetn h4 {
    margin-left: -0.5em;
}
.md-contetn ul,
.md-contetn ol {
    padding-left: 1em;
}
.md-contetn pre {
    padding: 5px;
    background-color: #eee;
    font-family: "times new roman";
}
.title {
    width: 120px;
    font-size: 18px;
    font-family: PingFangSC-Semibold, PingFang SC;
    font-weight: 600;
    color: #2d2d2d;
    line-height: 29px;
    padding-left: 20px;
    border-left: 3px solid #4983f4;
}

.header {
    margin: 20px;
    margin-bottom: 10px;
}

.project {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
}
.project-title {
    font-size: 20px;
    font-weight: 600;
    justify-content: center;
    align-items: center;
    display: flex;
}

.project-number {
    display: flex;
    width: 92px;
    height: 40px;
    font-size: 24px;
    font-family: PingFangSC-Semibold, PingFang SC;
    font-weight: 600;
    color: #4983f4;
    line-height: 40px;
    justify-content: center;
    align-items: center;
}

.index-task-title {
    font-size: 18px;
    font-family: PingFangSC-Semibold, PingFang SC;
    font-weight: 600;
    margin-top: 10px;
    display: flex;
    flex-direction: row;
}

.index-task-title-item {
    margin-left: 20px;
    margin-right: 20px;
}

.project-task-add {
    margin: 10px;
    height: 67px;
    background: #ffffff;
    border-radius: 8px;
    border: 1px solid #e2e2e2;
    display: flex;
    padding-left: 60px;
    justify-content: flex-start;
    align-items: center;
    color: #979797;
    flex-direction: row;
    margin-bottom: 20px;
}

.project-task-add span {
    margin-left: 20px;
    font-size: 20px;
}

.index-item {
    display: flex;
    justify-content: flex-start;
    align-items: flex-start;
    border-bottom: #eee 2px solid;
    padding: 10px;
    flex-direction: column;
    margin-top: 10px;
}

.index-item-circle {
    height: 18px;
    width: 18px;
    border-radius: 9px;
    color: #fff;
    margin-right: 10px;
    display: flex;
    justify-content: center;
    align-items: center;
}

.index-item-b {
    margin-top: 12px;
    display: flex;
    flex-direction: row;
    margin-left: 30px;
}

.el-radio__inner {
    height: 24px;
    width: 24px;
}


.active {
    color: #4983f4;
}

.index-item-title {
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    width: 90%;
}

.index-item-dev {
   display: flex;
   flex-direction: row;
   justify-content: left;

}
.index-item-info{
    word-break: break-all;  
    white-space: pre-wrap; 
    width: 90%;
	font-weight: bold;
	font-size: 16px;
}

.index-item-username {
    margin-left: 20px;
    width: 250px;
    font-weight: bold;
}

</style>

<template>
    <div class="vue-box">

        <div class="header">
            <el-row>
                <el-col :span="11">
                    <div class="title">项目总览</div>
                </el-col>

                <el-col :span="13">
                    <div class="title" style="margin-left:20px;">我的待办</div>
                </el-col>
            </el-row>
        </div>
        <el-row>
            <el-col :span="11">
                <!-- 欢迎 -->
                <div class="content-box">
                    <el-row>
                        <el-col :span="8">
                            <div class="project">
                                <div class="project-title">所有项目</div>
                                <div class="project-number">{{all}}</div>
                            </div>
                        </el-col>
                        <el-col :span="4" v-for="item in enums" :key="item.code">

                            <div class="project">
                                <div class="project-title">{{item.name}}</div>
                                <div class="project-number">{{item.count}}</div>
                            </div>
                        </el-col>

                    </el-row>

                </div>
                <div v-for="(g,index) in group" :key="index">
                    <div class="title" style="margin:20px;"> {{index==0?'待完成任务':'暂停任务'}} </div>
                    <!-- 介绍 -->
                    <div class="content-box">

                        <el-row>
                            <el-col :span="6">
                                <div class="project">
                                    <div class="project-title">采购任务</div>
                                    <div class="project-number">{{g.purchaseCount}}</div>
                                </div>
                            </el-col>
                            <el-col :span="6">

                                <div class="project">
                                    <div class="project-title">研发任务</div>
                                    <div class="project-number">{{g.devCount}}</div>
                                </div>
                            </el-col>

                            <el-col :span="6">

                                <div class="project">
                                    <div class="project-title">技术支持任务</div>
                                    <div class="project-number">{{g.supportCount}}</div>
                                </div>
                            </el-col>

                            <el-col :span="6">

                                <div class="project">
                                    <div class="project-title">项目实施任务</div>
                                    <div class="project-number">{{g.operationCount}}</div>
                                </div>
                            </el-col>

                        </el-row>
                    </div>

                </div>

            </el-col>

            <el-col :span="13">

                <!-- 更新日志 -->
                <div class="content-box">
                    <el-row>
                        <el-col :span="12">
                            <div class="index-task-title">

                                <div class="index-task-title-item" :class="{active: p.state == 0}" @click="send(0)">待办任务</div> |
                                <div class="index-task-title-item" :class="{active: p.state == 1}" @click="send(1)">已经完成任务</div>
                            </div>
                        </el-col>
                        <el-col :span="12" v-if="user">
                            <select-user v-model="p.userId" v-if="user.roleId == 1" @userChange="f5"></select-user>
                        </el-col>
                    </el-row>

                    <div style="margin:20px;">
                        <div class="project-task-add" @click="add">
                            <i class="el-icon-plus" style="font-size:50px;">

                            </i>
                            <span>点击创建待办任务</span>

                        </div>

                        <div class="index-table">
                            <div class="index-item" v-for="item in dataList" :key="item.id" @click="update(item)">
                                <div class="index-item-title">
                                    <div @click.stop class="index-item-dev">
                                        <el-radio v-model="item.state" size="medium" @change="change(item)"></el-radio>
                                        <div class="index-item-info">{{item.title}}</div>
                                    </div>
                                    <div class="index-item-username">
                                    <span style="font-size:14px;">处理人：{{item.userName}}</span>
                                    </div>
                                </div>
                                <div class="index-item-b">
                                    <div v-if="item.level" class="index-item-circle" :style="{backgroundColor:color[1]}">{{item.level}}</div>
                                    <div v-if="item.important" class="index-item-circle" :style="{backgroundColor:color[3]}">{{item.important}}</div>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>
            </el-col>
            <!-- 增改组件 -->
        </el-row>
    </div>
</template>

<script>
import SelectUser from "./select-user.vue";
export default {
    data() {
        return {
            enums: [],
            all: 0,
            value: 0,
            color: ["#E02020", "#F74000", "#FF7200", "#FFB103"],
            p: { pageSize: 10, page: 1, state: 0, userId: null },
            dataCount: 0,
            dataList: [],
            user: {},
            group: [],
            timer: ''
        };
    },
    components: {
        SelectUser
    },
    created() {
        this.user = this.sa_admin.user;
        console.log("====", this.user);
	
		this.f5();

    },
    mounted(){

    },
    beforeDestroy() {
      clearInterval(this.timer);
    },
    methods: {
        f5: function () {
			this.user = this.sa_admin.user;
            console.log("定时请求"+this.sa_admin.user);

            // 判断是否登录
            if(!this.user){
               // console.log(this.sa_admin.user+"====================");
                return;
            }
        },

        send(index) {
            this.p.state = index;
            this.f5();
        },
        // 显示文档页面
        openDoc: function () {
            parent.sp.selectMenu(11);
        },
        //添加
        add: function () {
            this.$refs["add-or-update"].open(0);
        },
        //更新
        update(row) {
            this.$refs["add-or-update"].open(row);
        },
        change(item) {
            if (this.p.state) {
                item.state = 0;
            } else {
                item.state = 1;
            }
        },
    },
};
</script>

