package com.company.entity;

import com.company.BaseEntity;
import com.company.enums.LikeStatus;

import javax.persistence.*;

@Entity
public class LikeEntity extends BaseEntity {



    @ManyToOne(fetch = FetchType.LAZY)
    ProfileEntity profile;

    @Enumerated(EnumType.STRING)
    private LikeStatus status;






}
