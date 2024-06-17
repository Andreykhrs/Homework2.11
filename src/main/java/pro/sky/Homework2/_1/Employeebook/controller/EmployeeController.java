package pro.sky.Homework2._1.Employeebook.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.Homework2._1.Employeebook.model.Employee;
import pro.sky.Homework2._1.Employeebook.service.EmployeeService;

import java.util.Collection;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    public Employee add(@RequestParam String firstName,
                        @RequestParam String lastName,
                        @RequestParam Integer salary,
                        @RequestParam Integer department) {
        return employeeService.add(firstName, lastName, salary, department);
    }

    @GetMapping("/remove")
    public Employee remove(@RequestParam String firstName,
                           @RequestParam String lastName) {
        return employeeService.remove(firstName, lastName);
    }

    @GetMapping("/find")
    public Employee find(@RequestParam String firstName,
                         @RequestParam String lastName) {
        return employeeService.find(firstName, lastName);
    }

    @GetMapping()
    public Collection<Employee> print() {
        return employeeService.print();
    }
}
