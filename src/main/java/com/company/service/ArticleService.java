package com.company.service;

import com.company.dto.ArticleDTO;
import com.company.entity.ArticleEntity;
import com.company.entity.ProfileEntity;
import com.company.enums.ArticleStatus;
import com.company.exceptions.BadRequestException;
import com.company.exceptions.ItemNotFoundException;
import com.company.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private ProfileService profileService;

    public ArticleDTO create(ArticleDTO dto, Integer userId) {
        ProfileEntity profileEntity = profileService.get(userId);

        if (dto.getTitle() == null || dto.getTitle().isEmpty()) {
            throw new BadRequestException("Title can not be null");
        }
        if (dto.getContent() == null || dto.getContent().isEmpty()) {
            throw new BadRequestException("Content can not be null");
        }


        ArticleEntity entity = new ArticleEntity();
        entity.setTitle(dto.getTitle()); // null
        entity.setContent(dto.getContent());
        entity.setCreatedDate(Timestamp.valueOf(LocalDateTime.now()));
        entity.setProfile(profileEntity);

        articleRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    public void publish(Integer articleId, Integer userId) {
        ProfileEntity profileEntity = profileService.get(userId);

        ArticleEntity entity = get(articleId);
        entity.setStatus(ArticleStatus.PUBLISHED);
        articleRepository.save(entity);
    }

    public ArticleEntity get(Integer id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new ItemNotFoundException("Article not found"));
    }
}
