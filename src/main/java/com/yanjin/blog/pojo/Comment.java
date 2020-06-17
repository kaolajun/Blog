package com.yanjin.blog.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "t_comment")
public class Comment {

    @Id
    @GeneratedValue
    private Long id;

    private String nickName;
    private String email;
    private String content;
    private String avatar;
    //指定数据库中的时间类型
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    //关系维护端
    @ManyToOne
    private Blog blog;

    //在这个评论下回复的评论（此时为父评论，一个父评论对应多个子评论）
    @OneToMany(mappedBy = "parentComment")
    private List<Comment> replyComment = new ArrayList<>();

    //这个评论下面的评论（此时为子评论，多个子评论对应一个父评论）
    @ManyToOne
    private Comment parentComment;
}
