/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.heli.scada.bl;

import java.sql.Time;
import java.util.Date;

/**
 *
 * @author daniel
 */
public class InstallationState {
    private Double lastValue;
    private String unit;
    private String description;
    private Date currentDate;
    private Date currentTime;
    
    
    public InstallationState(Double lastValue, String unit, String description, Date currentDate, Date currentTime)
    {
        this.lastValue = lastValue;
        this.unit = unit;
        this.description = description;
        this.currentDate = currentDate;
        this.currentTime = currentTime;
    }

    /**
     * @return the lastValue
     */
    public Double getLastValue() {
        return lastValue;
    }

    /**
     * @param lastValue the lastValue to set
     */
    public void setLastValue(Double lastValue) {
        this.lastValue = lastValue;
    }

    /**
     * @return the unit
     */
    public String getUnit() {
        return unit;
    }

    /**
     * @param unit the unit to set
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the currentDate
     */
    public Date getCurrentDate() {
        return currentDate;
    }

    /**
     * @param currentDate the currentDate to set
     */
    public void setCurrentDate(Date currentDate) {
        this.currentDate = currentDate;
    }

    /**
     * @return the currentTime
     */
    public Date getCurrentTime() {
        return currentTime;
    }

    /**
     * @param currentTime the currentTime to set
     */
    public void setCurrentTime(Date currentTime) {
        this.currentTime = currentTime;
    }
}
