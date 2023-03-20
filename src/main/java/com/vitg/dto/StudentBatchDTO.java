package com.vitg.dto;

import com.vitg.entity.Batch;
import com.vitg.entity.Student;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

public class StudentBatchDTO {
    private int id;
    private Student student;
    private Batch batch;
}
