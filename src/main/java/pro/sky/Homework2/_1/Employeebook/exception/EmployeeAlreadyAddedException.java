package pro.sky.Homework2._1.Employeebook.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_GATEWAY)
public class EmployeeAlreadyAddedException extends RuntimeException{
}
