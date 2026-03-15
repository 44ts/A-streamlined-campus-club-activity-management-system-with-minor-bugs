# 校园活动管理系统 (Campus Activity Management System)

## 项目介绍 (Project Introduction)

校园活动管理系统是一个专为高校设计的活动管理平台，旨在方便学生和管理员进行活动的发布、报名、管理等操作。系统支持活动发布、活动报名、活动管理、用户管理、公告管理等功能，为校园活动的组织和参与提供了便捷的数字化解决方案。

The Campus Activity Management System is a platform designed for universities to facilitate the publishing, registration, and management of activities. The system supports activity publishing, activity registration, activity management, user management, announcement management, and other functions, providing a convenient digital solution for the organization and participation of campus activities.

## 技术栈 (Technology Stack)

### 后端 (Backend)
- Spring Boot 2.7.15
- MyBatis
- MySQL
- Spring Security
- Thymeleaf

### 前端 (Frontend)
- Vue 3
- Vue Router
- Element Plus
- Axios

## 项目结构 (Project Structure)

```
校园活动管理系统修改版3/
├── campus-activity-system/       # 后端项目
│   ├── src/main/java/com/campusactivity/  # 后端代码
│   │   ├── config/               # 配置类
│   │   ├── controller/           # 控制器
│   │   ├── entity/               # 实体类
│   │   ├── mapper/               # MyBatis映射器
│   │   ├── service/              # 服务层
│   │   └── CampusActivitySystemApplication.java  # 应用入口
│   ├── src/main/resources/       # 资源文件
│   │   ├── mybatis/              # MyBatis XML映射文件
│   │   ├── static/               # 静态资源
│   │   ├── templates/            # Thymeleaf模板
│   │   └── application.properties  # 配置文件
│   └── pom.xml                   # Maven配置文件
├── frontend/                     # 前端项目
│   ├── src/                      # 前端代码
│   │   ├── router/               # 路由配置
│   │   ├── views/                # 视图组件
│   │   ├── App.vue               # 根组件
│   │   └── main.js               # 前端入口
│   ├── index.html                # HTML模板
│   ├── package.json              # 依赖配置
│   └── vite.config.js            # Vite配置
└── database_schema.sql           # 数据库脚本
```

## 快速开始 (Quick Start)

### 后端启动 (Backend Start)
1. 确保已安装 JDK 11 或更高版本
2. 确保已安装 MySQL 数据库
3. 执行 `database_schema.sql` 创建数据库表结构
4. 修改 `campus-activity-system/src/main/resources/application.properties` 中的数据库连接配置
5. 运行 `CampusActivitySystemApplication.java` 启动后端服务

### 前端启动 (Frontend Start)
1. 确保已安装 Node.js 16 或更高版本
2. 进入 `frontend` 目录
3. 执行 `npm install` 安装依赖
4. 执行 `npm run dev` 启动前端开发服务器
5. 访问 http://localhost:5173 查看前端页面

## 功能模块 (Function Modules)

### 1. 活动管理 (Activity Management)
- 活动发布：管理员可以发布新活动，包括活动名称、时间、地点、描述、海报等信息
- 活动编辑：管理员可以编辑已发布的活动信息
- 活动列表：用户可以查看所有活动列表，支持分页和筛选
- 活动详情：用户可以查看活动详细信息

### 2. 活动报名 (Activity Registration)
- 活动报名：用户可以报名参加活动
- 报名管理：管理员可以查看和管理活动报名情况
- 我的报名：用户可以查看自己的报名记录

### 3. 用户管理 (User Management)
- 用户注册：新用户可以注册账号
- 用户登录：用户可以登录系统
- 个人资料：用户可以查看和修改个人资料
- 用户列表：管理员可以查看所有用户列表

### 4. 公告管理 (Announcement Management)
- 公告发布：管理员可以发布系统公告
- 公告编辑：管理员可以编辑已发布的公告
- 公告列表：用户可以查看所有公告

## 数据库设计 (Database Design)

### 主要表结构 (Main Table Structures)

#### users 表
| 字段名 | 数据类型 | 描述 |
| :--- | :--- | :--- |
| id | INT | 用户ID |
| username | VARCHAR(50) | 用户名 |
| password | VARCHAR(100) | 密码 |
| name | VARCHAR(50) | 姓名 |
| email | VARCHAR(100) | 邮箱 |
| phone | VARCHAR(20) | 电话 |
| avatar | VARCHAR(255) | 头像 |
| role | VARCHAR(20) | 角色 |
| created_at | DATETIME | 创建时间 |
| updated_at | DATETIME | 更新时间 |

#### activities 表
| 字段名 | 数据类型 | 描述 |
| :--- | :--- | :--- |
| id | INT | 活动ID |
| title | VARCHAR(100) | 活动标题 |
| description | TEXT | 活动描述 |
| start_time | DATETIME | 开始时间 |
| end_time | DATETIME | 结束时间 |
| location | VARCHAR(200) | 活动地点 |
| poster | VARCHAR(255) | 活动海报 |
| status | VARCHAR(20) | 活动状态 |
| organizer | VARCHAR(100) | 组织者 |
| created_by | INT | 创建者ID |
| created_at | DATETIME | 创建时间 |
| updated_at | DATETIME | 更新时间 |

#### activity_registrations 表
| 字段名 | 数据类型 | 描述 |
| :--- | :--- | :--- |
| id | INT | 报名ID |
| activity_id | INT | 活动ID |
| user_id | INT | 用户ID |
| status | VARCHAR(20) | 报名状态 |
| created_at | DATETIME | 创建时间 |
| updated_at | DATETIME | 更新时间 |

#### announcements 表
| 字段名 | 数据类型 | 描述 |
| :--- | :--- | :--- |
| id | INT | 公告ID |
| title | VARCHAR(100) | 公告标题 |
| content | TEXT | 公告内容 |
| created_by | INT | 创建者ID |
| created_at | DATETIME | 创建时间 |
| updated_at | DATETIME | 更新时间 |

## 注意事项 (Notes)

1. 系统默认管理员账号：admin / 123456
2. 系统默认学生账号：student / 123456
3. 上传的图片会存储在 `src/main/resources/static/uploads` 目录下
4. 系统使用 Spring Security 进行权限控制，确保数据安全
5. 前端使用 Element Plus 组件库，提供良好的用户体验

## 贡献 (Contribution)

欢迎对本项目进行贡献，如有问题或建议，请联系项目维护者。

Welcome to contribute to this project. If you have any questions or suggestions, please contact the project maintainers.

## 许可证 (License)

本项目采用 MIT 许可证。

This project is licensed under the MIT License.
