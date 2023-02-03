package com.vitg.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vitg.dto.ResponseDTO;
import com.vitg.dto.TrainerCourseDTO;
import com.vitg.dto.TrainerDTO;
import com.vitg.entity.Course;
import com.vitg.entity.Trainer;
import com.vitg.entity.TrainerCourse;
import com.vitg.repository.CourseRepository;
import com.vitg.repository.TrainerCourseRepository;
import com.vitg.repository.TrainerRepository;
import com.vitg.service.TrainerCourseService;

@Service
public class TrainerCourseServiceImpl implements TrainerCourseService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	public TrainerCourseRepository trainerCourseRepository;

	@Autowired
	public TrainerRepository trainerRepository;

	@Autowired
	public CourseRepository courseRepository;

	@Override
	public ResponseDTO createCoursesOfTrainer(List<TrainerCourseDTO> trainerCourses) {
		for(TrainerCourseDTO selected:trainerCourses) {
			TrainerCourse trainerCourse = new TrainerCourse();
			Trainer trainer = trainerRepository.findById(selected.getTrainerId());
			Course course = courseRepository.findById(selected.getCourseId());
			trainerCourse.setTrainer(trainer);
			trainerCourse.setCourse(course);
			trainerCourseRepository.save(trainerCourse);
		}
		ResponseDTO responseDTO = new ResponseDTO();
		responseDTO.setMessage("Trainer Courses Created");
		return responseDTO;
	}


	@Override
	public List<TrainerCourseDTO> getTrainerListByCourseId(int courseId) {
		List<TrainerCourse> trainerList = trainerCourseRepository.findAll();

		List<TrainerCourseDTO> trainerCourseDTOList = new ArrayList<>();

		for(TrainerCourse trainerCourse:trainerList) {
			if(trainerCourse.getCourse().getId() == courseId) {
				TrainerCourseDTO trainerCourseDTO = modelMapper.map(trainerCourse, TrainerCourseDTO.class);
				trainerCourseDTOList.add(trainerCourseDTO);
			}
		}
		return trainerCourseDTOList;
	}

	@Override
	public List<TrainerCourseDTO> getCourseListById(int id){
		List<TrainerCourse> courseList = trainerCourseRepository.findAll();
		List<TrainerCourseDTO> trainerCouresDTOList = new ArrayList<>();

		for(TrainerCourse trainerCourse:courseList) {
			if(trainerCourse.getCourse().getId() == id) {
				TrainerCourseDTO trainerCourseDTO = modelMapper.map(trainerCourse, TrainerCourseDTO.class);
				trainerCouresDTOList.add(trainerCourseDTO);
			}
		}
		return trainerCouresDTOList;
	}

	@Override 
	public TrainerCourseDTO getTrainerCourseById(int id) {
		TrainerCourse trainerCourse = trainerCourseRepository.findById(id);
		TrainerCourseDTO trainerCourseDTO = modelMapper.map(trainerCourse,TrainerCourseDTO.class);
		return trainerCourseDTO;
	}


	@Override
	public List<TrainerCourseDTO> getAllTrainerCourse(){
		List<TrainerCourse> trainerCourseList = trainerCourseRepository.findAll();
		List<TrainerCourseDTO> trainerCourseDTOList = new ArrayList<>();

		for(TrainerCourse trainerCourse : trainerCourseList) {
			TrainerCourseDTO trainerCourseDTO = modelMapper.map(trainerCourse, TrainerCourseDTO.class);
			trainerCourseDTOList.add(trainerCourseDTO);
		}
		return trainerCourseDTOList;
	}


	
}
