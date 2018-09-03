package com.hyacinth.module.repository;

import com.hyacinth.module.base.entity.UserRole;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * 用户角色数据库操作层
 * @author zhangfb
 * @version 1.0.0.1
 * @since JDK 1.8
 */
@Repository
public interface UserRoleRepository extends PagingAndSortingRepository<UserRole,String>,
        JpaSpecificationExecutor<UserRole> {
}
