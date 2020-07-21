<template>
    <div class="userInfoAll" v-title data-title="用户管理" >
        <el-table
                :data="tableData"
                border
                style="width: 100%">
            <el-table-column
                    fixed
                    prop="id"
                    label="用户编号"
                    width="150">
            </el-table-column>
            <el-table-column
                    label="头像"
                    prop="src"
                    width="120"
            >
                <template slot-scope="scope">
                    <el-image :src="scope.row.src" :preview-src-list="[scope.row.srcList]" style="width: 60px;height: 60px"/>
                </template>
            </el-table-column>
            <el-table-column
                    prop="username"
                    label="用户姓名"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="age"
                    label="年龄"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="gender"
                    label="性别"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="address"
                    label="家庭住址"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="userPhone"
                    label="手机号码"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="likeModel"
                    label="喜欢的车"
                    width="120">
            </el-table-column>

            <el-table-column
                    fixed="right"
                    label="操作"
                    width="120">
                <template slot-scope="scope">
                    <el-button @click="deleteById(scope.row)" type="danger" size="mini">删除</el-button>
                    <el-button @click="edit(scope.row)" type="text" size="mini">编辑</el-button>
                </template>
            </el-table-column>
        </el-table>

        <el-pagination
                background
                layout="prev, pager, next"
                page-size="6"
                :total="total"
                style="margin-top: 10px"
                @current-change="page">
        </el-pagination>
    </div>
</template>

<script>
    export default {
        //汽车详情
        name: "userInfoAll",
        inject: ['reload'],
        data() {
            return {
                tableData: '',
                total: '',
                value: '',

            }
        },
        created() {
            const _this = this
            axios.get("http://39.106.154.21:8181/account/findAllUser/1/6").then(function (resp) {
                _this.tableData = resp.data.data.content
                _this.total = resp.data.data.totalElements
            })


        },
        methods: {
            page(currentPage) {
                const _this = this
                axios.get("http://39.106.154.21:8181/account/findAllUser/" + currentPage + "/6").then(function (resp) {
                    _this.tableData = resp.data.data.content
                    _this.total = resp.data.data.totalElements
                })
            },
            deleteById(row) {
                const _this = this
                if (confirm('确认要删除吗') ===true) {
                    axios.delete("http://39.106.154.21:8181/account/deleteUser/" + row.id).then(function (resp) {
                        if (resp.data.code === 0) {
                            _this.$message.success("删除成功")
                            _this.reload()
                        }
                    })
                }
            },
            edit(row) {
                const _this = this;
                _this.$router.push({
                    path: '/userEdit',
                    query: {
                        id: row.id
                    }
                })
            }
        }
    }
</script>

<style scoped>
    .userInfoAll{
        margin-left: 20px;
        width: 1120px;
    }
</style>