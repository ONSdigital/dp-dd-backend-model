package uk.co.onsdigital.discovery.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

/**
 * The persistent class for the time_type database table.
 */
@Entity
@Table(name = "time_type")
@NamedQuery(name = "TimeType.findAll", query = "SELECT t FROM TimeType t")
public class TimeType implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "time_type")
    private String id;

    //bi-directional many-to-one association to TimePeriod
    @OneToMany(mappedBy = "timeType")
    private List<TimePeriod> timePeriods;

    public TimeType() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<TimePeriod> getTimePeriods() {
        return this.timePeriods;
    }

    public void setTimePeriods(List<TimePeriod> timePeriods) {
        this.timePeriods = timePeriods;
    }

    public TimePeriod addTimePeriod(TimePeriod timePeriod) {
        getTimePeriods().add(timePeriod);
        timePeriod.setTimeType(this);

        return timePeriod;
    }

    public TimePeriod removeTimePeriod(TimePeriod timePeriod) {
        getTimePeriods().remove(timePeriod);
        timePeriod.setTimeType(null);

        return timePeriod;
    }

}