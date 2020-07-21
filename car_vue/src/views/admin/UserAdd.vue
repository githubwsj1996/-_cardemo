<template>
    <div v-title data-title="添加用户" >
        <el-form class="userAddForm" :model="userAddForm" status-icon
                 :rules="rules" ref="userAddForm" label-width="100px">
            <el-form-item label="用户名" prop="username">
                <el-input @change="nameNew" v-model="userAddForm.username" autocomplete="off"
                          :disabled=disabled></el-input>
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

            <el-form-item label="年龄" prop="age">
                <el-input v-model.number="userAddForm.age" :disabled=disabled></el-input>
            </el-form-item>

            <el-form-item label="性别" prop="gender" style="width: 400px;">
                <el-radio-group v-model="userAddForm.gender">
                    <el-radio label="1">男</el-radio>
                    <el-radio label="0">女</el-radio>
                </el-radio-group>
            </el-form-item>

            <el-form-item label="地址" prop="address">
                <el-input v-model.number="userAddForm.address" :disabled=disabled></el-input>
            </el-form-item>

            <el-form-item label="手机号码" prop="userPhone">
                <el-input v-model.number="userAddForm.userPhone" :disabled=disabled></el-input>
            </el-form-item>

            <el-form-item label="密码" prop="password">
                <el-input type="password" v-model="userAddForm.password" autocomplete="off" :disabled=disabled></el-input>
            </el-form-item>

            <el-form-item label="确认密码" prop="checkPass">
                <el-input type="password" v-model="userAddForm.checkPass" autocomplete="off" :disabled=disabled></el-input>
            </el-form-item>

            <el-form-item label="喜爱车型" prop="likeModel">
                <el-input v-model.number="userAddForm.likeModel" :disabled=disabled></el-input>
            </el-form-item>

            <el-form-item style="width: 300px;margin: 0 auto">
                <el-button type="primary" @click="submitForm('userAddForm')" style="margin-right: 20px">添加</el-button>
                <el-button @click="resetForm('userAddForm')">重置</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>


<script>
    export default {
        name: "UserAdd",
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
                } else if (pattern.test(this.userAddForm.username)) {
                    callback(new Error("用户名不能包含特殊字符"));
                } else {
                    callback();
                }
            }

            //年龄超时，长度验证
            var checkAge = (rule, value, callback) => {
                if (!value) {
                    return callback(new Error('年龄不能为空'));
                }
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
                    if (this.userAddForm.checkPass !== '') {
                        this.$refs.userAddForm.validateField('checkPass');
                    }
                    callback();
                }
            };

            //二次密码验证
            var validatePass2 = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请再次输入密码'));
                } else if (value.length < 6) {
                    callback(new Error('密码最少为6位'));
                } else if (value !== this.userAddForm.password) {
                    callback(new Error('两次输入密码不一致!'));
                } else {
                    callback();
                }
            };

            //手机号验证校验
            var validateNum = (rule, value, callback) => {
                var re = /^1\d{10}$/
                // if (!re.test(value)) {
                //     callback(new Error('手机号码不符合规范'))
                // }
                if (!/^[0-9]+$/.test(this.userAddForm.userPhone) || !re.test(value)) {
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
                disabled: false,
                nameMessage: '',
                imageUrl: '',
                src: '',
                srcList: '',
                userAddForm: {
                    username: '',
                    gender: '',
                    password: '',
                    checkPass: '',
                    age: '',
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
                    checkPass: [
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
            };
        },
        methods: {
            nameNew() {
                const _this = this;
                axios.get("http://39.106.154.21:8181/account/newName/" + this.userAddForm.username).then(function (resp) {
                    let login = resp.data.data
                    if (login.user != null || login.admin != null) {
                        let message = _this.nameMessage = '用户名已存在'
                        _this.disabled = true;
                        if (resp.data.data != null) {
                            _this.$alert(message + ",是否重新填写", {
                                confirmButtonText: '确定',
                                callback: action => {
                                    location.href = "/userAdd";
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
                th
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
                    username: this.userAddForm.username,
                    password: this.userAddForm.password,
                    checkPassword: this.userAddForm.checkPass,
                    age: this.userAddForm.age,
                    gender: this.userAddForm.gender,
                    address: this.userAddForm.address,
                    userPhone: this.userAddForm.userPhone,
                    likeModel: this.userAddForm.likeModel,
                    src: this.src,
                    srcList: this.srcList
                }
                const _this = this;
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        axios.post("http://39.106.154.21:8181/account/saveUser", user).then(function (resp) {
                            if (resp.data.code === 0) {
                                _this.$alert('注册成功', {
                                    confirmButtonText: '确定',
                                    callback: action => {
                                        _this.$router.push("/userAll");
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
            resetForm(formName) {
                this.$refs[formName].resetFields();
            },
            goBack() {
                history.go(-1);
            },
        }
    }
</script>

<style>

    .userAddForm {
        border-radius: 15px;
        margin: 0px auto;
        width: 600px;
        padding: 35px 35px 15px 35px;
        background: #fff;
        border: 1px solid #eaeaea;
        box-shadow: 0 0 25px #cac6c6;
    }


    .avatar-uploader {
        border: 1px dashed #d9d9d9;
        border-radius: 6px;
        cursor: pointer;
        position: relative;
        overflow: hidden;
        width: 100px;
    }

    .avatar-uploader:hover {
        border-color: #409EFF;
    }

    .avatar-uploader-icon {
        font-size: 28px;
        color: #8c939d;
        width: 100px;
        height: 100px;
        line-height: 100px;
        margin: 0 auto;
    }

    .avatar {
        width: 100px;
        height: 100px;
        display: block;
    }


</style>