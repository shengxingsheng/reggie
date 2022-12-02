package com.sxs.interceptor;

import com.sxs.common.BaseContext;
import com.sxs.common.JsonUtil;
import com.sxs.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

@Slf4j
public class MyInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        Long employeeId = (Long) request.getSession().getAttribute("employee");
        if (Objects.isNull(employeeId)){
            //当前请求未登录，返回 new ObjectMapper().writeValueAsString(R.error("NOTLOGIN"))
            log.info("拦截[{}]", requestURI);
            response.getWriter().write(JsonUtil.toJsonString(R.error("NOTLOGIN")));
            return false;
        }
        //已登录
        //将当前session的属性放在theadLocal中方方便操作
        BaseContext.setCurrentId(employeeId);
        log.info("放行[{}]", requestURI);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
