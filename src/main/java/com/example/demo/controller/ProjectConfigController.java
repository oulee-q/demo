package com.example.demo.controller;

import com.example.demo.model.ProjectConfigView;
import com.example.demo.service.ProjectConfigService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/project-config")
public class ProjectConfigController {

    private final ProjectConfigService projectConfigService;

    public ProjectConfigController(ProjectConfigService projectConfigService) {
        this.projectConfigService = projectConfigService;
    }

    @GetMapping
    public ProjectConfigView getProjectConfig() {
        return projectConfigService.getCurrentConfig();
    }
}
