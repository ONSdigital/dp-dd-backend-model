package uk.co.onsdigital.discovery.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * The persistent class for the time_period database table.
 */
@Entity
@Table(name = "time_period", indexes = {@Index(columnList = "name")})
@NamedQuery(name = "TimePeriod.findAll", query = "SELECT t FROM TimePeriod t")
public class TimePeriod implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @SequenceGenerator(name = "timeseq", sequenceName = "timeseq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "timeseq")
    @Column(name = "time_period_id")
    private Long timePeriodId;

    @Temporal(TemporalType.DATE)
    @Column(name = "end_date")
    private Date endDate;

    private String name;

    @Temporal(TemporalType.DATE)
    @Column(name = "start_date")
    private Date startDate;

    //bi-directional many-to-one association to Population
    @OneToMany(mappedBy = "timePeriod")
    private List<Population> populations;

    //bi-directional many-to-one association to TimeType
    @ManyToOne
    @JoinColumn(name = "time_type")
    private TimeType timeTypeBean;

    public TimePeriod() {
    }

    public Long getTimePeriodId() {
        return this.timePeriodId;
    }

    public void setTimePeriodId(Long timePeriodId) {
        this.timePeriodId = timePeriodId;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public List<Population> getPopulations() {
        return this.populations;
    }

    public void setPopulations(List<Population> populations) {
        this.populations = populations;
    }

    public Population addPopulation(Population population) {
        getPopulations().add(population);
        population.setTimePeriod(this);

        return population;
    }

    public Population removePopulation(Population population) {
        getPopulations().remove(population);
        population.setTimePeriod(null);

        return population;
    }

    public TimeType getTimeTypeBean() {
        return this.timeTypeBean;
    }

    public void setTimeTypeBean(TimeType timeTypeBean) {
        this.timeTypeBean = timeTypeBean;
    }

}