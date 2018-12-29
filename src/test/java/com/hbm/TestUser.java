package com.hbm;

import com.hbm.domain.Book;
import com.hbm.domain.User;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.jws.soap.SOAPBinding;
import java.util.List;

/**
 * @author wind
 * @date 2018/12/29
 */
public class TestUser {
    Session session;
    @Before
    public void set() {
        //获取加载配置管理类
        Configuration configuration = new Configuration();

        //不给参数就默认加载hibernate.cfg.xml文件，
        configuration.configure();

        //创建Session工厂对象
        SessionFactory factory = configuration.buildSessionFactory();

        //得到Session对象
        session = factory.openSession();
    }

    @Test
    public void testAddUser() {
        User user = new User();
        user.setUserName("aaaa");
        user.setAddress("beijing");
        user.setPassword("uuuu");
        session.save(user);
    }

    @Test
    public void testLoad() {
        Book book = session.load(Book.class, 1);
        System.out.println(book);
    }

    @Test
    public void testGet() {
        //使用Hibernate操作数据库，都要开启事务,得到事务对象
        Transaction transaction = session.getTransaction();

        //开启事务
        transaction.begin();

        User user = session.get(User.class, 1);
        System.out.println(user);

        //提交事务
        transaction.commit();
    }

    @Test
    public void testCreateQuery() {
        // 使用 hql 面向对象查询，区分大小写 必须用User
        Query query = session.createQuery("from User");
        List list = query.list();
        System.out.println(list);
    }

    @Test
    public void testCreateQuery2() {
        // 使用 hql 面向对象查询，区分大小写 必须用User
        User user = new User();
        user.setId(1);
        Query query = session.createQuery("from User where id = ?");
        query.setParameter(0, 2);
        List list = query.list();
        System.out.println(list);
    }

    @Test
    public void testCreateCriteria() {
        // 完全面向对象的查询
        Criteria criteria = session.createCriteria(User.class);
        criteria.add(Restrictions.eq("id", 1));
        List list = criteria.list();
        System.out.println(list);
    }

    @Test
    public void testcreateSQLQuery() {
        // 本地SQL查询
        SQLQuery sqlQuery = session.createSQLQuery("select * from book").addEntity(Book.class);
        List list = sqlQuery.list();
        System.out.println(list);
    }

    @After
    public void destroy() {
        //关闭Session
        session.close();
    }
}
