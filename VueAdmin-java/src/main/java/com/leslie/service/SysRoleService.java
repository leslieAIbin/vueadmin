package com.leslie.service;

import com.leslie.entity.SysRole;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author leslie
 * @since 2021-07-12
 */
public interface SysRoleService extends IService<SysRole> {
    List<SysRole> listRolesByUserId(Long userId);

}
