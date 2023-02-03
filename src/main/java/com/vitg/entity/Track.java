
package com.vitg.entity;
import java.io.Serializable;

import java.time.LocalDate;
import java.time.LocalTime;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "track")
public class Track  implements Serializable{

	private static final long serialVersionUID = 4130758031076098234L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;	

	@Column(name = "start_time")
	private LocalTime startTime;

	@Column(name = "end_time")
	private LocalTime endTime;

	@Column(name ="date")
	private LocalDate date;

	@Column(name = "remarks")
	private String remarks;

	@Column(name="topicsCovered")
	private String topicsCovered;
	
	@ManyToOne( targetEntity = Batch.class)
	@JoinColumn(name = "batch_id", referencedColumnName = "id")
	private Batch batch;

}