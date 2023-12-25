<template>
    <el-row :gutter="40" class="panel-group">
        <!-- <el-col :xs="12" :sm="24" :lg="8" class="card-panel-col">
            <div class="card-panel flexsAliCen flexBet" @click="handleSetLineChartData('newVisitis')">
                <div class="card-panel-icon-wrapper icon-people">
                    <el-icon class="card-panel-icon"><UserFilled /></el-icon>
                </div>
                <div class="card-panel-description">
                    <div class="card-panel-text">
                        总用户量
                    </div>
                    <CountTo :start-val="0" :end-val="props.numList['总用户量'] || 0" :duration="2600" class="card-panel-num" />
                </div>
            </div>
        </el-col> -->
        <el-col :xs="12" :sm="24" :lg="8" class="card-panel-col">
            <div class="card-panel flexsAliCen flexBet" @click="handleSetLineChartData('newVisitis')">
                <div class="card-panel-icon-wrapper icon-people">
                    <svg-icon class="card-panel-icon icon-shoppin" name="d"></svg-icon>
                </div>
                <div class="card-panel-description">
                    <div class="card-panel-text">
                        日保单数
                    </div>
                    <CountTo v-if="props.numList['保单统计']" :start-val="0" :end-val="props.numList['保单统计']['日保单数'] || 0" :duration="2600" class="card-panel-num" />
                </div>
            </div>
        </el-col>
        <el-col :xs="12" :sm="24" :lg="8" class="card-panel-col">
            <div class="card-panel flexsAliCen flexBet" @click="handleSetLineChartData('newVisitis')">
                <div class="card-panel-icon-wrapper icon-shoppin icon-message">
                    <svg-icon class="card-panel-icon" name="z"></svg-icon>
                </div>
                <div class="card-panel-description">
                    <div class="card-panel-text">
                        周保单数
                    </div>
                    <CountTo v-if="props.numList['保单统计']" :start-val="0" :end-val="props.numList['保单统计']['周保单数'] || 0" :duration="2600" class="card-panel-num" />
                </div>
            </div>
        </el-col>
        <el-col :xs="12" :sm="24" :lg="8" class="card-panel-col">
            <div class="card-panel flexsAliCen flexBet" @click="handleSetLineChartData('newVisitis')">
                <div class="card-panel-icon-wrapper icon-money">
                    <svg-icon class="card-panel-icon" icon-shoppin name="m"></svg-icon>
                </div>
                <div class="card-panel-description">
                    <div class="card-panel-text">
                        月保单数
                    </div>
                    <CountTo v-if="props.numList['保单统计']" :start-val="0" :end-val="props.numList['保单统计']['月保单数'] || 0" :duration="2600" class="card-panel-num" />
                </div>
            </div>
        </el-col>
    </el-row>
    <el-row :gutter="40" class="panel-group">
        <!-- <el-col :xs="12" :sm="24" :lg="8" class="card-panel-col">
            <div class="card-panel flexsAliCen flexBet" @click="handleSetLineChartData('newVisitis')">
                <div class="card-panel-icon-wrapper icon-people">
                    <el-icon class="card-panel-icon"><UserFilled /></el-icon>
                </div>
                <div class="card-panel-description">
                    <div class="card-panel-text">
                        总用户量
                    </div>
                    <CountTo :start-val="0" :end-val="props.numList['总用户量'] || 0" :duration="2600" class="card-panel-num" />
                </div>
            </div>
        </el-col> -->
        <el-col :xs="12" :sm="24" :lg="8" class="card-panel-col">
            <div class="card-panel flexsAliCen flexBet" @click="handleSetLineChartData('newVisitis')">
                <div class="card-panel-icon-wrapper icon-people">
                    <svg-icon class="card-panel-icon icon-shoppin" name="d"></svg-icon>
                </div>
                <div class="card-panel-description">
                    <div class="card-panel-text">
                        日保费
                    </div>
                    <CountTo v-if="props.numList['保单统计']" :start-val="0" :end-val="props.numList['保单统计']['日保费'] || 0" :duration="2600" class="card-panel-num" />
                </div>
            </div>
        </el-col>
        <el-col :xs="12" :sm="24" :lg="8" class="card-panel-col">
            <div class="card-panel flexsAliCen flexBet" @click="handleSetLineChartData('newVisitis')">
                <div class="card-panel-icon-wrapper icon-message">
                    <svg-icon class="card-panel-icon" name="z"></svg-icon>
                </div>
                <div class="card-panel-description">
                    <div class="card-panel-text">
                        周保费
                    </div>
                    <CountTo v-if="props.numList['保单统计']" :start-val="0" :end-val="props.numList['保单统计']['周保费'] || 0" :duration="2600" class="card-panel-num" />
                </div>
            </div>
        </el-col>
        <el-col :xs="12" :sm="24" :lg="8" class="card-panel-col">
            <div class="card-panel flexsAliCen flexBet" @click="handleSetLineChartData('newVisitis')">
                <div class="card-panel-icon-wrapper icon-money">
                    <svg-icon class="card-panel-icon" icon-shoppin name="m"></svg-icon>
                </div>
                <div class="card-panel-description">
                    <div class="card-panel-text">
                        月保费
                    </div>
                    <CountTo v-if="props.numList['保单统计']" :start-val="0" :end-val="props.numList['保单统计']['月保费'] || 0" :duration="2600" class="card-panel-num" />
                </div>
            </div>
        </el-col>
    </el-row>
</template>
  
<script setup>
import { CountTo } from 'vue3-count-to';
let props = defineProps({
    numList:{
        type:Object,
        default:{}
    }
})

function handleSetLineChartData() {

}
</script>
  
<style lang="scss" scoped>
.card-panel-col{
    margin-bottom: 20px;
}
.panel-group {
    .card-panel {
        height: 108px;
        cursor: pointer;
        font-size: 12px;
        position: relative;
        overflow: hidden;
        color: #666;
        background: #fff;
        box-shadow: 4px 4px 40px rgba(0, 0, 0, .05);
        border-color: rgba(0, 0, 0, .05);

        &:hover {
            .card-panel-icon-wrapper {
                color: #fff;
            }

            .icon-people {
                background: #67c23a;
            }

            .icon-message {
                background: #36a3f7;
            }

            .icon-money {
                background: #f4516c;
            }

            .icon-shopping {
                fill:currentColor;
                background: #34bfa3
            }
        }

        .icon-people {
            color: #67c23a;
        }

        .icon-message {
            color: #36a3f7;
        }

        .icon-money {
            color: #f4516c;
        }

        .icon-shopping {
            color: #34bfa3
        }

        .card-panel-icon-wrapper {
            float: left;
            margin: 14px 0 0 14px;
            padding: 16px;
            transition: all 0.38s ease-out;
            border-radius: 6px;
        }

        .card-panel-icon {
            float: left;
            font-size: 48px;
        }

        .card-panel-description {
            float: right;
            font-weight: bold;
            margin: 26px;
            margin-left: 0px;

            .card-panel-text {
                line-height: 18px;
                color: rgba(0, 0, 0, 0.45);
                font-size: 16px;
                margin-bottom: 12px;
            }

            .card-panel-num {
                font-size: 20px;
                color:#333;
            }
        }
    }
}

@media (max-width:550px) {
    .card-panel-description {
        display: none;
    }

    .card-panel-icon-wrapper {
        float: none !important;
        width: 100%;
        height: 100%;
        margin: 0 !important;

        .svg-icon {
            display: block;
            margin: 14px auto !important;
            float: none !important;
        }
    }
}</style>