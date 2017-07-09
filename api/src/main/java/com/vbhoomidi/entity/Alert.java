package com.vbhoomidi.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

/**
 * Created by vikramreddy on 7/9/2017.
 */

@Entity
@NamedQueries({
        @NamedQuery(name = "Alert.countHighAlertsbyVin",
                    query = "SELECT a FROM Alert a WHERE a.vin=:givenVin and a.priority=:givenPriority")
})
public class Alert {

    @Id
    @Column(columnDefinition = "VARCHAR(36)")
    private String id;

    private String vin;
    private String priority;
    private Date timeStamp;

    public Alert(){
        this.id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return "Alert{" +
                "id='" + id + '\'' +
                ", vin='" + vin + '\'' +
                ", priority='" + priority + '\'' +
                ", timeStamp=" + timeStamp +
                '}';
    }
}
