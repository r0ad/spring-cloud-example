# Gateway SSO 例子工程

基于 [sa-token](https://sa-token.cc/) 和 spring cloud gateway的单点登录示例工程。

## 项目结构


## 项目启动

```bash
# 启动服务端
mvn spring-boot:run

# 启动客户端
mvn spring-boot:run -pl client
```

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
http://localhost:10105/userDemo/tokenInfo
```

无权限用户登录测试：

```shell
## 进行用户登录
http://localhost:10105/user/doLogin?name=test&pwd=123456
## 测试接口信息
http://localhost:10105/userDemo/tokenInfo
```

