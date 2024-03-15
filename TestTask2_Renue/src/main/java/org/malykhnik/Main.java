package org.malykhnik;

import org.malykhnik.entity.FileInfo;
import org.malykhnik.entity.JsonAnswer;
import org.malykhnik.service.FileService;
import org.malykhnik.service.SearchEngine;
import org.malykhnik.service.impl.FileServiceImpl;
import org.malykhnik.service.impl.SearchEngineImpl;

import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        FileService fileService = new FileServiceImpl();

        String fileData = "";
        String inputFile = "";
        String outputFile = "";

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("--data") && i + 1 < args.length) {
                fileData = args[i + 1];
            } else if (args[i].equals("--input-file") && i + 1 < args.length) {
                inputFile = args[i + 1];
            } else if (args[i].equals("--output-file") && i + 1 < args.length) {
                outputFile = args[i + 1];
            }
        }

        if (fileData.isEmpty() || inputFile.isEmpty() || outputFile.isEmpty()) {
            throw new RuntimeException("Incorrect parameters provided!");
        }

        fileData = fileData.substring(1);
        inputFile = inputFile.substring(1);
        outputFile = outputFile.substring(1);

        JsonAnswer jsonAnswer = new JsonAnswer();
        long startTime = System.currentTimeMillis();

        List<FileInfo> csv = fileService.readCsv(fileData);
        List<String> queries = fileService.readQueries(inputFile);

        long endTime = System.currentTimeMillis();
        jsonAnswer.setInitTime(endTime - startTime);

        SearchEngine searchEngine = new SearchEngineImpl();
        fileService.writeParsedInfo(searchEngine.search(csv, queries, jsonAnswer), outputFile);
    }

}
