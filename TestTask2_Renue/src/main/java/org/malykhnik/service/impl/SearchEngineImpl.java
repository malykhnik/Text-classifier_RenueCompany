package org.malykhnik.service.impl;

import lombok.AllArgsConstructor;
import org.malykhnik.dto.ResultDto;
import org.malykhnik.entity.FileInfo;
import org.malykhnik.entity.JsonAnswer;
import org.malykhnik.entity.PriorityDoc;
import org.malykhnik.service.Comparator;
import org.malykhnik.service.SearchEngine;

import java.util.List;
import java.util.PriorityQueue;

@AllArgsConstructor
public class SearchEngineImpl implements SearchEngine {
    @Override
    public JsonAnswer search(List<FileInfo> csvLines, List<String> queries, JsonAnswer jsonAnswer) {
        Comparator comparator = new ComparatorImpl();

        for (String query : queries) {
            PriorityQueue<PriorityDoc> queue = new PriorityQueue<>();
            ResultDto resultDto = new ResultDto();

            resultDto.setSearchString(query);

            long startTime = System.currentTimeMillis();

            for (FileInfo csvLine : csvLines) {
                int priority = comparator.compareRequest(query, csvLine.getInfo());
                String guid = csvLine.getGuid();
                String docInfo = csvLine.getInfo();
                queue.add(new PriorityDoc(priority, docInfo, guid));
            }

            long endTime = System.currentTimeMillis();

            resultDto.setTime(endTime - startTime);

            int count = Math.min(queue.size(), 5);
            for (int i = 0; i < count; i++) {
                PriorityDoc doc = queue.poll();
                resultDto.getGuids().add(doc.getGuid());
            }

            jsonAnswer.getResult().add(resultDto);
        }

        return jsonAnswer;
    }
}
