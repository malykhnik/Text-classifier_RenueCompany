package org.malykhnik.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FileInfo {
    private String guid;
    private String codeName;
    private String info;
}
