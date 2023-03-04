package com.vitg.repository;

import com.vitg.entity.Student;
import com.vitg.entity.StudentBatch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentBatchRepository extends JpaRepository<StudentBatch, Integer> {
    public StudentBatch findByBatchId(int id);
}
