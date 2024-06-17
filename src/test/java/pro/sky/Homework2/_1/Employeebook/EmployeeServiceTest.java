package pro.sky.Homework2._1.Employeebook;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import pro.sky.Homework2._1.Employeebook.exception.EmployeeAlreadyAddedException;
import pro.sky.Homework2._1.Employeebook.exception.EmployeeNotFoundException;
import pro.sky.Homework2._1.Employeebook.exception.EmployeeStorageIsFullException;
import pro.sky.Homework2._1.Employeebook.model.Employee;
import pro.sky.Homework2._1.Employeebook.service.EmployeeService;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;


public class EmployeeServiceTest {
    private final EmployeeService employeeService = new EmployeeService(new HashMap<>());
    private final List<Employee> employees = List.of(
            new Employee("Иван", "Иванов", 40_000,1),
            new Employee("Дмитрий", "Скворцов", 50_000,1),
            new Employee("Петр", "Петров", 70_000,2),
            new Employee("Константин", "Козлов", 80_000,3)
    );

    @BeforeEach
    public void beforeEach() {
        employees.forEach(employee -> employeeService.add(employee.getFirstName(),
                employee.getLastName(),
                employee.getSalary(),
                employee.getDepartment()));
    }

    @AfterEach
    public void afterEach() {
        Collection<Employee> collection = employeeService.print();
        collection.forEach(employee -> employeeService.remove(employee.getFirstName(), employee.getLastName()));

    }

    @Test
    void addTest() {

        Employee expected = new Employee("Сергей", "Бурунов", 35_000, 1);

        Employee actual = employeeService.add("Сергей", "Бурунов", 35_000, 1);

        assertThat(actual).isEqualTo(expected);
        assertThat(actual).isEqualTo(employeeService.find("Сергей", "Бурунов"));
        assertThat(actual).isIn(employeeService.print());
    }

    @Test
    public void when_employeeService_is_full_then_EmployeeStorageIsFullException_is_thrown() {

        employeeService.add("Сергей", "Бурунов", 35_000, 1);
        employeeService.add("Сергей", "Светлаков", 135_000, 2);

        assertThatExceptionOfType(EmployeeStorageIsFullException.class)
                .isThrownBy(() -> employeeService.add("Максим", "Дюжев", 120_000, 3));


    }

    @Test
    public void when_employeeService_contains_employee_then_EmployeeAlreadyAddedException_is_thrown() {

        assertThatExceptionOfType(EmployeeAlreadyAddedException.class)
                .isThrownBy(() -> employeeService.add("Иван", "Иванов", 40_000,1));


    }


    @Test
    void findTest() {

        Employee expected = new Employee("Иван", "Иванов", 40_000,1);
        assertThat(employeeService.print().contains(expected));

        Employee actual = employeeService.find("Иван", "Иванов");

        assertThat(actual).isEqualTo(expected);

    }

    @Test
    void findNegativeTest() {
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeService.find("Сергей", "Бурунов"));
    }

    @Test
    void removeTest() {

        Employee expected = new Employee("Иван", "Иванов", 40_000,1);
        assertThat(employeeService.print().contains(expected));

        Employee actual = employeeService.remove("Иван", "Иванов");

        assertThat(actual).isEqualTo(expected);
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeService.find("Иван", "Иванов"));
        assertThat(actual).isNotIn(employeeService.print());
    }

    @Test
    void removeNegativeTest() {
        assertThatExceptionOfType(EmployeeNotFoundException.class)
                .isThrownBy(() -> employeeService.remove("Сергей", "Бурунов"));
    }

    @Test
    public void printTest() {
        assertThat(employeeService.print())
                .containsExactlyInAnyOrderElementsOf(employees);
    }


}
