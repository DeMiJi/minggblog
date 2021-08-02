package com.mingg.minggblog.dao;

import com.mingg.minggblog.po.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagsRepository extends JpaRepository<Tag,Long> {
    Tag findByTagName(String name);
}
