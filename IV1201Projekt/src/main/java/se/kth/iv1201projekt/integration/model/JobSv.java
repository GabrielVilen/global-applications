package se.kth.iv1201projekt.integration.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class represent the job_sv table in the database.
 *
 * @author Gabriel
 */
@Entity
@Table(name = "job_sv")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JobSv.findAll", query = "SELECT j FROM JobSv j"),
    @NamedQuery(name = "JobSv.findById", query = "SELECT j FROM JobSv j WHERE j.id = :id"),
    @NamedQuery(name = "JobSv.findByType", query = "SELECT j FROM JobSv j WHERE j.type = :type"),
    @NamedQuery(name = "JobSv.findByInformation", query = "SELECT j FROM JobSv j WHERE j.information = :information"),
    @NamedQuery(name = "JobSv.findByStartDate", query = "SELECT j FROM JobSv j WHERE j.startDate = :startDate"),
    @NamedQuery(name = "JobSv.findByEndDate", query = "SELECT j FROM JobSv j WHERE j.endDate = :endDate"),
    @NamedQuery(name = "JobSv.findByRecruiterPersonId", query = "SELECT j FROM JobSv j WHERE j.recruiterPersonId = :recruiterPersonId"),
    @NamedQuery(name = "JobSv.findByName", query = "SELECT j FROM JobSv j WHERE j.name = :name"),
    @NamedQuery(name = "JobSv.findByVersion", query = "SELECT j FROM JobSv j WHERE j.version = :version")})
public class JobSv implements Serializable, JobInterface {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "type")
    private String type;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "information")
    private String information;
    @Basic(optional = false)
    @NotNull
    @Column(name = "start_date")
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "end_date")
    @Temporal(TemporalType.DATE)
    private Date endDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "recruiter_person_id")
    private long recruiterPersonId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "version")
    private int version;

    public JobSv() {
    }

    public JobSv(Integer id) {
        this.id = id;
    }

    public JobSv(Integer id, String type, String information, Date startDate, Date endDate, long recruiterPersonId, String name, int version) {
        this.id = id;
        this.type = type;
        this.information = information;
        this.startDate = startDate;
        this.endDate = endDate;
        this.recruiterPersonId = recruiterPersonId;
        this.name = name;
        this.version = version;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String getInformation() {
        return information;
    }

    @Override
    public void setInformation(String information) {
        this.information = information;
    }

    @Override
    public Date getStartDate() {
        return startDate;
    }

    @Override
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Override
    public Date getEndDate() {
        return endDate;
    }

    @Override
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public long getRecruiterPersonId() {
        return recruiterPersonId;
    }

    public void setRecruiterPersonId(long recruiterPersonId) {
        this.recruiterPersonId = recruiterPersonId;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getVersion() {
        return version;
    }

    @Override
    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JobSv)) {
            return false;
        }
        JobSv other = (JobSv) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "se.kth.iv1201projekt.integration.model.JobSv[ id=" + id + " ]";
    }

}
