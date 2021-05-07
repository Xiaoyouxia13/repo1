package com.youyue.controller.controller;

import com.youyue.controller.domain.User;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*
Model 与 ModelMap 与 ModelAndView 的区别

没有太大的区别

Model的实现类ExtenedModelMap

ExtenedModelMap父类是ModelMap

ModelMap的父类LinkedHashMap

ModelAndView底层其实就是ModelMap 当然我们平常的处理器返回值是String的情况下  底层还是ModelAndView
 */
@Component
@RequestMapping("/user")
@SessionAttributes(names = "username",types = Integer.class)
public class UserController {

    /*
    测试响应结果为String类型
     */
    @RequestMapping("/testReturnString")
    public String testReturnString(Model model){
        System.out.println("UserController的testReturnString方法打印了");
        model.addAttribute("msg","美眉");//request域
        return "success";//请求转发
    }

    /*
    测试响应结果为void类型

    默认情况下 视图解析器会找/WEB-INF/pages/user/testReturnVoid.jsp页面
     */
    @RequestMapping("/testReturnVoid")
    public void testReturnVoid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("UserController的testReturnVoid方法打印了");
        //request.setAttribute("msg","苍老师");
        //请求转发  只要手动转发 视图解析器不会帮你找页面地址
        //request.getRequestDispatcher("/WEB-INF/pages/success.jsp").forward(request,response);
        //重定向 找不到WEB-INF下的资源
        response.sendRedirect(request.getContextPath()+"/index.jsp");
    }

    @RequestMapping("/saveSession")
    public String saveSession(Model model,String username,String password){
        model.addAttribute("username",username);
        model.addAttribute("id",111);
        return "success";
    }

    @RequestMapping("/testModelAndView")
    public ModelAndView testModelAndView(String username,String password){
        ModelAndView mav = new ModelAndView();
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        mav.addObject("user",user);
        mav.setViewName("success");
        return mav;
    }

    //转发
    @RequestMapping("/testForward")
    public String testForward(){
        System.out.println("进行请求转发...");
        return "forward:/WEB-INF/pages/success.jsp";
    }

    //重定向
    @RequestMapping("/testSendRedirect")
    public String testSendRedirect(){
        System.out.println("进行请求转发...");
        return "redirect:index.jsp";
    }

    /*
    @RequestBody将json字符串转换为对象
    @ResponseBody 将对象转换为json
     */
    @RequestMapping("/testJson")
    public @ResponseBody User testJson(@RequestBody User user){
        System.out.println(user);
        return user;
    }
}
