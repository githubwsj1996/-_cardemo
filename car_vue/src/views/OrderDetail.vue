<template>
    <div class="orderDetail" v-title data-title="用户订单" >
        <el-page-header @back="goBack" content="订单详情" style="color:deepskyblue;margin-left: 200px "/>
        <div class="main">
            <el-form ref="form" :model="form" label-width="80px" style="width: 100%" class="orderDetail_Form" >
                <el-form-item label="用户姓名">
                    <el-input v-model="form.username" readonly></el-input>
                </el-form-item>
                <el-form-item label="汽车名称">
                    <el-input v-model="form.carName" readonly></el-input>
                </el-form-item>
                <el-form-item label="汽车图片">
                    <el-image
                            style="width: 100px; height: 100px"
                            :src="form.carImg">
                    </el-image>
                </el-form-item>
                <el-form-item label="租赁价格">
                    <el-input v-model="form.carPrice+'元/天'" readonly></el-input>
                </el-form-item>
                <el-form-item label="租赁天数">
                    <el-input v-model="form.days+'天'" readonly></el-input>
                </el-form-item>
                <el-form-item label="租赁总价">
                    <el-input v-model="form.totalPrice+'元'" readonly></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" @click="createOrder">立即创建</el-button>
                    <el-button @click="goBack">取消</el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>

<script>
    export default {
        name: "OrderDetail",
        data() {
            return {
                form: {
                    // orderId: '',
                    username: '',
                    carName: '',
                    carImg: '',
                    carPrice: '',
                    days: '',
                    totalPrice: '',
                },
                user:JSON.parse(sessionStorage.getItem("user"))
            }
        },
        created() {
            if(this.user===null){
                this.$router.push("/")
            }
            this.form = this.$route.query.order
        },
        methods: {
            createOrder() {
                const _this=this;
                let car = this.form;
                let orderInfo = {
                    userId: _this.user.id,
                    userAddress: _this.user.address,
                    userPhone: _this.user.userPhone,
                    carId: this.$route.query.order.carId,
                    src: car.carImg,
                    carPrice: car.carPrice,
                    days: car.days,
                    totalPrice: car.totalPrice,
                }
                axios.post("http://39.106.154.21:8181/order/createOrder",orderInfo).then(function (resp) {
                    if(resp.data.data!=null){
                        _this.$message({
                            showClose: true,
                            message: '订单编号'+resp.data.data.orderId+'支付成功'
                        });
                        setTimeout(() => {
                            _this.$router.push("/success?orderId="+resp.data.data.orderId+"&totalPrice="+_this.form.totalPrice)
                        }, 1000)
                    }
                    else{
                        _this.$message.error("订单创建失败")
                    }
                })
            },
            goBack() {
                history.go(-1);
            },
        }
    }
</script>

<style scoped>
    .orderDetail{
        background: url("../../public/static/images/menu-bg.jpg") ;
        width: 100%;height: 100%;
        background-size: cover;
        position: fixed;
    }
    .main {
        width: 600px;
        margin: 10px auto;
    }

    .orderDetail_Form{
        border-radius: 15px;
        margin: 0px auto;
        width: 350px;
        padding: 35px 35px 15px 35px;
        background: #fff;
        border: 1px solid #eaeaea;
        box-shadow: 0 0 25px #cac6c6;
    }

</style>