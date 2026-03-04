# Java Demo：自定义项目配置 + Nacos 管理数据库连接

这是一个基于 **Spring Boot 3 + Spring Cloud Alibaba Nacos** 的 demo 工程，演示：

- 自定义项目配置（`custom.project.*`）
- 数据库连接信息统一放在 Nacos（`custom.project.database.*`）
- 通过接口查看当前生效配置（密码脱敏）

## 1. 启动 Nacos

本地安装并启动 Nacos（默认 `127.0.0.1:8848`）。

## 2. 在 Nacos 创建配置

- Data ID: `demo-custom-project.yaml`
- Group: `DEFAULT_GROUP`
- 配置内容可参考：`src/main/resources/nacos/demo-custom-project.yaml`

## 3. 启动应用

```bash
mvn spring-boot:run
```

如果你的 Nacos 地址不是默认值，可以通过环境变量覆盖：

```bash
export NACOS_SERVER_ADDR=192.168.1.10:8848
export NACOS_NAMESPACE=public
export NACOS_GROUP=DEFAULT_GROUP
mvn spring-boot:run
```

## 4. 查看配置

启动后访问：

```text
GET http://localhost:8080/api/project-config
```

返回示例：

```json
{
  "name": "order-center",
  "owner": "platform-team",
  "featureEnabled": true,
  "whitelistIps": ["10.10.1.11", "10.10.1.12"],
  "database": {
    "url": "jdbc:mysql://127.0.0.1:3306/demo?serverTimezone=Asia/Shanghai",
    "username": "demo_user",
    "passwordMasked": "d***d",
    "driverClassName": "com.mysql.cj.jdbc.Driver"
  }
}
```
