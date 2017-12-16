package prunusmume.java_conf.gr.jp.logchecker;

import java.util.Date;

/**
 * Created by Makoto Yaguchi on 2017/12/16.
 */

public class CustomList {
    public String getListField() {
        return ListField;
    }

    public void setListField(String listField) {
        ListField = listField;
    }

    public String getListDestination() {
        return ListDestination;
    }

    public void setListDestination(String listDestination) {
        ListDestination = listDestination;
    }

    public String getListSpecies() {
        return ListSpecies;
    }

    public void setListSpecies(String listSpecies) {
        ListSpecies = listSpecies;
    }

    public int getListLength() {
        return ListLength;
    }

    public void setListLength(int listLength) {
        ListLength = listLength;
    }

    public int getListNumSum() {
        return ListNumSum;
    }

    public void setListNumSum(int listNumSum) {
        ListNumSum = listNumSum;
    }

    public String getListVolSum() {
        return ListVolSum;
    }

    public void setListVolSum(String listVolSum) {
        ListVolSum = listVolSum;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    private String ListField;
    private String ListDestination;
    private String ListSpecies;
    private int ListLength;
    private int ListNumSum;
    private String ListVolSum;
    private Date date;


}
