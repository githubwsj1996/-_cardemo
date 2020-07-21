<template>
    <body id="poster" v-title data-title="用户登录" >
    <el-form class="login-container" :model="loginForm" status-icon :rules="rules" ref="loginForm" label-position="left"
             label-width="0px">
        <div class="lang">
            <a @click="zhLang">中文</a>
            <span style="color:rgba(80, 84, 88, 0.7);">&nbsp || &nbsp</span>
            <a @click="enLang">English</a>
        </div>
        <h3 class="login_title">{{$t('m.tip')}}</h3>

        <!-- 头像-->
        <div class="demo-type">
            <el-avatar :size="60" :src="src" @error="errorHandler">
                <img src="https://cube.elemecdn.com/e/fd/0fc7d20532fdaf769a25683617711png.png"/>
            </el-avatar>
        </div>
        <!-- 用户名-->
        <el-form-item prop="username">
            <el-input @blur="onChanged" type="text" v-model="loginForm.username" auto-complete="off"
                      :placeholder="$t('m.username')" prefix-icon="el-icon-user"></el-input>
        </el-form-item>
        <!-- 密码-->
        <el-form-item prop="password">
            <el-input :type="passwordType" v-model="loginForm.password" auto-complete="off"
                      :placeholder="$t('m.password')" prefix-icon="el-icon-lock">
                <i class="el-icon-view el-input__icon" :style="fontStyle" slot="suffix" @click="showPassword"></i>
            </el-input>
        </el-form-item>

        <!-- 验证码-->
        <el-form-item prop="verifyCode">
            <el-input class="identifyInput" v-model="loginForm.verifyCode" :placeholder="$t('m.verifyCode')"
                      prefix-icon="el-icon-warning-outline"/>
        </el-form-item>
        <el-form-item>
            <div class="identifyBox">
                <div @click="refreshCode" class="verifyCode">
                    <s-identify :identifyCode="identifyCode"></s-identify>
                </div>
                <el-button @click="refreshCode" type='text' class="textBtn">{{$t('m.cantSee')}}</el-button>
            </div>
        </el-form-item>

        <!-- 注册-->
        <el-form-item>
            <!--            <el-checkbox name="remember" >{{$t('m.remember')}}</el-checkbox>-->
            <div class="registerButton" @click="onRegister">{{$t('m.register')}}</div>
        </el-form-item>

        <!-- 登录-->
        <el-form-item class="bottom">
            <el-button type="primary" style="background: #505458;border: none;margin-left:95px;"
                       @click="login('loginForm')">
                {{$t('m.submit')}}
            </el-button>
            <el-button type="primary" style="background: red;border: none;" @click="resetForm('loginForm')">
                {{$t('m.reset')}}
            </el-button>
        </el-form-item>

    </el-form>
    </body>
</template>

