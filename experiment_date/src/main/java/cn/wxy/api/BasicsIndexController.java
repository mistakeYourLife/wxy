package cn.wxy.api;/**
 * Created by XR on 2018/8/16.
 */

import cn.wxy.common.pageable.Page;
import cn.wxy.core.basics.manager.BasicsIndexService;
import cn.wxy.core.basics.model.BasicsIndex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestBindingException;
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
@RequestMapping("basicsIndex")
public class BasicsIndexController {

    @Autowired
    private BasicsIndexService basicsIndexService;

    @RequestMapping(value = "index")
    public String index(HttpServletRequest req, HttpServletResponse resp) {
        return "/basics/index/index";
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
        Page<BasicsIndex> result = new Page<BasicsIndex>();
        List<BasicsIndex> basicsMaterials = basicsIndexService.getAllBasicsMaterials();
        result.setData(basicsMaterials);
        result.setTotalPageCount(basicsMaterials.size());
        result.setiTotalDisplayRecords(basicsMaterials.size());
        result.setiTotalRecords(basicsMaterials.size());
        return result;
    }

}
