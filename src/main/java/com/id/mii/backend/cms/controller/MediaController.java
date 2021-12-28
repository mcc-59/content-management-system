/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.mii.backend.cms.controller;

import com.id.mii.backend.cms.model.Media;
import com.id.mii.backend.cms.service.MediaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author USER
 */
@RestController
@RequestMapping("/media")
public class MediaController {

    private final MediaService mediaService;

    @Autowired
    public MediaController(MediaService mediaService) {
        this.mediaService = mediaService;
    }

    @GetMapping
    public ResponseEntity<List<Media>> getAll() {
        return new ResponseEntity(mediaService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Media> getById(@PathVariable("id") Long id) {
        return new ResponseEntity(mediaService.getById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Media> create(@RequestBody Media media) {
        return new ResponseEntity(mediaService.create(media), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Media> update(@PathVariable("id") Long id, @RequestBody Media media) {
        return new ResponseEntity(mediaService.update(id, media), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Media> delete(@PathVariable("id") Long id) {
        return new ResponseEntity(mediaService.delete(id), HttpStatus.OK);
    }
}
