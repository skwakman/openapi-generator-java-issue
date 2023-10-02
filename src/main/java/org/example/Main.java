package org.example;

import com.example.api.model.ResponseContent;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Attempting to deserialize src/main/resources/example.json..");

        ResponseContent responseContent = new ObjectMapper().readValue(Files.readString(Path.of("src/main/resources/example.json")), ResponseContent.class);
        System.out.println("Successfully deserialized into " + responseContent);
    }
}