package com.leslie.service;

import com.leslie.common.dto.SysMenuDto;
import com.leslie.entity.SysMenu;
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
public interface SysMenuService extends IService<SysMenu> {
    List<SysMenuDto> getCurrentUserNav();

    List<SysMenu> tree();

}
