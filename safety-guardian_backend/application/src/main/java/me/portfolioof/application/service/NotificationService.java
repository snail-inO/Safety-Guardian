package me.portfolioof.application.service;


import me.portfolioof.application.entity.RiskAssessment;

public interface NotificationService {
    void notify(RiskAssessment riskAssessment);
}
