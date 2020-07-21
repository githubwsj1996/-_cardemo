<template>
    <div class="orderAll" v-title data-title="订单详情" >
        <el-table
                :data="tableData"
                border
                style="width: 100%"
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
                    label="图片"
                    prop="src"
                    width="120"
            >
                <template slot-scope="scope">
                    <el-image :src="scope.row.src" />
                </template>
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
                    width="150"
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
</template>

<script>
    export default {
        name: "OrderAll",
        inject:['reload'],
        data() {
            return {
                tableData: '',
                total: '',
                value: '',
                admin:'',
            }
        },
        created() {
            this.admin=JSON.parse(sessionStorage.getItem("admin"));
            const _this = this;
            axios.get("http://39.106.154.21:8181/order/findAllByAdminId/"+this.admin.id+"/1/8").then(function (resp) {
                _this.tableData=resp.data.data.orderVO
                _this.total=resp.data.data.count
            })
        },
        methods: {
            page(currentPage) {
                const _this = this
                axios.get('http://39.106.154.21:8181/order/findAllByAdminId/'+this.admin.id+"/" +currentPage +'/8').then(function (resp) {
                    let data = resp.data.data
                    _this.tableData = data.orderVO
                    _this.total = data.count
                })
            },

        }
    }
</script>

<style scoped>
    .orderAll {
        width: 1280px;
    }
</style>>