package com.example.demo.service;

import com.example.demo.config.CustomProjectProperties;
import com.example.demo.model.ProjectConfigView;
import org.springframework.stereotype.Service;

@Service
public class ProjectConfigService {

    private final CustomProjectProperties properties;

    public ProjectConfigService(CustomProjectProperties properties) {
        this.properties = properties;
    }

    public ProjectConfigView getCurrentConfig() {
        return new ProjectConfigView(
                properties.getName(),
                properties.getOwner(),
                properties.isFeatureEnabled(),
                properties.getWhitelistIps(),
                new ProjectConfigView.DatabaseView(
                        properties.getDatabase().getUrl(),
                        properties.getDatabase().getUsername(),
                        mask(properties.getDatabase().getPassword()),
                        properties.getDatabase().getDriverClassName()
                )
        );
    }

    private String mask(String source) {
        if (source == null || source.length() < 2) {
            return "**";
        }
        return source.charAt(0) + "***" + source.charAt(source.length() - 1);
    }
}
