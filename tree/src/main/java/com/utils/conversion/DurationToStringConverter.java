/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.utils.conversion;



/**
 *
 * @author FITIA ARIVONY
 */
import java.time.Duration;
import org.springframework.core.convert.converter.Converter;

public class DurationToStringConverter implements Converter<Duration, String> {
    @Override
    public String convert(Duration duration) {
          
        long hours = duration.toHours();
        long totalMinutes = duration.toMinutes();
        long minutes = totalMinutes - (hours * 60);

        String formattedTime = String.format("%02d:%02d", hours, minutes);
        return formattedTime;
    }
}