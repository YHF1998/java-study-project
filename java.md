# Mybatis



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





## 5.CURD

```
com/getdream/dao/UserDao.java
com/getdream/dao/UserMapper.xml
com/getdream/dao/UserDaoTest.java
```



```java
package com.getdream.dao;


import com.getdream.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserDao {

    //查询列表
    List<User> getUserList();

    //根据id查询信息
    User getUserByID(int id);

    int addUser(User user);

    int addUserWithMap(Map<String,Object> map);

    int updateUser(User user);

    int deleteUserByID(int id);


}

```



```xml
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!--namespace对应dao的interface-->
<mapper namespace="com.getdream.dao.UserDao">

    <!--新增-->
    <insert id="addUser" parameterType="com.getdream.pojo.User">
        insert into javaweb.user(id, name, pwd)
        values (#{id}, #{name}, #{pwd})
    </insert>

    <!--新增-map版-->
    <insert id="addUserWithMap" parameterType="map">
        insert into javaweb.user(name, pwd)
        values (#{name}, #{pwd})
    </insert>

    <!--更新-->
    <update id="updateUser" parameterType="com.getdream.pojo.User">
        update javaweb.user
        set name=#{name},
            pwd=#{pwd}
        where id = #{id}
    </update>

    <delete id="deleteUserByID" parameterType="int">
        delete
        from javaweb.user
        where id =
              #{id}
    </delete>

    <!--查询语句块 id值独赢interface里面的方法 resultType指返回的对应类型，指向javaBean实体类-->
    <select id="getUserList" resultType="com.getdream.pojo.User">
        select *
        from user limit 10
    </select>

    <!--查询单条-->
    <select id="getUserByID" parameterType="int" resultType="com.getdream.pojo.User">
        select *
        from user
        where id = #{id}
    </select>
</mapper>
```



```java
package com.getdream.dao;

import com.getdream.pojo.User;
import com.getdream.utils.MybatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


class UserDaoTest {

    @Test
    public void test() {

        //获取sqlSession对象
        try (SqlSession sqlSession = MybatisUtils.getSqlSession()) {
            //方式一：
            UserDao userDao = sqlSession.getMapper(UserDao.class);
            List<User> userList = userDao.getUserList();

            //方式二：这种使用率不高，一般都是第一种
            //List<User> userList = sqlSession.selectList("com.getdream.dao.UserDao.getUserList");

            for (User user : userList) {
                System.out.println(user);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.getStackTrace();
        }


    }

    @Test
    public void getUserByID() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        UserDao mapper = sqlSession.getMapper(UserDao.class);

        User info = mapper.getUserByID(2);

        System.out.println(info);

        sqlSession.close();
    }

    @Test
    public void addUser() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        UserDao mapper = sqlSession.getMapper(UserDao.class);

        int i = mapper.addUser(new User(5, "xixi", "241234"));

        if (i > 0) {
            System.out.println("插入成功");
        }


        //提交事务  增删改都需要提交事务
        sqlSession.commit();

        sqlSession.close();

    }

    @Test
    void updateUser() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        UserDao mapper = sqlSession.getMapper(UserDao.class);

        int i = mapper.updateUser(new User(5, "chenchen", "chenchen"));

        if (i > 0) {
            System.out.println("更新成功");
        }

        //提交事务
        sqlSession.commit();

        sqlSession.close();

    }

    @Test
    void deleteUserByID() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        UserDao mapper = sqlSession.getMapper(UserDao.class);

        int i = mapper.deleteUserByID(5);

        if (i > 0) {
            System.out.println("删除成功");
        }

        sqlSession.commit();

        sqlSession.close();

    }

    @Test
    void addUserWithMap() {
        SqlSession sqlSession = MybatisUtils.getSqlSession();

        UserDao mapper = sqlSession.getMapper(UserDao.class);

        Map<String, Object> map = new HashMap<>();

        map.put("name", "李雷");
        map.put("pwd", "sdfsadfs");

        int i = mapper.addUserWithMap(map);

        if (i > 0) {
            System.out.println("插入成功");
        }

        sqlSession.commit();

        sqlSession.close();


    }
}
```



# Mybatis配置注意点

## 1.映射器

在MapperRegistry:注册绑定我们的Mapper文件

```
java-mybatis/mybatis-02/src/main/resources/mybatis-config.xml
```

方式一：

```xml
 <mappers>
        <mapper resource="com/getdream/dao/UserMapper.xml"/>
    </mappers>
```



方式二：使用class文件绑定注册 

```xml
 <mappers>
        <mapper class="com.getdream.dao.UserDao"/>
    </mappers>
```

注意点：

接口需要和他的Mapper配置文件同名（UserMapper 与UserMapper.xml）

接口需要和他的Mapper配置文件在同一个包下



方式三：使用扫描包进行注入绑定

```xml
<mappers>
        <!--package方式  dao的接口需要和xml同名同目录 其实就是相当于class的批量-->
        <package name="com.getdream.dao"/>
    </mappers>
```

注意点：

接口需要和他的Mapper配置文件同名（UserMapper 与UserMapper.xml）

接口需要和他的Mapper配置文件在同一个包下
