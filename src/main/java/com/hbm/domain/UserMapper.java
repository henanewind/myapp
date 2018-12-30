package com.hbm.domain;

import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author wind
 * @date 2018/12/30
 */
public interface UserMapper {
    /**
     * 根据Id查找用户
     * @param id 记录id 唯一标识
     * @return
     */
    User findById(int id);

    /**
     * 查找所有用户
     * @return
     */
    List<User> getUser();

    /**
     * 根据Id查找用户
     * @param id 记录id 唯一标识
     * @return
     */
    @Select("SELECT * FROM user WHERE id = #{id}")
    User selectUser(int id);

}
