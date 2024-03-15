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
                    throw new RuntimeException("Wrong file.csv format!");
                }
                files.add(new FileInfo(parts[0].trim(), parts[1].trim(), parts[2].trim()));
            }
        } catch (IOException e) {
            throw new RuntimeException("Can not open path-to-csv.csv");
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
            throw new RuntimeException("Can not open input-path-to-file.txt");
        }

        if (lines.isEmpty()) {
            throw new RuntimeException("input.txt is empty!!");
        }
        return lines;
    }

    @Override
    public void writeParsedInfo(JsonAnswer jsonAnswer) {
        try {
            Path filePath = Path.of("C:/Users/shere/IdeaProjects/TestTask2_Renue/temp/result1.json");
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
