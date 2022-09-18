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



# 多对一与一对多

## 多对一

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<!--
mapper为映射的根节点，用来管理DAO接口
namespace指定DAO接口的完整类名，表示mapper配置文件管理哪个DAO接口(包.接口名)
mybatis会依据这个接口动态创建一个实现类去实现这个接口，而这个实现类是一个Mapper对象
-->
<mapper namespace="com.getdream.dao.StudentMapper">
    <!--
    id = "接口中的方法名"
    parameterType = "接口中传入方法的参数类型"
    resultType = "返回实体类对象：包.类名" 处理结果集 自动封装
    注意:sql语句后不要出现";"号
    查询：select标签
    增加：insert标签
    修改：update标签
    删除：delete标签
    -->


    <!--多对一之方式二 开始-->
    <!--按照结果嵌套处理
    需要留意 resultMap里面的result对应的字段映射 配错了返回的值可就跟着错了
    -->
    <select id="getInfoList2" resultMap="StudentTeacher2">
        select s.id as sid, s.name as sname, t.id as tid, t.name as tname
        from javaweb.student as s,
             javaweb.teacher as t
        where s.tid = t.id
    </select>

    <resultMap id="StudentTeacher2" type="Student">
        <result property="id" column="sid"/>
        <result property="name" column="sname"/>

        <!--复杂的属性，需要单独处理-->
        <!--对象 association-->
        <!--集合：collection-->

        <association property="teacher" javaType="Teacher">
            <result property="name" column="tname"/>
            <result property="id" column="tid"/>
        </association>
    </resultMap>
    <!--多对一之方式二 结束-->


    <!--多对一之方式一 开始-->
    <!--先查询出学生数据，在根据tid查老师数据-->
    <select id="getInfoList" resultMap="StudentTeacher">
        select *
        from javaweb.student
        limit 10
    </select>

    <resultMap id="StudentTeacher" type="Student">
        <result property="id" column="id"/>
        <result property="name" column="name"/>

        <!--复杂的属性，需要单独处理-->
        <!--对象 association-->
        <!--集合：collection-->

        <association property="teacher" column="tid" javaType="Teacher" select="getTeacher"/>
    </resultMap>

    <!--查老师
    因为这个select其实是用作上面getInfoList的子查询 其实#{id}这个占位符里面写id tid xxx 效果都是一样的，因为StudentTeacher-》association-》column里面配置的student表的对应列进行填充（这里是tid列）
    -->
    <select id="getTeacher" resultType="Teacher">
        select *
        from javaweb.teacher
        where id = #{id}
    </select>
    <!--多对一之方式一 结束-->


    <select id="getInfoByID" resultType="com.getdream.pojo.Student">
        select *
        from javaweb.student
        where id = #{id}
    </select>


</mapper>

```



## 一对多

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<!--
mapper为映射的根节点，用来管理DAO接口
namespace指定DAO接口的完整类名，表示mapper配置文件管理哪个DAO接口(包.接口名)
mybatis会依据这个接口动态创建一个实现类去实现这个接口，而这个实现类是一个Mapper对象
-->
<mapper namespace="com.getdream.dao.TeacherMapper">
    <!--
    id = "接口中的方法名"
    parameterType = "接口中传入方法的参数类型"
    resultType = "返回实体类对象：包.类名" 处理结果集 自动封装
    注意:sql语句后不要出现";"号
    查询：select标签
    增加：insert标签
    修改：update标签
    删除：delete标签
    -->

    <!--多对一用关联 association-->
    <!--一对多用集合 collection-->


    <!--一对多  方式一 开始-->
    <resultMap id="TeacherMap" type="Teacher">
        <result property="id" column="tid"/>
        <result property="name" column="tname"/>

        <!--
        property 对应JavaBean里面的属性
        javaType 指定属性的类型
        集合中的泛型信息，我们使用ofType获取
        -->
        <collection property="students" column="tid" javaType="ArrayList" ofType="Student">
            <result property="id" column="sid"/>
            <result property="name" column="sname"/>
            <result property="tid" column="stid"/>
        </collection>
    </resultMap>

    <select id="getInfoList" resultMap="TeacherMap">
        select s.id sid, t.id tid, t.name tname, s.name sname, s.tid stid
        from javaweb.teacher t,
             javaweb.student s
        where t.id = s.tid;
    </select>

    <!--一对多  方式一 结束-->


    <!--一对多  方式二 开始-->

    <!--1.先查询老师-->
    <resultMap id="TeacherMap2" type="Teacher">
        <!--因为property一样 可以省略 不过这里省略 结果显示老师的id可能会变成0 要么在sql显示声明获取字段，要么写id的result咯-->
        <result property="id" column="id"/>
        <result property="name" column="name"/>

        <collection property="students" javaType="ArrayList" ofType="Student" select="getStudentByTeacherID"
                    column="id"/>
    </resultMap>


    <select id="getTeacher" resultMap="TeacherMap2">
        select *
        from javaweb.teacher
        where id = #{id}
    </select>

    <!--2.再查询学生-->
    <select id="getStudentByTeacherID" resultType="Student">
        select *
        from javaweb.student
        where tid = #{tid}
    </select>


    <!--一对多  方式二 结束-->
</mapper>

```



## 小结

1.关联-association 【多对一】

2.集合-collection 【一对多】

3.javaType 和 ofType

​	1.javaType 用来指定实体类中属性的类型

​	2.ofType 用来指定映射到List或者集合中的pojo类型，泛型中的集合约束

4.resultMap中的result 的property和column属性

​	1.property是对应javaBean的属性

​	2.column是对应xml的sql里面列名，因为sql可能使用字段别名，所以有时候需要用到映射
