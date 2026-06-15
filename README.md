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

## 🚀 业务功能与近期更新

### 💻 管理端 (Admin Web)
* **经营看板**：实时监控今日营业额、有效订单、新增用户。
* **数据分析**：基于 ECharts 生成销量 Top 10、用户增长、订单分布等可视化报表。
  * **[优化] 单天折线图空白修复**：优化了 ECharts 在“昨日”或单个日期筛选下因无连线显示空白的体验，支持动态控制数据点 Symbol 圆圈的显隐。
* **数据导出**：支持一键导出最近 30 天的指标汇总概览及每日运营明细。
  * **[新增] 运营报表 Excel 导出**：后端通过 Apache POI 动态生成带斑马线和自适应样式的 Excel 文档并流式传输；前端支持 Object URL 动态下载。

### 📱 用户端 (Mini Program)
* **自助点餐**：基于微信授权登录，支持口味选择、购物车动态结算。
  * **[优化] 菜品详情弹窗交互**：解决了详情框加减菜载荷数据结构不一致导致无法扣减的 Bug。
  * **[新增] 后端购物车动态扣减**：支持 `POST /user/shoppingCart/sub` 接口，支持购物车数量递减及数量归零时数据库物理清除。
  * **[新增] 真实销量统计**：小程序首页菜品与套餐动态计算近 30 天已完成订单的销售份数（`monthSales` 自动关联）。
* **订单追踪**：下单、支付、接单派送的全流程状态实时推送。

---

## 👥 团队分工 (Simulation)

本项目由 4 位小组成员协同开发，具体研发角色和任务切分可参考：
* 📄 **分工细则文档**：[项目团队功能分工模拟报告 (project_division_of_labor.md)](C:/Users/77550/.gemini/antigravity-ide/brain/a4821463-02d9-49cf-8828-6a5ba2c32471/project_division_of_labor.md)

---

## 🤝 参与开发

1. 阅读 [SETUP.md](docs/SETUP.md) 搭建本地环境。
2. 启动本地环境验证新特性：
   * **后端**：使用 IDE 或命令行在 `backend/luojia-server` 目录下运行 `mvn spring-boot:run` (后端服务运行于 `8080` 端口)。
   * **商家管理端 (Web)**：在 `frontend` 目录下运行 `npm run serve` (运行于 `8888` 端口)。
   * **用户端 (微信小程序)**：
     1. 安装 **HBuilderX** 编辑器及 **微信开发者工具**。
     2. 在微信开发者工具中开启安全设置：`设置 -> 安全设置 -> 开启服务端口`。
     3. 在 HBuilderX 中导入项目的 `miniapp` 目录。
     4. 点击 HBuilderX 菜单栏的 `运行 -> 运行到小程序模拟器 -> 微信开发者工具` 编译启动。
3. 参考 [API_Standard.md](docs/API_Standard.md) 进行接口联调。
4. 遵循 [ARCHITECTURE.md](docs/ARCHITECTURE.md) 的分层规范提交代码。
