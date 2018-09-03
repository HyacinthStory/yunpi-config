/********************************************
 * 功能说明: 
 * 模块名称: 
 * 系统名称: 
 * 软件版权: 北京银杉金服科技有限公司
 * 系统版本: 1.0.0
 * 开发人员: zhangfb
 * 开发时间: 2018/8/26 0:31
 * 审核人员: 
 * 相关文档: 
 * 修改记录: 修改日期 修改人员 修改说明
 *********************************************/
package com.hyacinth.module.repository;

import com.hyacinth.module.base.entity.Role;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * 角色数据持久层
 * @author zhangfb
 * @version 1.0.0.1
 * @since JDK 1.8
 */
@Repository
public interface RoleRepository extends PagingAndSortingRepository<Role, String>, JpaSpecificationExecutor<Role> {
    /**
     * 根据用户Id查询角色信息
     * @param userId           用户ID
     * @return Iterable<Role> 角色迭代器
     */
    @Query(value = "select r FROM UserRole ur left join Role r on ur.roleId = r.id WHERE ur.dataFlag='0' AND ur.userId=?1")
    Iterable<Role> findRoleByUserId(String userId);
}
