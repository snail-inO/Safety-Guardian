package me.portfolioof.application.entity;

import me.portfolioof.library.enums.RiskLevel;

import java.util.List;

public class RiskAssessment {
    private RiskLevel riskLevel;
    private List<CrimeData> crimeDataList;

    public RiskAssessment() {
    }

    public RiskAssessment(RiskLevel riskLevel, List<CrimeData> crimeDataList) {
        this.riskLevel = riskLevel;
        this.crimeDataList = crimeDataList;
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

        if (getRiskLevel() != that.getRiskLevel()) return false;
        return getCrimeDataList() != null ? getCrimeDataList().equals(that.getCrimeDataList()) : that.getCrimeDataList() == null;
    }

    @Override
    public int hashCode() {
        int result = getRiskLevel() != null ? getRiskLevel().hashCode() : 0;
        result = 31 * result + (getCrimeDataList() != null ? getCrimeDataList().hashCode() : 0);
        return result;
    }
}
