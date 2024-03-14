package org.malykhnik;

import org.malykhnik.dto.ResultDto;
import org.malykhnik.entity.FileInfo;
import org.malykhnik.entity.JsonAnswer;
import org.malykhnik.entity.PriorityDoc;
import org.malykhnik.service.FileService;
import org.malykhnik.service.SearchEngine;
import org.malykhnik.service.impl.ComparatorImpl;
import org.malykhnik.service.impl.FileServiceImpl;
import org.malykhnik.service.impl.SearchEngineImpl;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        FileService fileService = new FileServiceImpl();
        Scanner scanner = new Scanner(System.in);
        String fileData = scanner.nextLine();
        String inputFile = scanner.nextLine();

        JsonAnswer jsonAnswer = new JsonAnswer();
        long startTime = System.currentTimeMillis();

        List<FileInfo> csv = fileService.readCsv("C:/Users/shere/IdeaProjects/TestTask2_Renue/src/main/resources/data-files/" + fileData);
        List<String> queries = fileService.readQueries("C:/Users/shere/IdeaProjects/TestTask2_Renue/src/main/resources/input-files/" + inputFile);

        long endTime = System.currentTimeMillis();
        jsonAnswer.setInitTime(endTime - startTime);

        SearchEngine searchEngine = new SearchEngineImpl();
        fileService.writeParsedInfo(searchEngine.search(csv, queries, jsonAnswer));
    }

}
