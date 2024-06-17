package pro.sky.Homework2._1.Employeebook;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.Homework2._1.Employeebook.exception.EmployeeNotFoundException;
import pro.sky.Homework2._1.Employeebook.model.Employee;
import pro.sky.Homework2._1.Employeebook.service.DepartmentService;
import pro.sky.Homework2._1.Employeebook.service.EmployeeService;

import java.util.List;
import java.util.Map;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class DepartmentServiceTest {
    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private DepartmentService departmentService;

    private final List<Employee> employees = List.of(
            new Employee("Иван", "Иванов", 40_000,1),
            new Employee("Дмитрий", "Скворцов", 50_000,1),
            new Employee("Петр", "Петров", 70_000,2),
            new Employee("Константин", "Козлов", 80_000,3)
    );

    @BeforeEach
    public void beforeEach() {
        when(employeeService.print()).thenReturn(employees);
    }

    @Test
    public void findEmployeesByDepartmentFromDepartmentTest() {
        assertThat(departmentService.findEmployeesByDepartment(1))
                .containsExactlyInAnyOrder(
                        new Employee("Иван", "Иванов", 40_000,1),
                        new Employee("Дмитрий", "Скворцов", 50_000,1)
                );
    }

    @Test
    public void calculateSumOfSalariesOfEmployeesFromDepartmentTest() {
        assertThat(departmentService.calculateSumOfSalariesOfEmployeesFromDepartment(1))
                .isEqualTo(90_000);
    }

    @Test
    public void calculateSumOfSalariesOfEmployeesFromDepartmentNegativeTest() {
        assertThat(departmentService.calculateSumOfSalariesOfEmployeesFromDepartment(5))
                .isEqualTo(0);
    }

    @Test
    public void findEmployeeWithMaxSalaryTest() {
        assertThat(departmentService.findEmployeeWithMaxSalary(1))
                .isEqualTo(50_000);
    }

    @Test
    public void findEmployeeWithMaxSalaryNegativeTest() {
        assertThatExceptionOfType(EmployeeNotFoundException.class)
        .isThrownBy(() -> departmentService.findEmployeeWithMaxSalary(5));
    }

    @Test
    public void findEmployeeWithMinSalaryTest() {
        assertThat(departmentService.findEmployeeWithMinSalary(1))
                .isEqualTo(40_000);
    }

    @Test
    public void findEmployeeWithMinSalaryNegativeTest() {
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> departmentService.findEmployeeWithMinSalary(5));
    }

    @Test
    public void findEmployeesByDepartmentTest() {
        assertThat(departmentService.findEmployeesByDepartment())
                .containsExactlyInAnyOrderEntriesOf(
                        Map.of(
                                1, List.of(new Employee("Иван", "Иванов", 40_000,1),
                                        new Employee("Дмитрий", "Скворцов", 50_000,1)),
                                2, List.of(new Employee("Петр", "Петров", 70_000,2)),
                                3, List.of(new Employee("Константин", "Козлов", 80_000,3))
                        )
                );
    }


}
