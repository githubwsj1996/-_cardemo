<template>
    <div v-title data-title="用户信息编辑" >
        <el-page-header @back="goBack" content="用户信息修改" style="color:deepskyblue;margin-left: 200px;margin-top: 10px"/>

        <el-form class="userEditForm" :model="userEditForm" status-icon
                 :rules="rules" ref="userEditForm" label-width="100px">

            <el-form-item label="用户编号" >
                <el-input v-model.number="userEditForm.id" :disabled=disabled  readonly></el-input>
            </el-form-item>

            <el-form-item label="用户名称" prop="username">
                <el-input @blur="newUsername" v-model="userEditForm.username" autocomplete="off"
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
                <el-input v-model.number="userEditForm.age" :disabled=disabled></el-input>
            </el-form-item>

            <el-form-item label="性别" prop="gender" style="width: 400px;">
                <el-radio-group v-model="userEditForm.gender">
                    <el-radio label="1">男</el-radio>
                    <el-radio label="0">女</el-radio>
                </el-radio-group>
            </el-form-item>

            <el-form-item label="地址" prop="address">
                <el-input v-model.number="userEditForm.address" :disabled=disabled></el-input>
            </el-form-item>

            <el-form-item label="手机号码" prop="userPhone">
                <el-input v-model.number="userEditForm.userPhone" :disabled=disabled></el-input>
            </el-form-item>

            <el-form-item label="密码" prop="password">
                <el-input type="password" v-model="userEditForm.password" autocomplete="off" :disabled=disabled clearable="true"></el-input>
            </el-form-item>

            <el-form-item label="确认密码" prop="checkPassword">
                <el-input type="password" v-model="userEditForm.checkPassword" autocomplete="off" :disabled=disabled clearable="true"></el-input>
            </el-form-item>

            <el-form-item label="喜爱车型" prop="likeModel">
                <el-input v-model.number="userEditForm.likeModel" :disabled=disabled></el-input>
            </el-form-item>

            <el-form-item style="width: 300px;margin: 0 auto">
                <el-button type="primary" @click="onSubmit('userEditForm')" style="margin-right: 20px">保存</el-button>
                <el-button @click="goBack">取消</el-button>
            </el-form-item>
        </el-form>
    </div>
</template>

<script>
    export default {
        name: "UserEdit",
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
                } else if (pattern.test(this.userEditForm.username)) {
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
                    if (this.userEditForm.checkPassword !== '') {
                        this.$refs.userEditForm.validateField('checkPassword');
                    }
                    callback();
                }
            };

            //二次密码验证
            var validatePass2 = (rule, value, callback) => {
                if (this.userEditForm.checkPassword  === '') {
                    callback(new Error('请再次输入密码'));
                } else if (this.userEditForm.checkPassword .length < 6) {
                    callback(new Error('密码最少为6位'));
                } else if (this.userEditForm.checkPassword  !== this.userEditForm.password) {
                    callback(new Error('两次输入密码不一致!'));
                } else {
                    callback();
                }
            };

            //手机号验证校验
            var validateNum = (rule, value, callback) => {
                var re = /^1\d{10}$/
                if (!/^[0-9]+$/.test(this.userEditForm.userPhone) || !re.test(value)) {
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
                newNameMessage: '',
                imageUrl: '',
                src: '',
                srcList: '',
                userEditForm: {
                    id:'',
                    username: '',
                    gender: '',
                    password: '',
                    checkPassword: '',
                    age: '',
                    address: '',
                    userPhone: '',
                    likeModel: ''
                },
                uid:'',
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
                        {required: true, message: '请再次输入密码', trigger: 'blur'},
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
        created() {
            const _this=this;
            this.uid=this.$route.query.id
            axios.get("http://39.106.154.21:8181/account/getUser/"+this.uid).then(function (resp) {
                    _this.userEditForm=resp.data.data
                    _this.imageUrl=resp.data.data.src
                    _this.src=resp.data.data.src
                    _this.srcList=resp.data.data.srcList
            })
        },
        methods: {
            newUsername() {
                const _this = this;
                axios.get("http://39.106.154.21:8181/account/newName/" + this.userEditForm.username).then(function (resp) {
                    let login = resp.data.data
                    if (login.user != null || login.admin != null) {
                        let message = _this.newNameMessage = '用户名已存在'
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
                this.src = res.data
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
            onSubmit(formName) {
                const _this = this;
                let user1 = {
                    id: this.userEditForm.id,
                    username: this.userEditForm.username,
                    password: this.userEditForm.password,
                    checkPassword: this.userEditForm.checkPassword,
                    age: this.userEditForm.age,
                    gender: this.userEditForm.gender,
                    address: this.userEditForm.address,
                    userPhone: this.userEditForm.userPhone,
                    likeModel: this.userEditForm.likeModel,
                    src: this.src,
                    srcList: this.srcList
                }
                console.log(user1)
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        axios.post("http://39.106.154.21:8181/account/saveUser", user1).then(function (resp) {
                            if (resp.data.data!=null) {
                                _this.$alert('修改用户信息成功', {
                                    confirmButtonText: '确定',
                                    callback: action => {
                                        _this.$router.push("/userInfoAll");
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
        }
    }
</script>


<style scoped>
    .userEditForm {
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