/********************************************
 * 功能说明: 
 * 模块名称: 
 * 系统名称: 
 * 软件版权: 北京银杉金服科技有限公司
 * 系统版本: 1.0.0
 * 开发人员: zhangfb
 * 开发时间: 2018/8/26 0:00
 * 审核人员: 
 * 相关文档: 
 * 修改记录: 修改日期 修改人员 修改说明
 *********************************************/
package com.hyacinth.module.repository;

import com.hyacinth.module.base.entity.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * 用户数据库操作层
 * @author zhangfb
 * @version 1.0.0.1
 * @since JDK 1.8
 */
@Repository
public interface UserRepository extends PagingAndSortingRepository<User, String>, JpaSpecificationExecutor<User> {

    /**
     * 根据用户名和用户数据状态查询用户信息
     * @param username 用户名称
     * @param dataFlag 数据状态
     * @return User 用戶信息
     */
    User findByUserNameAndDataFlag(String username, String dataFlag);
}
