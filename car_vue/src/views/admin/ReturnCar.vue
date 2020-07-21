<template>
    <div class="return" v-title data-title="还车详情" >
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
                    prop="userName"
                    label="用户名"
                    width="90">
            </el-table-column>
            <el-table-column
                    prop="userPhone"
                    label="用户电话"
                    width="120">
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
                    prop="createTime"
                    label="受理时间"
                    width="103">
            </el-table-column>
            <el-table-column
                    prop="updateTime"
                    label="还车时间"
                    width="103">
            </el-table-column>
            <el-table-column
                    prop="adminName"
                    label="受理管理员"
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
        //还车详情表
        name: "ReturnCar",
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
            axios.get("http://39.106.154.21:8181/return/findAll/1/8/"+this.admin.id).then(function (resp) {
                _this.tableData=resp.data.data.returnVOList
                _this.total=resp.data.data.counts
            })
        },
        methods: {
            page(currentPage) {
                const _this = this
                axios.get('http://39.106.154.21:8181/return/findAll/' + currentPage + '/8/'+this.admin.id).then(function (resp) {
                    _this.tableData=resp.data.data.returnVOList
                    _this.total=resp.data.data.counts
                })
            }

        }
    }
</script>

<style scoped>
    .return {
        width: 1150px;
        margin-left: 40px;
    }
</style>
