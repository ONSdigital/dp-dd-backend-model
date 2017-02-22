package uk.co.onsdigital.discovery.model;

import javax.persistence.*;
import lombok.Data;
import lombok.NonNull;
import lombok.experimental.Tolerate;


/**
 * Indicates the status of a particular output file in a job.
 */
@Data @Entity @Table(name = "file_status")
public class FileStatus {
    @Id
    private @NonNull String name;

    @Enumerated(EnumType.STRING)
    private @NonNull Status status = Status.PENDING;

    private String url;

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
