<template>
    <div id="myEcharts" :style="{ width: '100%', height: '400px' }"></div>
</template>

<script setup>
import * as echarts from "echarts";
let echart = echarts;
import { ref, onMounted,nextTick , onUpdated,watch } from "vue";

let props = defineProps({
    chartData:{
        type:Object,
        default:{}
    }
})
watch(
    () => props.chartData,
    async (value, oldValue) => {
        await nextTick()
        initChart()
    }, {immediate:true}
  )
onMounted(async () => {
    await nextTick()
    initChart();
});

// 基础配置一下Echarts
function initChart() {
    var charts = echart.init(document.getElementById("myEcharts"));
    let xAxisData = []
    let seriesData = []
    var resData = props.chartData
var sortedKeys = Object.keys(resData).sort((key1, key2) => key1.localeCompare(key2));

var sortedObj = {};
sortedKeys.forEach(key => {
    sortedObj[key] = resData[key];
});
    for (const key in sortedObj) {
        xAxisData.push(key)
        seriesData.push(sortedObj[key])
    }
    // 把配置和数据放这里
    charts.setOption({
        title: {
            text: '周保单统计矩形图'
        },
        grid: {
            left: '0%',
            right: '0%',
            bottom: '0%',
            containLabel: true
        },
        xAxis: {
            type: "category",
            data:xAxisData
        },
        tooltip: {
            trigger: "axis"
        },
        yAxis: {
            type: "value",
            left: "0%",
        },
        series: [
            {
                data:seriesData,
                type: "bar",
                smooth: true
            }
        ]
    });
    window.addEventListener('resize', () => {
        charts.resize();
    });
}


</script>