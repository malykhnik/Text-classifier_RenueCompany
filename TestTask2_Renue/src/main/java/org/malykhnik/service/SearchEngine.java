package org.malykhnik.service;

import org.malykhnik.entity.FileInfo;
import org.malykhnik.entity.JsonAnswer;
import org.malykhnik.entity.PriorityDoc;

import java.util.List;
import java.util.PriorityQueue;

public interface SearchEngine {
    JsonAnswer search(List<FileInfo> csvLines, List<String> queries, JsonAnswer jsonAnswer);
}
