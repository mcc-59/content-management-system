/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.mii.backend.cms.service;

import com.id.mii.backend.cms.model.Content;
import com.id.mii.backend.cms.model.ContentCategory;
import com.id.mii.backend.cms.model.Media;
import com.id.mii.backend.cms.model.Status;
import com.id.mii.backend.cms.model.Type;
import com.id.mii.backend.cms.model.User;
import com.id.mii.backend.cms.model.data.CategoryDto;
import com.id.mii.backend.cms.model.data.ContentDto;
import com.id.mii.backend.cms.model.key.ContentCategoryKey;
import com.id.mii.backend.cms.repository.ContentCategoryRepository;
import com.id.mii.backend.cms.repository.ContentRepository;
import com.id.mii.backend.cms.repository.MediaRepository;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author USER
 */
@Service
public class ContentService {

    private final ContentRepository contentRepository;
    private final ContentCategoryRepository contentCategoryRepository;
    private final MediaRepository mediaRepository;
    private final UserService userService;
    private final CategoryService categoryService;
    private final TypeService typeService;
    private final StatusService statusService;

    @Autowired
    public ContentService(ContentRepository contentRepository, ContentCategoryRepository contentCategoryRepository, MediaRepository mediaRepository, UserService userService, CategoryService categoryService, TypeService typeService, StatusService statusService) {
        this.contentRepository = contentRepository;
        this.contentCategoryRepository = contentCategoryRepository;
        this.mediaRepository = mediaRepository;
        this.userService = userService;
        this.categoryService = categoryService;
        this.typeService = typeService;
        this.statusService = statusService;
    }
    
    public List<CategoryDto> getAll() {
        List<Content> findAll = contentRepository.findAll();
        List<CategoryDto> categoryDtos = new ArrayList<>();
        
        findAll.forEach(data -> {
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setUser(data.getUser());
            categoryDto.setType(data.getType());
            categoryDto.setTitle(data.getTitle());
            data.getContentCategories().forEach(contentCategory -> {
                categoryDto.getCategories().add(contentCategory.getCategory().getName());
            });
            data.getMedias().forEach(media -> {
                categoryDto.getMedias().add(media.getPath());
            });
            categoryDto.setBody(data.getBody());
            categoryDto.setViews(data.getViews());
            categoryDto.setIsLocked(data.getIsLocked());
            categoryDtos.add(categoryDto);
        });
        
        return categoryDtos;
    }

    public Content getById(Long id) {
        return contentRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Content not found"
        ));
    }

    @Transactional
    public Content create(ContentDto contentDto) {
        User user = userService.getById(contentDto.getUser());
        Status status = statusService.getById(1L);
        Type type = typeService.getById(contentDto.getType());
        System.out.println(contentDto);
        Content contentData = Content
                .builder()
                .user(user)
                .status(status)
                .publishDate(LocalDateTime.now())
                .expiredDate(LocalDateTime.now().plusDays(14))
                .type(type)
                .title(contentDto.getTitle())
                .body(contentDto.getBody())
                .views(0L)
                .isLocked(true)
                .build();

        Content content = contentRepository.save(contentData);
        
        List<ContentCategory> contentCategories = contentDto.getCategories()
                .stream()
                .map(category -> ContentCategory
                    .builder()
                    .id(ContentCategoryKey
                        .builder()
                        .contentId(content.getId())
                        .categoryId(category)
                        .build())
                    .content(content)
                    .category(categoryService.getById(category))
                    .build())
                .collect(Collectors.toList());

        contentCategoryRepository.saveAll(contentCategories);
        
        List<Media> medias = contentDto.getMedias()
                .stream()
                .map(media -> Media
                    .builder()
                    .path(media)
                    .content(content)
                    .build())
                .collect(Collectors.toList());
        
        mediaRepository.saveAll(medias);
        
        return content;
    }

    public Content update(Long id, Content content) {
        getById(id);

        content.setCreatedBy(content.getCreatedBy());
        content.setCreatedDate(content.getCreatedDate());
        content.setId(id);
        
        return contentRepository.save(content);
    }
    
    public Content countViews(Long id) {
        Content content = getById(id);
        content.setViews(content.getViews() + 1);
        return contentRepository.save(content);
    }

    public Content delete(Long id) {
        Content content = getById(id);
        contentRepository.deleteById(id);

        return content;
    }
}
