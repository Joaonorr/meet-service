package com.demo.meetup;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class GenericControllerTest {
    
    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private MessageSource messageSource;

    @Value("${app.language}")
    private String appLanguageDefault;

    protected String getMessage(String messageKey) {
        return messageSource.getMessage(messageKey, null, new Locale(appLanguageDefault));
    }
}
