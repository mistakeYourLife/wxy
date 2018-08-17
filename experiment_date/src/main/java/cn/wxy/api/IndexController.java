package cn.wxy.api;/**
 * Created by XR on 2018/1/2.
 */
import cn.wxy.core.test.dao.TestMapper;
import cn.wxy.core.test.model.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author
 * @create 2018-01-02 16:18
 **/
@Controller
public class IndexController {

    private static Logger log = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private TestMapper testMapper;

    /**
     * 首页
     *
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String showIndex() {

        Test test = new Test();
        test.setTest1("大");
        test.setTest2("吉");
        test.setTest3("大");
        test.setTest4("利");

        testMapper.insert(test);

        return "index";
    }

    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String welcome() {
        return "welcome";
    }
    @RequestMapping(value = "/personal", method = RequestMethod.GET)
    public String personal() {
        return "personal";
    }



}