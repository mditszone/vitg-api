package com.vitg.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "student_batch")
public class StudentBatch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER, targetEntity = Student.class)
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student student;

    @OneToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER, targetEntity = Batch.class)
    @JoinColumn(name = "batch_id", referencedColumnName = "id")
    private Batch batch;
    @Column(name = "is_message_sent", columnDefinition = "boolean default false")
    private boolean isMessageSent;

}
