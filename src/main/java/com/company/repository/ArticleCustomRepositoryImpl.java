package com.company.repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;


@Repository
public class ArticleCustomRepositoryImpl {
    @Autowired
    private EntityManager entityManager;

}


