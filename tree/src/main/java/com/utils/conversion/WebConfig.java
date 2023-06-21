package com.utils.conversion;

import com.utils.conversion.StringToDurationConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        System.out.println("Add converters ----------------");
        registry.addConverter(new DurationToStringConverter());
        registry.addConverter(new StringToDurationConverter());
    }
}
