<template>
    <div id="myEcharts1" :style="{ width: '100%', height: '400px' }"></div>
</template>

<script setup>
import * as echarts from "echarts";
let echart = echarts;
import { ref, onMounted, watch, nextTick } from "vue";

let props = defineProps({
    chartData: {
        type: Object,
        default: {}
    }
})
watch(
    () => props.chartData,
    async (value, oldValue) => {
        await nextTick()
        initChart()
    }, { immediate: true }
)
onMounted(async () => {
    await nextTick()
    initChart();
});
// 基础配置一下Echarts
function initChart() {
    var chart = echart.init(document.getElementById("myEcharts1"));
    let seriesData = []
    for (const key in props.chartData) {
        seriesData.push({
            value: props.chartData[key],
            name: `${key}：￥${props.chartData[key]}`,
        })
    }
    // 把配置和数据放这里
    chart.setOption({
        title: {
            text: '当日交易统计'
        },

        tooltip: {
            trigger: 'item',
            formatter: '{a} <br/>{b}'
        },

        series: [
            {
                name: '当日交易额统计',
                type: 'pie',
                center: ['50%', '50%'],
                //roseType: 'area',
                itemStyle: {
                    borderRadius: 4
                },
                data: seriesData
            }
        ]
    });

    window.onresize = function () {
        chart.resize();
    };
}
</script>