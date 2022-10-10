package com.nowcoder.community.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.nowcoder.community.service.AlphaService;

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

}
