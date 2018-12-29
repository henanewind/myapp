package com.hbm;

import com.hbm.domain.Book;
import com.hbm.domain.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 * @author wind
 * @date 2018/12/29
 */
public class UserDemo {
    public static void main(String[] args) {
        User user = new User();
        user.setUserName("user");
        user.setAddress("zhengzhou");
        user.setPassword("123");

        Book book = new Book();
        book.setName("java");
        book.setAuthor("Broke");
        book.setPrice(100);

        //获取加载配置管理类
        Configuration configuration = new Configuration();

        //不给参数就默认加载hibernate.cfg.xml文件，
        configuration.configure();

        //创建Session工厂对象
        SessionFactory factory = configuration.buildSessionFactory();

        //得到Session对象
        Session session = factory.openSession();

        //使用Hibernate操作数据库，都要开启事务,得到事务对象
        Transaction transaction = session.getTransaction();

        //开启事务
        transaction.begin();

        //把对象添加到数据库中
        session.save(user);
        session.save(book);

        //提交事务
        transaction.commit();

        //关闭Session
        session.close();

    }
}
