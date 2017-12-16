package prunusmume.java_conf.gr.jp.logchecker;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Makoto Yaguchi on 2017/12/14.
 */

public class Record extends RealmObject implements Serializable {
    private String recField;   //現場名
    private String recDestination; //出荷先
    private int recLength;  //材長
    private String recSpecies; //樹種
    private int recNumSum;
    private String recVolSum;
    private int recNum8, recNum9, recNum10, recNum11, recNum12, recNum13, recNum14, recNum16, recNum18, recNum20,
            recNum22, recNum24, recNum26, recNum28, recNum30, recNum32, recNum34, recNum36, recNum38, recNum40,
            recNum42, recNum44, recNum46, recNum48, recNum50, recNum52, recNum54, recNum56, recNum58, recNum60;
    private Date date;

    @PrimaryKey
    private int id;

    public String getRecField() {
        return recField;
    }

    public void setRecField(String recField) {
        this.recField = recField;
    }

    public String getRecDestination() {
        return recDestination;
    }

    public void setRecDestination(String recDestination) {
        this.recDestination = recDestination;
    }

    public int getRecLength() {
        return recLength;
    }

    public void setRecLength(int recLength) {
        this.recLength = recLength;
    }

    public String getRecSpecies() {
        return recSpecies;
    }

    public void setRecSpecies(String recSpecies) {
        this.recSpecies = recSpecies;
    }

    public int getRecNumSum() {
        return recNumSum;
    }

    public void setRecNumSum(int recNumSum) {
        this.recNumSum = recNumSum;
    }

    public String getRecVolSum() {
        return recVolSum;
    }

    public void setRecVolSum(String recVolSum) {
        this.recVolSum = recVolSum;
    }

    public int getRecNum8() {
        return recNum8;
    }

    public void setRecNum8(int recNum8) {
        this.recNum8 = recNum8;
    }

    public int getRecNum9() {
        return recNum9;
    }

    public void setRecNum9(int recNum9) {
        this.recNum9 = recNum9;
    }

    public int getRecNum10() {
        return recNum10;
    }

    public void setRecNum10(int recNum10) {
        this.recNum10 = recNum10;
    }

    public int getRecNum11() {
        return recNum11;
    }

    public void setRecNum11(int recNum11) {
        this.recNum11 = recNum11;
    }

    public int getRecNum12() {
        return recNum12;
    }

    public void setRecNum12(int recNum12) {
        this.recNum12 = recNum12;
    }

    public int getRecNum13() {
        return recNum13;
    }

    public void setRecNum13(int recNum13) {
        this.recNum13 = recNum13;
    }

    public int getRecNum14() {
        return recNum14;
    }

    public void setRecNum14(int recNum14) {
        this.recNum14 = recNum14;
    }

    public int getRecNum16() {
        return recNum16;
    }

    public void setRecNum16(int recNum16) {
        this.recNum16 = recNum16;
    }

    public int getRecNum18() {
        return recNum18;
    }

    public void setRecNum18(int recNum18) {
        this.recNum18 = recNum18;
    }

    public int getRecNum20() {
        return recNum20;
    }

    public void setRecNum20(int recNum20) {
        this.recNum20 = recNum20;
    }

    public int getRecNum22() {
        return recNum22;
    }

    public void setRecNum22(int recNum22) {
        this.recNum22 = recNum22;
    }

    public int getRecNum24() {
        return recNum24;
    }

    public void setRecNum24(int recNum24) {
        this.recNum24 = recNum24;
    }

    public int getRecNum26() {
        return recNum26;
    }

    public void setRecNum26(int recNum26) {
        this.recNum26 = recNum26;
    }

    public int getRecNum28() {
        return recNum28;
    }

    public void setRecNum28(int recNum28) {
        this.recNum28 = recNum28;
    }

    public int getRecNum30() {
        return recNum30;
    }

    public void setRecNum30(int recNum30) {
        this.recNum30 = recNum30;
    }

    public int getRecNum32() {
        return recNum32;
    }

    public void setRecNum32(int recNum32) {
        this.recNum32 = recNum32;
    }

    public int getRecNum34() {
        return recNum34;
    }

    public void setRecNum34(int recNum34) {
        this.recNum34 = recNum34;
    }

    public int getRecNum36() {
        return recNum36;
    }

    public void setRecNum36(int recNum36) {
        this.recNum36 = recNum36;
    }

    public int getRecNum38() {
        return recNum38;
    }

    public void setRecNum38(int recNum38) {
        this.recNum38 = recNum38;
    }

    public int getRecNum40() {
        return recNum40;
    }

    public void setRecNum40(int recNum40) {
        this.recNum40 = recNum40;
    }

    public int getRecNum42() {
        return recNum42;
    }

    public void setRecNum42(int recNum42) {
        this.recNum42 = recNum42;
    }

    public int getRecNum44() {
        return recNum44;
    }

    public void setRecNum44(int recNum44) {
        this.recNum44 = recNum44;
    }

    public int getRecNum46() {
        return recNum46;
    }

    public void setRecNum46(int recNum46) {
        this.recNum46 = recNum46;
    }

    public int getRecNum48() {
        return recNum48;
    }

    public void setRecNum48(int recNum48) {
        this.recNum48 = recNum48;
    }

    public int getRecNum50() {
        return recNum50;
    }

    public void setRecNum50(int recNum50) {
        this.recNum50 = recNum50;
    }

    public int getRecNum52() {
        return recNum52;
    }

    public void setRecNum52(int recNum52) {
        this.recNum52 = recNum52;
    }

    public int getRecNum54() {
        return recNum54;
    }

    public void setRecNum54(int recNum54) {
        this.recNum54 = recNum54;
    }

    public int getRecNum56() {
        return recNum56;
    }

    public void setRecNum56(int recNum56) {
        this.recNum56 = recNum56;
    }

    public int getRecNum58() {
        return recNum58;
    }

    public void setRecNum58(int recNum58) {
        this.recNum58 = recNum58;
    }

    public int getRecNum60() {
        return recNum60;
    }

    public void setRecNum60(int recNum60) {
        this.recNum60 = recNum60;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    }
