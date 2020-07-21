SpringBoot+Vue  前后端分离项目

car_vue  前端显示界面
cardemo  后端程序

car_vue  {


     登录：{登录界面国际化(中英文切换)，注册，验证码}，
     
     用户界面：{用户首页，用户信息，商品展示，
              商品搜索栏，分类显现，分页，
              租赁购买，支付购买，查看订单状态，用户注销}，
              
     管理员界面：{管理员首页，商品管理(添加,修改,删除)，用户管理(添加,修改,删除)，
                 订单审核，订单详情，还车审核，
                 还车详情，数据视图，管理员信息编辑，注销}
}

cardemo{


    userHandler:用户登录和用户信息管理操作的类；
    carMenuHandler: 商品有关的类；
    orderHandler： 租赁汽车和订单的相关的类；
    returnHandler : 还车操作相关的类；
    config:  CorsConfig  跨域配置类；ShiroConfiguration:Shiro安全组配置类；
    entity: 和数据库相对应的POJO类；
    exception:自定义异常；
    interceptor：拦截器；
    realm:自定义Shiro安全框架的Realm；
    repository:数据持久层；
    service：数据处理层；
    util: 自定义工具类(KeyUtil:生成随机id的各个方法；ResultUtil:返回前端的封装数据接口)；
    vo: 返回的前端数据
    
}



该项目已经部署到阿里云网址：http://39.106.154.21:8080/

