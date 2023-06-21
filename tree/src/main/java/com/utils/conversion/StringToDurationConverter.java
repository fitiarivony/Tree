/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utils.conversion;

import org.springframework.core.convert.converter.Converter;
import java.time.Duration;

/**
 *
 * @author FITIA ARIVONY
 */
public class StringToDurationConverter implements Converter<String, Duration> {
    @Override
    public Duration convert(String source) {
        Duration duration = null;
        if (source.matches("\\d{2}:\\d{2}")) { // Format HH:mm
            String[] parts = source.split(":");
            int hours = Integer.parseInt(parts[0]);
            int minutes = Integer.parseInt(parts[1]);
            duration = Duration.ofHours(hours).plusMinutes(minutes);
        } else if (source.startsWith("PT")) { // Format PT...
            try {
                duration = Duration.parse(source);
            } catch (Exception e) {
              e.printStackTrace();
                // Handle parsing exception if needed
            }
        }

        return duration;
    }
    

    
}