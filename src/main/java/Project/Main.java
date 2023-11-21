package Project;

import Project.Model.Employee;
import Project.Model.Job;
import Project.Service.EmployeeService;
import Project.Service.EmployeeServiceImpl;
import Project.Service.JobService;
import Project.Service.JobServiceImpl;
import Project.Util.ConfigOrUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        ConfigOrUtil.getConnection();
        EmployeeService employeeServiceDAO = new EmployeeServiceImpl();
        JobService jobService = new JobServiceImpl();
//        jobService.crateJobTable();
//        employeeServiceDAO.createEmployee();


//        Scanner scan = new Scanner(System.in);
//       try {System.out.println("Введите position ");
//        String posit = scan.next();
//        System.out.println("Введите  profession");
//        String profess = scan.next();
//
//        System.out.println("Введите description ");
//        String desc = scan.next();
//
//        System.out.println("Введите experience ");
//        int exper = scan.nextInt();
//
//        Job job = new Job(posit, profess, desc, exper);
//        jobService.addJob(job);
//       }catch (InputMismatchException e){
//        System.out.println("Vvedite pravilnye dannye");
//       }finally {
//           scan.close();
//       }

//        System.out.println(jobService.getJobById(2L));
//        System.out.println(jobService.sortByExperience("desc"));
//        System.out.println(jobService.getJobByEmployeeId(2L));
//        jobService.deleteDescriptionColumn();






//        employeeServiceDAO.createEmployee();
//        Employee employee = new Employee("firstName", "lastName", 19, "email.com",1);
//        Employee employee1 = new Employee("second", "lastname second", 76, "JustEmail.com",2);
        Employee employee = new Employee("third", "lastname third", 46, "ThirdEmail.com",2);
//        employeeServiceDAO.addEmployee(employee);
//        employeeServiceDAO.addEmployee(employee1);
//        employeeServiceDAO.updateEmployee(2L,employee);
//        System.out.println(employeeServiceDAO.getAllEmployees());
//        System.out.println(employeeServiceDAO.findEmail("ThirdEmail.com"));
//        System.out.println(employeeServiceDAO.getEmployeeById(1L));
        System.out.println(employeeServiceDAO.getEmployeeByPosition("newPosition"));

    }
}
