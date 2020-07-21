<template>
    <div class="carMenu" style="width: 100%;height: 100%" v-title data-title="用户首页" >
        <div class="top">
            <div class="car_user" style="width:400px;float: right ;margin-top: 20px;margin-left: 20px">
                <el-avatar :size="40" :src=user.src @error="errorHandler"
                           style="width:40px;height:40px;margin-right: 10px;margin-top: 10px">
                    <img src="https://cube.elemecdn.com/e/fd/0fc7d20532fdaf769a25683617711png.png"/>
                </el-avatar>
                <el-link @click="editUser" type="primary" v-text="user.username"
                         style="margin-right:20px;font-size: 18px;" :underline="false"></el-link>
                <i class="el-icon-shopping-cart-2" style="width: 20px"></i>
                <el-link @click="userOrder" type="success" style="margin-right:20px;font-size: 18px" :underline="false">
                    我的订单
                </el-link>

                <i class="el-icon-refresh-left" style="width: 20px"></i>
                <el-link @click="logOut" type="warning" style="font-size: 18px" :underline="false">
                    注销
                </el-link>
            </div>
            <div class="carousel" style="width: 400px;">
                <el-carousel :interval="2000" type="card" height="110px" autoplay="true">
                    <el-carousel-item v-for="item in carouselList" :key="item.carId" style="text-align: center">

                        <el-tooltip class="item" effect="dark" :content=item.carName
                                    placement="top-end">
                            <img @click="carouselClick(item.carId)" :src="item.src" style="width: 200px;height: 110px">
                        </el-tooltip>
                    </el-carousel-item>
                </el-carousel>
            </div>
        </div>
        <el-dialog
                title="汽车信息"
                :visible.sync="centerDialogVisible"
                width="40%"
                center
                append-to-body="true"
                style="background-image: url('../../public/static/images/menu-bg.jpg')"
        >

            <el-form :model="carouselForm">
                <el-form-item label="汽车名称">
                    <span>{{carouselForm.carName}}</span>
                </el-form-item>
                <el-form-item label="汽车图片">
                    <el-image
                            style="width: 100px; height: 100px"
                            :src=carouselForm.src
                            :preview-src-list=[carouselForm.srcList]>
                    </el-image>
                </el-form-item>
                <el-form-item label="生产制造">
                    <span>{{ carouselForm.carFactory }}</span>
                </el-form-item>
                <el-form-item label="汽车 ID">
                    <span>{{ carouselForm.carId }}</span>
                </el-form-item>
                <el-form-item label="汽车排量">
                    <span>{{ carouselForm.displacement}}</span>
                </el-form-item>
                <el-form-item label="汽车公里数">
                    <span>{{ carouselForm.kilometers}}</span>
                </el-form-item>
                <el-form-item label="油耗">
                    <span>{{ carouselForm.oliConsumption}}</span>
                </el-form-item>
                <el-form-item label="商品描述">
                    <span>{{ carouselForm.carDesc }}</span>
                </el-form-item>
                <el-form-item label="租赁价格">
                    <span>{{ carouselForm.carPrice+"元/天"}}</span>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
        <el-button type="primary" @click="centerDialogVisible = false">关闭</el-button>
        </span>
        </el-dialog>

        <div class="main">
            <el-form class="search" :model="searchForm" :rules="rules" ref="searchForm"
                     label-position="left">
                <el-form-item prop="name">
                    <el-input v-model="searchForm.name" placeholder="请输入汽车名称" prefix-icon="el-icon-search"
                              style="width: 400px;" auto-complete="off"></el-input>
                    <el-button type="primary" @click="search" icon="el-icon-search" style="margin-left: 20px">搜索
                    </el-button>

                    <el-select @change="onClick" v-model="value" placeholder="汽车分类" style="margin-left: 60px">
                        <el-option
                                v-for="item in categories"
                                :key="item.categoryId"
                                :label="item.categoryName"
                                :value="item.categoryId"
                        >
                        </el-option>
                    </el-select>
                </el-form-item>
            </el-form>

            <el-table
                    :data="tableData"
                    style="width:800px">
                <el-table-column type="expand" highlight-current-row="true">
                    <template slot-scope="props">
                        <el-form label-position="left" inline class="demo-table-expand">
                            <el-form-item label="汽车名称">
                                <span>{{ props.row.carName }}</span>
                            </el-form-item>
                            <el-form-item label="汽车图片">
                                <el-image
                                        style="width: 100px; height: 100px"
                                        :src=props.row.src
                                        :preview-src-list=[props.row.srcList]>
                                </el-image>
                            </el-form-item>
                            <el-form-item label="生产制造">
                                <span>{{ props.row.carFactory }}</span>
                            </el-form-item>
                            <el-form-item label="汽车 ID">
                                <span>{{ props.row.carId }}</span>
                            </el-form-item>
                            <el-form-item label="汽车排量">
                                <span>{{ props.row.displacement}}</span>
                            </el-form-item>
                            <el-form-item label="汽车公里数">
                                <span>{{ props.row.kilometers}}</span>
                            </el-form-item>
                            <el-form-item label="油耗">
                                <span>{{ props.row.oliConsumption}}</span>
                            </el-form-item>
                            <el-form-item label="商品描述">
                                <span>{{ props.row.carDesc }}</span>
                            </el-form-item>
                            <el-form-item label="租赁价格">
                                <span>{{ props.row.carPrice+"元/天"}}</span>
                            </el-form-item>
                            <el-form-item label="租赁天数" style="width: 600px">
                                <el-input-number v-model="num" :min="1" :max="100"></el-input-number>
                            </el-form-item>
                            <el-form-item label="租赁总价">
                                <span>{{ props.row.carPrice*num+"元"}}</span>
                            </el-form-item>
                        </el-form>
                    </template>
                </el-table-column>

                <el-table-column
                        label="汽车 ID"
                        prop="carId">
                </el-table-column>
                <el-table-column
                        label="汽车名称"
                        prop="carName">
                </el-table-column>
                <el-table-column
                        label="汽车描述"
                        prop="carDesc" width="200">
                </el-table-column>
                <el-table-column
                        label="租赁价格(元/天)"
                        prop="carPrice">
                </el-table-column>
                <el-table-column
                        fixed="right"
                        label="操作"
                        width="100">
                    <template slot-scope="scope">
                        <el-button @click="handleClick(scope.row)" type="success">租赁</el-button>
                    </template>
                </el-table-column>
            </el-table>

            <div v-if="display">
                <el-pagination
                        background
                        layout="prev, pager, next"
                        page-size="8"
                        :total="total"
                        style="margin-left: 120px;margin-top: 10px"
                        @current-change="page">
                </el-pagination>
            </div>
        </div>
    </div>

