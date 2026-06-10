# 珞珈外卖 - 环境搭建手册

## 一、 软件版本要求

| 软件 | 推荐版本 | 备注 |
| :--- | :--- | :--- |
| **JDK** | **17** | 必须，项目中使用了部分 Java 17 特性 |
| **Maven** | **3.6.1+** | 用于项目构建和依赖管理 |
| **MySQL** | **5.7 或 8.0** | 存储业务数据 |
| **Redis** | **3.0+** | 用于缓存菜品、店铺状态、以及验证码 |
| **Nginx** | **1.20+** | 用于部署前端静态资源并配置反向代理 |

---

## 二、 基础环境配置步骤

### 1. 数据库初始化
1.  登录 MySQL。
2.  执行项目根目录下的 `luojia.sql` 脚本（创建库名 `luojia_take_out`）。
3.  确保 `luojia-server` 中的 `application-dev.yml` 中的数据库用户名和密码与你本地一致。

### 2. Redis 启动
- 直接启动本地 Redis 服务即可，默认端口 `6379`。
- 本项目主要使用 Redis 存储：
  - 店铺营业状态。
  - 移动端缓存数据。
  - 微信登录状态。

### 3. Nginx 配置 (前端部署)
由于前后端分离，前端请求需通过 Nginx 转发到后端的 `8080` 端口。
1.  修改 `nginx.conf`：
    ```nginx
    server {
        listen       80;
        server_name  localhost;
        location /api/ {
            proxy_pass   http://localhost:8080/admin/; # 反向代理
        }
    }
    ```

---

## 三、 项目启动指南

1.  **后端启动**：
    - 在 IDE (IntelliJ IDEA) 中打开根目录的 `pom.xml`。
    - 找到 `luojia-server` 模块下的 `LuojiaApplication` 类，直接运行。
2.  **API 调试**：
    - 启动成功后，访问：`http://localhost:8080/doc.html`。
    - 这是 Knife4j 生成的图形化接口文档，可以直接进行接口测试。

---

## 四、 常见问题 (FAQ)
- **Q: 数据库连接不上？**
  - A: 检查 `application-dev.yml` 是否使用了正确的驱动类名（MySQL 8.0 使用 `com.mysql.cj.jdbc.Driver`）。
- **Q: 启动报 JWT 签名错误？**
  - A: 确保配置文件中的 `luojia.jwt.admin-secret-key` 长度足够（建议不少于 32 位）。
