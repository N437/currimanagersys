package com.winter.filter;

import com.winter.utils.StringUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "sessionFilter",urlPatterns = {"/*"}) //注解配置filter
public class SessionFilter implements Filter {

    //标示符：表示当前用户未登录(可根据自己项目需要改为json样式)
    String NO_LOGIN = "您还未登录";

    //需要过滤的内容，及请求的页面属于admin
    String[] includeUrls = new String[]{"/login","/"};


    /**
     * 初始化
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * filter过滤操作
     * @param servletRequest 请求上下文
     * @param servletResponse 相应上下文
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession(false);
        String uri = request.getRequestURI();

//        System.out.println("filter url:"+uri);
        //是否需要过滤
        boolean needFilter = isNeedFilter(uri);


        if (!needFilter) { //不需要过滤直接传给下一个过滤器
            filterChain.doFilter(servletRequest, servletResponse);
        } else { //需要过滤器
            // session中包含user对象,则是登录状态
            if(session!=null&&session.getAttribute(StringUtil.winUserSession) != null){
                // System.out.println("user:"+session.getAttribute("user"));

                int time= request.getSession().getMaxInactiveInterval();

                filterChain.doFilter(request, response);
            }else{
                String requestType = request.getHeader("X-Requested-With");
                //判断是否是ajax请求
                if(requestType!=null && "XMLHttpRequest".equals(requestType)){
                    response.getWriter().write(this.NO_LOGIN);

                }else{
                    //重定向到登录页(需要在static文件夹下建立此html文件)
                    response.sendRedirect("/withoutlogin");
                }
                return;
            }
        }
    }

    /**
     * 判断是否需要过滤
     * @param uri
     * @return
     */
    private boolean isNeedFilter(String uri) {
        if (uri.startsWith("/admin")){
            return true;
        }

        return false;
    }

    @Override
    public void destroy() {

    }
}
