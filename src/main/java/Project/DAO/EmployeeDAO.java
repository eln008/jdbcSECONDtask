package Project.DAO;

import Project.Model.Employee;
import Project.Model.Job;

import java.util.List;
import java.util.Map;

public interface EmployeeDAO {
    void createEmployee();
    void addEmployee(Employee employee);
    void dropTable();
    void cleanTable();
    void updateEmployee(Long id, Employee employee);
    List<Employee>getAllEmployees();
    Employee findEmail(String email);
    Map<Employee, Job>  getEmployeeById(Long employeeId);
    List<Employee> getEmployeeByPosition(String position);
}
