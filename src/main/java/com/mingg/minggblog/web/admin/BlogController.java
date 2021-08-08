package com.mingg.minggblog.web.admin;

import com.mingg.minggblog.po.Blog;
import com.mingg.minggblog.po.User;
import com.mingg.minggblog.service.BlogService;
import com.mingg.minggblog.service.TagsService;
import com.mingg.minggblog.service.TypeService;
import com.mingg.minggblog.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class BlogController {

    private static final String INPUT = "admin/blogs-input";
    private static final String LIST = "admin/blogs";
    private static final String REDIRECT = "redirect:/admin/blogs";
    private static final String BLOG_LIST = "admin/blogs :: blogList";


    @Autowired
    private BlogService blogService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private TagsService tagsService;

    @GetMapping("/blogs")
    public String blogs(@PageableDefault(size = 3,sort = {"updateTime"},direction = Sort.Direction.DESC)Pageable pageable , BlogQuery blog, Model model){
        model.addAttribute("types",typeService.listType());
        model.addAttribute("page",blogService.listBlog(pageable,blog));
        return LIST;
    }

    @PostMapping("/blogs/search")
    public String search(@PageableDefault(size = 3,sort = {"updateTime"},direction = Sort.Direction.DESC)Pageable pageable , BlogQuery blog, Model model){
        model.addAttribute("page",blogService.listBlog(pageable,blog));
        return BLOG_LIST;
    }

    //新增
    @GetMapping("/blogs/input")
    public String input(Model model){
        setTypeAndTag(model);
        model.addAttribute("blog", new Blog());
        return INPUT;
    }

    //封装成方法
    private void setTypeAndTag(Model model) {
        model.addAttribute("types",typeService.listType());
        model.addAttribute("tags",tagsService.listTags());
    }

    @Transactional
    @GetMapping("/blogs/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        setTypeAndTag(model);
        Blog blog = blogService.getBlog(id);
        blog.init();
        model.addAttribute("blog",blog);
        return INPUT;
    }


    @PostMapping("/blogs")
    public String post(Blog blog, RedirectAttributes attributes, HttpSession session){
        blog.setUser((User) session.getAttribute("user"));
        blog.setType(typeService.getType(blog.getType().getId()));
        System.out.println("blog.getTagsIds())="+blog.getTagsIds());
        blog.setTags(tagsService.listTags(blog.getTagsIds()));

        Blog b;
        if (blog.getId() == null) {
            b =  blogService.saveBlog(blog);
        } else {
            b = blogService.updateBlog(blog.getId(), blog);
        }
        if (b == null ) {
            attributes.addFlashAttribute("message", "操作失败");
        } else {
            attributes.addFlashAttribute("message", "操作成功");
        }
        return REDIRECT;
    }
    @GetMapping("/blogs/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        blogService.deleteBlog(id);
        attributes.addFlashAttribute("message", "删除成功");
        return REDIRECT;
    }

}
