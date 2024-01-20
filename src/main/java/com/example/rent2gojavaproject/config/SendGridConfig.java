package com.example.rent2gojavaproject.config;

import com.sendgrid.SendGrid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class SendGridConfig {



        @Value("${sendgrid.key}")
        private String key;
       String SENDGRID_API_KEY= "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";

        @Bean
        public SendGrid getSendgrid()
        {
            return new SendGrid(SENDGRID_API_KEY);
        }


}
