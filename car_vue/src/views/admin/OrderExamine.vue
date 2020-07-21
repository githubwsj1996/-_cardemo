<template>
    <div class="order"  v-title data-title="订单审核" >
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
            <el-table-column
                    fixed="right"
                    label="操作"
                    width="150"
            >
                <template slot-scope="scope">
                    <el-button @click="agree(scope.row)" type="text" size="mini">同意</el-button>
                    <el-button @click="disagree(scope.row)" type="danger" size="mini">拒绝</el-button>
                </template>
            </el-table-column>
        </el-table>

        <el-pagination
                background
                layout="prev, pager, next"
                page-size="7"
                :total="total"
                style="margin-top: 10px"
                @current-change="page">
        </el-pagination>
    </div>
</template>

<script>
    export default {
        name: "OrderExamine",
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
            axios.get("http://39.106.154.21:8181/order/findAllByState/1/7").then(function (resp) {
                _this.tableData=resp.data.data.orderVO
                _this.total=resp.data.data.count
            })
        },
        methods: {
            page(currentPage) {
                const _this = this
                axios.get('http://39.106.154.21:8181/order/findAllByState/' + currentPage + '/7').then(function (resp) {
                    let data = resp.data.data
                    _this.tableData = data.orderVO
                    _this.total = data.count
                })
            },
            agree(row){
                const _this=this;
                let order={
                    orderId: row.orderId,
                    userId: row.userId,
                    userAddress: row.userAddress,
                    userPhone: row.userPhone,
                    carId: row.carId,
                    src: row.src,
                    carPrice: row.carPrice,
                    days: row.days,
                    totalPrice:row.totalPrice,
                    createTime: new Date(Date.parse(row.createTime)),
                    updateTime: new Date(Date.parse(row.updateTime)),
                    state: 1,
                    adminId: this.admin.id
                }
                axios.post("http://39.106.154.21:8181/order/agree",order).then(function (resp) {
                    if(resp.data.code===0){
                        _this.$message.success("订单受理成功")
                        _this.reload()
                        // _this.$router.go(0);
                    }
                })

                let returns={
                    orderId: row.orderId,
                    userName: row.username,
                    userPhone: row.userPhone,
                    carName: row.carName,
                    carPrice: row.carPrice,
                    days: row.days,
                    totalPrice: row.totalPrice,
                    adminName: this.admin.username,
                    adminId: this.admin.id,
                    updateTime:new Date(Date.parse(row.updateTime)),
                }

                axios.post("http://39.106.154.21:8181/return/saveReturn",returns).then(function (resp) {
                    if(resp.data.code!==0){
                        _this.$message.error("还书数据，异常");
                    }
                })
            },
            disagree(row){
                const _this=this
                    axios.put("http://39.106.154.21:8181/order/disagree/"+row.carId+"/"+row.orderId+"/"+this.admin.id).then(function (resp) {
                        if(resp.data.code===0){
                            if(confirm("确定要拒绝,"+row.orderId+",订单吗?")===true){
                            _this.reload()
                           }
                        }
                    })

            }

        }
    }
</script>

<style scoped>
    .order {
        width: 1280px;
    }
</style>