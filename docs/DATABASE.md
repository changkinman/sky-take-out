# 苍穹外卖 - 数据库设计全量文档

## 一、 核心实体设计规范

### 1. 员工表 (employee)
| 字段名 | 类型 | 描述 | 备注 |
| :--- | :--- | :--- | :--- |
| id | bigint | 主键ID | 自增, format: int64 |
| name | varchar(32) | 姓名 | |
| username | varchar(32) | 用户名 | 唯一索引 |
| password | varchar(64) | 密码 | MD5加密存储 |
| phone | varchar(11) | 手机号 | |
| sex | varchar(2) | 性别 | |
| id_number | varchar(18) | 身份证号 | |
| status | int | 账号状态 | 1:启用, 0:禁用 |
| create_time | datetime | 创建时间 | |
| update_time | datetime | 更新时间 | |
| create_user | bigint | 创建人ID | |
| update_user | bigint | 修改人ID | |

### 2. 分类表 (category)
| 字段名 | 类型 | 描述 | 备注 |
| :--- | :--- | :--- | :--- |
| id | bigint | 主键ID | |
| type | int | 类型 | 1:菜品分类, 2:套餐分类 |
| name | varchar(64) | 分类名称 | |
| sort | int | 排序 | 数值越小越靠前 |
| status | int | 状态 | 1:启用, 0:禁用 |
| create_time | datetime | 创建时间 | |
| update_time | datetime | 更新时间 | |

### 3. 菜品表 (dish)
| 字段名 | 类型 | 描述 | 备注 |
| :--- | :--- | :--- | :--- |
| id | bigint | 主键ID | |
| name | varchar(64) | 菜品名称 | 唯一索引 |
| category_id | bigint | 分类ID | 外键关联 category.id |
| price | decimal(10,2) | 价格 | |
| image | varchar(255) | 图片路径 | |
| description | varchar(255) | 描述 | |
| status | int | 状态 | 1:起售, 0:停售 |
| create_time | datetime | 创建时间 | |
| update_time | datetime | 更新时间 | |

### 4. 菜品口味表 (dish_flavor)
| 字段名 | 类型 | 描述 | 备注 |
| :--- | :--- | :--- | :--- |
| id | bigint | 主键ID | |
| dish_id | bigint | 菜品ID | 关联 dish.id |
| name | varchar(64) | 口味名称 | |
| value | varchar(255) | 口味值 | |

### 5. 套餐表 (setmeal)
| 字段名 | 类型 | 描述 | 备注 |
| :--- | :--- | :--- | :--- |
| id | bigint | 主键ID | |
| category_id | bigint | 分类ID | 关联 category.id |
| name | varchar(64) | 套餐名称 | 唯一索引 |
| price | decimal(10,2) | 价格 | |
| status | int | 状态 | 1:起售, 0:停售 |
| description | varchar(255) | 描述 | |
| image | varchar(255) | 图片路径 | |
| create_time | datetime | 创建时间 | |
| update_time | datetime | 更新时间 | |

### 6. 套餐菜品关系表 (setmeal_dish)
| 字段名 | 类型 | 描述 | 备注 |
| :--- | :--- | :--- | :--- |
| id | bigint | 主键ID | |
| setmeal_id | bigint | 套餐ID | 关联 setmeal.id |
| dish_id | bigint | 菜品ID | 关联 dish.id |
| name | varchar(32) | 菜品名称 | 冗余字段优化查询 |
| price | decimal(10,2) | 菜品原价 | 冗余字段 |
| copies | int | 份数 | |

