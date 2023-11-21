package Project.DAO;

import Project.Model.Job;
import Project.Util.ConfigOrUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JobDAOImpl implements JobDAO{
    @Override
    public void crateJobTable() {
        String sql = "CREATE TABLE IF NOT EXISTS Job (" +
                "id serial primary key," +
                "position varchar (150)," +
                "profession varchar (150)," +
                "description text," +
                "experience int)";
        try(Connection connection = ConfigOrUtil.getConnection();
        Statement statement = connection.createStatement()){
            statement.executeUpdate(sql);
            System.out.println("Job table created successfully");
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addJob(Job job) {
        String sql = "INSERT INTO Job (position, profession, description, experience) values (?, ?, ?, ?)";
        try(Connection connection = ConfigOrUtil.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){
                preparedStatement.setString(1, job.getPosition());
                preparedStatement.setString(2, job.getProfession());
                preparedStatement.setString(3, job.getDescription());
                preparedStatement.setInt(4, job.getExperience());
                preparedStatement.executeUpdate();
            System.out.println("Successfully added!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Job getJobById(Long jobId) {
        String sql = "SELECT * FROM Job WHERE id = ?";
        try (Connection connection = ConfigOrUtil.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, jobId);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String position = resultSet.getString("position");
                String profession = resultSet.getString("profession");
                String description = resultSet.getString("description");
                int experience = resultSet.getInt("experience");
                return new Job(id, position, profession, description, experience);
            }
        } catch (SQLException e) {
            System.err.println("Error executing SQL query: " + e.getMessage());
            throw new RuntimeException(e);
        }

        return null;
    }


    @Override
    public List<Job> sortByExperience(String ascOrDesc) {
        String sortOrder = "ASC";
        if("desc".equalsIgnoreCase(ascOrDesc)){
            sortOrder = "DESC";
        }
        String sql = "SELECT * FROM Job ORDER BY experience "+ sortOrder;
        List<Job> jobList = new ArrayList<>();

        try(Connection connection = ConfigOrUtil.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql)){
            while(resultSet.next()){
                Job job = new Job();
                job.setId(resultSet.getLong("id"));
                job.setPosition(resultSet.getString("position"));
                job.setProfession(resultSet.getString("profession"));
                job.setDescription(resultSet.getString("description"));
                job.setExperience(resultSet.getInt("experience"));
                jobList.add(job);
            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return jobList;
    }

    @Override
    public Job getJobByEmployeeId(Long employeeId) {
        String sql = "SELECT job.id, job.profession, job.position, job.description, job.experience FROM Job INNER JOIN Employee ON Job.id = Employee.jobid WHERE Employee.id = ?";
        try(Connection connection = ConfigOrUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql)
        ){
            preparedStatement.setLong(1, employeeId);
            try (ResultSet resultSet = preparedStatement.executeQuery()){
                if (resultSet.next()){
                    Job job = new Job();
                    job.setId(resultSet.getLong(1));
                    job.setPosition(resultSet.getString(2));
                    job.setProfession(resultSet.getString(3));
                    job.setDescription(resultSet.getString(4));
                    job.setExperience(resultSet.getInt(5));
                return job;
                }
            }
        }catch (SQLException e){
           throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void deleteDescriptionColumn() {
        String sql = "ALTER TABLE Job DROP COLUMN description";
        try(
                Connection connection = ConfigOrUtil.getConnection();
                Statement statement = connection.createStatement()
                ){
            statement.executeUpdate(sql);
            System.out.println("successfully deleted");
        }catch (SQLException e){
            throw  new RuntimeException(e);
        }

    }
}
