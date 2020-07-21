import Vue from 'vue'
import VueI18n from "vue-i18n"
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Login from "../views/Login.vue";
import CarMenu from "../views/CarMenu";
import OrderDetail from "../views/OrderDetail";
import Success from "../views/Success";
import UserInfo from "../views/UserInfo";
import UserOrder from "../views/UserOrder";
import Register from "../views/Register";
import AddCar from "../views/admin/AddCar";
import AdminEdit from "../views/admin/AdminEdit";
import CarManage from "../views/admin/CarManage";
import CarEdit from "../views/admin/CarEdit";
import Index from "../views/admin/Index";
import OrderAll from "../views/admin/OrderAll";
import OrderColumnar from "../views/admin/OrderColumnar";
import OrderExamine from "../views/admin/OrderExamine";
import OrderSector from "../views/admin/OrderSector";
import ReturnCar from "../views/admin/ReturnCar";
import ReturnExamine from "../views/admin/ReturnExamine";
import UserAdd from "../views/admin/UserAdd";
import UserInfoAll from "../views/admin/UserInfoAll";
import UserEdit from "../views/admin/UserEdit";
import ErrorInfo from "../views/error/ErrorInfo";
import SIdentify from "../utils/SIdentify";

Vue.use(VueRouter)
Vue.use(VueI18n)

const routes = [
    {
        path: '/home',
        name: 'Home',
        component: Home
    },
    {
        path: "/",
        name: "登录",
        component: Login
    },
    {
        path: "/register",
        name: "注册",
        component: Register
    },
    {
        path: "/userInfo",
        name: "用户信息",
        component: UserInfo
    },
    {
        path: "/userOrder",
        name: "用户订单",
        component: UserOrder
    },
    {
        path: "/success",
        name: "支付成功",
        component: Success
    },
    {
        path: "/carMenu",
        name: "汽车详情页",
        component: CarMenu
    },
    {
        path: "/orderDetail",
        name: "订单详情页",
        component: OrderDetail
    },

    {
        path: '/adminMenu',
        name: '汽车管理',
        iconName: 'el-icon-s-tools',
        component: Index,
        redirect: '/carManage',
        show: true,
        children: [
            {
                path: '/carManage', name: '汽车列表', component: CarManage
            },
            {
                path: '/addCar', name: '添加汽车', component: AddCar
            }
        ],

    },
    {
        path: "/carEdit",
        name: "汽车信息修改",
        component: CarEdit,
        show: false
    },
    {
        path: '/daoHang2',
        name: '订单管理',
        iconName: 'el-icon-s-order',
        component: Index,
        show: true,
        children: [
            {
                path: '/orderExamine', name: '订单审核', component: OrderExamine
            },
            {
                path: '/orderAll', name: '订单列表', component: OrderAll
            }
        ]
    },
    {
        path: '/daoHang3',
        name: '还车记录',
        iconName: 'el-icon-back',
        component: Index,
        show: true,
        children: [
            {
                path: '/returnExamine', name: '还车审核', component: ReturnExamine
            },
            {
                path: '/returnCar', name: '还车信息', component: ReturnCar
            }

        ]
    },
    {
        path: '/daoHang4',
        name: '用户管理',
        iconName: 'el-icon-user-solid',
        component: Index,
        show: true,
        children: [
            {
                path: '/userInfoAll', name: '查看用户', component: UserInfoAll
            },
            {
                path: '/userAdd', name: '添加用户', component: UserAdd
            },
        ]
    },
    {
        path: "/userEdit",
        name: "用户信息修改",
        component: UserEdit,
        show: false
    },
    {
        path: "/adminEdit",
        name: "管理员信息修改",
        component: AdminEdit,
        show: false
    },
    {
        path: '/daoHang5',
        name: '数据视图',
        iconName: 'el-icon-s-data',
        component: Index,
        show: true,
        children: [
            {
                path: '/orderSector', name: '饼图', component: OrderSector
            },
            {
                path: '/orderColumnar', name: '柱状图', component: OrderColumnar
            },
        ]
    },
    {
        path: "/errorInfo",
        name:'报错页面',
        component: ErrorInfo,
    }
]

const router = new VueRouter({
    mode: 'history',
    base: "/carvue/",
    routes
})

router.beforeEach((to, from, next) => {
    if (to.matched.length === 0) { //匹配前往的路由不存在
        from.name ? next({
            name: from.name
        }) : next('/errorInfo'); //判断此跳转路由的来源路由是否存在，存在的情况跳转到来源路由，否则跳转到404页面
    } else {
        next(); //如果匹配到正确跳转
    }
});

export default router
