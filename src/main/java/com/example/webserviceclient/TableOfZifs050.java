package com.example.webserviceclient;

import cn.hutool.core.date.DateUtil;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.Date;

public class TableOfZifs050 {
    private String Mblnr;
    private String Mjahr;
    private String Zeile;
    private String Biaos;
    private String Bldat;
    private String Werks;
    private String Zxmwz;
    private String Matnr;
    private String Maktx;
    private String Matkl;
    private BigDecimal Menge;
    private String Mseht;
    private BigDecimal Dmbtr;
    private String Kostl;
    private String Waers;

    public TableOfZifs050() {
        Mblnr = "mblnr";
        Mjahr = "11";
        Zeile = "22";
        Biaos = "b";
        Bldat = DateUtil.format(new Date(), "yyyy-MM-dd");
        Werks = "werk";
        Zxmwz = "z";
        Matnr = "Matnr";
        Maktx = "Maktx";
        Matkl = "Matkl";
        Menge = new BigDecimal("10.001") ;
        Mseht = "Mseht";
        Dmbtr =  new BigDecimal("12.13") ;
        Kostl = "Kostl";
        Waers = "Waers";
    }

    public TableOfZifs050(String mblnr, String mjahr, String zeile, String biaos, String bldat, String werks, String zxmwz, String matnr, String maktx, String matkl, BigDecimal menge, String mseht, BigDecimal dmbtr, String kostl, String waers) {
        Mblnr = mblnr;
        Mjahr = mjahr;
        Zeile = zeile;
        Biaos = biaos;
        Bldat = bldat;
        Werks = werks;
        Zxmwz = zxmwz;
        Matnr = matnr;
        Maktx = maktx;
        Matkl = matkl;
        Menge = menge;
        Mseht = mseht;
        Dmbtr = dmbtr;
        Kostl = kostl;
        Waers = waers;
    }

    public Object[] toArray() {
        String currentDateStr = DateUtil.format(new Date(), "yyyy-MM-dd");
        Object[] s = new Object[]{
                "mblnr",
                "11",
                "22",
                "biaos",
                currentDateStr,
                "werks",
                "zxmwz",
                "matnr",
                "maktx",
                "matkl",
                10.5,
                "mseht",
                12.3,
                "kostl",
                "waers"
        };
        return s;
    }

    public String getMblnr() {
        return Mblnr;
    }

    public void setMblnr(String mblnr) {
        Mblnr = mblnr;
    }

    public String getMjahr() {
        return Mjahr;
    }

    public void setMjahr(String mjahr) {
        Mjahr = mjahr;
    }

    public String getZeile() {
        return Zeile;
    }

    public void setZeile(String zeile) {
        Zeile = zeile;
    }

    public String getBiaos() {
        return Biaos;
    }

    public void setBiaos(String biaos) {
        Biaos = biaos;
    }

    public String getBldat() {
        return Bldat;
    }

    public void setBldat(String bldat) {
        Bldat = bldat;
    }

    public String getWerks() {
        return Werks;
    }

    public void setWerks(String werks) {
        Werks = werks;
    }

    public String getZxmwz() {
        return Zxmwz;
    }

    public void setZxmwz(String zxmwz) {
        Zxmwz = zxmwz;
    }

    public String getMatnr() {
        return Matnr;
    }

    public void setMatnr(String matnr) {
        Matnr = matnr;
    }

    public String getMaktx() {
        return Maktx;
    }

    public void setMaktx(String maktx) {
        Maktx = maktx;
    }

    public String getMatkl() {
        return Matkl;
    }

    public void setMatkl(String matkl) {
        Matkl = matkl;
    }


    public String getMseht() {
        return Mseht;
    }

    public void setMseht(String mseht) {
        Mseht = mseht;
    }



    public String getKostl() {
        return Kostl;
    }

    public void setKostl(String kostl) {
        Kostl = kostl;
    }

    public String getWaers() {
        return Waers;
    }

    public void setWaers(String waers) {
        Waers = waers;
    }

    public BigDecimal getMenge() {
        return Menge;
    }

    public void setMenge(BigDecimal menge) {
        Menge = menge;
    }

    public BigDecimal getDmbtr() {
        return Dmbtr;
    }

    public void setDmbtr(BigDecimal dmbtr) {
        Dmbtr = dmbtr;
    }
}
