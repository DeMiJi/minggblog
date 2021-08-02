package com.mingg.minggblog.service;

import com.mingg.minggblog.po.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TypeService {
    //新增
    Type saveType(Type type);
    //查询
    Type getType(Long id);
    //根据名字查询
    Type getTypeByName(String name);
    //分页
    Page<Type> listType(Pageable pageable);
    //查询所有的分类
    List<Type> listType();

    List<Type> listTypeTop(Integer size);
    //修改
    Type updateType(Long id, Type type);
    //删除
    void deleteType(Long id);
}
