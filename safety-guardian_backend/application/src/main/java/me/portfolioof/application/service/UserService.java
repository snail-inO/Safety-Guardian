package me.portfolioof.application.service;

import me.portfolioof.application.entity.RiskAssessment;
import me.portfolioof.application.entity.User;

public interface UserService {
    User retrieveUser(Long uid);
    User updateUser(User user);
    RiskAssessment assessRisk(Long uid);
}
