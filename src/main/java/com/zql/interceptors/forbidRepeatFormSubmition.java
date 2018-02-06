package com.zql.interceptors;

import com.zql.customAnnotation.SameUrlData;
import org.springframework.ui.ModelMap;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Author:zhangqinglei
 * Description:验证表单重复提交
 * Created by qwert on 2018/2/5.
 * Modified By:
 */
public class forbidRepeatFormSubmition extends HandlerInterceptorAdapter{

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){

        if (handler instanceof HandlerMethod){
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            SameUrlData annotation = method.getAnnotation(SameUrlData.class);
            if (annotation != null){
                if (isRepeatDataValidator(request)){//重复提交
                    return false;
                }else {
                    return true;
                }
            }
            return true;
        }else {
            try {
                return super.preHandle(request,response,handler);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

    }

    /*
    * 验证同一个url数据重复提交
    *
    * */
    public boolean isRepeatDataValidator(HttpServletRequest request){
        String params = request.getParameterMap().toString();
        String requestURI = request.getRequestURI();
        Map<String,String> map = new HashMap<String,String>();
        map.put(requestURI,params);
        String nowUrlParams = map.toString();
        Object preRepeatData = request.getSession().getAttribute("repeatData");
        if (preRepeatData==null){//为null表示还没有访问此页面
            request.getSession().setAttribute("repeatData",nowUrlParams);
            return false;
        }else {//已经访问过页面
            if (preRepeatData.toString().equals(nowUrlParams)){
                return true;
            }else {
                request.getSession().setAttribute("repeatData",nowUrlParams);
                return false;
            }
        }

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }
}
