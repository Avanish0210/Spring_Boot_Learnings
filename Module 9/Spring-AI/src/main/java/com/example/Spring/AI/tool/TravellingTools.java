package com.example.Spring.AI.tool;

import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Service;

@Service
public class TravellingTools {

    @Tool(description = "Get the Weather of a city")
    public String getWeather(@ToolParam(description = "City name for which to get the weather information" ) String city){
        return switch (city){
            case "Delhi" -> "sunny , 26 Degrees";
            case "London" -> "cloudy , 2 Degrees";
            default -> "cannot identify the city";
        };
    }
}
