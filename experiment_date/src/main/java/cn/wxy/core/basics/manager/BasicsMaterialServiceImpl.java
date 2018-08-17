package cn.wxy.core.basics.manager;/**
 * Created by XR on 2018/8/17.
 */

import cn.wxy.core.basics.dao.BasicsMaterialMapper;
import cn.wxy.core.basics.model.BasicsMaterial;
import cn.wxy.core.sys.dao.SysMenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author
 * @create 2018-08-17 16:27
 **/
@Service
public class BasicsMaterialServiceImpl implements BasicsMaterialService{


    @Autowired
    private BasicsMaterialMapper basicsMaterialMapper;

    public List<BasicsMaterial> getAllBasicsMaterials(){

        return basicsMaterialMapper.getAllBasicsMaterials();
    }
}
