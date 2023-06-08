package telran.java47.student.dao;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.data.mongodb.core.aggregation.AccumulatorOperators.Min;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import telran.java47.student.model.Student;

public interface StudentRepository extends MongoRepository<Student, Integer> {
	Stream<Student> findAllBy();
	
	Stream<Student> findByNameIgnoreCase(String name);
	
	Long countByNameInIgnoreCase(List<String> names);
	
	@Query("{'scores.?0':{'$gte': ?1}}")
	Stream<Student> findByScoresExamAndScoresGreaterThanEquals(String exam, int minScore);
}
