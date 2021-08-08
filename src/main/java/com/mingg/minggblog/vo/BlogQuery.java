package com.mingg.minggblog.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogQuery {
    private String title;
    private Long typeId;
    private boolean isRecommend;
}
