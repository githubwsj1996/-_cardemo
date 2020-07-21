<template>
    <div v-title data-title="汽车类别租赁图" >
        <el-button @click="downloadExcel()" type="primary">导出租赁数据</el-button>
        <div class="aa">
            <v-chart ref="chart1" :options="orgOption" :auto-resize="true"
                   style="width: 600px ;margin:0 auto"></v-chart>
        </div>
    </div>
</template>

<script>
    import 'echarts/lib/chart/pie'
    //提示
    import  'echarts/lib/component/tooltip'
    //图例
     import  'echarts/lib/component/legend'
    //标题
    import  'echarts/lib/component/title'


    export default {

        name: 'OrderColumnar',
        data() {
            return {
                orgOption: {},
                orderData: '',
                orderDetails: '',

            }
        },
        created() {
            const _this = this
            axios.get("http://39.106.154.21:8181/order/getPie").then(function (resp) {
                _this.orderData = resp.data.data
                _this.orgOption = {
                    //标题
                    title: {
                        text: '车辆租赁扇形图',
                        top: 5,
                        left: 'center'
                    },
                    //工具提示
                    tooltip: {
                        trigger: 'item',
                        formatter: '{a} <br/>{b} : {c} ({d}%)'
                    },
                    //图例说明
                    legend: {
                        show:true,
                        left:'right',
                        orient: 'vertical',
                        data: ['德系','美系','日系','国产'],
                        textStyle:{
                            color:'#333',
                            fontSize:14
                        }
                    },
                    //各个部分颜色
                    color: ['#18DBDF','#3DE16F','#EF69FB','#FE5679'],
                    //拖拽时候重新渲染，默认关闭
                    calculable:true,
                    //列表设置
                    series: [
                        {
                            name: '系列',
                            type: 'pie',
                            radius: '80%',
                            center: ['50%', '50%'],
                            data: _this.orderData.sort(function (a, b) {
                                return a.value - b.value;
                            }),
                            emphasis: {
                                itemStyle: {
                                    shadowBlur: 10,
                                    shadowOffsetX: 0,
                                    shadowColor: 'rgba(0, 0, 0, 0.5)'
                                }
                            }
                        }
                    ]
                };
            })


            // axios.get("http://39.106.154.21:8181/order/getPie").then(function (resp) {
            //     _this.orderData = resp.data.data
            //     _this.orgOption = {
            //         backgroundColor: '#2c343c',
            //
            //         title: {
            //             text: '租车订单数据统计',
            //             left: 'center',
            //             top: 20,
            //             textStyle: {
            //                 color: '#ccc'
            //             },
            //             show: true
            //         },
            //
            //         tooltip: {
            //             trigger: 'item',
            //             formatter: "{a} <br/>{b} : {c} ({d}%)"
            //         },
            //
            //         visualMap: {
            //             show: false,
            //             min: 80,
            //             max: 600,
            //             inRange: {
            //                 colorLightness: [0, 1]
            //             }
            //         },
            //         series: [
            //             {
            //                 name: '访问来源',
            //                 type: 'pie',
            //                 radius: '55%',
            //                 center: ['50%', '50%'],
            //                 roseType: 'radius',
            //                 avoidLabelOverlap:false,
            //                 data: _this.orderData.sort(function (a, b) {
            //                     return a.value - b.value;
            //                 }),
            //                 label: {
            //                     color: 'rgba(255, 255, 255, 0.3)',
            //
            //                 },
            //                 labelLine: {
            //                     lineStyle: {
            //                         color: 'rgba(255, 255, 255, 0.3)'
            //                     },
            //                     smooth: 0.2,
            //                     length: 10,
            //                     length2: 20
            //                 },
            //                 itemStyle: {
            //                     emphasis:{
            //                         color:'#ff9999'
            //                     },
            //                     color: '#c23531',
            //                     shadowBlur: 200,
            //                     shadowColor: 'rgba(0, 0, 0, 0.5)'
            //                 },
            //
            //                 animationType: 'scale',
            //                 animationEasing: 'elasticOut',
            //                 animationDelay: function (idx) {
            //                     return Math.random() * 200;
            //                 },
            //
            //             }
            //         ]
            //     }
            // })
        },
        methods: {
            //列表下载
            downloadExcel() {
                this.$confirm('确定下载列表文件?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.excelData = this.orderDetails //你要导出的数据list。
                    this.export2Excel()
                }).catch(() => {

                });
            },
            //数据写入excel
            export2Excel() {
                const _this = this;
                let admin = JSON.parse(sessionStorage.getItem("admin"))
                axios.get("http://39.106.154.21:8181/order/findAllOrder/" + admin.id).then(function (resp) {
                    _this.orderDetails = resp.data.data
                })
                require.ensure([], () => {
                    const {export_json_to_excel} = require('@/excel/export2Excel'); //这里必须使用绝对路径，使用@/+存放export2Excel的路径
                    const tHeader = ['订单编号', '用户名', '汽车名称', '租赁价格', '租赁天数', '租赁总价', '用户地址', '用户电话', '下单时间', '还车时间', '订单状态']; // 导出的表头名信息
                    const filterVal = ['orderId', 'username', 'carName', 'carPrice', 'days', 'totalPrice', 'userAddress', 'userPhone', 'createTime', 'updateTime', 'state']; // 导出的表头字段名，需要导出表格字段名
                    const list = _this.excelData;
                    const data = _this.formatJson(filterVal, list);
                    export_json_to_excel(tHeader, data, admin.username + '受理的订单表');// 导出的表格名称，根据需要自己命名
                })
            },
            //格式转换，直接复制即可
            formatJson(filterVal, jsonData) {
                return jsonData.map(v => filterVal.map(j => v[j]))
            },
        }

    }
</script>