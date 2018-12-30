package com.mybatis;

import com.hbm.domain.User;
import com.hbm.domain.UserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

/**
 * @author wind
 * @date 2018/12/30
 */
public class UserTest {

    SqlSessionFactory sqlMapper;

    @Before
    public void setup() {
        //定义读取文件名
        String resources = "mybatis-config.xml";
        //创建流
        Reader reader=null;
        try {
            //读取mybatis-config.xml文件到reader对象中
            reader= Resources.getResourceAsReader(resources);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //初始化mybatis,创建SqlSessionFactory类的实例
        sqlMapper=new SqlSessionFactoryBuilder().build(reader);
    }

    @Test
    public void userFindByIdTest(){
        //创建session实例
        SqlSession session=sqlMapper.openSession();
        //传入参数查询，返回结果
        User user=session.selectOne("findById",2);
        //输出结果
        System.out.println(user.getUserName());
        //关闭session
        session.close();
    }

    @Test
    public void testfindById(){
        //创建session实例
        SqlSession session=sqlMapper.openSession();
        //传入参数查询，返回结果
        UserMapper mapper=session.getMapper(UserMapper.class);
        User user = mapper.findById(1);
        //输出结果
        System.out.println(user);
        //关闭session
        session.close();
    }

    @Test
    public void testgetUser(){
        //创建session实例
        SqlSession session=sqlMapper.openSession();
        //传入参数查询，返回结果
        UserMapper mapper=session.getMapper(UserMapper.class);
        List<User> list = mapper.getUser();
        //输出结果
        System.out.println(list);
        //关闭session
        session.close();
    }

    @Test
    public void getuser_when_id_given() {
        SqlSession session = sqlMapper.openSession();
        UserMapper mapper = session.getMapper(UserMapper.class);
        User user = mapper.selectUser(2);
        System.out.println(user);
    }

}
