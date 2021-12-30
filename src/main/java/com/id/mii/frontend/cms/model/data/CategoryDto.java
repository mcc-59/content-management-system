/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.mii.frontend.cms.model.data;

import com.id.mii.frontend.cms.model.User;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author sihlihis
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDto {

    private User user;

    private List<String> categories = new ArrayList<>();

    private String title;

    private String body;

    private List<String> medias = new ArrayList<>();

    private Long views;

    private Boolean isLocked;
}
