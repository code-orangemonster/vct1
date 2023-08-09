package com.dhu.autooptimizationvctdatabase.entity;

import com.dhu.autooptimizationvctdatabase.utils.IntervalTime;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class GraphMsg implements Serializable {
    private String id;
    private Integer steps;
    private Integer indegrees;
}
