package com.company.controller;

import com.company.dto.ArticleDTO;
import com.company.dto.ProfileJwtDTO;
import com.company.enums.ProfileRole;
import com.company.exceptions.BadRequestException;
import com.company.exceptions.ForbiddenException;
import com.company.exceptions.ItemNotFoundException;
import com.company.exceptions.UnauthorizedException;
import com.company.service.ArticleService;
import com.company.util.JwtUtil;
import org.hibernate.PropertyValueException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ArticleDTO dto,
                                    HttpServletRequest request) {
        ProfileJwtDTO jwtDTO = JwtUtil.getProfile(request, ProfileRole.MODERATOR_ROLE);
        ArticleDTO response = articleService.create(dto, jwtDTO.getId());
        return ResponseEntity.ok(response);
    }

    @PutMapping("/publish/{id}")
    public ResponseEntity<?> publish(@PathVariable("id") Integer id,
                                     HttpServletRequest request) {
        ProfileJwtDTO jwtDTO = JwtUtil.getProfile(request, ProfileRole.PUBLISHER_ROLE);
        articleService.publish(id, jwtDTO.getId());
        return ResponseEntity.ok().build();
    }

}
