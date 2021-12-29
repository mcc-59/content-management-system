/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.mii.backend.cms.service;

import com.id.mii.backend.cms.model.Media;
import com.id.mii.backend.cms.repository.MediaRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 *
 * @author USER
 */
@Service
public class MediaService {

    private final MediaRepository mediaRepository;

    @Autowired
    public MediaService(MediaRepository mediaRepository) {
        this.mediaRepository = mediaRepository;
    }

    public List<Media> getAll() {
        return mediaRepository.findAll();
    }
    
    public Media getById(Long id) {
        return mediaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Media not found"
                ));
    }
    
    public Media create(Media media) {
        if (media.getId() != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Media already exist");
        }
        return mediaRepository.save(media);
    }
    
    public Media update(Long id, Media media) {
        getById(id);
        
        media.setCreatedBy(media.getCreatedBy());
        media.setCreatedDate(media.getCreatedDate());
        media.setId(id);
        
        return mediaRepository.save(media);
    }
    
    public Media delete(Long id) {
        Media media = getById(id);
        mediaRepository.deleteById(id);
        
        return media;
    }
}
