package com.company.entity;

import com.company.BaseEntity;
import com.company.enums.ArticleStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "article")
public class ArticleEntity extends BaseEntity {

    @Column(nullable = false)
    private String title;


    @Column
    private String content;

    // sttaus
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id")
    private ProfileEntity profile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_id")
    private ProfileEntity publisher;

    @Enumerated(EnumType.STRING)
    @Column
    private ArticleStatus status;

    // region


}
