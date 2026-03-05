package com.example.demo.model;

import java.util.List;

public record ProjectConfigView(
        String name,
        String owner,
        boolean featureEnabled,
        List<String> whitelistIps,
        DatabaseView database
) {
    public record DatabaseView(
            String url,
            String username,
            String passwordMasked,
            String driverClassName
    ) {
    }
}
