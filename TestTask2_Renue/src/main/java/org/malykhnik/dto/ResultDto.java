package org.malykhnik.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ResultDto {
    private String searchString;
    private List<String> guids = new ArrayList<>();
    private long time;
}
