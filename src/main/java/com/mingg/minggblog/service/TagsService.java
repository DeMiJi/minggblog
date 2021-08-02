package com.mingg.minggblog.service;

import com.mingg.minggblog.po.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TagsService {
    //新增
    Tag saveTags(Tag tag);
    //查询
    Tag getTags(Long id);
    //根据名字查询
    Tag getTagsByName(String name);
    //分页
    Page<Tag> listTags(Pageable pageable);
    //查询所有的分类
    List<Tag> listTags();

    List<Tag> listTagsTop(Integer size);
    //修改
    Tag updateTags(Long id, Tag tag);
    //删除
    void deleteTags(Long id);
}
