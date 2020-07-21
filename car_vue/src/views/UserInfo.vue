<template>
    <div class="userInfo" v-title data-title="用户信息" >
        <div class="main">
            <el-form class="userForm" ref="userForm" :model="userForm" :rules="rules" status-icon
                     label-width="80px" style="width: 100%">
                <el-page-header @back="goBack" style="color:deepskyblue;"/>
                <h2 style=" width:100px;margin: 0 auto">个人信息</h2>

                <el-form-item label="用户编号" prsop="id" style="width: 200px;">
                    <el-input v-model="userForm.id" style="color: #42b983" readonly></el-input>
                </el-form-item>

                <el-form-item label="用户名称" prop="username" style="width: 400px;">
                    <el-input @blur="nameNews" v-model="userForm.username" autocomplete="off" :disabled=disabled
                              style="color: #42b983"></el-input>
                </el-form-item>

                <el-form-item label="头像" prop="src">
                    <el-upload
                            class="avatar-uploader"
                            action="http://39.106.154.21:8181/account/uploadHeadImg"
                            :show-file-list="true"
                            accept=".jpg,.png"
                            :on-success="handleAvatarSuccess"
                            :before-upload="beforeAvatarUpload"
                    >
                        <img v-if="imageUrl" :src="imageUrl" class="avatar">
                        <i v-else class="el-icon-plus avatar-uploader-icon"></i>
                    </el-upload>
                </el-form-item>

                <el-form-item label="密码" prop="password" style="width: 400px;">
                    <el-input type="password" v-model="userForm.password" autocomplete="off"
                              :disabled=disabled  clearable="true" @change="editPass"></el-input>
                </el-form-item>

                <el-form-item label="确认密码" prop="checkPassword" style="width: 400px;">
                    <el-input type="password" v-model="userForm.checkPassword" autocomplete="off"
                              :disabled=disabled clearable="true" ></el-input>
                </el-form-item>

                <el-form-item label="年龄" prop="age" style="width: 400px;">
                    <el-input v-model.number="userForm.age" :disabled=disabled></el-input>
                </el-form-item>

                <el-form-item label="性别" prop="gender" style="width: 400px;">
                    <el-radio-group v-model="userForm.gender">
                        <el-radio label="1">男</el-radio>
                        <el-radio label="0">女</el-radio>
                    </el-radio-group>
                </el-form-item>

                <el-form-item label="地址" prop="address" style="width: 400px;">
                    <el-input v-model="userForm.address" :disabled=disabled></el-input>
                </el-form-item>

                <el-form-item label="手机号码" prop="userPhone" style="width: 400px;">
                    <el-input v-model="userForm.userPhone" :disabled=disabled></el-input>
                </el-form-item>

                <el-form-item label="最爱车型" prop="likeModel" style="width: 400px;">
                    <el-input v-model="userForm.likeModel" :disabled=disabled></el-input>
                </el-form-item>

                <el-form-item>
                    <el-button type="primary" @click="submitForm('userForm')">保存</el-button>
                    <el-button @click="goBack">取消</el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>

