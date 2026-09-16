# tlias 员工管理系统（学习项目）

基于 Spring Boot + MyBatis 的三层架构练习项目，实现**部门、员工、员工经历**三个模块的增删改查与分页查询。

## 技术栈

| 组件 | 版本 / 说明 |
| --- | --- |
| JDK | 17 |
| Spring Boot | 3.2.5 |
| MyBatis | mybatis-spring-boot-starter 3.0.3 |
| PageHelper | pagehelper-spring-boot-starter 2.1.0（分页插件） |
| MySQL | 8.x（mysql-connector-j） |
| Lombok | 简化实体类样板代码 |
| Maven | 依赖管理与构建 |

## 项目结构

```
src/main/java/com/demo
├── controller/          # 表现层：接收请求、返回统一响应
│   ├── DeptController.java
│   ├── EmpController.java
│   └── EmpExprController.java
├── service/             # 业务层：接口定义
│   ├── DeptService.java
│   ├── EmpService.java
│   ├── EmpExprService.java
│   └── impl/            # 业务层实现
├── mapper/              # 持久层：MyBatis（简单 SQL 用注解，动态 SQL 用 XML）
│   ├── DeptMapper.java
│   ├── EmpMapper.java
│   └── EmpExprMapper.java
└── pojo/                # 实体类、查询参数与通用返回结果
    ├── Dept.java / Emp.java / EmpExpr.java
    ├── EmpQueryParam.java  # 员工条件分页查询参数
    ├── Result.java      # 统一响应结果 {code, msg, data}
    └── PageResult.java  # 分页结果 {total, rows}

src/main/resources/mapper/EmpMapper.xml   # EmpMapper 的动态 SQL
```

分层调用关系：`Controller → Service → Mapper → MySQL`。

## 数据库准备

1. 创建数据库 `tlias`
2. 执行 `db/tlias.sql`（部门表 dept）
3. 执行 `src/main/resources/sql/emp.sql`（员工表 emp、员工经历表 emp_expr，含示例数据）

> 表间关联采用**逻辑外键**：只建索引、不建物理 `FOREIGN KEY`，关联完整性由应用层校验（例如删除部门前先校验该部门下是否还有员工）。

## 运行方式

1. 配置数据库密码。项目使用环境变量注入，避免密码写入仓库：

   `application.yml` 中为 `password: ${DB_PASSWORD}`

   在启动配置里添加环境变量 `DB_PASSWORD=你的数据库密码` 即可。

2. 数据库连接配置（默认）：

   ```yaml
   url: jdbc:mysql://localhost:3306/tlias
   username: root
   ```

3. 启动 `SpringbootDemoApplication`，服务默认端口 `8080`。

## 接口清单

统一响应格式：

```json
{ "code": 1, "msg": "success", "data": {} }
```

### 部门

| 方法 | 路径 | 说明 |
| --- | --- | --- |
| GET | `/depts` | 查询全部部门 |
| GET | `/depts/{id}` | 根据 id 查询部门 |
| POST | `/depts` | 新增部门 |
| PUT | `/depts` | 修改部门 |
| DELETE | `/depts?id=1` | 删除部门（部门下有员工时禁止删除） |

### 员工

| 方法 | 路径 | 说明 |
| --- | --- | --- |
| GET | `/emps?page=1&pageSize=10` | 条件分页查询员工（联表带部门名称） |
| GET | `/emps/all` | 查询全部员工 |
| GET | `/emps/{id}` | 根据 id 查询员工 |
| POST | `/emps` | 新增员工 |
| PUT | `/emps` | 修改员工 |
| DELETE | `/emps/{id}` | 删除员工 |

`GET /emps` 支持的查询参数（均为可选，不传则该条件不参与筛选）：

| 参数 | 类型 | 默认值 | 说明 |
| --- | --- | --- | --- |
| page | int | 1 | 页码 |
| pageSize | int | 10 | 每页条数 |
| name | string | — | 姓名，模糊匹配 |
| gender | int | — | 性别：1 男 2 女 |
| begin | date | — | 入职日期范围开始，格式 `yyyy-MM-dd` |
| end | date | — | 入职日期范围结束，格式 `yyyy-MM-dd` |

示例：`GET /emps?page=1&pageSize=10&name=张&gender=1&begin=2020-01-01&end=2021-12-31`

### 员工经历

| 方法 | 路径 | 说明 |
| --- | --- | --- |
| GET | `/emps/{empId}/exprs` | 查询某员工的全部经历 |
| POST | `/emps/{empId}/exprs` | 新增某员工的一条经历 |
| GET | `/emps/exprs/{id}` | 根据 id 查询经历 |
| PUT | `/emps/exprs/{id}` | 修改经历 |
| DELETE | `/emps/exprs/{id}` | 删除经历 |

## 说明

本项目为学习用途，用于练习 Spring Boot 三层架构、MyBatis 注解式开发、联表查询与分页查询等基础能力。

分页由 PageHelper 插件实现：Service 层调用 `PageHelper.startPage` 后，紧随其后的那条查询会被自动改写为分页 SQL，总记录数也由插件一并查出，因此 Mapper 中不写 `limit` 与 `count`。

员工的条件分页查询使用 MyBatis XML 动态 SQL（`<if>` 标签）拼装条件，条件为空时不会拼入 SQL，所以不传任何条件时就退化为全量分页查询。
