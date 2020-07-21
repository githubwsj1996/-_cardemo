<template>
    <div id="error" v-title data-title="走开了..." >

        <el-button class="back_login" type="warning" round @click="back_login">返回登录</el-button>

        <el-button class="back_login"  type="primary" round @click="goBack">返回上一页</el-button>

    </div>
</template>

<script>
    export default {
        name: "ErrorInfo",
        methods:{
            back_login(){
                this.$router.push("/")
            },
            goBack(){
                history.go(-1)
            }

        }
    }
</script>

<style scoped>
    #error{
        width: 100%;
        height: 722px;
        background: url("../../../public/static/images/404.jpg") no-repeat fixed;
        background-size: 100% 100%;
    }

    .back_login{
        width: 130px;
        height: 40px;
        margin-top: 10px;
    }
</style>