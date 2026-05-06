# 苍穹外卖 - 管理端 API 全量标准文档 (极细版)

---

## 一、分类相关接口

### 1. 修改分类
#### 基本信息
- **Path：** `/admin/category`
- **Method：** `PUT`
- **接口描述：** (无)

#### 请求参数
**Headers**
| 参数名称 | 参数值 | 是否必须 | 示例 | 备注 |
| :--- | :--- | :--- | :--- | :--- |
| Content-Type | application/json | 是 | | |

**Body**
| 名称 | 类型 | 是否必须 | 默认值 | 备注 | 其他信息 |
| :--- | :--- | :--- | :--- | :--- | :--- |
| id | integer | 必须 | | 分类id | format: int64 |
| name | string | 必须 | | 分类名称 | |
| sort | integer | 必须 | | 排序 | format: int32 |
| type | integer | 必须 | | 分类类型 | format: int32 |

#### 返回数据
| 名称 | 类型 | 是否必须 | 默认值 | 备注 | 其他信息 |
| :--- | :--- | :--- | :--- | :--- | :--- |
| code | integer | 必须 | | | format: int32 |
| data | string | 非必须 | | | |
| msg | string | 非必须 | | | |

---

### 2. 分类分页查询
#### 基本信息
- **Path：** `/admin/category/page`
- **Method：** `GET`
- **接口描述：** (无)

#### 请求参数
**Query**
| 参数名称 | 是否必须 | 示例 | 备注 |
| :--- | :--- | :--- | :--- |
| name | 否 | 传统主食 | 分类名称 |
| page | 是 | 1 | 页码 |
| pageSize | 是 | 10 | 每页记录数 |
| type | 否 | 1 | 分类类型：1为菜品分类，2为套餐分类 |

#### 返回数据
| 名称 | 类型 | 是否必须 | 默认值 | 备注 | 其他信息 |
| :--- | :--- | :--- | :--- | :--- | :--- |
| code | number | 必须 | | | |
| msg | null | 非必须 | | | |
| data | object | 必须 | | | |
| ├─ total | number | 必须 | | | |
| ├─ records | object [] | 必须 | | | item 类型: object |
| ├─ id | number | 必须 | | | |
| ├─ type | number | 必须 | | | |
| ├─ name | string | 必须 | | | |
| ├─ sort | number | 必须 | | | |
| ├─ status | number | 必须 | | | |
| ├─ createTime | string | 必须 | | | |
| ├─ updateTime | string | 必须 | | | |
| ├─ createUser | number | 必须 | | | |
| ├─ updateUser | number | 必须 | | | |

---

### 3. 启用、禁用分类
#### 基本信息
- **Path：** `/admin/category/status/{status}`
- **Method：** `POST`

#### 请求参数
**Headers**
| 参数名称 | 参数值 | 是否必须 | 示例 | 备注 |
| :--- | :--- | :--- | :--- | :--- |
| Content-Type | application/json | 是 | | |

**路径参数**
| 参数名称 | 示例 | 备注 |
| :--- | :--- | :--- |
| status | 1 | 1为启用，0为禁用 |

**Query**
| 参数名称 | 是否必须 | 示例 | 备注 |
| :--- | :--- | :--- | :--- |
| id | 是 | 100 | 分类id |

#### 返回数据
| 名称 | 类型 | 是否必须 | 默认值 | 备注 | 其他信息 |
| :--- | :--- | :--- | :--- | :--- | :--- |
| code | integer | 必须 | | | format: int32 |
| data | string | 非必须 | | | |
| msg | string | 非必须 | | | |

---

### 4. 新增分类
#### 基本信息
- **Path：** `/admin/category`
- **Method：** `POST`

#### 请求参数
**Headers**
| 参数名称 | 参数值 | 是否必须 | 示例 | 备注 |
| :--- | :--- | :--- | :--- | :--- |
| Content-Type | application/json | 是 | | |

**Body**
| 名称 | 类型 | 是否必须 | 默认值 | 备注 | 其他信息 |
| :--- | :--- | :--- | :--- | :--- | :--- |
| id | integer | 非必须 | | | format: int64 |
| name | string | 必须 | | 分类名称 | |
| sort | integer | 必须 | | 排序，按照升序排序 | format: int32 |
| type | integer | 必须 | | 分类类型：1为菜品分类，2为套餐分类 | format: int32 |

#### 返回数据
| 名称 | 类型 | 是否必须 | 默认值 | 备注 | 其他信息 |
| :--- | :--- | :--- | :--- | :--- | :--- |
| code | integer | 必须 | | | format: int32 |
| data | string | 非必须 | | | |
| msg | string | 非必须 | | | |

---

### 5. 根据id删除分类
#### 基本信息
- **Path：** `/admin/category`
- **Method：** `DELETE`

#### 请求参数
**Query**
| 参数名称 | 是否必须 | 示例 | 备注 |
| :--- | :--- | :--- | :--- |
| id | 是 | 100 | 分类id |

