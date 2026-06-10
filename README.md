# 珞珈外卖 (Luojia Take-out)

[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.7.3-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![MyBatis](https://img.shields.io/badge/MyBatis-2.2.0-blue.svg)](https://blog.mybatis.org/)
[![MySQL](https://img.shields.io/badge/MySQL-5.7%2B-blue.svg)](https://www.mysql.com/)
[![Redis](https://img.shields.io/badge/Redis-3.0%2B-red.svg)](https://redis.io/)

**珞珈外卖** 是一套专为餐饮企业定制的数字化运营解决方案。项目基于前后端分离架构，涵盖了从管理端到小程序端的全流程业务闭环。

---

## 📚 项目开发导航 (Must Read)

> [!IMPORTANT]
> **在开始编写代码前，请务必阅读以下标准化文档，以确保开发规范的一致性。**

| 模块 | 文档路径 | 核心内容 |
| :--- | :--- | :--- |
| **🚀 快速开始** | [SETUP.md](docs/SETUP.md) | 环境配置、Nginx 部署、启动指南 |
| **🏗️ 架构规范** | [ARCHITECTURE.md](docs/ARCHITECTURE.md) | 模块职责、DTO/VO 流转、分层规范 |
| **📖 接口文档** | [API_Standard.md](docs/API_Standard.md) | 全量 Admin API 字段复刻与业务逻辑 |
| **🗄️ 数据库设计** | [DATABASE.md](docs/DATABASE.md) | 核心 11 张表结构、AOP 自动填充机制 |
| **🚥 响应与日志** | [LOGGING.md](docs/LOGGING.md) | 业务状态码、HTTP 信号、日志分级 |

---

## 🛠️ 技术栈
- **后端**: Spring Boot, MyBatis, Knife4j (Swagger), JWT, AliOSS, Apache POI, WebSocket
- **数据库**: MySQL 5.7+ / Redis (数据缓存与状态控制)
- **中间件**: Nginx (反向代理), Maven (多模块管理)
- **工具**: Lombok, Git, PowerDesigner

---

## 🏗️ 项目目录结构
项目采用全栈式目录管理规范：
- **`backend/`**: 后端 Java 工程根目录 (Maven 多模块)。
  - `luojia-common`: 存放常量、异常、工具类、配置属性。
  - `luojia-pojo`: 存放实体类 (Entity)、传参对象 (DTO)、视图对象 (VO)。
  - `luojia-server`: 核心业务实现（Controller, Service, Mapper）。
- **`frontend/`**: 前端工程根目录。
  - `admin-web`: 管理端 Vue 项目。
  - `mp-weixin`: 微信小程序项目。
- **`docs/`**: 全局设计文档与 SQL 脚本。

---

## 🚀 业务功能概览

### 管理端 (Admin Web)
- **经营看板**：实时监控今日营业额、有效订单、新增用户。
- **全量管理**：分类、菜品、套餐、订单、员工的全生命周期管理。
- **数据分析**：自动生成销量 Top10、用户增长、订单分布等可视化报表。

### 用户端 (Mini Program)
- **自助点餐**：基于微信授权登录，支持口味选择、购物车动态结算。
- **订单追踪**：支持下单、支付、接单派送的全流程状态实时推送。
- **地址管理**：多地址薄维护，支持默认地址设置。

---

## 🤝 参与开发
1.  阅读 [SETUP.md](docs/SETUP.md) 搭建本地环境。
2.  参考 [API_Standard.md](docs/API_Standard.md) 进行接口联调。
3.  遵循 [ARCHITECTURE.md](docs/ARCHITECTURE.md) 的分层规范提交代码。
