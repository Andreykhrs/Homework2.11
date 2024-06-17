package pro.sky.Homework2._1.Employeebook.controller;

import org.springframework.web.bind.annotation.*;
import pro.sky.Homework2._1.Employeebook.model.Employee;
import pro.sky.Homework2._1.Employeebook.service.DepartmentService;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("{department}/salary/max")
    public int findEmployeeWithMaxSalary(@PathVariable int department) {
        return departmentService.findEmployeeWithMaxSalary(department);
    }

    @GetMapping("{department}/salary/min")
    public  int findEmployeeWithMinSalary(@PathVariable int department) {
        return departmentService.findEmployeeWithMinSalary(department);
    }

    @GetMapping("{department}/employees")
    public Collection<Employee> findEmployeesByDepartment(@PathVariable int department){
        return departmentService.findEmployeesByDepartment(department);
    }
    @GetMapping("employees")
    public Map<Integer, List<Employee>> findEmployeesByDepartment(){
        return departmentService.findEmployeesByDepartment();
    }

    @GetMapping("{department}/salary/sum")
    public int calculateSumOfSalariesOfEmployeesFromDepartment(@PathVariable int department){
        return departmentService.calculateSumOfSalariesOfEmployeesFromDepartment(department);
    }




}
