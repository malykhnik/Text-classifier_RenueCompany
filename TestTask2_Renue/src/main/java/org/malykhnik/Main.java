package org.malykhnik;

import org.malykhnik.entity.FileInfo;
import org.malykhnik.entity.JsonAnswer;
import org.malykhnik.service.FileService;
import org.malykhnik.service.SearchEngine;
import org.malykhnik.service.impl.FileServiceImpl;
import org.malykhnik.service.impl.SearchEngineImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        FileService fileService = new FileServiceImpl();

        String fileData = "C:/Users/shere/IdeaProjects/TestTask2_Renue/data/test.csv";
        String inputFile = "C:/Users/shere/IdeaProjects/TestTask2_Renue/temp/input1.txt";

        JsonAnswer jsonAnswer = new JsonAnswer();
        long startTime = System.currentTimeMillis();

        if (fileData.isEmpty() || inputFile.isEmpty()) {
            throw new RuntimeException("fileData name or inputFile name is Empty!");
        }

        List<FileInfo> csv = fileService.readCsv(fileData);
        List<String> queries = fileService.readQueries(inputFile);

        long endTime = System.currentTimeMillis();
        jsonAnswer.setInitTime(endTime - startTime);

        SearchEngine searchEngine = new SearchEngineImpl();
        fileService.writeParsedInfo(searchEngine.search(csv, queries, jsonAnswer));
    }

}
