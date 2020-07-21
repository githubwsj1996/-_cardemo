<template>
    <div v-title data-title="添加汽车" >
        <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
            <el-form-item label="汽车名称" prop="carName">
                <el-input v-model="ruleForm.carName" placeholder="请填写汽车名称"></el-input>
            </el-form-item>

            <el-form-item label="汽车分类" prop="categoryType">
                <el-select v-model="ruleForm.categoryType" placeholder="请选择活动区域">
                    <el-option
                            v-for="item in categories"
                            :key="item.categoryId"
                            :label="item.categoryName"
                            :value="item.categoryId"
                    ></el-option>
                </el-select>
            </el-form-item>

            <el-form-item label="汽车头像" prop="src" >
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

            <el-form-item label="汽车大图" prop="srcList" >
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
                <el-input v-model="ruleForm.carFactory" placeholder="请填写汽车厂商"></el-input>
            </el-form-item>

            <el-form-item label="排量" prop="displacement">
                <el-radio-group v-model="ruleForm.displacement">
                    <el-radio label="1.8T"></el-radio>
                    <el-radio label="2.0T"></el-radio>
                    <el-radio label="3.0T"></el-radio>
                    <el-radio label="5.0T"></el-radio>
                </el-radio-group>
            </el-form-item>

            <el-form-item label="公里数" prop="kilometers">
                <el-input v-model="ruleForm.kilometers" placeholder="请填写汽车公里数"></el-input>
            </el-form-item>

            <el-form-item label="油耗" prop="oliConsumption">
                <el-input v-model="ruleForm.oliConsumption" placeholder="请填写汽车油耗"></el-input>
            </el-form-item>

            <el-form-item label="租赁价格" prop="carPrice">
                <el-input v-model="ruleForm.carPrice" placeholder="请填写租赁价格"></el-input>
            </el-form-item>

            <el-form-item label="特点描述" prop="carDesc">
                <el-input type="textarea" v-model="ruleForm.carDesc" placeholder="请填写汽车描述"></el-input>
            </el-form-item>

            <el-form-item>
                <el-button type="primary" @click="submitForm('ruleForm')">添加</el-button>
                <el-button @click="resetForm('ruleForm')">重置</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                ruleForm: {
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
                src:'',
                srcList:'',
                categories:''
            }
        },
        created() {
            const _this=this
            axios.get("http://39.106.154.21:8181/category/findAllNot5").then(function (resp) {
                _this.categories =  resp.data.data
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
                this.src=res.data
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

                this.srcList=res.data
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
                const  _this=this;
                let carMenus={
                    carName: _this.ruleForm.carName,
                    categoryType: _this.ruleForm.categoryType,
                    src:  _this.src,
                    srcList:  _this.srcList,
                    carFactory:  _this.ruleForm.carFactory,
                    displacement: _this.ruleForm.displacement,
                    kilometers:  _this.ruleForm.kilometers,
                    oliConsumption:  _this.ruleForm.oliConsumption,
                    carDesc:  _this.ruleForm.carDesc,
                    carPrice: _this.ruleForm.carPrice,
                }
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        axios.post("http://39.106.154.21:8181/car/saveCar",carMenus).then(function (resp) {
                                if(resp.data.code===0){
                                    if (resp.data.code === 0) {
                                        _this.$alert('添加车辆=========>成功!', {
                                            confirmButtonText: '确定',
                                            callback: action => {
                                                _this.$router.push("/carManage")
                                            }
                                        });
                                    }
                                    else{
                                        _this.$message.error("数据有误，添加失败");
                                    }
                                }
                        })
                    } else {
                        alert('数据有误，添加失败');
                        return false;
                    }
                });
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            },
        }
    }
</script>

<style scoped>
    .demo-ruleForm {
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