package org.malykhnik.service.impl;

import org.malykhnik.entity.FileInfo;
import org.malykhnik.entity.JsonAnswer;
import org.malykhnik.service.FileService;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;


public class FileServiceImpl implements FileService {
    @Override
    public List<FileInfo> readCsv(String filename) {
        List<FileInfo> files = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename, StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine())!= null) {
                String[] parts = line.split("\\|");
                if (parts.length < 3) {
                    throw new IllegalArgumentException("Wrong test.csv format!");
                }
                files.add(new FileInfo(parts[0].trim(), parts[1].trim(), parts[2].trim()));
            }
        } catch (IOException e) {
            throw new RuntimeException("Can not open test.csv");
        }
        if (files.isEmpty()) {
            throw new RuntimeException("test.csv is empty!!");
        }
        return files;
    }

    @Override
    public List<String> readQueries(String filename) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename, StandardCharsets.UTF_8))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can not open input1.txt");
        }

        if (lines.isEmpty()) {
            throw new RuntimeException("input1.txt is empty!!");
        }
        return lines;
    }

    @Override
    public void writeParsedInfo(JsonAnswer jsonAnswer, String filename) {
        try {
            Path filePath = Path.of(filename);
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }

            BufferedWriter writer = Files.newBufferedWriter(filePath, StandardCharsets.UTF_8, StandardOpenOption.TRUNCATE_EXISTING);

            Gson gson = new Gson();
            String json = gson.toJson(jsonAnswer);

            writer.write(json);
            writer.newLine();

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
