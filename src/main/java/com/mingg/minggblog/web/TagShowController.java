package com.mingg.minggblog.web;

import com.mingg.minggblog.po.Tag;
import com.mingg.minggblog.service.BlogService;
import com.mingg.minggblog.service.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class TagShowController {
    @Autowired
    private TagsService tagsService;

    @Autowired
    private BlogService blogService;

    @Transactional
    @GetMapping("/tags/{id}")
    public String tags(@PageableDefault(size = 8, sort = {"updateTime"}, direction = Sort.Direction.DESC) Pageable pageable,
                       @PathVariable Long id, Model model) {
        List<Tag> tagBlogs = tagsService.listTagsTop(1000);
        if (id == -1) {
            id = tagBlogs.get(0).getId();
        }
        model.addAttribute("tags", tagBlogs);
        model.addAttribute("page", blogService.listBlog(id, pageable));
        model.addAttribute("activeTagId", id);
        return "tags";
    }
}