</template>


<script>
    export default {
        inject: ['reload'],
        data() {
            //特殊字符验证
            var pattern = new RegExp(
                "[`~!@#$^&*()=|{}':;',\\[\\].<>《》/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？]"
            );
            var checkUsername = (rule, value, callback) => {
                if (pattern.test(this.searchForm.name)) {
                    callback(new Error("汽车名称不能包含特殊字符"));
                } else {
                    callback();
                }
            }
            return {
                carName: '',
                total: '',
                tableData: null,
                num: 1,
                value: '',
                onPage: '',
                user: JSON.parse(sessionStorage.getItem("user")),
                categories: '',
                searchForm: {
                    name: ''
                },
                rules: {
                    name: [
                        {validator: checkUsername, trigger: 'blur'}
                    ],
                },
                display: true,
                carouselList: '',
                centerDialogVisible: false,
                carouselForm: {
                    carName: '',
                    categoryType: '',
                    carFactory: '',
                    src: '',
                    srcList: '',
                    displacement: '',
                    kilometers: '',
                    oliConsumption: '',
                    carDesc: '',
                    carPrice: ''
                }
            }
        },
        created() {
            if (this.user == null) {
                this.$router.push("/")
            }
            const _this = this
            //首页加载
            axios.get("http://39.106.154.21:8181/car/findAll/1/8").then(function (resp) {
                _this.tableData = resp.data.data.content
                _this.total = resp.data.data.totalElements
            });

            //分类查询，全部
            axios.get("http://39.106.154.21:8181/category/findAll").then(function (resp) {
                _this.categories = resp.data.data
            })

            //走马灯图片加载
            axios.get("http://39.106.154.21:8181/car/carousel").then(function (resp) {
                _this.carouselList = resp.data.data
            })

        },
        methods: {
            //分页
            page(currentPage) {
                const _this = this
                if (this.onPage === "" || this.onPage === 5)
                    axios.get('http://39.106.154.21:8181/car/findAll/' + currentPage + '/8').then(function (resp) {
                        _this.tableData = resp.data.data.content
                        _this.total = resp.data.data.totalElements
                    })
                else {
                    axios.get("http://39.106.154.21:8181/car/findByType/" + this.onPage + "/" + currentPage + "/8").then(function (resp) {
                        _this.tableData = resp.data.data.content
                        _this.total = resp.data.data.totalElements
                    });
                }
            },
            //分类
            onClick(index) {
                this.display = true
                this.onPage = index
                const _this = this
                if (index === 5) {
                    axios.get("http://39.106.154.21:8181/car/findAll/1/8").then(function (resp) {
                        _this.tableData = resp.data.data.content
                        _this.total = resp.data.data.totalElements
                    });
                }

                axios.get("http://39.106.154.21:8181/car/findByType/" + index + "/1/8").then(function (resp) {
                    _this.tableData = resp.data.data.content
                    _this.total = resp.data.data.totalElements
                });

            },
            //车名查询
            search() {
                const _this = this
                axios.get("http://39.106.154.21:8181/car/findByName/" + _this.searchForm.name).then(function (response) {
                    if (response.data.data.length === 0) {
                        _this.$message.error("不好意思,该车辆不存在")
                        _this.reload()
                    }
                    _this.tableData = response.data.data
                    _this.display = false
                })
                this.searchForm.name = null
            },
            //订单页面传值
            handleClick(row) {
                let order = {
                    //orderId: "10123123", //订单编号
                    username: this.user.username, // 用户姓名
                    carId: row.carId,    //汽车Id
                    carName: row.carName,  //汽车名称
                    carImg: row.src,//汽车图片
                    carPrice: row.carPrice,//租赁价格
                    days: this.num,//租赁天数
                    totalPrice: row.carPrice * (this.num), //租赁总价
                }
                this.$router.push({
                    path: "/orderDetail",
                    query: {
                        order: order
                    }
                })
            },
            //错误头像
            errorHandler() {
                return true
            },
            //修改用户信息
            editUser() {
                this.$router.push("/userInfo")
            },
            //用户查看订单
            userOrder() {
                let id = this.user.id;
                this.$router.push({
                    path: "/userOrder",
                    query: {
                        id: id
                    }
                })
            },
            //退出
            logOut() {
                sessionStorage.removeItem("user")
                this.$router.push("/");
            },
            //走马灯图片
            carouselClick(carId) {
                this.centerDialogVisible = true
                const _this = this
                axios.get("http://39.106.154.21:8181/car/carousel/" + carId).then(function (resp) {
                    _this.carouselForm = resp.data.data
                })
            }
        }
    }
</script>
<style>
    .carMenu {
        background: url("../../public/static/images/menu-bg.jpg") no-repeat fixed;
        width: 840px;height: 513px;
        background-size: 100% 100%;
    }

    .main {
        width: 800px;
        margin: 0 auto;
    }

    .top {
        width: 800px;
        margin: 0 auto;
    }

    .demo-table-expand {
        font-size: 12px;
    }

    .demo-table-expand label {
        width: 90px;
        color: #99a9bf;
    }

    .demo-table-expand .el-form-item {
        margin-right: 0;
        margin-bottom: 0;
        width: 50%;
    }


    .el-table .cell {
        text-align: center;
    }


    .el-form--inline .el-form-item {
        margin-right: 10px;
        vertical-align: top;
    }

    .el-carousel__item h3 {
        color: #475669;
        font-size: 14px;
        opacity: 0.75;
        line-height: 200px;
        margin: 0;
    }


    .top {
        text-align: center;
    }


    .item {
        margin: 4px;
    }

    .left .el-tooltip__popper,
    .right .el-tooltip__popper {
        padding: 8px 10px;
    }


</style>