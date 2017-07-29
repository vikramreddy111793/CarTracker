package com.vbhoomidi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

/**
 * Created by vikramreddy on 7/9/2017.
 */

@Entity
@NamedQueries({
        @NamedQuery(name = "Alert.countHighAlertsbyVin",
                    query = "SELECT a FROM Alert a WHERE a.vin=:givenVin and a.priority=:givenPriority"),
        @NamedQuery(name = "Alert.findAlertsbyVin",
                    query = "SELECT a FROM Alert a WHERE a.vin=:givenVin")
})
public class Alert {

    @Id
    @Column(columnDefinition = "VARCHAR(36)")
    private String id;

    private String vin;
    private String priority;
    private String description;

    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd HH:mm:ss")
    private String timeStamp;

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

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Alert{" +
                "id='" + id + '\'' +
                ", vin='" + vin + '\'' +
                ", priority='" + priority + '\'' +
                ", description='" + description + '\'' +
                ", timeStamp=" + timeStamp +
                '}';
    }
}
