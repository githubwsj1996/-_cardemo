<template>
    <el-container style="height: 100%; border: 1px solid #eee">

        <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
            <a  href="http://39.106.154.21:8080/carManage" style="font-size: 25px;color: #42b983;line-height: 60px;text-align: center; text-decoration: none;" >Car后台管理系统</a>
            <!--   默认打开导航 :default-openeds="['1']"  默认打开子菜单:default-active="'1-1'"-->
            <el-menu router :default-openeds="['8']">
                <el-submenu v-for="(item,index) in $router.options.routes"   :index="index+''" v-if="item.show"  >
                    <template slot="title"><i :class="item.iconName"></i>{{item.name}}</template>
                    <el-menu-item v-for="(item2) in item.children" :index="item2.path"
                                  :class="$route.path===item2.path?'is-active':''">
                        {{item2.name}}
                    </el-menu-item>
                </el-submenu>
            </el-menu>
        </el-aside>

        <el-container>
            <el-header style="text-align: right; font-size: 18px">
                <div style="display: inline;width: 200px">
                    <el-avatar :size="40" :src=admin.src @error="errorHandler" style="margin-right: 10px;margin-top: 10px">
                        <img src="https://cube.elemecdn.com/e/fd/0fc7d20532fdaf769a25683617711png.png"/>
                    </el-avatar>
                      <div style="display: inline"> <el-link @click="adminEdit" v-text="admin.username"  type="info" style="font-size: 18px;color: azure" /></div>
                </div>
                <a @click="logOut" style="display: inline;margin-left: 20px;color: red;cursor: pointer;">注销</a>
            </el-header>

            <el-main>
                <router-view></router-view>
            </el-main>
        </el-container>

    </el-container>
</template>

<script>
    export default {
        data(){
            return{
                admin:''
            }
        },
        created() {
            this.admin=JSON.parse(sessionStorage.getItem("admin"));
            if(this.admin==null){
                this.$router.push("/")
            }
        },
        methods:{
            //错误头像
            errorHandler() {
                return true
            },
            logOut(){
                sessionStorage.removeItem("admin");
                this.$router.push("/");
            },
            adminEdit(){
                this.$router.push("/adminEdit")
            }
        }
    }

</script>

<style>
    .el-header {
        background-color: #42b983;
        color: #333;
        line-height: 80px;
    }

    .el-aside {
        color: #333;
    }


</style>