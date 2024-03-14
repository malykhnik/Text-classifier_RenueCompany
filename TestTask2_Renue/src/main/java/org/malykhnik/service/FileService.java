package org.malykhnik.service;

import org.malykhnik.entity.FileInfo;
import org.malykhnik.entity.JsonAnswer;
import org.malykhnik.entity.PriorityDoc;

import java.util.List;
import java.util.PriorityQueue;

public interface FileService {
    List<FileInfo> readCsv(String filename);
    List<String> readQueries(String filename);

    void writeParsedInfo(JsonAnswer jsonAnswer);
}
