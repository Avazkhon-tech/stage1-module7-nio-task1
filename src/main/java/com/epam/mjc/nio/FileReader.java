package com.epam.mjc.nio;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.HashMap;
import java.util.Map;


public class FileReader {

    public Profile getDataFromFile(File file) {

        StringBuilder content = new StringBuilder();

        try (BufferedReader reader = Files.newBufferedReader(Paths.get(file.getPath()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Profile profile = new Profile();

        String string = content.toString();


        Map<String, String> info = new HashMap<>();
        String[] lines = string.split("\n");
        for (String line : lines) {
            String key = line.split(":")[0].replace(" ", "").toLowerCase();
            String value = line.split(":")[1].replace(" ", "");
            info.put(key, value);
        }

        profile.setName(info.get("name"));
        profile.setAge(Integer.valueOf(info.get("age")));
        profile.setPhone(Long.valueOf(info.get("phone")));
        profile.setEmail(info.get("email"));
        return profile;
    }
}
