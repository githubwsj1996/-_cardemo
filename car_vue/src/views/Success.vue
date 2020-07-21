<template>
    <div style="margin: 0px auto;width: 600px;" v-title data-title="支付界面" >
        <img src="../../public/static/images/paysuccess.png"
             style="width: 600px;height: 400px"/>

        <div>
            <p style="margin-left: 120px;display: inline">订单编号:</p>
            <el-link v-text="orderId" style="margin-left: 10px"></el-link>
            <p style="margin-left: 30px;display: inline">价格:</p>
            <el-link v-text="totalPrice" style="margin-left: 10px"> </el-link>
        </div>
        <el-button type="warning" @click="backHome" style="margin-top:20px;margin-left: 150px;width: 300px">返回首页</el-button>
    </div>

</template>

<script>
    export default {
        name: "Success",
        data() {

            return {
                orderId: '',
                totalPrice: ''
            }
        },
        created() {
            this.orderId = this.$route.query.orderId,
                this.totalPrice = this.$route.query.totalPrice
        },
        methods: {
            backHome() {
                this.$router.push("/carMenu")
            }
        }
    }
</script>

<style scoped>

</style>