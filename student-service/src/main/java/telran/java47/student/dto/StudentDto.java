package telran.java47.student.dto;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDto {
	int id;
	String name;
	Map<String, Integer> scores;
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public Map<String, Integer> getScores() {
		return scores;
	}
	
	
}
