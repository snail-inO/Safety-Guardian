package me.portfolioof.application.service;


import me.portfolioof.application.entity.RiskAssessment;

public interface RiskAssessmentService {
    RiskAssessment assess(double x, double y, double range);
}
