package com.vitg.entity;
import java.io.Serializable;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "topic")
public class Topic   implements Serializable{

	private static final long serialVersionUID = 4130758031076098234L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;	

	@Column(name = "name")
	private String name;

	@ManyToOne( targetEntity = SubCourse.class)
	@JoinColumn(name = "subcourse_id", referencedColumnName = "id")
	private SubCourse subCourse;

}
