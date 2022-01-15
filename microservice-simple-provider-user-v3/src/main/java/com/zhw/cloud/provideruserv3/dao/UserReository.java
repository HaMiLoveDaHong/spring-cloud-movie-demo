package com.zhw.cloud.provideruserv3.dao;

import com.zhw.cloud.provideruserv3.po.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author zhw
 * @since 2021/10/31
 */
@Repository
public interface UserReository extends JpaRepository<User,Long> {
}
