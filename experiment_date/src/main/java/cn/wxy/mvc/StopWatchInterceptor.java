package cn.wxy.mvc;/**
 * Created by XR on 2018/3/21.
 */

import com.google.common.collect.Lists;
import org.apache.commons.lang3.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;

public class StopWatchInterceptor extends HandlerInterceptorAdapter {
    private final static String STOPWATH_TIME = "StopWath_time";
    protected Logger logger = LoggerFactory.getLogger(StopWatchInterceptor.class);

    /**
     * 需要排除的URL
     */
    private static List<String> excludeUrls = Lists.newArrayList();

    static {
        excludeUrls.add("/download/apk");
        Collections.unmodifiableList(excludeUrls);
    }


    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        if (ignore(request))
            return true;
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        request.setAttribute(STOPWATH_TIME, stopWatch);
        return true;
    }

    private boolean ignore(HttpServletRequest request) {
        String path = request.getRequestURI().trim();
        boolean include = isInclude(path);
        if (include) {
            return true;
        }
        return false;
    }

    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        if (ignore(request))
            return;
        StopWatch stopWatch = (StopWatch) request.getAttribute(STOPWATH_TIME);
        if (stopWatch != null) {
            //大于500毫秒，记录日志
            if (stopWatch.getTime() > 500) {
                String handlerMethodName;

                if (handler instanceof HandlerMethod) {
                    HandlerMethod handlerMethod = (HandlerMethod) handler;
                    Method method = handlerMethod.getMethod();
                    handlerMethodName = method.getDeclaringClass() + "." + method.getName();
                } else {
                    handlerMethodName = handler + "";
                }

                String currentPath = request.getRequestURI();
                String queryString = request.getQueryString();
                queryString = queryString == null ? "" : "?" + queryString;
                logger.info("当前请求URL[{}],handler[{}],请求参数[{}],执行时间[{}]毫秒", currentPath, handlerMethodName, queryString, stopWatch.getTime());
            }
            if (stopWatch.isStarted())
                stopWatch.stop();
            //移除对象
            request.removeAttribute(STOPWATH_TIME);

        }

    }

    private boolean isInclude(String path) {
        for (String excludeUrl : excludeUrls) {
            if (excludeUrl.indexOf("*") >= 0) {
                if (path.matches(excludeUrl.replaceAll("\\*", ".*"))) {
                    return true;
                }
            } else {
                if (path.indexOf(excludeUrl) >= 0) {
                    return true;
                }
            }
        }
        return false;
    }
}
