package org.malykhnik.service.impl;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.malykhnik.entity.FileInfo;
import org.malykhnik.entity.JsonAnswer;
import org.malykhnik.entity.PriorityDoc;
import org.malykhnik.service.FileService;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import com.google.gson.Gson;


public class FileServiceImpl implements FileService {
    @Override
    public List<FileInfo> readCsv(String filename) {
        List<FileInfo> files = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(filename))) {
            String[] line;
            while ((line = reader.readNext())!= null) {
                String[] parts = line[0].split("\\|");
                if (parts.length < 3) {
                    throw new RuntimeException("Wrong file.csv format!");
                }
                files.add(new FileInfo(parts[0].trim(), parts[1].trim(), parts[2].trim()));
            }
        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException("Can not open path-to-csv.csv");
        }
        return files;
    }

    @Override
    public List<String> readQueries(String filename) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException("Can not open input-path-to-file.txt");
        }
        return lines;
    }

    @Override
    public void writeParsedInfo(JsonAnswer jsonAnswer) {
        try {
            Path filePath = Path.of("C:/Users/shere/IdeaProjects/TestTask2_Renue/src/main/resources/output-files/output-path-to-file.json");
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }

            BufferedWriter writer = Files.newBufferedWriter(filePath, StandardOpenOption.TRUNCATE_EXISTING);

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
