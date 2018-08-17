package cn.wxy.core.sys.manager;/**
 * Created by XR on 2018/8/16.
 */

import cn.wxy.core.sys.dao.SysMenuMapper;
import cn.wxy.core.sys.model.SysMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author
 * @create 2018-08-16 17:26
 **/
@Service
public class SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    public List<SysMenu> getTreeMenu(){
        return sysMenuMapper.getTreeSysmenus();
    }
}
