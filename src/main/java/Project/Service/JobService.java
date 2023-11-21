package Project.Service;

import Project.Model.Job;

import java.util.List;

public interface JobService {
    void crateJobTable();
    void addJob(Job job);
    Job getJobById (Long jobId);
    List<Job> sortByExperience(String ascOrDesc);
    Job getJobByEmployeeId(Long employeeId);
    void deleteDescriptionColumn();
}
