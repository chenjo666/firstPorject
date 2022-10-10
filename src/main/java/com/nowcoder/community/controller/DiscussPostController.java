package com.nowcoder.community.controller;

import com.nowcoder.community.pojo.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.nowcoder.community.pojo.DiscussPost;
import com.nowcoder.community.pojo.User;
import com.nowcoder.community.service.DiscussPostService;
import com.nowcoder.community.service.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class DiscussPostController {
    // 帖子
    @Autowired
    private DiscussPostService discussPostService;

    // 帖主
    @Autowired
    private UserService userService;

    // 帖子主页（路径 & 请求方式）
    @RequestMapping(path="/index", method = RequestMethod.GET)
    public String getIndexPage(Model model, Page page) {
        // 方法调用前，Spring MVC会自动实例化model和page，并且会将page注入model中
        // 因此可以在页面中使用page对象
        page.setRows(discussPostService.getRows(0));
        page.setPath("/index");

        List<DiscussPost> list = discussPostService.getDiscussPost(0, page.getOffset(), page.getLimit());
        List<Map<String, Object>> discussPosts = new ArrayList<>();
        if (list != null) {
            for (DiscussPost post : list) {
                Map<String, Object> map = new HashMap<>();
                map.put("post", post);
                User user = userService.getUserById(post.getUserId());
                map.put("user", user);
                discussPosts.add(map);
            }
        }
        // 将数据模型传入 model 中
        model.addAttribute("discussPosts", discussPosts);
        return "index";
    }
}