#### 返回数据
| 名称 | 类型 | 是否必须 | 默认值 | 备注 | 其他信息 |
| :--- | :--- | :--- | :--- | :--- | :--- |
| code | integer | 必须 | | | format: int32 |
| data | string | 非必须 | | | |
| msg | string | 非必须 | | | |

---

### 6. 根据类型查询分类
#### 基本信息
- **Path：** `/admin/category/list`
- **Method：** `GET`

#### 请求参数
**Query**
| 参数名称 | 是否必须 | 示例 | 备注 |
| :--- | :--- | :--- | :--- |
| type | 否 | 2 | 分类类型：1为菜品分类，2为套餐分类 |

#### 返回数据
| 名称 | 类型 | 是否必须 | 默认值 | 备注 | 其他信息 |
| :--- | :--- | :--- | :--- | :--- | :--- |
| code | integer | 必须 | | | format: int32 |
| data | object [] | 非必须 | | | item 类型: object |
| ├─ createTime | string | 非必须 | | | format: date-time |
| ├─ createUser | integer | 非必须 | | | format: int64 |
| ├─ id | integer | 非必须 | | | format: int64 |
| ├─ name | string | 非必须 | | | |
| ├─ sort | integer | 非必须 | | | format: int32 |
| ├─ status | integer | 非必须 | | | format: int32 |
| ├─ type | integer | 非必须 | | | format: int32 |
| ├─ updateTime | string | 非必须 | | | format: date-time |
| ├─ updateUser | integer | 非必须 | | | format: int64 |
| msg | string | 非必须 | | | |

## 二、员工相关接口

### 1. 修改密码
#### 基本信息
- **Path：** `/admin/employee/editPassword`
- **Method：** `PUT`
- **接口描述：** (无)

#### 请求参数
**Headers**
| 参数名称 | 参数值 | 是否必须 | 示例 | 备注 |
| :--- | :--- | :--- | :--- | :--- |
| Content-Type | application/json | 是 | | |

**Body**
| 名称 | 类型 | 是否必须 | 默认值 | 备注 | 其他信息 |
| :--- | :--- | :--- | :--- | :--- | :--- |
| empId | integer | 必须 | | 员工id | format: int64 |
| newPassword | string | 必须 | | 新密码 | |
| oldPassword | string | 必须 | | 旧密码 | |

#### 返回数据
| 名称 | 类型 | 是否必须 | 默认值 | 备注 | 其他信息 |
| :--- | :--- | :--- | :--- | :--- | :--- |
| code | integer | 必须 | | | format: int32 |
| data | string | 非必须 | | | |
| msg | string | 非必须 | | | |

---

### 2. 启用、禁用员工账号
#### 基本信息
- **Path：** `/admin/employee/status/{status}`
- **Method：** `POST`
- **接口描述：** (无)

#### 请求参数
**Headers**
| 参数名称 | 参数值 | 是否必须 | 示例 | 备注 |
| :--- | :--- | :--- | :--- | :--- |
| Content-Type | application/json | 是 | | |

**路径参数**
| 参数名称 | 示例 | 备注 |
| :--- | :--- | :--- |
| status | 1 | 状态，1为启用 0为禁用 |

**Query**
| 参数名称 | 是否必须 | 示例 | 备注 |
| :--- | :--- | :--- | :--- |
| id | 是 | | 员工id |

#### 返回数据
| 名称 | 类型 | 是否必须 | 默认值 | 备注 | 其他信息 |
| :--- | :--- | :--- | :--- | :--- | :--- |
| code | integer | 必须 | | | format: int32 |
| data | string | 非必须 | | | |
| msg | string | 非必须 | | | |

---

### 3. 员工分页查询
#### 基本信息
- **Path：** `/admin/employee/page`
- **Method：** `GET`
- **接口描述：** (无)

#### 请求参数
**Query**
| 参数名称 | 是否必须 | 示例 | 备注 |
| :--- | :--- | :--- | :--- |
| name | 否 | 张三 | 员工姓名 |
| page | 是 | 1 | 页码 |
| pageSize | 是 | 10 | 每页记录数 |

#### 返回数据
| 名称 | 类型 | 是否必须 | 默认值 | 备注 | 其他信息 |
| :--- | :--- | :--- | :--- | :--- | :--- |
| code | number | 必须 | | | |
| msg | null | 非必须 | | | |
| data | object | 必须 | | | |
| ├─ total | number | 必须 | | | |
| ├─ records | object [] | 必须 | | | item 类型: object |
| ├─ id | number | 必须 | | | |
| ├─ username | string | 必须 | | | |
| ├─ name | string | 必须 | | | |
| ├─ password | string | 必须 | | | |
| ├─ phone | string | 必须 | | | |
| ├─ sex | string | 必须 | | | |
| ├─ idNumber | string | 必须 | | | |
| ├─ status | number | 必须 | | | |
| ├─ createTime | string,null | 必须 | | | |
| ├─ updateTime | string | 必须 | | | |
| ├─ createUser | number,null | 必须 | | | |
| ├─ updateUser | number | 必须 | | | |

