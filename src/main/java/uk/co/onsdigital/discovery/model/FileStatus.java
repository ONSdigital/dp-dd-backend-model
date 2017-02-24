package uk.co.onsdigital.discovery.model;

import javax.persistence.*;
import lombok.Data;
import lombok.NonNull;
import lombok.experimental.Tolerate;

import java.util.Date;


/**
 * Indicates the status of a particular output file in a job.
 */
@Data @Entity @Table(name = "file_status")
@NamedQueries({
        @NamedQuery(name = FileStatus.DELETE_EXPIRED_FILES, query = "DELETE FROM FileStatus fs WHERE fs.submittedAt < :before")
})
public class FileStatus {
    public static final String DELETE_EXPIRED_FILES = "FileStatus.deleteExpired";

    @Id
    private @NonNull String name;

    @Enumerated(EnumType.STRING)
    private @NonNull Status status = Status.PENDING;

    private String url;

    @Temporal(TemporalType.TIMESTAMP)
    private Date submittedAt;

    @Tolerate
    public FileStatus() {
        // Default constructor for JPA
    }


    public boolean isComplete() {
        return status == Status.COMPLETE;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
