package com.netcracker.config;

import com.netcracker.models.DomenUser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DomenUserConfig {

    @Bean
    public DomenUser getDomenUser() {
        return new DomenUser()
                .setDomenName("assistent.folktaxi@yandex.ru")
                .setDomenPassword("WEf-J53-v8g-dYx");
    }

}
