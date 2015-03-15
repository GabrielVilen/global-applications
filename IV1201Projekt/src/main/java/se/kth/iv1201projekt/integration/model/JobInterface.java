package se.kth.iv1201projekt.integration.model;

import java.util.Date;

/**
 * Interface for the job classes
 *
 * @author Samy
 */
public interface JobInterface {

    public Integer getId();

    public void setId(Integer id);

    public String getType();

    public void setType(String type);

    public String getInformation();

    public void setInformation(String information);

    public Date getStartDate();

    public void setStartDate(Date startDate);

    public Date getEndDate();

    public void setEndDate(Date endDate);

    public String getName();

    public void setName(String name);

    public int getVersion();

    public void setVersion(int version);

    @Override
    public int hashCode();

    @Override
    public boolean equals(Object object);

    @Override
    public String toString();

}