<script>
    export default {
        name: "UserInfo",
        inject:['reload'],
        data() {
            //特殊字符验证
            var pattern = new RegExp(
                "[`~!@#$^&*()=|{}':;',\\[\\].<>《》/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？]"
            );
            var checkUsername = (rule, value, callback) => {
                if (!value) {
                    return callback(new Error('用户名不能为空'));
                } else if (value.length < 3) {
                    callback(new Error('用户名最少为3个字符'));
                } else if (pattern.test(this.userForm.username)) {
                    callback(new Error("用户名不能包含特殊字符"));
                } else {
                    callback();
                }
            }

            //年龄超时，长度验证
            var checkAge = (rule, value, callback) => {
                setTimeout(() => {
                    if (!Number.isInteger(value)) {
                        callback(new Error('请输入数字值'));
                    } else {
                        if (value < 18) {
                            callback(new Error('必须年满18岁'));
                        } else {
                            callback();
                        }
                    }
                }, 500);
            };

            //密码验证
            var validatePass = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请输入密码'));
                } else if (value.length < 6) {
                    callback(new Error('密码最少为6位'));
                } else {
                    callback();
                }
            };

            //二次密码验证
            var validatePass2 = (rule, value, callback) => {
                if (this.userForm.checkPassword === '') {
                    callback(new Error('请再次输入密码'));
                } else if (this.userForm.checkPassword.length < 6) {
                    callback(new Error('密码最少为6位'));
                } else if (this.userForm.checkPassword !== this.userForm.password) {
                    callback(new Error('两次输入密码不一致!'));
                } else {
                    callback();
                }
            };

            //手机号验证
            var validateNum = (rule, value, callback) => {
                var re = /^1\d{10}$/
                // if (!re.test(value)) {
                //     callback(new Error('手机号码不符合规范'))
                // }
                if (!/^[0-9]+$/.test(this.userForm.userPhone) || !re.test(value)) {
                    callback(new Error('手机号码不符合规范'))
                } else {
                    callback();
                }

            }

            //地址
            var address = (rule, value, callback) => {
                if (!value) {
                    return callback(new Error('地址不能为空'));
                } else {
                    callback();
                }
            }

            //车型
            var likeModel = (rule, value, callback) => {
                if (!value) {
                    return callback(new Error('喜爱车型不能为空'));
                } else {
                    callback();
                }
            }

            return {
                userForm: {
                    id: '',
                    username: '',
                    password: '',
                    checkPassword: '',
                    age: '',
                    gender: '',
                    address: '',
                    userPhone: '',
                    likeModel: ''
                },
                rules: {
                    username: [
                        {required: true, message: '请输入姓名', trigger: 'blur'},
                        {validator: checkUsername, trigger: 'blur'}
                    ],
                    password: [
                        {required: true, message: '请输入密码', trigger: 'blur'},
                        {validator: validatePass, trigger: 'blur'}
                    ],
                    checkPassword: [
                        {required: true, message: '请输入确认密码', trigger: 'blur'},
                        {validator: validatePass2, trigger: 'blur'}
                    ],
                    age: [
                        {required: true, message: '请输入年龄', trigger: 'blur'},
                        {validator: checkAge, trigger: 'blur'}
                    ],
                    gender: [
                        {required: true, message: '请选择性别', trigger: 'blur'},
                    ],
                    address: [
                        {required: true, message: '请输入地址', trigger: 'blur'},
                        {validator: address, trigger: 'blur'}
                    ],
                    userPhone: [
                        {required: true, message: '请输入手机号码', trigger: 'blur'},
                        {validator: validateNum, trigger: 'blur'}
                    ],
                    likeModel: [
                        {required: true, message: '请输入喜爱车型', trigger: 'blur'},
                        {validator: likeModel, trigger: 'blur'}
                    ]
                },
                imageUrl:'',
                src:'',
                srcList:'',
                user:JSON.parse(sessionStorage.getItem("user"))
            }
        },
        created() {
            const _this = this;
            axios.get("http://39.106.154.21:8181/account/getUser/" +_this.user.id).then(function (resp) {
                _this.userForm = resp.data.data
                _this.src=resp.data.data.src
                _this.srcList=resp.data.data.srcList
                _this.imageUrl=resp.data.data.src
            })

        },
        methods: {
            nameNews() {
                const _this = this;
                axios.get("http://39.106.154.21:8181/account/newName/" + this.userForm.username).then(function (resp) {
                    if (resp.data.code===0) {
                        let message ='用户名已存在'
                        _this.disabled = true;
                        if (resp.data.data != null) {
                            _this.$alert(message + ",是否重新填写", {
                                confirmButtonText: '确定',
                                callback: action => {
                                    _this.reload()
                                }
                            });
                        }
                    }
                })
            },
            handleAvatarSuccess(res, file) {
                this.imageUrl = URL.createObjectURL(file.raw);
                if(res.code===0){
                    this.$message.success("上传成功")
                    console.log(res.data)
                }
                else{
                    this.$message.error("上传失败")
                }
                this.src =  res.data
                this.srcList =  res.data
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
            submitForm(formName) {
                let user = {
                    id:this.userForm.id,
                    username: this.userForm.username,
                    password: this.userForm.password,
                    checkPassword:this.userForm.checkPassword,
                    age: this.userForm.age,
                    gender: this.userForm.gender,
                    address: this.userForm.address,
                    userPhone: this.userForm.userPhone,
                    likeModel: this.userForm.likeModel,
                    src: this.src,
                    srcList: this.srcList
                }
                const _this = this;
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        axios.post("http://39.106.154.21:8181/account/saveUser",user).then(function (resp) {
                            if (resp.data.code === 0) {
                                _this.$alert('保存成功', {
                                    confirmButtonText: '确定',
                                    callback: action => {
                                        _this.$router.push("/");
                                        sessionStorage.removeItem("user")
                                    }
                                });
                            }
                        })
                    } else {
                        _this.$message.error("信息有误，请重新填写");
                        return false;
                    }
                });
            },
            goBack() {
                history.go(-1);
            },
            editPass(){
                this.userForm.checkPassword=""
            }



        }
    }
</script>

<style scoped>
    .userInfo {
        background: url("../../public/static/images/bg.png");
        background-size: 100%;
    }

    .main {
        width: 600px;
        margin: 0 auto;
    }

    .userForm {
        border-radius: 15px;
        margin: 0px auto;
        width: 350px;
        padding: 35px 35px 15px 35px;
        background: #fff;
        border: 1px solid #eaeaea;
        box-shadow: 0 0 25px #cac6c6;
    }
</style>