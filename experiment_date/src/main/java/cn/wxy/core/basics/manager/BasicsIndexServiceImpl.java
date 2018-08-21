package cn.wxy.core.basics.manager;/**
 * Created by XR on 2018/8/17.
 */

import cn.wxy.core.basics.dao.BasicsIndexMapper;
import cn.wxy.core.basics.model.BasicsIndex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author
 * @create 2018-08-17 18:11
 **/
@Service
public class BasicsIndexServiceImpl implements BasicsIndexService {


    @Autowired
    private BasicsIndexMapper basicsIndexMapper;

    public List<BasicsIndex> getAllBasicsMaterials(){
        return basicsIndexMapper.getAllBasicsIndexs();
    }
}