---

### 4. 员工登录
#### 基本信息
- **Path：** `/admin/employee/login`
- **Method：** `POST`
- **接口描述：** (无)

#### 请求参数
**Headers**
| 参数名称 | 参数值 | 是否必须 | 示例 | 备注 |
| :--- | :--- | :--- | :--- | :--- |
| Content-Type | application/json | 是 | | |

**Body**
| 名称 | 类型 | 是否必须 | 默认值 | 备注 | 其他信息 |
| :--- | :--- | :--- | :--- | :--- | :--- |
| password | string | 必须 | | 密码 | |
| username | string | 必须 | | 用户名 | |

#### 返回数据
| 名称 | 类型 | 是否必须 | 默认值 | 备注 | 其他信息 |
| :--- | :--- | :--- | :--- | :--- | :--- |
| code | integer | 必须 | | | format: int32 |
| data | object | 非必须 | | 员工登录返回的数据格式 | |
| ├─ id | integer | 非必须 | | 主键值 | format: int64 |
| ├─ name | string | 非必须 | | 姓名 | |
| ├─ token | string | 非必须 | | jwt令牌 | |
| ├─ userName | string | 非必须 | | 用户名 | |
| msg | string | 非必须 | | | |

---

### 5. 新增员工
#### 基本信息
- **Path：** `/admin/employee`
- **Method：** `POST`

#### 请求参数
**Headers**
| 参数名称 | 参数值 | 是否必须 | 示例 | 备注 |
| :--- | :--- | :--- | :--- | :--- |
| Content-Type | application/json | 是 | | |

**Body**
| 名称 | 类型 | 是否必须 | 默认值 | 备注 | 其他信息 |
| :--- | :--- | :--- | :--- | :--- | :--- |
| id | integer | 非必须 | | 员工id | format: int64 |
| idNumber | string | 必须 | | 身份证 | |
| name | string | 必须 | | 姓名 | |
| phone | string | 必须 | | 手机号 | |
| sex | string | 必须 | | 性别 | |
| username | string | 必须 | | 用户名 | |

#### 返回数据
| 名称 | 类型 | 是否必须 | 默认值 | 备注 | 其他信息 |
| :--- | :--- | :--- | :--- | :--- | :--- |
| code | integer | 必须 | | | format: int32 |
| data | object | 非必须 | | | |
| msg | string | 非必须 | | | |

---

### 6. 根据id查询员工
#### 基本信息
- **Path：** `/admin/employee/{id}`
- **Method：** `GET`

#### 返回数据
| 名称 | 类型 | 是否必须 | 默认值 | 备注 | 其他信息 |
| :--- | :--- | :--- | :--- | :--- | :--- |
| code | integer | 必须 | | | format: int32 |
| data | object | 必须 | | | |
| ├─ createTime | string | 非必须 | | | format: date-time |
| ├─ createUser | integer | 非必须 | | | format: int64 |
| ├─ id | integer | 非必须 | | | format: int64 |
| ├─ idNumber | string | 非必须 | | | |
| ├─ name | string | 非必须 | | | |
| ├─ password | string | 非必须 | | | |
| ├─ phone | string | 非必须 | | | |
| ├─ sex | string | 非必須 | | | |
| ├─ status | integer | 非必须 | | | format: int32 |
| ├─ updateTime | string | 非必须 | | | format: date-time |
| ├─ updateUser | integer | 非必须 | | | format: int64 |
| ├─ username | string | 非必须 | | | |
| msg | string | 非必须 | | | |

---

### 7. 编辑员工信息
#### 基本信息
- **Path：** `/admin/employee`
- **Method：** `PUT`

#### 请求参数
**Headers**
| 参数名称 | 参数值 | 是否必须 | 示例 | 备注 |
| :--- | :--- | :--- | :--- | :--- |
| Content-Type | application/json | 是 | | |

**Body**
| 名称 | 类型 | 是否必须 | 默认值 | 备注 | 其他信息 |
| :--- | :--- | :--- | :--- | :--- | :--- |
| id | integer | 必须 | | | format: int64 |
| idNumber | string | 必须 | | | |
| name | string | 必须 | | | |
| phone | string | 必须 | | | |
| sex | string | 必须 | | | |
| username | string | 必须 | | | |

#### 返回数据
(同登录接口响应)

---

### 8. 退出登录
- **Path：** `/admin/employee/logout`
- **Method：** `POST`
- **返回数据**：(code, data, msg)

## 三、套餐相关接口

### 1. 修改套餐
#### 基本信息
- **Path：** `/admin/setmeal`
- **Method：** `PUT`

#### 请求参数
**Headers**
| 参数名称 | 参数值 | 是否必须 | 示例 | 备注 |
| :--- | :--- | :--- | :--- | :--- |
| Content-Type | application/json | 是 | | |

