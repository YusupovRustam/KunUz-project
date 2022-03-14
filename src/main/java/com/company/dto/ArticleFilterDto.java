package com.company.dto;


import com.company.enums.ArticleStatus;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Setter
@Getter
public class ArticleFilterDto {

    private String title;
    private Integer profileId;
    private Integer articleId;
    private ArticleStatus status;

    private LocalDate fromDate;
    private LocalDate toDate;
}
