package org.malykhnik.service;

import org.malykhnik.entity.FileInfo;
import org.malykhnik.entity.JsonAnswer;

import java.util.List;

public interface FileService {
    List<FileInfo> readCsv(String filename);
    List<String> readQueries(String filename);
    void writeParsedInfo(JsonAnswer jsonAnswer, String filename);
}
