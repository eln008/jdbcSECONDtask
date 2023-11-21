package Project.DAO;

import Project.Model.Job;

import java.util.List;

public interface JobDAO {
    void crateJobTable();
    void addJob(Job job);
    Job getJobById (Long jobId);
    List<Job> sortByExperience(String ascOrDesc);
    Job getJobByEmployeeId(Long employeeId);
    void deleteDescriptionColumn();
}