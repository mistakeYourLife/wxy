package cn.wxy.api;/**
 * Created by XR on 2018/8/16.
 */

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author
 * @create 2018-08-16 18:23
 **/
@Controller
@RequestMapping("basicsIndex")
public class BasicsIndexController {


    @RequestMapping(value = "index")
    public String index(HttpServletRequest req, HttpServletResponse resp) {
        return "/basics/index/index";
    }

}
