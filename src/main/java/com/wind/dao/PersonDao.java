package com.wind.dao;

import com.wind.entity.Person;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/*
 * 1.Repository是一个空接口，即是一个标记接口
 * 2.若我们定义的接口继承了Repository，则该接口会被IOC容器识别为一个Repository Bean
 * 注入到IOC容器中，进而可以在该接口中定义满足一定规则的接口
 * 3.实际上也可以通过一个注解@RepositoryDefination 注解来替代Repository接口
 */
//@RepositoryDefinition(domainClass=Person.class,idClass=Integer.class)
public interface PersonDao extends Repository<Person, Integer> {
    /**
     * 通过id查找实体
     */
    Person getById(Integer id);

    /**
     * 简单条件查询
     *
     * @param id 编号
     * @param name 姓名
     * @return
     */
    Person findByIdAndName(Integer id, String name);


    /**
     * where id < ? or birth < ?
     */
    List<Person> findByIdIsLessThanOrBirthLessThan(Integer id, Date birth);

    /**
     * where email like ?
     */
    List<Person> findByEmailLike(String email);

    /**
     * 也支持count查询
     *
     * @param email
     * @return
     */
    long countByEmailLike(String email);

    List<Person> findByIdIsAndBirthLessThan(int i, Date date);

    List<Person> findByIdIsLessThanAndBirthLessThan(int i, Date date);

    List<Person> findByIdGreaterThanAndBirthLessThan(int i, Date date);

    /**
     * 级联查询，查询address的id等于条件值
     *
     * @param addressId
     * @return
     */
    List<Person> findByAddressId(Integer addressId);

    /**
     * 索引参数，参数顺序和索引顺序一一对应
     * 自定义的查询,直接写jpql语句; 查询id<? 或者 名字 like?的person集合
     *
     * @param id
     * @param name
     * @return
     */
    @Query("from Person where id < ?1 or name like ?2")
    List<Person> testPerson(Integer id, String name);


    @Query("from Person where id > :id or name like %:name%")
    List<Person> testPerson2(@Param("id") Integer id, @Param("name") String name);

    /**
     * 自定义查询之子查询,直接写jpql语句; 查询出id最大的person
     *
     * @return 返回查询到的person记录
     */
    @Query("from Person where id = (select max(p.id) from Person as p)")
    Person testSubquery();

    /**
     * 使用本地的sql查询, 注解@Query需要填写完整的sql语句
     */
    @Query(value = "select * from jpa_persons where id = :id", nativeQuery = true)
    Person getPersonByIdWithSql(@Param("id") Integer id);

    //可以通过自定义的 JPQL 完成 UPDATE 和 DELETE 操作. 注意: JPQL 不支持使用 INSERT
    //在 @Query 注解中编写 JPQL 语句, 但必须使用 @Modifying 进行修饰. 以通知 SpringData, 这是一个 UPDATE 或 DELETE 操作
    //UPDATE 或 DELETE 操作需要使用事务, 此时需要定义 Service 层. 在 Service 层的方法上添加事务操作.
    //默认情况下, SpringData 的每个方法上有事务, 但都是一个只读事务. 他们不能完成修改操作!
    @Modifying
    @Query("UPDATE Person p SET p.name = :name WHERE p.id < :id")
    int updatePersonById(@Param("id") Integer id, @Param("name") String updateName);
}
