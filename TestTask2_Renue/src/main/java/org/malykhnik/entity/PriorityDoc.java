package org.malykhnik.entity;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class PriorityDoc implements Comparable<PriorityDoc> {
    private int priority;
    private String csvLine;
    private String guid;

    @Override
    public int compareTo(PriorityDoc other) {
        return Integer.compare(this.priority, other.priority);
    }
}