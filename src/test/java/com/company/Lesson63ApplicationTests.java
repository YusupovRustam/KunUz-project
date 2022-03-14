package com.company;

import com.company.dto.ArticleDTO;
import com.company.dto.ProfileDTO;
import com.company.enums.ProfileRole;
import com.company.service.ArticleService;
import com.company.service.ProfileService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Lesson63ApplicationTests {

    @Autowired
    private ProfileService profileService;
    @Autowired
    private ArticleService articleService;

@Test
    public void filterTest(){

}

}
