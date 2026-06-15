# 珞珈外卖 - 项目架构设计文档

## 一、 多模块 Maven 体系说明

项目采用模块化开发，通过父工程管理依赖版本，子模块职责如下：

### 1. Luojia-common (通用工具模块)
- **职责**：存放项目中通用的工具类、异常类、常量、属性配置等。
- **关键包结构**：
  - `com.Luojia.constant`：静态常量（如订单状态、支付状态）。
  - `com.Luojia.exception`：自定义业务异常。
  - `com.Luojia.utils`：工具类（如 HttpClient, AliOssUtil, JwtUtil）。
  - `com.Luojia.json`：JSON 处理与反序列化配置。

### 2. Luojia-pojo (数据模型模块)
- **职责**：定义所有数据传输对象，严禁在此模块编写业务逻辑。
- **关键包结构**：
  - `com.Luojia.entity`：与数据库表结构 1:1 对应的实体类。
  - `com.Luojia.dto`：**数据传输对象**，用于前端向后端传参。
  - `com.Luojia.vo`：**视图对象**，用于后端向前端返回结果。

### 3. Luojia-server (业务逻辑模块) —— **核心**
- **职责**：存放所有的 Controller, Service, Mapper 以及配置类。
- **关键包结构**：
  - `com.Luojia.controller`：控制层，处理 HTTP 请求及参数校验。
  - `com.Luojia.service`：业务层，实现具体的业务逻辑。
  - `com.Luojia.mapper`：数据访问层，编写 SQL 语句。
  - `com.Luojia.config`：配置类（Redis, Swagger, MVC, MyBatis）。
  - `com.Luojia.handler`：全局异常处理器及各类拦截器。
  - `com.Luojia.task`：定时任务。

---

## 二、 数据流转规范

为了保证系统的整洁，我们规定数据的流转方向如下：

1.  **请求进入**：前端发送请求 -> `Controller` 接收 `DTO`。
2.  **业务处理**：`Controller` 调用 `Service` -> `Service` 内部将 `DTO` 转换为 `Entity`。
3.  **持久化**：`Service` 调用 `Mapper` 操作 `Entity` -> 存入数据库。
4.  **结果返回**：数据库结果 -> `Entity` -> `Service` 将其转换为 `VO` -> 返回给前端。

> [!IMPORTANT]
> **严禁** 直接在 Controller 层编写复杂的 SQL 或业务逻辑。
> **严禁** 将 Entity 直接返回给前端（涉及数据安全和字段泄露）。