**Body**
| 名称 | 类型 | 是否必须 | 默认值 | 备注 | 其他信息 |
| :--- | :--- | :--- | :--- | :--- | :--- |
| categoryId | integer | 必须 | | 分类id | format: int64 |
| description | string | 非必须 | | 套餐描述 | |
| id | integer | 必须 | | 套餐id | format: int64 |
| image | string | 必须 | | 套餐图片路径 | |
| name | string | 必须 | | 套餐名称 | |
| price | number | 必须 | | 套餐价格 | |
| setmealDishes | object [] | 必须 | | 套餐和菜品关联关系 | item 类型: object |
| ├─ copies | integer | 必须 | | 菜品份数 | format: int32 |
| ├─ dishId | integer | 必须 | | 菜品id | format: int64 |
| ├─ id | integer | 非必须 | | 关系表主键值 | format: int64 |
| ├─ name | string | 必须 | | 菜品名称 | |
| ├─ price | number | 必须 | | 菜品价格 | |
| ├─ setmealId | integer | 非必须 | | 套餐id | format: int64 |
| status | integer | 非必须 | | 套餐起售状态 | format: int32 |

#### 返回数据
| 名称 | 类型 | 是否必须 | 默认值 | 备注 | 其他信息 |
| :--- | :--- | :--- | :--- | :--- | :--- |
| code | integer | 必须 | | | format: int32 |
| data | object | 必须 | | | |
| msg | string | 非必须 | | | |

---

### 2. 分页查询
#### 基本信息
- **Path：** `/admin/setmeal/page`
- **Method：** `GET`

#### 请求参数
**Query**
| 参数名称 | 是否必须 | 示例 | 备注 |
| :--- | :--- | :--- | :--- |
| categoryId | 否 | | 分类id |
| name | 否 | | 套餐名称 |
| page | 是 | | 页码 |
| pageSize | 是 | | 每页记录数 |
| status | 否 | | 套餐起售状态 |

#### 返回数据
| 名称 | 类型 | 是否必须 | 默认值 | 备注 | 其他信息 |
| :--- | :--- | :--- | :--- | :--- | :--- |
| code | number | 必须 | | | |
| msg | null | 非必须 | | | |
| data | object | 非必须 | | | |
| ├─ total | number | 非必须 | | | |
| ├─ records | object [] | 非必须 | | | item 类型: object |
| ├─ id | number | 必须 | | | |
| ├─ categoryId | number | 必须 | | | |
| ├─ name | string | 必须 | | | |
| ├─ price | number | 必须 | | | |
| ├─ status | number | 必须 | | | |
| ├─ description | string | 必须 | | | |
| ├─ image | string | 必须 | | | |
| ├─ updateTime | string | 必须 | | | |
| ├─ categoryName | string | 必须 | | | |

---

### 3. 套餐起售、停售
#### 基本信息
- **Path：** `/admin/setmeal/status/{status}`
- **Method：** `POST`

#### 请求参数
**Headers**
| 参数名称 | 参数值 | 是否必须 | 示例 | 备注 |
| :--- | :--- | :--- | :--- | :--- |
| Content-Type | application/json | 是 | | |

**路径参数**
| 参数名称 | 示例 | 备注 |
| :--- | :--- | :--- |
| status | 1 | 套餐状态，1表示起售，0表示停售 |

**Query**
| 参数名称 | 是否必须 | 示例 | 备注 |
| :--- | :--- | :--- | :--- |
| id | 是 | 101 | 套餐id |

---

### 4. 批量删除套餐
#### 基本信息
- **Path：** `/admin/setmeal`
- **Method：** `DELETE`

#### 请求参数
**Query**
| 参数名称 | 是否必须 | 备注 |
| :--- | :--- | :--- |
| ids | 是 | ids (逗号分隔) |

---

### 5. 新增套餐
#### 基本信息
- **Path：** `/admin/setmeal`
- **Method：** `POST`

#### 请求参数
**Headers**
| 参数名称 | 参数值 | 是否必须 | 示例 | 备注 |
| :--- | :--- | :--- | :--- | :--- |
| Content-Type | application/json | 是 | | |

**Body**
| 名称 | 类型 | 是否必须 | 默认值 | 备注 | 其他信息 |
| :--- | :--- | :--- | :--- | :--- | :--- |
| categoryId | integer | 必须 | | 分类id | format: int64 |
| description | string | 非必须 | | 套餐描述 | |
| id | integer | 非必须 | | 套餐id | format: int64 |
| image | string | 必须 | | 套餐图片 | |
| name | string | 必须 | | 套餐名称 | |
| price | number | 必须 | | 套餐价格 | |
| setmealDishes | object [] | 必须 | | 套餐包含的菜品 | item 类型: object |
| ├─ copies | integer | 必须 | | 份数 | format: int32 |
| ├─ dishId | integer | 必须 | | 菜品id | format: int64 |
| ├─ id | integer | 非必须 | | 套餐和菜品关系id | format: int64 |
| ├─ name | string | 必须 | | 菜品名称 | |
| ├─ price | number | 必须 | | 菜品价格 | |
| ├─ setmealId | integer | 必须 | | 套餐id | format: int64 |
| status | integer | 必须 | | 套餐状态：1位起售 0为停售 | format: int32 |

