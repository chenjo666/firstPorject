package com.nowcoder.community.controller;

import com.nowcoder.community.util.CommunityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.nowcoder.community.service.AlphaService;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/alpha")
public class AlphaController {
    @Autowired
    private AlphaService alphaService;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    @ResponseBody
    public String get() {
        return "get success";
    }

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    @ResponseBody
    public String post() {
        return "post success";
    }



    @RequestMapping(value = "/student", method = RequestMethod.POST)
    @ResponseBody
    public String post(String username, String password) {
        System.out.println(username + " . " + password);
        return "hello";
    }

    @RequestMapping(value="/view")
    public ModelAndView view() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("username", "123");
        mav.addObject("password", "456");
        mav.setViewName("/demo/view");
        return mav;
    }

    @RequestMapping("/json")
    @ResponseBody
    public Map<String, String> returnJSON() {
        Map<String, String> map = new HashMap<>();
        map.put("username", "com");
        map.put("password", "good");
        return map;
    }


    // Cookie 示例
    @RequestMapping(path = "/cookie/set", method = RequestMethod.GET)
    @ResponseBody
    public String setCookie(HttpServletResponse response) {
        Cookie cookie = new Cookie("code", CommunityUtil.generateUUID());
        cookie.setPath("/community/alpha");
        cookie.setMaxAge(60);
        response.addCookie(cookie);
        return "set cookie successfully";
    }

    @RequestMapping(path = "/cookie/get", method = RequestMethod.GET)
    @ResponseBody
    public String getCookie(@CookieValue("code") String code) {
        System.out.println(code);
        return "get cookie successfully";
    }


    // Session 示例
    @RequestMapping(path = "/session/set", method = RequestMethod.GET)
    @ResponseBody
    public String setSession(HttpSession session) {
        session.setAttribute("id", 1);
        session.setAttribute("name", "张三");
        return "set session successfully";
    }

    @RequestMapping(path = "/session/get", method = RequestMethod.GET)
    @ResponseBody
    public String getSession(HttpSession session) {
        System.out.println(session.getAttribute("id"));
        System.out.println(session.getAttribute("name"));
        return "set session successfully";
    }
}
