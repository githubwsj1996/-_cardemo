<template>
    <div v-title data-title="管理员信息" >
        <el-page-header @back="goBack" content="管理员信息" style="color:deepskyblue;margin-left: 200px;margin-top: 10px"/>
        <el-form class="demo-adminForm" ref="adminForm" :model="adminForm" :rules="rules" status-icon
                 label-width="100px">
            <el-form-item label="用户编号" prsop="id" style="width: 200px;">
                <el-input v-model="adminForm.id" style="color: #42b983" readonly></el-input>
            </el-form-item>

            <el-form-item label="用户名称" prop="username" style="width: 500px;">
                <el-input @blur="newAdminName" v-model="adminForm.username" autocomplete="off" :disabled=disabled
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

            <el-form-item label="密码" prop="password" style="width: 500px;">
                <el-input type="password" v-model="adminForm.password" autocomplete="off"
                          :clearable="true" :disabled=disabled></el-input>
            </el-form-item>

            <el-form-item label="确认密码" prop="checkPassword" style="width: 500px;">
                <el-input type="password" v-model="adminForm.checkPassword" autocomplete="off"
                          :clearable="true" :disabled=disabled></el-input>
            </el-form-item>

            <el-form-item label="年龄" prop="age">
                <el-input v-model.number="adminForm.age" :disabled=disabled></el-input>
            </el-form-item>

            <el-form-item label="性别" prop="gender" style="width: 400px;">
                <el-radio-group v-model="adminForm.gender">
                    <el-radio label="1">男</el-radio>
                    <el-radio label="0">女</el-radio>
                </el-radio-group>
            </el-form-item>

            <el-form-item label="地址" prop="address">
                <el-input v-model.number="adminForm.address" :disabled=disabled></el-input>
            </el-form-item>

            <el-form-item label="手机号码" prop="userPhone">
                <el-input v-model.number="adminForm.userPhone" :disabled=disabled></el-input>
            </el-form-item>

            <el-form-item label="喜爱车型" prop="likeModel">
                <el-input v-model.number="adminForm.likeModel" :disabled=disabled></el-input>
            </el-form-item>

            <el-form-item>
                <el-button type="primary" @click="submitForm('adminForm')">保存</el-button>
                <el-button @click="goBack">取消</el-button>
            </el-form-item>
        </el-form>

    </div>
</template>

<script>
    export default {
        name: "adminInfo",
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
                } else if (pattern.test(this.adminForm.username)) {
                    callback(new Error("用户名不能包含特殊字符"));
                } else {
                    callback();
                }
            }

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
                if (this.adminForm.checkPassword.length < 6) {
                    callback(new Error('密码最少为6位'));
                } else if (this.adminForm.checkPassword !== this.adminForm.password) {
                    callback(new Error('两次输入密码不一致!'));
                } else {
                    callback();
                }
            };

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
            //手机号验证校验
            var validateNum = (rule, value, callback) => {
                var re = /^1\d{10}$/
                // if (!re.test(value)) {
                //     callback(new Error('手机号码不符合规范'))
                // }
                if (!/^[0-9]+$/.test(this.adminForm.userPhone) || !re.test(value)) {
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
                adminForm: {
                    id: '',
                    username: '',
                    password: '',
                    checkPassword: '',
                    age:'',
                    gender:'',
                    address:'',
                    userPhone:'',
                    likeModel:'',
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
                imageUrl: '',
                src: '',
                srcListL:'',
            }
        },
        created() {
            const _this = this;
            let id = JSON.parse(sessionStorage.getItem("admin")).id;
            axios.get("http://39.106.154.21:8181/account/getUser/" + id).then(function (resp) {
                _this.adminForm = resp.data.data
                _this.src = resp.data.data.src
                _this.srcList=resp.data.data.srcList
                _this.imageUrl = resp.data.data.src
            })
        },
        methods: {
            newAdminName() {
                const _this = this;
                axios.get("http://39.106.154.21:8181/account/newName/" + this.adminForm.username).then(function (resp) {
                    let login = resp.data.data
                    if (login.id!=null) {
                        let message = '管理员已存在'
                        _this.disabled = true
                            _this.$alert(message + ",是否重新填写", {
                                confirmButtonText: '确定',
                                callback: action => {
                                    _this.$router.go(0)
                                }
                            });
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
                this.srcList=res.data
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
                    id: this.adminForm.id,
                    username: this.adminForm.username,
                    password: this.adminForm.password,
                    checkPassword: this.adminForm.checkPassword,
                    src: this.src,
                    srcList :this.srcList,
                    age: this.adminForm.age,
                    gender: this.adminForm.gender,
                    address: this.adminForm.address,
                    userPhone: this.adminForm.userPhone,
                    likeModel: this.adminForm.likeModel
                }
                const _this = this;
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        axios.put("http://39.106.154.21:8181/account/adminEdit", user).then(function (resp) {
                            if (resp.data.code === 0) {
                                _this.$alert(resp.data.data, {
                                    confirmButtonText: '确定',
                                    callback: action => {
                                        _this.$router.push("/");
                                        sessionStorage.removeItem("admin")
                                    }
                                });
                            }else{
                                _this.$message.error("信息有误，请重新填写");
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

<style>
    .demo-adminForm {
        border-radius: 15px;
        background-clip: padding-box;
        margin: 0px auto;
        width: 600px;
        padding: 35px 35px 15px 35px;
        background: #fff;
        border: 0px solid #eaeaea;
    }


</style>