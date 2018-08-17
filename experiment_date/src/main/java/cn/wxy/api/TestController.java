package cn.wxy.api;/**
 * Created by XR on 2018/8/14.
 */

import cn.wxy.common.pageable.Page;
import cn.wxy.core.test.dao.TestMapper;
import cn.wxy.core.test.model.Test;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author
 * @create 2018-08-14 16:52
 **/
@Controller
@RequestMapping("test")
public class TestController {

    @Autowired
    private TestMapper testMapper;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public Object test() {
        Test test = new Test();
        test.setTest1("大");
        test.setTest2("吉");
        test.setTest3("大");
        test.setTest4("利");
        testMapper.insert(test);
        return "index";
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
        Page<Test> result = new Page<Test>();
        List<Test> testList = Lists.newArrayList();
        Test test = new Test();
        test.setId(1L);
        test.setTest1("小");
        test.setTest2("企");
        test.setTest3("鹅");
        test.setTest4("实");
        test.setTest5("验");
        testList.add(test);
        result.setData(testList);
        result.setTotalPageCount(testList.size());
        return result;
    }
}
