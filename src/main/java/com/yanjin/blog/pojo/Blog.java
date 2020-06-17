package com.yanjin.blog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@Entity
@Table(name = "t_blog")
public class Blog {

    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String content;
    private String firstPicture;
    private String flag;
    private Integer viewTimes;
    private boolean appreciation;
    private boolean shareStatement;
    private boolean commentable;
    private boolean published;
    private boolean recommend;

    @Temporal(TemporalType.TIMESTAMP)
    private Date creatTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    //关系维护端
    @ManyToOne
    private Type type;

    //关系维护端；级联新增（当新建具有新Tag的blog时，tag也要更新）
    @ManyToMany(cascade = {CascadeType.PERSIST})
    private List<Tag> tags = new ArrayList<>();

    //关系维护端
    @ManyToOne
    private User user;

    //关系被维护端
    @OneToMany(mappedBy = "blog")
    private List<Comment> comments = new ArrayList<>();
}
