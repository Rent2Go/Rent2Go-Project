package com.example.rent2gojavaproject.config;

import com.sendgrid.SendGrid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class SendGridConfig {



        @Value("${sendgrid.key}")
        String SENDGRID_API_KEY;

        @Bean
        public SendGrid getSendgrid()
        {
            return new SendGrid(SENDGRID_API_KEY);
        }


}
