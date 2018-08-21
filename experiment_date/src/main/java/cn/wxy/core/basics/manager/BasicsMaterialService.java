package cn.wxy.core.basics.manager;/**
 * Created by XR on 2018/8/17.
 */

import cn.wxy.common.domain.ResultDO;
import cn.wxy.core.basics.model.BasicsMaterial;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author
 * @create 2018-08-17 16:24
 **/

public interface BasicsMaterialService {

    List<BasicsMaterial> getAllBasicsMaterials();

    ResultDO saveBasicsMaterial(BasicsMaterial basicsMaterial);

}
