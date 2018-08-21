package cn.wxy.api;/**
 * Created by XR on 2018/8/16.
 */

import cn.wxy.common.domain.ResultDO;
import cn.wxy.common.pageable.Page;
import cn.wxy.core.basics.manager.BasicsMaterialService;
import cn.wxy.core.basics.model.BasicsMaterial;
import cn.wxy.core.test.model.Test;
import com.google.common.collect.Lists;
import javafx.beans.binding.ObjectExpression;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author
 * @create 2018-08-16 18:23
 **/
@Controller
@RequestMapping("material")
public class BasicsMaterialController {

    @Autowired
    private BasicsMaterialService basicsMaterialService;

    @RequestMapping(value = "index")
    public String index(HttpServletRequest req, HttpServletResponse resp) {
        return "/basics/material/index";
    }


    /**
     * 分页查询消息
     *
     * @param req
     * @param resp
     * @return
     * @throws ServletRequestBindingException
     */
    @RequestMapping(value = "init")
    @ResponseBody
    public Object init(HttpServletRequest req,
                       HttpServletResponse resp)  {
        Page<BasicsMaterial> result = new Page<BasicsMaterial>();
        List<BasicsMaterial> basicsMaterials = basicsMaterialService.getAllBasicsMaterials();
        result.setData(basicsMaterials);
        result.setTotalPageCount(basicsMaterials.size());
        result.setiTotalDisplayRecords(basicsMaterials.size());
        result.setiTotalRecords(basicsMaterials.size());
        return result;
    }


    /**
     * 保存
     * @param basicsMaterial
     * @param req
     * @param resp
     * @return
     */
    @RequestMapping(value = "save")
    @ResponseBody
    public ResultDO saveRedeem(
            @ModelAttribute("basicsMaterial") BasicsMaterial basicsMaterial,
            HttpServletRequest req, HttpServletResponse resp) {
        ResultDO result = new ResultDO();
        result = basicsMaterialService.saveBasicsMaterial(basicsMaterial);
        return result;
    }
}



