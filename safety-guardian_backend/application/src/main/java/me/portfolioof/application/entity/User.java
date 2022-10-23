package me.portfolioof.application.entity;

import me.portfolioof.library.enums.RiskLevel;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    private Long id;
    private Double detectRange;
    private RiskLevel noticeThreshold;
    private String phone;
    private String email;
    private Double latestX;
    private Double latestY;

    public User() {
    }

    public User(Long id, Double detectRange, RiskLevel noticeThreshold, String phone, String email, Double latestX, Double latestY) {
        this.id = id;
        this.detectRange = detectRange;
        this.noticeThreshold = noticeThreshold;
        this.phone = phone;
        this.email = email;
        this.latestX = latestX;
        this.latestY = latestY;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getDetectRange() {
        return detectRange;
    }

    public void setDetectRange(Double detectRange) {
        this.detectRange = detectRange;
    }

    public RiskLevel getNoticeThreshold() {
        return noticeThreshold;
    }

    public void setNoticeThreshold(RiskLevel noticeThreshold) {
        this.noticeThreshold = noticeThreshold;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getLatestX() {
        return latestX;
    }

    public void setLatestX(Double latestX) {
        this.latestX = latestX;
    }

    public Double getLatestY() {
        return latestY;
    }

    public void setLatestY(Double latestY) {
        this.latestY = latestY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (!getId().equals(user.getId())) return false;
        if (getDetectRange() != null ? !getDetectRange().equals(user.getDetectRange()) : user.getDetectRange() != null)
            return false;
        if (getNoticeThreshold() != user.getNoticeThreshold()) return false;
        if (getPhone() != null ? !getPhone().equals(user.getPhone()) : user.getPhone() != null) return false;
        if (getEmail() != null ? !getEmail().equals(user.getEmail()) : user.getEmail() != null) return false;
        if (getLatestX() != null ? !getLatestX().equals(user.getLatestX()) : user.getLatestX() != null) return false;
        return getLatestY() != null ? getLatestY().equals(user.getLatestY()) : user.getLatestY() == null;
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + (getDetectRange() != null ? getDetectRange().hashCode() : 0);
        result = 31 * result + (getNoticeThreshold() != null ? getNoticeThreshold().hashCode() : 0);
        result = 31 * result + (getPhone() != null ? getPhone().hashCode() : 0);
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        result = 31 * result + (getLatestX() != null ? getLatestX().hashCode() : 0);
        result = 31 * result + (getLatestY() != null ? getLatestY().hashCode() : 0);
        return result;
    }
}
