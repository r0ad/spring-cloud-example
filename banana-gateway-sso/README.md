# Gateway SSO 例子工程

基于 [sa-token](https://sa-token.cc/) 和 spring cloud gateway的单点登录示例工程。

## 项目结构

- spring cloud gateway 网关服务
- sa-token 权限认证服务

## 项目启动

```bash
# 启动服务端
mvn spring-boot:run
```

## 基本api说明

项目地址： <http://localhost:10105>

接口|说明
---|---
/user/login|用户登录
/user/token|获取token信息
/user/isLogin|判断用户是否登录
/user/logout|用户登出
/user/userInfo|用户信息
/user/role|用户角色信息
/user/permission|用户权限信息

## 权限验证说明

```java
@Bean
public SaReactorFilter getSaReactorFilter() {
    return new SaReactorFilter()
            // 指定 [拦截路由]
            .addInclude("/**")    /* 拦截所有path */
            // 指定 [放行路由]
            .addExclude("/favicon.ico")
            .addExclude("/user/**")
            // 指定[认证函数]: 每次请求执行 
            .setAuth(obj -> {
//                    System.out.println("---------- sa全局认证");
                SaRouter.match("/**", () -> StpUtil.checkLogin());
                // 根据路由划分模块，不同模块不同鉴权
                // todo 修改为动态权限鉴权，角色权限和路径基于数据库配置
                SaRouter.match("/admin/**", r -> StpUtil.checkPermission("admin"));
                SaRouter.match("/goods/**", r -> StpUtil.checkPermission("goods"));
            })
            // 指定[异常处理函数]：每次[认证函数]发生异常时执行此函数 
            .setError(e -> {
//                    System.out.println("---------- sa全局异常 ");
                return SaResult.error(e.getMessage());
            });
}
```

1. `/admin/**` 接口校验是否拥有`admin`角色
2. `/goods/**` 接口校验是否拥有`goods`角色
3. 实际场景并不多使用这种硬编码方式，后续修改为动态权限鉴权，角色权限和路径基于数据库配置。

## 例子说明

### 用户登录流程

```shell
## 进行用户登录
http://localhost:10105/user/doLogin?name=sa&pwd=123456
http://localhost:10105/user/doLogin?name=test&pwd=123456
## 测试接口信息
http://localhost:10105/user/tokenInfo
```

### 测试角色流程

有权限用户登录：

```shell
## 进行用户登录
http://localhost:10105/user/doLogin?name=sa&pwd=123456
## 测试接口信息
http://localhost:10105/demoUser/tokenInfo
```

无权限用户登录测试：

```shell
## 进行用户登录
http://localhost:10105/user/doLogin?name=test&pwd=123456
## 测试接口信息
http://localhost:10105/demoUser/tokenInfo
```

注： 实际测试中基于注解的权限并未生效。基于filter的权限拦截生效了。
