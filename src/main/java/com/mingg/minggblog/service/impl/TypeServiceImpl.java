package com.mingg.minggblog.service.impl;

import com.mingg.minggblog.NotFoundException;
import com.mingg.minggblog.dao.TypeRepository;
import com.mingg.minggblog.po.Type;
import com.mingg.minggblog.service.TypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {

    @Autowired
    private TypeRepository typeRepository;

    @Transient
    @Override
    public Type saveType(Type type) {
        return typeRepository.save(type);
    }

    @Transient
    @Override
    public Type getType(Long id) {
        return typeRepository.getOne(id);
    }

    @Transient
    @Override
    public Type getTypeByName(String name) {
        return typeRepository.findByName(name);
    }

    @Transient
    @Override
    public Page<Type> listType(Pageable pageable) {
        return typeRepository.findAll(pageable);
    }

    @Transient
    @Override
    public List<Type> listType() {
        return null;
    }

    @Transient
    @Override
    public List<Type> listTypeTop(Integer size) {
        return null;
    }

    @Transient
    @Override
    public Type updateType(Long id, Type type) {
        Type t = typeRepository.getOne(id);
        if (t == null){
            throw new NotFoundException("不存在");
        }
        BeanUtils.copyProperties(type,t);
        return typeRepository.save(t);
    }

    @Transient
    @Override
    public void deleteType(Long id) {
        typeRepository.deleteById(id);
    }
}
