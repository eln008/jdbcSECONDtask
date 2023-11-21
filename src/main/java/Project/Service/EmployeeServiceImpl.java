package Project.Service;

import Project.DAO.EmployeeDAO;
import Project.DAO.EmployeeDAOImpl;
import Project.Model.Employee;
import Project.Model.Job;

import java.util.List;
import java.util.Map;

public class EmployeeServiceImpl implements EmployeeService {
    EmployeeDAO employeeDAO = new EmployeeDAOImpl();
    @Override
    public void createEmployee() {
        employeeDAO.createEmployee();
    }

    @Override
    public void addEmployee(Employee employee) {
        employeeDAO.addEmployee(employee);
    }

    @Override
    public void dropTable() {
        employeeDAO.dropTable();
    }

    @Override
    public void cleanTable() {
        employeeDAO.cleanTable();
    }

    @Override
    public void updateEmployee(Long id, Employee employee) {
        employeeDAO.updateEmployee(id, employee);
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeDAO.getAllEmployees();
    }

    @Override
    public Employee findEmail(String email) {
        return employeeDAO.findEmail(email);
    }

    @Override
    public Map<Employee, Job> getEmployeeById(Long employeeId) {
        return employeeDAO.getEmployeeById(employeeId);
    }

    @Override
    public List<Employee> getEmployeeByPosition(String position) {
        return employeeDAO.getEmployeeByPosition(position);
    }
}
