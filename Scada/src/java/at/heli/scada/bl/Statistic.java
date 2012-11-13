/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.heli.scada.bl;

/**
 *
 * @author daniel
 */
public class Statistic {
    private Double average;
    private Double maxvalue;
    private Double minvalue;
    private String unit;
    private String description;
    
    public Statistic()
    {
        average = 0.00;
        maxvalue = 0.00;
        minvalue = 0.00;
        unit = "";
        description = "";
    }
    
    public Statistic(Double average, Double maxvalue, Double minvalue, String unit, String description)
    {
        this.average = average;
        this.minvalue = minvalue;
        this.maxvalue = maxvalue;
        this.unit = unit;
        this.description = description;
    }

    /**
     * @return the average
     */
    public Double getAverage() {
        return average;
    }

    /**
     * @param average the average to set
     */
    public void setAverage(Double average) {
        this.average = average;
    }

    /**
     * @return the maxvalue
     */
    public Double getMaxvalue() {
        return maxvalue;
    }

    /**
     * @param maxvalue the maxvalue to set
     */
    public void setMaxvalue(Double maxvalue) {
        this.maxvalue = maxvalue;
    }

    /**
     * @return the minvalue
     */
    public Double getMinvalue() {
        return minvalue;
    }

    /**
     * @param minvalue the minvalue to set
     */
    public void setMinvalue(Double minvalue) {
        this.minvalue = minvalue;
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
}
