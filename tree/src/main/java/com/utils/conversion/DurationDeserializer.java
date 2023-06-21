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
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.Duration;
import java.time.format.DateTimeParseException;

/**
 *
 * @author FITIA ARIVONY
 */
public class DurationDeserializer extends JsonDeserializer<Duration> {

  @Override
public Duration deserialize(JsonParser parser, DeserializationContext context) throws IOException {
    String value = parser.readValueAs(String.class);
    System.out.println("Received value: " + value);
    if(value.startsWith("PT"))return Duration.parse(value);
    String formattedValue = "PT" + value.replace(":", "H") + "M";
    System.out.println("Formatted value: " + formattedValue);

    try {
        Duration duration = Duration.parse(formattedValue);
        System.out.println("Parsed duration: " + duration);
        return duration;
    } catch (DateTimeParseException e) {
        System.out.println("Failed to parse duration: " + e.getMessage());
        throw new JsonParseException(parser, "Failed to parse duration: " + e.getMessage(), e);
    }
}

}

