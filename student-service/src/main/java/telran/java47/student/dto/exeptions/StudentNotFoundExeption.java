package telran.java47.student.dto.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class StudentNotFoundExeption extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8263391932617132155L;

}
