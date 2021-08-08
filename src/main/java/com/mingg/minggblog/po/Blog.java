package com.mingg.minggblog.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="t_blog")
public class Blog {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private String content;
    private String firstPicture;
    private String flag;
    private Integer views;
    @Transient
    private String tagsIds;

    private String description;
    //赞赏是否开启
    private boolean appreciation;
    //转载声明是否开启
    private boolean shareStatment;
    //评论是否开启
    private boolean commentable;
    //发布是否开启
    private boolean published;
    //是否推荐
    private boolean recommend;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    //blog和type的关系
    @ManyToOne
    private Type type;

//    @ManyToMany(cascade = {CascadeType.PERSIST})
//    private List<Tag> tags = new ArrayList<>();
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Tag> tags = new ArrayList<>();

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "blog")
    private List<Comment> comments = new ArrayList<>();



    public void init(){
        this.tagsIds = tagsToIds(this.getTags());
    }

    // convert tags list to string
    private String tagsToIds(List<Tag> tags) {
        if (!tags.isEmpty()) {
            StringBuffer ids = new StringBuffer();
            boolean flag = false;
            for (Tag tag : tags) {
                if (flag) {
                    ids.append(",");
                } else {
                    flag = true;
                }
                ids.append(tag.getId());
            }
            return ids.toString();
        } else {
            return tagsIds;
        }
    }
}
