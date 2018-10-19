package com.wind.test;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * 整合效果测试类 测试spring容器是否整合成功
 */
public class TestConfig {
    private static ApplicationContext ctx ;
    static {
        // 通过静态代码块的方式,让程序加载spring的配置文件
        ctx = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
    }

    @Test
    public void  testDataSource() throws SQLException {
        DataSource dataSource = (DataSource)ctx.getBean("dataSource");
        System.out.println("数据源：" + dataSource);
        System.out.println("连接：" + dataSource.getConnection());
    }
}