### 7. 订单表 (orders)
| 字段名 | 类型 | 描述 | 备注 |
| :--- | :--- | :--- | :--- |
| id | bigint | 主键ID | |
| number | varchar(50) | 订单号 | |
| status | int | 订单状态 | 1待付款 2待接单 3已接单 4派送中 5已完成 6已取消 |
| user_id | bigint | 用户ID | 关联 user.id |
| address_book_id | bigint | 地址ID | 关联 address_book.id |
| order_time | datetime | 下单时间 | |
| checkout_time | datetime | 结账时间 | |
| pay_method | int | 支付方式 | 1微信 2支付宝 |
| pay_status | int | 支付状态 | 0未支付 1已支付 2退款 |
| amount | decimal(10,2) | 实收金额 | |
| remark | varchar(100) | 备注 | |
| phone | varchar(11) | 手机号 | |
| address | varchar(255) | 地址 | |
| consignee | varchar(32) | 收货人 | |
| cancel_reason | varchar(255) | 取消原因 | |
| rejection_reason | varchar(255) | 拒单原因 | |
| cancel_time | datetime | 取消时间 | |
| estimated_delivery_time | datetime | 预计送达时间 | |
| delivery_status | tinyint(1) | 派送状态 | 1正在派送 0未派送 |
| delivery_time | datetime | 送达时间 | |
| pack_amount | int | 打包费 | |
| tableware_number | int | 餐具数量 | |
| tableware_status | tinyint(1) | 餐具状态 | 1提供 0不提供 |

### 8. 订单明细表 (order_detail)
| 字段名 | 类型 | 描述 | 备注 |
| :--- | :--- | :--- | :--- |
| id | bigint | 主键ID | |
| name | varchar(32) | 商品名称 | |
| image | varchar(255) | 图片路径 | |
| order_id | bigint | 订单ID | 关联 orders.id |
| dish_id | bigint | 菜品ID | |
| setmeal_id | bigint | 套餐ID | |
| dish_flavor | varchar(64) | 口味 | |
| number | int | 数量 | |
| amount | decimal(10,2) | 金额 | |

### 9. 用户表 (user)
| 字段名 | 类型 | 描述 | 备注 |
| :--- | :--- | :--- | :--- |
| id | bigint | 主键ID | |
| openid | varchar(45) | 微信标识 | 唯一索引 |
| name | varchar(32) | 姓名 | |
| phone | varchar(11) | 手机号 | |
| sex | varchar(2) | 性别 | |
| id_number | varchar(18) | 身份证号 | |
| avatar | varchar(500) | 头像 | |
| create_time | datetime | 注册时间 | |

### 10. 地址簿表 (address_book)
| 字段名 | 类型 | 描述 | 备注 |
| :--- | :--- | :--- | :--- |
| id | bigint | 主键ID | |
| user_id | bigint | 用户ID | |
| consignee | varchar(32) | 收货人 | |
| sex | varchar(2) | 性别 | |
| phone | varchar(11) | 手机号 | |
| province_code | varchar(12) | 省份编码 | |
| city_code | varchar(12) | 城市编码 | |
| district_code | varchar(12) | 区县编码 | |
| detail | varchar(200) | 详细地址 | |
| label | varchar(100) | 标签 | 家/公司 |
| is_default | tinyint(1) | 是否默认 | 1是 0否 |

### 11. 购物车表 (shopping_cart)
| 字段名 | 类型 | 描述 | 备注 |
| :--- | :--- | :--- | :--- |
| id | bigint | 主键ID | |
| name | varchar(32) | 名称 | |
| image | varchar(255) | 图片 | |
| user_id | bigint | 用户ID | |
| dish_id | bigint | 菜品ID | |
| setmeal_id | bigint | 套餐ID | |
| dish_flavor | varchar(64) | 口味 | |
| number | int | 数量 | |
| amount | decimal(10,2) | 金额 | |
| create_time | datetime | 创建时间 | |

---

## 二、 数据库公共字段自动填充机制

项目采用 **AOP (面向切面编程)** 结合 **自定义注解 `@AutoFill`** 实现公共字段的统一管理。

### 1. 拦截策略
- **INSERT 阶段**：自动填充 `create_time`, `update_time`, `create_user`, `update_user`。
- **UPDATE 阶段**：自动填充 `update_time`, `update_user`。

### 2. 实现原理
1.  **ThreadLocal**：使用 `BaseContext` 获取当前操作人员的 `EmpId`。
2.  **反射机制**：在 Mapper 执行前，通过反射调用 Entity 的 Setter 方法注入属性值。

---

## 三、 索引与性能优化
1.  **唯一索引**：`employee(username)`, `dish(name)` 防止数据重复。
2.  **普通索引**：`orders(order_time)`, `setmeal(category_id)` 优化分页查询性能。
3.  **外键约束**：逻辑外键，代码层级维护一致性，数据库不强制约束。
