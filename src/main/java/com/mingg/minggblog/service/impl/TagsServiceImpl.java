package com.mingg.minggblog.service.impl;

import com.mingg.minggblog.NotFoundException;
import com.mingg.minggblog.dao.TagsRepository;
import com.mingg.minggblog.po.Tag;
import com.mingg.minggblog.service.TagsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.List;

@Service
public class TagsServiceImpl implements TagsService {

    @Autowired
    private TagsRepository tagsRepository;

    @Transient
    @Override
    public Tag saveTags(Tag tag) {
        return tagsRepository.save(tag);
    }

    @Transient
    @Override
    public Tag getTags(Long id) {
        return tagsRepository.getOne(id);
    }

    @Transient
    @Override
    public Tag getTagsByName(String name) {
        return tagsRepository.findByTagName(name);
    }

    @Transient
    @Override
    public Page<Tag> listTags(Pageable pageable) {
        return tagsRepository.findAll(pageable);
    }

    @Transient
    @Override
    public List<Tag> listTags() {
        return null;
    }

    @Transient
    @Override
    public List<Tag> listTagsTop(Integer size) {
        return null;
    }

    @Transient
    @Override
    public Tag updateTags(Long id, Tag tag) {
        Tag t= tagsRepository.getOne(id);
        if (t == null){
            throw new NotFoundException("不存在");
        }
        BeanUtils.copyProperties(tag,t);
        return tagsRepository.save(t);
    }

    @Transient
    @Override
    public void deleteTags(Long id) {
        tagsRepository.deleteById(id);
    }
}
