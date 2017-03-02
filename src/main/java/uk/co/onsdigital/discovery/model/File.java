package uk.co.onsdigital.discovery.model;

import lombok.Data;
import lombok.NonNull;
import lombok.experimental.Tolerate;

import javax.persistence.*;
import java.util.Date;


/**
 * Indicates the status of a particular output file in a job.
 */
@Data @Entity @Table(name = "file")
public class File {

    @Id
    private @NonNull String name;

    @Enumerated(EnumType.STRING)
    private @NonNull Status status = Status.PENDING;

    private String url;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "submitted_at")
    private Date submittedAt;

    @Tolerate
    public File() {
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
