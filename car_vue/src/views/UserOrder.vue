<template>
    <div class="userOrder_main" v-title data-title="订单详情" >
        <div class="userOrder">
            <el-page-header @back="goBack" content="用户订单表"
                            style="color:deepskyblue;">
            </el-page-header>

            <div style="width:200px;margin-left:1170px">
                <el-button @click="downloadExcel()" type="primary">导出数据</el-button>
            </div>
            <el-table
                    :data="tableData"
                    border
                    style="width: 100%; margin-top:20px;text-align: center"
            >
                <el-table-column
                        prop="orderId"
                        label="订单编号"
                        width="220">
                </el-table-column>
                <el-table-column
                        prop="username"
                        label="用户名"
                        width="90">
                </el-table-column>
                <el-table-column
                        prop="carName"
                        label="汽车名称"
                        width="120">
                </el-table-column>
                <el-table-column
                        prop="carPrice"
                        label="租赁价格"
                        width="80">
                </el-table-column>
                <el-table-column
                        prop="days"
                        label="租赁天数"
                        width="60">
                </el-table-column>
                <el-table-column
                        prop="totalPrice"
                        label="租赁总价"
                        width="120">
                </el-table-column>
                <el-table-column
                        prop="userAddress"
                        label="用户地址"
                        width="120">
                </el-table-column>
                <el-table-column
                        prop="userPhone"
                        label="用户电话"
                        width="120">
                </el-table-column>
                <el-table-column
                        prop="createTime"
                        label="下单时间"
                        width="103">
                </el-table-column>
                <el-table-column
                        prop="updateTime"
                        label="还车时间"
                        width="103">
                </el-table-column>
                <el-table-column
                        prop="state"
                        label="订单状态"
                        width="143"
                >
                </el-table-column>
            </el-table>

            <el-pagination
                    background
                    layout="prev, pager, next"
                    page-size="8"
                    :total="total"
                    style="margin-top: 10px"
                    @current-change="page">
            </el-pagination>
        </div>
    </div>
</template>

<script>
    export default {
        name: "UserOrder",
        data() {
            return {
                tableData: '',
                total: '',
                value: '',
                user: JSON.parse(sessionStorage.getItem("user"))
            }
        },
        created() {
            if (this.user === null) {
                this.$router.push("/")
            }
            const _this = this;
            let id = this.$route.query.id;
            axios.get("http://39.106.154.21:8181/order/findOrderById/1/8/" + id).then(function (resp) {
                let data = resp.data.data
                _this.tableData = data.orderVO
                _this.total = data.count
            })

        },
        methods: {
            page(currentPage) {
                const _this = this
                let id = this.$route.query.id;
                axios.get('http://39.106.154.21:8181/order/findOrderById/' + currentPage + '/8/' + id).then(function (resp) {
                    let data = resp.data.data
                    _this.tableData = data.orderVO
                    _this.total = data.count
                })
            },
            goBack() {
                history.go(-1);
            },
            //列表下载
            downloadExcel() {
                this.$confirm('确定下载列表文件?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.excelData = this.tableData //你要导出的数据list。
                    this.export2Excel()
                }).catch(() => {

                });
            },
            //数据写入excel
            export2Excel() {
                let _this = this;
                let user = JSON.parse(sessionStorage.getItem("user"))
                axios.get("http://39.106.154.21:8181/order/findAllByUserId/" + user.id).then(function (resp) {
                    _this.tableData = resp.data.data
                })
                require.ensure([], () => {
                    //这里必须使用绝对路径，使用@/+存放export2Excel的路径
                    const {export_json_to_excel} = require('@/excel/export2Excel');
                    // 导出的表头名信息
                    const tHeader = ['订单编号', '用户名', '汽车名称', '租赁价格', '租赁天数', '租赁总价', '用户地址', '用户电话', '下单时间', '还车时间', '订单状态'];
                    // 导出的表头字段名，需要导出表格字段名
                    const filterVal = ['orderId', 'username', 'carName', 'carPrice', 'days', 'totalPrice', 'userAddress', 'userPhone', 'createTime', 'updateTime', 'state'];
                    const list = _this.excelData;
                    const data = _this.formatJson(filterVal, list);
                    let username = user.username
                    // 导出的表格名称，根据需要自己命名
                    export_json_to_excel(tHeader, data, username + '的订单表');
                })
            },
            //格式转换，
            formatJson(filterVal, jsonData) {
                return jsonData.map(v => filterVal.map(j => v[j]))
            },
        }
    }
</script>

<style scoped>
    .userOrder_main{
        padding:0px;margin: 0px;
       background:url("../../public/static/images/menu-bg.jpg") no-repeat fixed;
        background-size: 100% 100%;
    }
    .userOrder {
        width: 1280px;
        height: 700px;
        margin: 0 auto;
        padding: 10px;
    }


</style>