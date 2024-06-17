package pro.sky.Homework2._1.Employeebook.service;

import org.springframework.stereotype.Service;
import pro.sky.Homework2._1.Employeebook.exception.EmployeeNotFoundException;
import pro.sky.Homework2._1.Employeebook.model.Employee;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.groupingBy;

@Service
public class DepartmentService {
    private final EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public int findEmployeeWithMaxSalary(int department) {

        return employeeService.print().stream()
                .filter(e -> e.getDepartment() == department)
                .max(comparingInt(e -> e.getSalary()))
                .map(Employee::getSalary)
                .orElseThrow(EmployeeNotFoundException::new);

    }

    public int findEmployeeWithMinSalary(int department) {
        return employeeService.print().stream()
                .filter(e -> e.getDepartment() == department)
                .min(comparingInt(e -> e.getSalary()))
                .map(Employee::getSalary)
                .orElseThrow(EmployeeNotFoundException::new);
    }

    public Collection<Employee> findEmployeesByDepartment(int department) {
        return employeeService.print().stream()
                .filter(e -> e.getDepartment() == department)
                .collect(Collectors.toList());
    }

    public Map<Integer, List<Employee>> findEmployeesByDepartment() {
        return employeeService.print().stream()
                .collect(groupingBy(Employee::getDepartment));
    }

    public int calculateSumOfSalariesOfEmployeesFromDepartment(int department) {
        return employeeService.print().stream()
                .filter(e -> e.getDepartment() == department)
                .map(Employee::getSalary)
                .reduce(0, Integer::sum);
    }


}
