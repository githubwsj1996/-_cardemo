<template>
    <div v-title data-title="汽车编辑" >
        <el-page-header @back="goBack" content="汽车信息修改" style="color:deepskyblue;margin-left: 200px;margin-top: 10px"/>

        <el-form :model="carForm" :rules="rules" ref="carForm" label-width="100px" class="demo-carForm">
            <el-form-item label="汽车编号" prop="carId">
                <el-input v-model="carForm.carId"  readonly></el-input>
            </el-form-item>

            <el-form-item label="汽车名称" prop="carName">
                <el-input v-model="carForm.carName" placeholder="请填写汽车名称"></el-input>
            </el-form-item>

            <el-form-item label="汽车分类" prop="categoryType">
                <el-select v-model="carForm.categoryType" placeholder="请选择活动区域">
                    <el-option
                            v-for="item in categories"
                            :key="item.categoryId"
                            :label="item.categoryName"
                            :value="item.categoryId"
                    ></el-option>
                </el-select>
            </el-form-item>

            <el-form-item label="汽车头像" prop="src">
                <el-upload
                        class="avatar-uploader"
                        action="http://39.106.154.21:8181/car/toUpload"
                        :show-file-list="true"
                        accept=".jpg,.png"
                        :on-success="handleAvatarSuccess"
                        :before-upload="beforeAvatarUpload"
                >
                    <img v-if="imageUrl" :src="imageUrl" class="avatar">
                    <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                </el-upload>
            </el-form-item>

            <el-form-item label="汽车大图" prop="srcList">
                <el-upload
                        class="avatar-uploader"
                        action="http://39.106.154.21:8181/car/toUpload"
                        :show-file-list="true"
                        accept=".jpg,.png"
                        :on-success="handleAvatarSuccess1"
                        :before-upload="beforeAvatarUpload1"
                >
                    <img v-if="imageUrl1" :src="imageUrl1" class="avatar">
                    <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                </el-upload>
            </el-form-item>

            <el-form-item label="汽车厂商" prop="carFactory">
                <el-input v-model="carForm.carFactory" placeholder="请填写汽车厂商"></el-input>
            </el-form-item>

            <el-form-item label="排量" prop="displacement">
                <el-radio-group v-model="carForm.displacement">
                    <el-radio label="1.8T"></el-radio>
                    <el-radio label="2.0T"></el-radio>
                    <el-radio label="3.0T"></el-radio>
                    <el-radio label="5.0T"></el-radio>
                </el-radio-group>
            </el-form-item>

            <el-form-item label="公里数" prop="kilometers">
                <el-input v-model="carForm.kilometers" placeholder="请填写汽车公里数"></el-input>
            </el-form-item>

            <el-form-item label="油耗" prop="oliConsumption">
                <el-input v-model="carForm.oliConsumption" placeholder="请填写汽车油耗"></el-input>
            </el-form-item>

            <el-form-item label="租赁价格" prop="carPrice">
                <el-input v-model="carForm.carPrice" placeholder="请填写租赁价格"></el-input>
            </el-form-item>

            <el-form-item label="特点描述" prop="carDesc">
                <el-input type="textarea" v-model="carForm.carDesc" placeholder="请填写汽车描述"></el-input>
            </el-form-item>

            <el-form-item>
                <el-button type="primary" @click="submitForm('carForm')">保存</el-button>
                <el-button @click="goBack()">取消</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
    export default {
        name: "CarEdit",
        data() {
            return {
                carForm: {
                    carId:'',
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
                },
                rules: {
                    carName: [
                        {required: true, message: '请输入汽车名称', trigger: 'blur'},
                        {min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur'}
                    ],
                    categoryType: [
                        {required: true, message: '请选择汽车类别', trigger: 'change'}
                    ],
                    carFactory: [
                        {required: true, message: '请输入汽车厂商', trigger: 'blur'}
                    ],
                    // src: [
                    //     {required: true, message: '请选择汽车图片', trigger: 'blur'}
                    // ],
                    // srcList: [
                    //     {required: true, message: '请选择汽车大图', trigger: 'blur'}
                    // ],
                    displacement: [
                        {required: true, message: '请选择汽车排量', trigger: 'change'}
                    ],
                    kilometers: [
                        {required: true, message: '请输入汽车公里数', trigger: 'blur'}
                    ],
                    oliConsumption: [
                        {required: true, message: '请输入汽车油耗', trigger: 'blur'}
                    ],
                    carDesc: [
                        {required: true, message: '请输入汽车描述', trigger: 'blur'}
                    ],
                    carPrice: [
                        {required: true, message: '请输入租赁价格', trigger: 'blur'}
                    ]
                },
                imageUrl: '',
                imageUrl1: '',
                src: '',
                srcList: '',
                categories: '',
                createTime:'',
            }
        },
        created() {
            const _this=this
            let id = this.$route.query.id
            axios.get("http://39.106.154.21:8181/car/findByCarId/"+id).then(function (resp) {
                _this.carForm=resp.data.data
                _this.src=resp.data.data.src
                _this.srcList=resp.data.data.srcList
                _this.imageUrl=resp.data.data.src
                _this.imageUrl1=resp.data.data.srcList
                _this.createTime=resp.data.data.createTime
            })

            axios.get("http://39.106.154.21:8181/category/findAllNot5").then(function (resp) {
                _this.categories = resp.data.data
            })

        },
        methods: {
            handleAvatarSuccess(res, file) {
                this.imageUrl = URL.createObjectURL(file.raw);
                if(res.code===0){
                    this.$message.success("上传成功")
                    console.log(res.data)
                }
                else{
                    this.$message.error("上传失败")
                }
                console.log(res.data)
                this.src =  res.data
            },
            beforeAvatarUpload(file) {
                const isJPG = file.type === 'image/jpeg';
                const isLt2M = file.size / 1024 / 1024 < 2;

                if (!isJPG) {
                    this.$message.error('上传头像图片只能是 JPG 格式!');
                }
                if (!isLt2M) {
                    this.$message.error('上传头像图片大小不能超过 2MB!');
                }
                return isJPG && isLt2M;
            },
            handleAvatarSuccess1(res, file) {
                this.imageUrl1 = URL.createObjectURL(file.raw);
                if(res.code===0){
                    this.$message.success("上传成功")
                    console.log(res.data)
                }
                else{
                    this.$message.error("上传失败")
                }
                this.srcList =  res.data
            },
            beforeAvatarUpload1(file) {
                const isJPG = file.type === 'image/jpeg';
                const isLt2M = file.size / 1024 / 1024 < 2;

                if (!isJPG) {
                    this.$message.error('上传头像图片只能是 JPG 格式!');
                }
                if (!isLt2M) {
                    this.$message.error('上传头像图片大小不能超过 2MB!');
                }
                return isJPG && isLt2M;
            },
            submitForm(formName) {
                const _this = this;
                let carMenu = {
                    carId:_this.carForm.carId,
                    carName: _this.carForm.carName,
                    categoryType: _this.carForm.categoryType,
                    src: _this.src,
                    srcList: _this.srcList,
                    carFactory: _this.carForm.carFactory,
                    displacement: _this.carForm.displacement,
                    kilometers: _this.carForm.kilometers,
                    oliConsumption: _this.carForm.oliConsumption,
                    carDesc: _this.carForm.carDesc,
                    carPrice: _this.carForm.carPrice,
                    createTime: _this.createTime
                }
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        axios.post("http://39.106.154.21:8181/car/saveCar", carMenu).then(function (resp) {
                            if (resp.data.code === 0) {
                                if (resp.data.code === 0) {
                                    _this.$alert('修改车辆=========>成功!', {
                                        confirmButtonText: '确定',
                                        callback: action => {
                                            _this.$router.push("/carManage");
                                        }
                                    });
                                }
                            }
                            else{
                               _this.$message.error("数据有误，添加失败");
                            }
                        })
                    } else {
                        alert('数据有误，添加失败');
                        return false;
                    }
                });
            },
            goBack() {
                history.go(-1)
            }
        }
    }
</script>

<style scoped>
    .demo-carForm {
        border-radius: 15px;
        background-clip: padding-box;
        margin: 0px auto;
        width: 600px;
        padding: 35px 35px 15px 35px;
        background: #fff;
        border: 0px solid #eaeaea;
    }

    .avatar-uploader {
        border: 1px dashed #d9d9d9;
        border-radius: 6px;
        cursor: pointer;
        position: relative;
        overflow: hidden;
        width: 178px;
    }

    .avatar-uploader:hover {
        border-color: #409EFF;
    }

    .avatar-uploader-icon {
        font-size: 28px;
        color: #8c939d;
        width: 178px;
        height: 178px;
        line-height: 178px;
        text-align: center;
    }

    .avatar {
        width: 178px;
        height: 178px;
        display: block;
    }
</style>