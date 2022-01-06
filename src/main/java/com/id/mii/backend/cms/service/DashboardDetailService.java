/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.id.mii.backend.cms.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.id.mii.backend.cms.repository.DashboardDetailRepository;
import java.util.List;

/**
 *
 * @author akhma
 */
@Service
public class DashboardDetailService {
    private DashboardDetailRepository dashboardDetailRepository;
    
    @Autowired
    public DashboardDetailService(DashboardDetailRepository dashboardDetailRepository) {
        this.dashboardDetailRepository = dashboardDetailRepository;
    }
    
    
    
    public Integer countWriter(){
        return dashboardDetailRepository.countWriter();
    }
    
    public Integer countContent(){
        return dashboardDetailRepository.countContent();
    }
    
    public Integer countView(){
        return dashboardDetailRepository.countViews();
    }
    
    public Integer countPending(){
        return dashboardDetailRepository.countPending();
    }
    
    public Integer countReject(){
        return dashboardDetailRepository.countRejected();
    }
    
}
