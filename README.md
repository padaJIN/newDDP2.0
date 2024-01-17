### 1. 环境配置
  #### 更新项目所需依赖
    刷新pom.xml(DataProcess-springboot/pom.xml) 下载项目所需依赖
  ####  ip、端口、数据库配置(DataProcess-springboot/src/main/resources/application.yml)
    ip、端口可按需修改
    数据库默认连接无需修改

### 2. 启动项目
   运行项目启动类DataProcessSpringbootApplication (com/DataProcess/springboot/DataProcessSpringbootApplication.java)
### 3. 结构说明
  #### 项目配置类(com/DataProcess/springboot/config)
     公共常量配置(common.constans)、统一异常处理(exception)、jwt过滤器(jwt)、跨域处理请求(jwt)
  #### 工具类(com/DataProcess/springboot/utils)
     实体类代码生成器
   
