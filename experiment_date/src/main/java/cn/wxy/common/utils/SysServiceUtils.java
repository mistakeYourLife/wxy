package cn.wxy.common.utils;/**
 * Created by XR on 2018/8/16.
 */

import cn.wxy.core.sys.manager.SysMenuService;
import cn.wxy.core.sys.model.SysMenu;
import com.google.common.collect.Lists;

import java.util.List;

/**
 * 工具类
 *
 * @author
 * @create 2018-08-16 14:26
 **/
public  class SysServiceUtils {

    private static SysMenuService sysMenuService = SpringContextHolder.getBean(SysMenuService.class);

    /**
     * 获取所有菜单
     * @return
     *//*
    public static List<SysMenu> getTreeMenu(){
        return sysMenuMapper.getTreeSysmenus();
    }*/

    public static List<SysMenu> getTreeMenu(){

        return sysMenuService.getTreeMenu();
    }

}
