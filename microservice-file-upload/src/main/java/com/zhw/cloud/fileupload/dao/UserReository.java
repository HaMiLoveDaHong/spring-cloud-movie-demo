package com.zhw.cloud.fileupload.dao;

import com.zhw.cloud.fileupload.po.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author zhw
 * @since 2021/10/31
 */
@Repository
public interface UserReository extends JpaRepository<User,Long> {
}
