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

    public User() {
    }

    public User(Long id, Double detectRange, RiskLevel noticeThreshold, String phone, String email) {
        this.id = id;
        this.detectRange = detectRange;
        this.noticeThreshold = noticeThreshold;
        this.phone = phone;
        this.email = email;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (!getId().equals(user.getId())) return false;
        if (getDetectRange() != null ? !getDetectRange().equals(user.getDetectRange()) : user.getDetectRange() != null)
            return false;
        if (getNoticeThreshold() != null ? !getNoticeThreshold().equals(user.getNoticeThreshold()) : user.getNoticeThreshold() != null)
            return false;
        if (getPhone() != null ? !getPhone().equals(user.getPhone()) : user.getPhone() != null) return false;
        return getEmail() != null ? getEmail().equals(user.getEmail()) : user.getEmail() == null;
    }

    @Override
    public int hashCode() {
        int result = getId().hashCode();
        result = 31 * result + (getDetectRange() != null ? getDetectRange().hashCode() : 0);
        result = 31 * result + (getNoticeThreshold() != null ? getNoticeThreshold().hashCode() : 0);
        result = 31 * result + (getPhone() != null ? getPhone().hashCode() : 0);
        result = 31 * result + (getEmail() != null ? getEmail().hashCode() : 0);
        return result;
    }
}
