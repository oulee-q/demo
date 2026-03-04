package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {
        "spring.cloud.nacos.config.enabled=false",
        "custom.project.name=test-project",
        "custom.project.database.url=jdbc:h2:mem:testdb",
        "custom.project.database.username=sa",
        "custom.project.database.password=sa",
        "custom.project.database.driver-class-name=org.h2.Driver"
})
class DemoApplicationTests {

    @Test
    void contextLoads() {
    }
}
