package telran.java47.student.service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import telran.java47.student.dao.StudentRepository;
import telran.java47.student.dto.ScoreDto;
import telran.java47.student.dto.StudentCreateDto;
import telran.java47.student.dto.StudentDto;
import telran.java47.student.dto.StudentUpdateDto;
import telran.java47.student.dto.exeptions.StudentNotFoundExeption;
import telran.java47.student.model.Student;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

	final StudentRepository studentRepository;
	final ModelMapper modelMapper;

	@Override
	public boolean addStudent(StudentCreateDto studentCreateDto) {
		if (studentRepository.existsById(studentCreateDto.getId())) {
			return false;
		}
//		Student student = new Student(studentCreateDto.getId(), studentCreateDto.getName(),
//				studentCreateDto.getPassword());
		Student student = modelMapper.map(studentCreateDto, Student.class);
		studentRepository.save(student);
		return true;
	}

	@Override
	public StudentDto findStudent(int id) {
		Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundExeption());
		return modelMapper.map(student, StudentDto.class);
	}

	@Override
	public StudentDto removeStudent(int id) {
		Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundExeption());
//		if (student != null) {
		studentRepository.deleteById(id);
		return modelMapper.map(student, StudentDto.class);
//		}
	}

	@Override
	public StudentCreateDto updateStudent(int id, StudentUpdateDto studentUpdateDto) {
		Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundExeption());
//		if (student != null) {
		student.setName(studentUpdateDto.getName());
		student.setPassword(studentUpdateDto.getPassword());
		studentRepository.save(student);
		return modelMapper.map(student, StudentCreateDto.class);
//		}
//		return null;
	}

	@Override
	public boolean addScore(int id, ScoreDto scoreDto) {
		Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundExeption());
//		if (student != null) {
		boolean res = student.addScore(scoreDto.getExamName(), scoreDto.getScore());
		studentRepository.save(student);
		return res;
//		}
//		return false;
	}

	@Override
	public List<StudentDto> findStudentsByName(String name) {
		return studentRepository.findByNameIgnoreCase(name)
				.map(s -> modelMapper.map(s, StudentDto.class))
				.collect(Collectors.toList());
	}

	@Override
	public long getStudentsNamesQuantity(List<String> names) {
		return studentRepository.countByNameInIgnoreCase(names);
	}

	@Override
	public List<StudentDto> getStudentsByExamMinScore(String exam, int minScore) {
		return studentRepository.findByScoresExamAndScoresGreaterThanEquals(exam, minScore)
				.map(s -> modelMapper.map(s, StudentDto.class))
				.collect(Collectors.toList());
	}
}