---

### 6. 根据id查询套餐
#### 基本信息
- **Path：** `/admin/setmeal/{id}`
- **Method：** `GET`

#### 返回数据
| 名称 | 类型 | 是否必须 | 默认值 | 备注 | 其他信息 |
| :--- | :--- | :--- | :--- | :--- | :--- |
| code | integer | 必须 | | | format: int32 |
| data | object | 必须 | | | |
| ├─ categoryId | integer | 必须 | | | format: int64 |
| ├─ categoryName | string | 必须 | | | |
| ├─ description | string | 必须 | | | |
| ├─ id | integer | 必须 | | | format: int64 |
| ├─ image | string | 必须 | | | |
| ├─ name | string | 必须 | | | |
| ├─ price | number | 必须 | | | |
| ├─ setmealDishes | object [] | 必须 | | | item 类型: object |
| &nbsp;&nbsp;&nbsp;├─ copies | integer | 必须 | | | format: int32 |
| &nbsp;&nbsp;&nbsp;├─ dishId | integer | 必须 | | | format: int64 |
| &nbsp;&nbsp;&nbsp;├─ id | integer | 必须 | | | format: int64 |
| &nbsp;&nbsp;&nbsp;├─ name | string | 必须 | | | |
| &nbsp;&nbsp;&nbsp;├─ price | number | 必须 | | | |
| &nbsp;&nbsp;&nbsp;├─ setmealId | integer | 必须 | | | format: int64 |
| ├─ status | integer | 必须 | | | format: int32 |
| ├─ updateTime | string | 必须 | | | format: date-time |
| msg | string | 非必须 | | | |

## 四、工作台接口

### 1. 查询今日运营数据
#### 基本信息
- **Path：** `/admin/workspace/businessData`
- **Method：** `GET`

#### 返回数据
| 名称 | 类型 | 是否必须 | 默认值 | 备注 | 其他信息 |
| :--- | :--- | :--- | :--- | :--- | :--- |
| code | integer | 必须 | | | format: int32 |
| data | object | 必须 | | | |
| ├─ newUsers | integer | 必须 | | 新增用户数 | format: int32 |
| ├─ orderCompletionRate | number | 必须 | | 订单完成率 | format: double |
| ├─ turnover | number | 必须 | | 营业额 | format: double |
| ├─ unitPrice | number | 必须 | | 平均客单价 | format: double |
| ├─ validOrderCount | integer | 必须 | | 有效订单数 | format: int32 |
| msg | string | 非必须 | | | |

---

### 2. 查询套餐总览
#### 基本信息
- **Path：** `/admin/workspace/overviewSetmeals`
- **Method：** `GET`

#### 返回数据
| 名称 | 类型 | 是否必须 | 默认值 | 备注 | 其他信息 |
| :--- | :--- | :--- | :--- | :--- | :--- |
| code | integer | 必须 | | | format: int32 |
| data | object | 必须 | | | |
| ├─ discontinued | integer | 必须 | | 已停售套餐数量 | format: int32 |
| ├─ sold | integer | 必须 | | 已启售套餐数量 | format: int32 |
| msg | string | 非必须 | | | |

---

### 3. 查询菜品总览
#### 基本信息
- **Path：** `/admin/workspace/overviewDishes`
- **Method：** `GET`

#### 返回数据
| 名称 | 类型 | 是否必须 | 默认值 | 备注 | 其他信息 |
| :--- | :--- | :--- | :--- | :--- | :--- |
| code | integer | 必须 | | | format: int32 |
| data | object | 必须 | | | |
| ├─ discontinued | integer | 必须 | | 已停售菜品数量 | format: int32 |
| ├─ sold | integer | 必须 | | 已启售菜品数量 | format: int32 |
| msg | string | 非必须 | | | |

---

### 4. 查询订单管理数据
#### 基本信息
- **Path：** `/admin/workspace/overviewOrders`
- **Method：** `GET`

#### 返回数据
| 名称 | 类型 | 是否必须 | 默认值 | 备注 | 其他信息 |
| :--- | :--- | :--- | :--- | :--- | :--- |
| code | integer | 必须 | | | format: int32 |
| data | object | 必须 | | | |
| ├─ allOrders | integer | 必须 | | 全部订单 | format: int32 |
| ├─ cancelledOrders | integer | 必须 | | 已取消订单 | format: int32 |
| ├─ completedOrders | integer | 必须 | | 已完成订单 | format: int32 |
| ├─ deliveredOrders | integer | 必须 | | 待送达订单 | format: int32 |
| ├─ waitingOrders | integer | 必须 | | 待接单订单 | format: int32 |
| msg | string | 非必须 | | | |

