package com.mingg.minggblog.dao;

import com.mingg.minggblog.po.Tag;
import com.mingg.minggblog.po.Type;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TagsRepository extends JpaRepository<Tag,Long> {
    Tag findByTagName(String name);

    @Query("select t from t_tag t")
    List<Tag> findTop(Pageable pageable);
}
