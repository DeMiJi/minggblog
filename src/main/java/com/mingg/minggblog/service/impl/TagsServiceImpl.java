package com.mingg.minggblog.service.impl;

import com.mingg.minggblog.NotFoundException;
import com.mingg.minggblog.dao.TagsRepository;
import com.mingg.minggblog.po.Tag;
import com.mingg.minggblog.service.TagsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.ArrayList;
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
        return tagsRepository.findAll();
    }

    @Override
    public List<Tag> listTags(String ids) {
        return tagsRepository.findAllById(convertToList(ids));
    }

    private List<Long> convertToList(String ids) {
        List<Long> list = new ArrayList<>();
        if (!"".equals(ids) && ids != null) {
            String[] idarray = ids.split(",");
            for (int i=0; i < idarray.length;i++) {
                list.add(Long.valueOf(idarray[i]));
            }
        }
        return list;
    }

    @Transient
    @Override
    public List<Tag> listTagsTop(Integer size) {
        Pageable pageable = PageRequest.of(0, size, Sort.by(Sort.Direction.DESC, "blogs.size"));
        return tagsRepository.findTop(pageable);
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
