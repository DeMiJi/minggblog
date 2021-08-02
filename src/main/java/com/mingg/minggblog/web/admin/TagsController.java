package com.mingg.minggblog.web.admin;

import com.mingg.minggblog.po.Tag;
import com.mingg.minggblog.service.TagsService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class TagsController {

    private final TagsService tagsService;

    public TagsController(TagsService tagsService) {
        this.tagsService = tagsService;
    }

    //跳转
    @GetMapping("/tags")
    public String tags(@PageableDefault(size = 8,sort = {"id"},direction = Sort.Direction.DESC) Pageable pageable, Model model){
        model.addAttribute("page",tagsService.listTags(pageable));
        return "admin/tags";
    }

    //跳转
    @GetMapping("/tags/input")
    public String input(Model model){
        //后端为空校验
        model.addAttribute("tag", new Tag());
        return "admin/tags-input";
    }

    /*添加分类按钮 post请求处理业务*/
    @PostMapping("/tags")
    public String post(@Valid Tag tag, BindingResult result, RedirectAttributes attributes) {
        Tag tag1 = tagsService.getTagsByName(tag.getTagName());

        if (tag1 != null) {
            result.rejectValue("tagName","nameError","Tag already exists");
        }
        if (result.hasErrors()) {
            return "admin/input-tag";
        }

        Tag t = tagsService.saveTags(tag);
        if (t == null) {
            attributes.addFlashAttribute("message", "新增失败");
        } else {
            attributes.addFlashAttribute("message", "新增成功");
        }
        return "redirect:/admin/tags";
    }

    //修改
    @GetMapping(value = "/tags/{id}/input")
    public String editInput(@PathVariable Long id, Model model) {
        Tag tag = tagsService.getTags(id);
        System.out.println("----------------editInput-------" + tag.toString());
        model.addAttribute("tag", tag);
        return "admin/tags-input";
    }

    @PostMapping("/tags/{id}")
    public String editPost(@Valid Tag tags, BindingResult result, @PathVariable Long id, RedirectAttributes attributes) {
        Tag tags1 = tagsService.getTagsByName(tags.getTagName());
        if (tags1 != null) {
            result.rejectValue("name","nameError","该分类已经存在，不能重复添加");
        }
        if (result.hasErrors()) {
            return "admin/tags-input";
        }
        Tag t = tagsService.updateTags(id, tags);
        if (t == null ) {
            attributes.addFlashAttribute("message", "更新失败");
        } else {
            attributes.addFlashAttribute("message", "更新成功");
        }
        return "redirect:/admin/tags";
    }

    //删除
    @GetMapping("/tags/{id}/delete")
    public String delete(@PathVariable Long id,RedirectAttributes attributes) {
        tagsService.deleteTags(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/tags";
    }
}
