package com.mingg.minggblog.dao;

import com.mingg.minggblog.po.Blog;
import com.mingg.minggblog.po.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BlogRepository extends JpaRepository<Blog,Long>, JpaSpecificationExecutor<Blog> {
    @Query("select b from t_blog b where b.recommend = true")
    List<Blog> findTop(Pageable pageable);
}
