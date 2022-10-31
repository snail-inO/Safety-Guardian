package me.portfolioof.application.service;


import me.portfolioof.application.entity.RiskAssessment;
import me.portfolioof.application.entity.User;

public interface RiskAssessmentService {
    RiskAssessment assess(double x, double y, double range);
    RiskAssessment assess(User user);
}
