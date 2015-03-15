package se.kth.iv1201projekt.integration.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * This class represent the competence_sv table in the database.
 *
 * @author Gabriel
 */
@Entity
@Table(name = "competence_sv")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CompetenceSv.findAll", query = "SELECT c FROM CompetenceSv c"),
    @NamedQuery(name = "CompetenceSv.findByCompetenceId", query = "SELECT c FROM CompetenceSv c WHERE c.competenceId = :competenceId"),
    @NamedQuery(name = "CompetenceSv.findByName", query = "SELECT c FROM CompetenceSv c WHERE c.name = :name"),
    @NamedQuery(name = "CompetenceSv.findByVersion", query = "SELECT c FROM CompetenceSv c WHERE c.version = :version")})
public class CompetenceSv implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "competence_id")
    private Long competenceId;
    @Size(max = 255)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "version")
    private int version;
    @OneToMany(mappedBy = "competenceId")
    private Collection<CompetenceProfileSv> competenceProfileSvCollection;

    public CompetenceSv() {
    }

    public CompetenceSv(Long competenceId) {
        this.competenceId = competenceId;
    }

    public CompetenceSv(Long competenceId, int version) {
        this.competenceId = competenceId;
        this.version = version;
    }

    public Long getCompetenceId() {
        return competenceId;
    }

    public void setCompetenceId(Long competenceId) {
        this.competenceId = competenceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<CompetenceProfileSv> getCompetenceProfileSvCollection() {
        return competenceProfileSvCollection;
    }

    public void setCompetenceProfileSvCollection(Collection<CompetenceProfileSv> competenceProfileSvCollection) {
        this.competenceProfileSvCollection = competenceProfileSvCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (competenceId != null ? competenceId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CompetenceSv)) {
            return false;
        }
        CompetenceSv other = (CompetenceSv) object;
        if ((this.competenceId == null && other.competenceId != null) || (this.competenceId != null && !this.competenceId.equals(other.competenceId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "se.kth.iv1201projekt.integration.model.CompetenceSv[ competenceId=" + competenceId + " ]";
    }

}
