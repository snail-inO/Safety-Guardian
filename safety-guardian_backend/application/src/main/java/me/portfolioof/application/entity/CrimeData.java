package me.portfolioof.application.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
public class CrimeData {
    @Id
    @GeneratedValue
    private Long id;
    private String address;
    private String addressType;
    private String street;
    private String streetType;
    private char streetDir;
    private double x;
    private double y;
    private Date reportDate;
    private int timeStart;
    private int timeEnd;
    @ManyToOne(targetEntity = CrimeType.class)
    private CrimeType crimeType;
    private boolean arrested;
    private String larcenyCode;

    public CrimeData() {
    }

    public CrimeData(CrimeData crimeData) {
        this.id = crimeData.id;
        this.address = crimeData.address;
        this.addressType = crimeData.addressType;
        this.streetDir = crimeData.streetDir;
        this.street = crimeData.street;
        this.streetType = crimeData.streetType;
        this.x = crimeData.x;
        this.y = crimeData.y;
        this.reportDate = crimeData.reportDate;
        this.timeStart = crimeData.timeStart;
        this.timeEnd = crimeData.timeEnd;
        this.crimeType = crimeData.crimeType;
        this.arrested = crimeData.arrested;
        this.larcenyCode = crimeData.larcenyCode;

    }

    public CrimeData(Long id, String address, String addressType, String street, String streetType, char streetDir, double x, double y, Date reportDate, int timeStart, int timeEnd, CrimeType crimeType, boolean arrested, String larcenyCode) {
        this.id = id;
        this.address = address;
        this.addressType = addressType;
        this.street = street;
        this.streetType = streetType;
        this.streetDir = streetDir;
        this.x = x;
        this.y = y;
        this.reportDate = reportDate;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.crimeType = crimeType;
        this.arrested = arrested;
        this.larcenyCode = larcenyCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddressType() {
        return addressType;
    }

    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetType() {
        return streetType;
    }

    public void setStreetType(String streetType) {
        this.streetType = streetType;
    }

    public char getStreetDir() {
        return streetDir;
    }

    public void setStreetDir(char streetDir) {
        this.streetDir = streetDir;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public int getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(int timeStart) {
        this.timeStart = timeStart;
    }

    public int getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(int timeEnd) {
        this.timeEnd = timeEnd;
    }

    public CrimeType getCrimeType() {
        return crimeType;
    }

    public void setCrimeType(CrimeType crimeType) {
        this.crimeType = crimeType;
    }

    public boolean isArrested() {
        return arrested;
    }

    public void setArrested(boolean arrested) {
        this.arrested = arrested;
    }

    public String getLarcenyCode() {
        return larcenyCode;
    }

    public void setLarcenyCode(String larcenyCode) {
        this.larcenyCode = larcenyCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CrimeData)) return false;

        CrimeData data = (CrimeData) o;

        if (getId() != data.getId()) return false;
        if (getStreetDir() != data.getStreetDir()) return false;
        if (Double.compare(data.getX(), getX()) != 0) return false;
        if (Double.compare(data.getY(), getY()) != 0) return false;
        if (getTimeStart() != data.getTimeStart()) return false;
        if (getTimeEnd() != data.getTimeEnd()) return false;
        if (isArrested() != data.isArrested()) return false;
        if (getAddress() != null ? !getAddress().equals(data.getAddress()) : data.getAddress() != null) return false;
        if (getAddressType() != null ? !getAddressType().equals(data.getAddressType()) : data.getAddressType() != null)
            return false;
        if (getStreet() != null ? !getStreet().equals(data.getStreet()) : data.getStreet() != null) return false;
        if (getStreetType() != null ? !getStreetType().equals(data.getStreetType()) : data.getStreetType() != null)
            return false;
        if (getReportDate() != null ? !getReportDate().equals(data.getReportDate()) : data.getReportDate() != null)
            return false;
        if (getCrimeType() != null ? !getCrimeType().equals(data.getCrimeType()) : data.getCrimeType() != null)
            return false;
        return getLarcenyCode() != null ? getLarcenyCode().equals(data.getLarcenyCode()) : data.getLarcenyCode() == null;
    }

    @Override
    public int hashCode() {
        int result;
        Long temp;
        result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + (getAddress() != null ? getAddress().hashCode() : 0);
        result = 31 * result + (getAddressType() != null ? getAddressType().hashCode() : 0);
        result = 31 * result + (getStreet() != null ? getStreet().hashCode() : 0);
        result = 31 * result + (getStreetType() != null ? getStreetType().hashCode() : 0);
        result = 31 * result + (int) getStreetDir();
        temp = Double.doubleToLongBits(getX());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(getY());
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (getReportDate() != null ? getReportDate().hashCode() : 0);
        result = 31 * result + getTimeStart();
        result = 31 * result + getTimeEnd();
        result = 31 * result + (getCrimeType() != null ? getCrimeType().hashCode() : 0);
        result = 31 * result + (isArrested() ? 1 : 0);
        result = 31 * result + (getLarcenyCode() != null ? getLarcenyCode().hashCode() : 0);
        return result;
    }
}
