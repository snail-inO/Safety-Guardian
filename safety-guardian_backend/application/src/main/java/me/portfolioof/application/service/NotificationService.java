package me.portfolioof.application.service;


import me.portfolioof.application.entity.RiskAssessment;
import me.portfolioof.application.entity.User;

public interface NotificationService {
    boolean notify(User user, RiskAssessment riskAssessment);
}
