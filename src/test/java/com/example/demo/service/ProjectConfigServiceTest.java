package com.example.demo.service;

import com.example.demo.config.CustomProjectProperties;
import com.example.demo.model.ProjectConfigView;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProjectConfigServiceTest {

    @Test
    void shouldBuildCurrentConfigAndMaskPassword() {
        CustomProjectProperties properties = new CustomProjectProperties();
        properties.setName("order-center");
        properties.setOwner("platform-team");
        properties.setFeatureEnabled(true);
        properties.setWhitelistIps(List.of("10.10.1.11", "10.10.1.12"));
        properties.getDatabase().setUrl("jdbc:mysql://127.0.0.1:3306/demo");
        properties.getDatabase().setUsername("demo_user");
        properties.getDatabase().setPassword("demo_password");
        properties.getDatabase().setDriverClassName("com.mysql.cj.jdbc.Driver");

        ProjectConfigService service = new ProjectConfigService(properties);

        ProjectConfigView view = service.getCurrentConfig();

        assertEquals("order-center", view.name());
        assertEquals("platform-team", view.owner());
        assertEquals(List.of("10.10.1.11", "10.10.1.12"), view.whitelistIps());
        assertEquals("jdbc:mysql://127.0.0.1:3306/demo", view.database().url());
        assertEquals("demo_user", view.database().username());
        assertEquals("d***d", view.database().passwordMasked());
        assertEquals("com.mysql.cj.jdbc.Driver", view.database().driverClassName());
    }

    @Test
    void shouldMaskShortOrNullPasswordToFallbackValue() {
        CustomProjectProperties properties = new CustomProjectProperties();
        properties.setName("test");
        properties.getDatabase().setUrl("jdbc:h2:mem:testdb");
        properties.getDatabase().setUsername("sa");
        properties.getDatabase().setPassword("x");
        properties.getDatabase().setDriverClassName("org.h2.Driver");

        ProjectConfigService service = new ProjectConfigService(properties);
        ProjectConfigView view = service.getCurrentConfig();
        assertEquals("**", view.database().passwordMasked());

        properties.getDatabase().setPassword(null);
        view = service.getCurrentConfig();
        assertEquals("**", view.database().passwordMasked());
    }
}
