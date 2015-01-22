package se.kth.iv1201projekt.util;

import java.util.Date;

/**
 * Klass som visar mellan vilka datum kandidaten är tillgänlig
 * @author Samy
 */
public class Availability {
    private Date from;
    private Date to;

    public Availability(Date from, Date to) {
        this.from = from;
        this.to = to;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }
    
    
}