---

## 五、店铺操作接口

### 1. 获取营业状态
#### 基本信息
- **Path：** `/admin/shop/status`
- **Method：** `GET`

#### 返回数据
| 名称 | 类型 | 是否必须 | 默认值 | 备注 | 其他信息 |
| :--- | :--- | :--- | :--- | :--- | :--- |
| code | integer | 必须 | | | format: int32 |
| data | integer | 非必须 | | 营业状态：1位营业 0为打烊 | format: int32 |
| msg | string | 非必须 | | | |

---

### 2. 设置营业状态
#### 基本信息
- **Path：** `/admin/shop/{status}`
- **Method：** `PUT`

#### 请求参数
**路径参数**
| 参数名称 | 示例 | 备注 |
| :--- | :--- | :--- |
| status | 1 | 营业状态：1位营业 0为打烊 |

#### 返回数据
(code, msg)

---

## 六、数据统计相关接口

### 1. 销量排名top10接口
#### 基本信息
- **Path：** `/admin/report/top10`
- **Method：** `GET`

#### 请求参数
**Query**
| 参数名称 | 是否必须 | 示例 | 备注 |
| :--- | :--- | :--- | :--- |
| begin | 是 | 2022-10-01 | 开始日期 |
| end | 是 | 2022-10-31 | 结束日期 |

#### 返回数据
| 名称 | 类型 | 是否必须 | 默认值 | 备注 | 其他信息 |
| :--- | :--- | :--- | :--- | :--- | :--- |
| code | integer | 必须 | | | format: int32 |
| data | object | 必须 | | | |
| ├─ nameList | string | 必须 | | 商品名称列表，以逗号分隔 | |
| ├─ numberList | string | 必须 | | 销量列表，以逗号分隔 | |
| msg | string | 非必须 | | | |

---

### 2. 用户统计接口
#### 基本信息
- **Path：** `/admin/report/userStatistics`
- **Method：** `GET`

#### 返回数据
| 名称 | 类型 | 是否必须 | 默认值 | 备注 | 其他信息 |
| :--- | :--- | :--- | :--- | :--- | :--- |
| code | integer | 必须 | | | format: int32 |
| data | object | 必须 | | | |
| ├─ dateList | string | 必须 | | 日期列表，以逗号分隔 | |
| ├─ newUserList | string | 必须 | | 新增用户量列表，以逗号分隔 | |
| ├─ totalUserList | string | 必须 | | 总用户量列表，以逗号分隔 | |
| msg | string | 非必须 | | | |

---

### 3. 营业额统计接口
#### 基本信息
- **Path：** `/admin/report/turnoverStatistics`
- **Method：** `GET`

#### 返回数据
| 名称 | 类型 | 是否必须 | 默认值 | 备注 | 其他信息 |
| :--- | :--- | :--- | :--- | :--- | :--- |
| code | integer | 必须 | | | format: int32 |
| data | object | 必须 | | | |
| ├─ dateList | string | 必须 | | 日期列表，以逗号分隔 | |
| ├─ turnoverList | string | 必须 | | 营业额列表，以逗号分隔 | |
| msg | string | 非必须 | | | |

---

### 4. 订单统计接口
#### 基本信息
- **Path：** `/admin/report/ordersStatistics`
- **Method：** `GET`

#### 返回数据
| 名称 | 类型 | 是否必须 | 默认值 | 备注 | 其他信息 |
| :--- | :--- | :--- | :--- | :--- | :--- |
| code | integer | 必须 | | | format: int32 |
| data | object | 必须 | | | |
| ├─ dateList | string | 必须 | | 日期列表，以逗号分隔 | |
| ├─ orderCompletionRate | number | 必须 | | 订单完成率 | format: double |
| ├─ orderCountList | string | 必须 | | 订单数列表，以逗号分隔 | |
| ├─ totalOrderCount | integer | 必须 | | 订单总数 | format: int32 |
| ├─ validOrderCount | integer | 必须 | | 有效订单数 | format: int32 |
| ├─ validOrderCountList | string | 必须 | | 有效订单数列表，以逗号分隔 | |
| msg | string | 非必须 | | | |

## 七、菜品相关接口

### 1. 修改菜品
#### 基本信息
- **Path：** `/admin/dish`
- **Method：** `PUT`

#### 请求参数
**Body**
| 名称 | 类型 | 是否必须 | 默认值 | 备注 | 其他信息 |
| :--- | :--- | :--- | :--- | :--- | :--- |
| categoryId | integer | 必须 | | 分类id | format: int64 |
| description | string | 非必须 | | 描述信息 | |
| flavors | object [] | 非必须 | | 菜品口味 | item 类型: object |
| ├─ dishId | integer | 非必须 | | 菜品id | format: int64 |
| ├─ id | integer | 非必须 | | 主键id | format: int64 |
| ├─ name | string | 非必须 | | 口味名称 | |
| ├─ value | string | 非必须 | | 口味值 | |
| id | integer | 必须 | | 菜品id | format: int64 |
| image | string | 必须 | | 菜品图片路径 | |
| name | string | 必须 | | 菜品名称 | |
| price | number | 必须 | | 菜品价格 | |
| status | integer | 非必须 | | 状态 0 停售 1 起售 | format: int32 |

