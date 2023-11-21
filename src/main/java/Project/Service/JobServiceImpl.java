package Project.Service;

import Project.DAO.JobDAO;
import Project.DAO.JobDAOImpl;
import Project.Model.Job;

import java.util.List;

public class JobServiceImpl implements JobService {
    JobDAO jobDAO = new JobDAOImpl();
    @Override
    public void crateJobTable() {
        jobDAO.crateJobTable();
    }

    @Override
    public void addJob(Job job) {
        jobDAO.addJob(job);
    }

    @Override
    public Job getJobById(Long jobId) {
        return jobDAO.getJobById(jobId);
    }

    @Override
    public List<Job> sortByExperience(String ascOrDesc) {
        return jobDAO.sortByExperience( ascOrDesc);
    }

    @Override
    public Job getJobByEmployeeId(Long employeeId) {
        return jobDAO.getJobByEmployeeId(employeeId);
    }

    @Override
    public void deleteDescriptionColumn() {
        jobDAO.deleteDescriptionColumn();
    }
}
