package se.kth.iv1201projekt.integration.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * This class represent the role_sv table in the database.
 *
 * @author Gabriel
 */
@Entity
@Table(name = "role_sv")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RoleSv.findAll", query = "SELECT r FROM RoleSv r"),
    @NamedQuery(name = "RoleSv.findById", query = "SELECT r FROM RoleSv r WHERE r.id = :id"),
    @NamedQuery(name = "RoleSv.findByName", query = "SELECT r FROM RoleSv r WHERE r.name = :name"),
    @NamedQuery(name = "RoleSv.findByVersion", query = "SELECT r FROM RoleSv r WHERE r.version = :version")})
public class RoleSv implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Column(name = "version")
    private int version;

    public RoleSv() {
    }

    public RoleSv(Integer id) {
        this.id = id;
    }

    public RoleSv(Integer id, int version) {
        this.id = id;
        this.version = version;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RoleSv)) {
            return false;
        }
        RoleSv other = (RoleSv) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "se.kth.iv1201projekt.integration.model.RoleSv[ id=" + id + " ]";
    }

}