<script>
    import SIdentify from '@/utils/SIdentify.vue'

    export default {
        data() {
            //用户名，特殊字符验证
            var pattern = new RegExp(
                "[`~!@#$^&*()=|{}':;',\\[\\].<>《》/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？]"
            );
            var checkUsername = (rule, value, callback) => {
                if (!value) {
                    return callback(new Error('用户名不能为空'));
                } else if (pattern.test(this.loginForm.username)) {
                    callback(new Error("用户名不能包含特殊字符"));
                } else if (value.length < 3) {
                    callback(new Error('用户名最少为3位'));
                } else {
                    callback();
                }
            }

            //密码验证
            var validatePass = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('密码不能为空'));
                } else if (value.length < 6) {
                    callback(new Error('密码最少为6位'));
                } else {
                    callback();
                }
            };

            // 验证码自定义验证规则
            const validateVerifyCode = (rule, value, callback) => {
                if (value === '') {
                    callback(new Error('请输入验证码'))
                } else if (value !== this.identifyCode) {
                    console.log('validateVerifyCode:', value)
                    callback(new Error('验证码不正确!'))
                    this.refreshCode()
                } else {
                    callback()
                }
            }

            return {
                loginForm: {
                    username: '',
                    password: '',
                    verifyCode: ''
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
                    verifyCode: [
                        {required: true, trigger: 'blur', validator: validateVerifyCode}
                    ]
                },
                fontStyle: {},
                src: '',
                passwordType: 'password',
                identifyCodes: '1234567890',
                identifyCode: '',
            }
        },
        components: {
            SIdentify
        },
        mounted() {
            // 验证码初始化
            this.identifyCode = ''
            this.makeCode(this.identifyCodes, 4)
        },
        methods: {
            // 通过改变input的type使密码可见
            showPassword() {
                this.fontStyle === '' ? (this.fontStyle = '') : (this.fontStyle = 'color: red')  // 改变密码可见按钮颜色
                this.passwordType === ''
                    ? (this.passwordType = 'password') : (this.passwordType = '')
            },
            onChanged() {
                const _this = this;
                axios.get("http://39.106.154.21:8181/account/newName/" + this.loginForm.username).then(function (resp) {
                    if (resp.data.code === 1) {
                        _this.$message.error(resp.data.data)
                    } else {
                        _this.src = resp.data.data.src
                    }
                })
            },
            login(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        const _this = this;
                        axios.post("http://39.106.154.21:8181/account/login", this.loginForm).then(function (resp) {
                            let login = resp.data.data;
                            if (resp.data.code===0) {
                                if(parseInt(login.perm)===0){
                                    let user = {
                                        id: login.id,
                                        username: login.username,
                                        address: login.address,
                                        userPhone: login.userPhone,
                                        src: login.src,
                                        srcList: login.srcList,
                                    };
                                    sessionStorage.setItem('user', JSON.stringify(user));
                                    _this.$router.push("/carMenu")
                                }
                                else if(parseInt(login.perm)===1){
                                    let admin = {
                                        id: login.id,
                                        username: login.username,
                                        src: login.src,
                                        srcList: login.srcList,
                                    };
                                    sessionStorage.setItem('admin', JSON.stringify(admin));
                                    _this.$router.push("/adminMenu")
                                }
                            }
                            else if (resp.data.code===1) {
                                _this.$message.error(login);
                            }

                        })
                    }
                });

            },
            errorHandler() {
                return true
            },
            onRegister() {
                this.$router.push("/register")
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            },
            zhLang() {
                this.$i18n.locale = 'zh'
            },
            enLang() {
                this.$i18n.locale = 'en'
            },
            // 生成随机数
            randomNum(min, max) {
                return Math.floor(Math.random() * (max - min) + min)
            },
            // 切换验证码
            refreshCode() {
                this.identifyCode = ''
                this.makeCode(this.identifyCodes, 4)
            },
            // 生成四位随机验证码
            makeCode(o, l) {
                for (let i = 0; i < l; i++) {
                    this.identifyCode += this.identifyCodes[
                        this.randomNum(0, this.identifyCodes.length)
                        ]
                }
                console.log(this.identifyCode)
            }
        }
    }
</script>

<style>
    #poster {
        background: url("../../public/static/images/bg.png") no-repeat;
        height: 100%;
        width: 100%;
        background-size: cover;
        position: fixed;
    }

    body {
        margin: 0px;
        padding: 0;
    }

    .login-container {
        border-radius: 15px;
        background-clip: padding-box;
        margin: 90px auto;
        width: 350px;
        padding: 35px 35px 15px 35px;
        background: #fff;
        border: 1px solid #eaeaea;
        box-shadow: 0 0 25px #cac6c6;
    }

    .login_title {
        margin: 0px auto 40px auto;
        text-align: center;
        color: #505458;
    }

    .demo-type {
        width: 60px;
        margin: 0 auto;
        margin-bottom: 10px;
    }

    .lang a {
        text-decoration: none;
        font-size: 12px;
        color: rgba(80, 84, 88, 0.7);
        cursor: pointer;
    }

    .lang a:hover {
        color: deepskyblue;
    }

    .registerButton {
        display: inline;
        margin-left: 10px;
        font-size: 14px;
        color: darkorange;
        cursor: pointer;
        background-color: white;
    }

    .registerButton:hover {
        color: deepskyblue;
    }

    .verifyCode:hover {
        cursor: pointer;
    }

    .identifyBox {
        display: flex;
        justify-content: space-between;
        margin-top: 7px;
    }

</style>
 