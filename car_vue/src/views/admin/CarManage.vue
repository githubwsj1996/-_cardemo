<template>
    <div v-title data-title="汽车管理" >
        <el-table
                :data="tableData"
                border
                style="width: 100%">
            <el-table-column
                    fixed
                    prop="carId"
                    label="编号"
                    width="150">
            </el-table-column>
            <el-table-column
                    prop="carName"
                    label="汽车名称"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="categoryName"
                    label="分类"
                    width="120">
            </el-table-column>
            <el-table-column
                    label="图片"
                    prop="src"
                    width="120"
            >
                <template slot-scope="scope">
                    <el-image :src="scope.row.src" :preview-src-list="[scope.row.srcList]"/>
                </template>
            </el-table-column>
            <el-table-column
                    prop="carFactory"
                    label="厂商"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="displacement"
                    label="排量"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="kilometers"
                    label="公里数"
                    width="120">
            </el-table-column>
            <el-table-column
                    prop="oliConsumption"
                    label="油耗"
                    width="120">
            </el-table-column>

            <el-table-column
                    prop="carDesc"
                    label="描述"
                    width="120">
            </el-table-column>

            <el-table-column
                    prop="carPrice"
                    label="价格"
                    width="120">
            </el-table-column>

            <el-table-column
                    prop="createTime"
                    label="创建时间"
                    width="106">
            </el-table-column>

            <el-table-column
                    prop="updateTime"
                    label="修改时间"
                    width="106">
            </el-table-column>

            <el-table-column
                    fixed="right"
                    label="操作"
                    width="130">
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
                @current-change="page">
        </el-pagination>
    </div>
</template>

<script>
    export default {
        //汽车详情
        name: "CarManage",
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
            axios.get("http://39.106.154.21:8181/car/findCarManage/1/6").then(function (resp) {
                _this.tableData = resp.data.data.carVOList
                _this.total = resp.data.data.counts
            })


        },
        methods: {
            page(currentPage) {
                const _this = this
                axios.get("http://39.106.154.21:8181/car/findCarManage/" + currentPage + "/6").then(function (resp) {
                    _this.tableData = resp.data.data.carVOList
                    _this.total = resp.data.data.counts

                })
            },
            deleteById(row) {
                const _this = this
                if (confirm('确认要删除吗') ===true) {
                    axios.delete("http://39.106.154.21:8181/car/deleteByCarId/" + row.carId).then(function (resp) {
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
                    path: '/carEdit',
                    query: {
                        id: row.carId
                    }
                })
            }
        }
    }
</script>

<style>

</style>