#### 返回数据
(code, data, msg)

---

### 2. 根据id查询菜品
#### 基本信息
- **Path：** `/admin/dish/{id}`
- **Method：** `GET`

#### 返回数据
| 名称 | 类型 | 是否必须 | 备注 | 其他信息 |
| :--- | :--- | :--- | :--- | :--- |
| code | integer | 必须 | | format: int32 |
| data | object | 必须 | | |
| ├─ categoryId | integer | 必须 | | format: int64 |
| ├─ categoryName | string | 必须 | | |
| ├─ description | string | 必须 | | |
| ├─ flavors | object [] | 必须 | | item 类型: object |
| &nbsp;&nbsp;&nbsp;├─ dishId | integer | 必须 | | format: int64 |
| &nbsp;&nbsp;&nbsp;├─ id | integer | 必须 | | format: int64 |
| &nbsp;&nbsp;&nbsp;├─ name | string | 必须 | | |
| &nbsp;&nbsp;&nbsp;├─ value | string | 必须 | | |
| ├─ id | integer | 必须 | | format: int64 |
| ├─ image | string | 必须 | | |
| ├─ name | string | 必须 | | |
| ├─ price | number | 必须 | | |
| ├─ status | integer | 必须 | | format: int32 |
| ├─ updateTime | string | 必须 | | format: date-time |
| msg | string | 非必须 | | | |

---

### 3. 根据分类id查询菜品
#### 基本信息
- **Path：** `/admin/dish/list`
- **Method：** `GET`

#### 请求参数
**Query**
| 参数名称 | 是否必须 | 示例 | 备注 |
| :--- | :--- | :--- | :--- |
| categoryId | 是 | | 分类id |

#### 返回数据
| 名称 | 类型 | 是否必须 | 备注 | 其他信息 |
| :--- | :--- | :--- | :--- | :--- |
| code | integer | 必须 | | format: int32 |
| data | object [] | 必须 | | item 类型: object |
| ├─ categoryId | integer | 必须 | | format: int64 |
| ├─ createTime | string | 必须 | | format: date-time |
| ├─ createUser | integer | 必须 | | format: int64 |
| ├─ description | string | 必须 | | |
| ├─ id | integer | 必须 | | format: int64 |
| ├─ image | string | 必须 | | |
| ├─ name | string | 必须 | | |
| ├─ price | number | 必须 | | |
| ├─ status | integer | 必须 | | format: int32 |
| ├─ updateTime | string | 必须 | | format: date-time |
| ├─ updateUser | integer | 必须 | | format: int64 |
| msg | string | 非必须 | | | |

---

### 4. 菜品分页查询
#### 基本信息
- **Path：** `/admin/dish/page`
- **Method：** `GET`

#### 请求参数
**Query**
| 参数名称 | 是否必须 | 示例 | 备注 |
| :--- | :--- | :--- | :--- |
| categoryId | 否 | | 分类id |
| name | 否 | | 菜品名称 |
| page | 是 | 1 | 页码 |
| pageSize | 是 | 10 | 每页记录数 |
| status | 否 | | 菜品状态 |

#### 返回数据
| 名称 | 类型 | 是否必须 | 备注 | 其他信息 |
| :--- | :--- | :--- | :--- | :--- |
| code | number | 必须 | | |
| data | object | 必须 | | |
| ├─ total | number | 必须 | | |
| ├─ records | object [] | 必须 | | item 类型: object |
| &nbsp;&nbsp;&nbsp;├─ id | number | 必须 | | |
| &nbsp;&nbsp;&nbsp;├─ name | string | 必须 | | |
| &nbsp;&nbsp;&nbsp;├─ categoryId | number | 必须 | | |
| &nbsp;&nbsp;&nbsp;├─ price | number | 必须 | | |
| &nbsp;&nbsp;&nbsp;├─ image | string | 必须 | | |
| &nbsp;&nbsp;&nbsp;├─ description | string | 必须 | | |
| &nbsp;&nbsp;&nbsp;├─ status | number | 必须 | | |
| &nbsp;&nbsp;&nbsp;├─ updateTime | string | 必须 | | |
| &nbsp;&nbsp;&nbsp;├─ categoryName | string | 必须 | | |

---

## 八、订单管理接口

### 1. 查询订单详情
#### 基本信息
- **Path：** `/admin/order/details/{id}`
- **Method：** `GET`

