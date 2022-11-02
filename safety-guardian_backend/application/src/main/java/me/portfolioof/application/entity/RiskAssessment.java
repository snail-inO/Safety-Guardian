package me.portfolioof.application.entity;

import me.portfolioof.library.enums.RiskLevel;

import java.util.List;
import java.util.Objects;

public class RiskAssessment {
    private Long uid;
    private Double x;
    private Double y;
    private Double range;
    private RiskLevel riskLevel;
    private List<CrimeData> crimeDataList;

    public RiskAssessment() {
    }

    public RiskAssessment(Long uid, Double x, Double y, Double range, RiskLevel riskLevel, List<CrimeData> crimeDataList) {
        this.uid = uid;
        this.x = x;
        this.y = y;
        this.range = range;
        this.riskLevel = riskLevel;
        this.crimeDataList = crimeDataList;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    public Double getY() {
        return y;
    }

    public void setY(Double y) {
        this.y = y;
    }

    public Double getRange() {
        return range;
    }

    public void setRange(Double range) {
        this.range = range;
    }

    public RiskLevel getRiskLevel() {
        return riskLevel;
    }

    public void setRiskLevel(RiskLevel riskLevel) {
        this.riskLevel = riskLevel;
    }

    public List<CrimeData> getCrimeDataList() {
        return crimeDataList;
    }

    public void setCrimeDataList(List<CrimeData> crimeDataList) {
        this.crimeDataList = crimeDataList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RiskAssessment)) return false;
        RiskAssessment that = (RiskAssessment) o;
        return Objects.equals(getUid(), that.getUid()) && Objects.equals(getX(), that.getX()) && Objects.equals(getY(), that.getY()) && Objects.equals(getRange(), that.getRange()) && getRiskLevel() == that.getRiskLevel() && Objects.equals(getCrimeDataList(), that.getCrimeDataList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUid(), getX(), getY(), getRange(), getRiskLevel(), getCrimeDataList());
    }
}
