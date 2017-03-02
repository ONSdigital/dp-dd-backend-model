package uk.co.onsdigital.discovery.model;

import javax.persistence.*;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="job")
@NamedQueries({
        @NamedQuery(name = Job.COUNT_JOBS_WITH_STATUS, query = "SELECT COUNT(j) FROM Job j WHERE j.status=:status"),
        @NamedQuery(name = Job.DELETE_JOBS_EXPIRING_BEFORE, query = "DELETE FROM Job j WHERE j.expiryTime < :before"),
        @NamedQuery(name = Job.FIND_ONE_QUERY, query = "SELECT j FROM Job j WHERE j.id=:id"),
        @NamedQuery(name = Job.DELETE_ONE_QUERY, query = "DELETE FROM Job j WHERE j.id=:id")
})
public class Job {
    private static final long serialVersionUID = 1L;
    public static final String COUNT_JOBS_WITH_STATUS = "Job.countJobsWithStatus";
    public static final String DELETE_JOBS_EXPIRING_BEFORE = "Job.deleteJobsExpiringBefore";
    public static final String FIND_ONE_QUERY = "Job.findOne";
    public static final String DELETE_ONE_QUERY = "Job.deleteOne";
    public static final String STATUS_PARAM = "status";
    public static final String BEFORE_DATE_PARAM = "before";
    public static final String ID_PARAM = "id";

    @Id
    @Column(name = "id")
    private String id;

    @Enumerated(EnumType.STRING)
    private Status status = Status.PENDING;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(name = "job_file")
    private List<File> files = Collections.emptyList();

    @Temporal(TemporalType.TIMESTAMP)
    private Date expiryTime;

    public Job() {
        // No arg constructor for JPA
    }

    public boolean isComplete() {
        return status == Status.COMPLETE;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    public Date getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(Date expiryTime) {
        this.expiryTime = expiryTime;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }
}
