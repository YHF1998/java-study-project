# Mybatis配置



## 1.引入依赖

最先要做的就是这个，没有依赖其他都是屁

```xml
 <!--导入依赖-->
 <dependencies>
    
    <dependency>
        <groupId>org.mybatis</groupId>
        <artifactId>mybatis</artifactId>
        <version>3.5.10</version>
    </dependency>

</dependencies>
```



## 2.pom加build

这是为了防止编译的时候，没有把子目录的xml和properties文件进行打包

如果没有打包，那Dao配置就白写了

```xml
 <!--在build中配置resource，防止资源导出失败的问题 比如mybatis.xml-->
    <build>
        <resources>
            <resource>
                <directory>src/main/java</directory>
                <includes>
                    <include>**/*.properties</include>
                    <include>**/*.xml</include>
                </includes>
                <filtering>true</filtering>
            </resource>

            <resource>
                <directory>src/main/resources</directory>
                <includes>
                    <include>**/*.properties</include>
                    <include>**/*.xml</include>
                </includes>
                <filtering>true</filtering>
            </resource>
        </resources>
    </build>
```



## 3.编写根配置文件

```
src/main/resources/mybatis-config.xml
```



```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<!--configuration 核心配置文件-->
<configuration>

    <!--environments内部可以配置多套环境 通过default属性值来确定哪个是默认环境,目前是development为默认环境-->
    <environments default="development">

        <environment id="development">
            <transactionManager type="JDBC"/>
            <dataSource type="POOLED">
                <!--驱动-->
                <property name="driver" value="com.mysql.cj.jdbc.Driver"/>
                <!--连接-->
                <property name="url"
                          value="jdbc:mysql://localhost:3400/javaweb?useSSL=true&amp;useUnicode=true&amp;characterEncoding=UTF-8"/>
                <property name="username" value="root"/>
                <property name="password" value="example"/>
            </dataSource>
        </environment>

        <!--  <environment id="test">
              <transactionManager type="JDBC"/>
              <dataSource type="POOLED">
                  <property name="driver" value="${driver}"/>
                  <property name="url" value="${url}"/>
                  <property name="username" value="${username}"/>
                  <property name="password" value="${password}"/>
              </dataSource>
          </environment>-->

    </environments>

    <!--每一个mapper.xml 都需要在mybatis核心配置文件中注册-->
    <mappers>
        <mapper resource="com/getdream/dao/UserMapper.xml"/>
    </mappers>
</configuration>



```



## 4.编写Dao和Mapper

```
com/getdream/dao/UserDao.java
com/getdream/dao/UserMapper.xml
```



```java
package com.getdream.dao;


import com.getdream.pojo.User;

import java.util.List;

public interface UserDao {
    List<User> getUserList();
}
```

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.getdream.dao.UserDao">
    <!--查询语句块-->
    <select id="getUserList" resultType="com.getdream.pojo.User">
        select *
        from user
    </select>
</mapper>
```

