package com.isaac.devall.controller;

import com.isaac.devall.entity.Site;
import com.isaac.devall.repository.SiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sites")
public class SiteController {

    @Autowired
    private SiteRepository siteRepository;

    @GetMapping
    public List<Site> listarSites() {
        return siteRepository.findAll();
    }
}
