<template>
    <div v-title data-title="订单柱状展示" >
        <v-chart style="width: 1600px;margin-top: 10px" ref="chart1" :options="orgOptions" :auto-resize="true"></v-chart>
    </div>
</template>

<script>
    import 'echarts/lib/chart/bar'

    export default {
        name: 'OrderColumnar',
        data() {
            return {
                orgOptions: {},
                orderData: ''
            }
        },
        created() {
            const _this = this
            axios.get("http://39.106.154.21:8181/order/getBar").then(function (resp) {
                _this.orderData = resp.data.data
                _this.orgOptions = {
                    color: ["#64CDF0"],
                    title: {
                        text: '租车订单数据统计',
                        left: 'center',
                        textStyle: {
                            fontSize: 30
                        },
                        show: true
                    },
                    tooltip: {
                        trigger: "axis",
                        axisPointer: {
                            type: "shadow"
                        }
                    },
                    xAxis: {
                        type: "category",
                        data: _this.orderData.name,
                        //文本
                        axisLabel: {
                            color: "#BB3137",
                            fontSize: 10,
                        },
                        //轴线
                        axisLine: {
                            lineStyle: {
                                color: "#BB3137",
                            }
                        },
                        axisTick: {
                            alignWithLabel: true
                        }
                    },
                    yAxis: [
                        {
                            type: "value",
                            axisLabel: {
                                color: "#BB3137",
                                fontSize: 10
                            },
                            // 修改y轴横向分割线的颜色
                            splitLine: {
                                lineStyle: {
                                    color: ["#FFC9CB"]
                                }
                            },
                            axisLine: {
                                lineStyle: {
                                    color: "#BB3137"
                                }
                            }
                        }
                    ],
                    series: [
                        {
                            name: '租赁数量',
                            type: 'bar',
                            barWidth: "40%",
                            // label: {
                            //     show: true,
                            //     position: "top",
                            //     color: "#3D383A",
                            //     formatter: "{c}辆"
                            // },
                            data: _this.orderData.count,
                        }
                    ],
                }
            })
        },

    }
</script>

