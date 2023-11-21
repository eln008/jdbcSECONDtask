package Project.DAO;

import Project.Model.Employee;
import Project.Model.Job;
import Project.Util.ConfigOrUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeDAOImpl implements EmployeeDAO{
    @Override
    public void createEmployee() {
        String sql = "CREATE TABLE if not exists Employee (" +
                "id serial PRIMARY KEY," +
                "firstName varchar (50)," +
                "lastName varchar (50)," +
                "age int," +
                "email varchar(50)," +
                "jobId int REFERENCES Job (id))";
        try (Connection connection = ConfigOrUtil.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
            System.out.println("Successfully created table Employee!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addEmployee(Employee employee) {
        String sql = "INSERT INTO Employee (firstName, lastName, age, email, jobId) values(?,?,?,?,?)";

        try(Connection connection = ConfigOrUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1,employee.getFirstName());
            preparedStatement.setString(2,employee.getLastName());
            preparedStatement.setInt(3,employee.getAge());
            preparedStatement.setString(4,employee.getEmail());
            preparedStatement.setLong(5,employee.getJobId());
            preparedStatement.executeUpdate();
            System.out.println("Successfully inserted");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void dropTable() {
        String sql = "DROP TABLE Employee";
        try(Connection connection = ConfigOrUtil.getConnection();
        Statement statement = connection.createStatement()
        ){
          statement.executeUpdate(sql);
            System.out.println("Table drop successfully");
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void cleanTable() {
        String sql = "DELETE FROM Employee";
        try(Connection connection = ConfigOrUtil.getConnection();
        Statement statement = connection.createStatement()){
            statement.executeUpdate(sql);
            System.out.println("Table cleaned successfully");
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateEmployee(Long id, Employee employee) {
        String sql = "UPDATE Employee SET firstName = ?, lastName = ?, age = ?,email = ?, jobid = ? WHERE id = ?";
        try(Connection connection = ConfigOrUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setInt(3, employee.getAge());
            preparedStatement.setString(4, employee.getEmail());
            preparedStatement.setLong(5, employee.getJobId());
            preparedStatement.setLong(6, id);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0){
                System.out.println("Employee updated successfully");
            }else {
                System.out.println("No employee found with id "+ id);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Employee> getAllEmployees() {
        String sql = "SELECT * FROM Employee";
        List<Employee> employeeList = new ArrayList<>();

        try(Connection connection = ConfigOrUtil.getConnection();
            Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql)){
            while (resultSet.next()){
                Employee employee = new Employee();
                employee.setId(resultSet.getLong("id"));
                employee.setFirstName(resultSet.getString("firstName"));
                employee.setLastName(resultSet.getString("lastName"));
                employee.setAge(resultSet.getInt("age"));
                employee.setEmail(resultSet.getString("email"));
                employee.setJobId(resultSet.getInt("jobid"));
                employeeList.add(employee);
            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return employeeList;
    }

    @Override
    public Employee findEmail(String email) {
        String sql = "SELECT * FROM Employee WHERE email = ?" ;
        Employee employee = null;

        try(Connection connection = ConfigOrUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setString(1, email);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()){
                    employee = new Employee();
                    employee.setId(resultSet.getLong("id"));
                    employee.setFirstName(resultSet.getString("firstName"));
                    employee.setLastName(resultSet.getString("lastName"));
                    employee.setAge(resultSet.getInt("age"));
                    employee.setEmail(resultSet.getString("email"));
                    employee.setJobId(resultSet.getInt("jobid"));
                }
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return employee;
    }

    @Override
    public Map<Employee, Job> getEmployeeById(Long employeeId) {
        String sql = "SELECT e.id AS employee_id, e.firstName, e.lastName, e.age, e.email, e.jobid,\n" +
                "       j.id AS job_id, j.position, j.profession, j.description, j.experience\n" +
                "FROM Employee e\n" +
                "INNER JOIN Job j ON e.jobid = j.id\n" +
                "WHERE e.id = ?";
        Map<Employee, Job> employeeJobMap = new HashMap<>();

        try (Connection connection = ConfigOrUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setLong(1, employeeId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Employee employee = new Employee();
                    employee.setId(resultSet.getLong("employee_id"));
                    employee.setFirstName(resultSet.getString("firstName"));
                    employee.setLastName(resultSet.getString("lastName"));
                    employee.setAge(resultSet.getInt("age"));
                    employee.setEmail(resultSet.getString("email"));
                    employee.setJobId(resultSet.getInt("jobid"));

                    Job job = new Job();
                    job.setId(resultSet.getLong("job_id"));
                    job.setPosition(resultSet.getString("position"));
                    job.setProfession(resultSet.getString("profession"));
                    job.setDescription(resultSet.getString("description"));
                    job.setExperience(resultSet.getInt("experience"));

                    employeeJobMap.put(employee, job);
                } else {
                    System.out.println("Нет результатов для запроса с employeeId = " + employeeId);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return employeeJobMap;
    }

    @Override
    public List<Employee> getEmployeeByPosition(String position) {
        String sql = "SELECT e.* FROM Employee e INNER JOIN Job j ON e.jobid = j.id WHERE j.position = ?";
        List<Employee> employeeList = new ArrayList<>();

        try(Connection connection = ConfigOrUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql) ){
            preparedStatement.setString(1, position);
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while (resultSet.next()){
                    Employee employee = new Employee();
                    employee.setId(resultSet.getLong("id"));
                    employee.setFirstName(resultSet.getString("firstName"));
                    employee.setLastName(resultSet.getString("lastName"));
                    employee.setAge(resultSet.getInt("age"));
                    employee.setEmail(resultSet.getString("email"));
                    employee.setJobId(resultSet.getInt("jobid"));
                    employeeList.add(employee);
                }
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return employeeList;
    }
}
