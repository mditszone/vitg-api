package com.vitg.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Data
public class FaqDataDTO {
        private int id;

        private String question;

        private String answer;
}