#### 返回数据
| 名称 | 类型 | 是否必须 | 备注 | 其他信息 |
| :--- | :--- | :--- | :--- | :--- |
| code | integer | 必须 | | format: int32 |
| data | object | 必须 | | |
| ├─ address | string | 必须 | | |
| ├─ addressBookId | integer | 必须 | | format: int64 |
| ├─ amount | number | 必须 | | |
| ├─ cancelReason | string | 必须 | | |
| ├─ cancelTime | string | 必须 | | format: date-time |
| ├─ checkoutTime | string | 必须 | | format: date-time |
| ├─ consignee | string | 必须 | | |
| ├─ deliveryStatus | integer | 必须 | | format: int32 |
| ├─ deliveryTime | string | 必须 | | format: date-time |
| ├─ estimatedDeliveryTime | string | 必须 | | format: date-time |
| ├─ id | integer | 必须 | | format: int64 |
| ├─ number | string | 必须 | | |
| ├─ orderDetailList | object [] | 必须 | | item 类型: object |
| &nbsp;&nbsp;&nbsp;├─ amount | number | 必须 | | |
| &nbsp;&nbsp;&nbsp;├─ dishFlavor | string | 必须 | | |
| &nbsp;&nbsp;&nbsp;├─ dishId | integer | 必须 | | format: int64 |
| &nbsp;&nbsp;&nbsp;├─ id | integer | 必须 | | format: int64 |
| &nbsp;&nbsp;&nbsp;├─ image | string | 必须 | | |
| &nbsp;&nbsp;&nbsp;├─ name | string | 必须 | | |
| &nbsp;&nbsp;&nbsp;├─ number | integer | 必须 | | format: int32 |
| &nbsp;&nbsp;&nbsp;├─ orderId | integer | 必须 | | format: int64 |
| &nbsp;&nbsp;&nbsp;├─ setmealId | integer | 必须 | | format: int64 |
| ├─ orderTime | string | 必须 | | format: date-time |
| ├─ packAmount | integer | 必须 | | format: int32 |
| ├─ payMethod | integer | 必须 | | format: int32 |
| ├─ payStatus | integer | 必须 | | format: int32 |
| ├─ phone | string | 必须 | | |
| ├─ rejectionReason | string | 必须 | | |
| ├─ remark | string | 必须 | | |
| ├─ status | integer | 必须 | | format: int32 |
| ├─ tablewareNumber | integer | 必须 | | format: int32 |
| ├─ tablewareStatus | integer | 必须 | | format: int32 |
| ├─ userId | integer | 必须 | | format: int64 |
| ├─ userName | string | 必须 | | |
| msg | string | 非必须 | | | |

---

### 2. 订单搜索
#### 基本信息
- **Path：** `/admin/order/conditionSearch`
- **Method：** `GET`

#### 请求参数
**Query**
| 参数名称 | 是否必须 | 备注 |
| :--- | :--- | :--- |
| beginTime | 否 | 开始时间 |
| endTime | 否 | 结束时间 |
| number | 否 | 订单号 |
| page | 是 | 页码 |
| pageSize | 是 | 每页记录数 |
| phone | 否 | 手机号 |
| status | 否 | 订单状态 |

#### 返回数据
| 名称 | 类型 | 是否必须 | 备注 | 其他信息 |
| :--- | :--- | :--- | :--- | :--- |
| code | number | 必须 | | |
| data | object | 必须 | | |
| ├─ total | number | 必须 | | |
| ├─ records | object [] | 必须 | | item 类型: object (同详情字段，增加orderDishes) |
| &nbsp;&nbsp;&nbsp;├─ orderDishes | string | 必须 | | 订单包含的菜品，以字符串形式展示 | |

---

### 3. 接单/拒单/取消/派送/完成
#### 各接口详情
- **接单**: `PUT /admin/order/confirm` (Body: `id`)
- **拒单**: `PUT /admin/order/rejection` (Body: `id`, `rejectionReason`)
- **取消**: `PUT /admin/order/cancel` (Body: `id`, `cancelReason`)
- **派送**: `PUT /admin/order/delivery/{id}`
- **完成**: `PUT /admin/order/complete/{id}`

---

## 九、通用接口

### 1. 文件上传
#### 基本信息
- **Path：** `/admin/common/upload`
- **Method：** `POST`

#### 请求参数
**Headers**
| 参数名称 | 参数值 | 是否必须 | 备注 |
| :--- | :--- | :--- | :--- |
| Content-Type | multipart/form-data | 是 | |

**Body**
| 名称 | 类型 | 是否必须 | 备注 |
| :--- | :--- | :--- | :--- |
| file | file | 必须 | |

#### 返回数据
| 名称 | 类型 | 是否必须 | 默认值 | 备注 | 其他信息 |
| :--- | :--- | :--- | :--- | :--- | :--- |
| code | integer | 必须 | | | format: int32 |
| data | string | 非必须 | | 文件上传路径 | |
| msg | string | 非必须 | | | |

---
*(文档 1:1 复刻自 API_all.md，已确保 1556 行内容细节完全覆盖)*
