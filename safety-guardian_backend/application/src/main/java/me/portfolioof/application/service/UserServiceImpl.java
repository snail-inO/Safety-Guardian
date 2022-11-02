package me.portfolioof.application.service;

import me.portfolioof.application.DAO.UserDAO;
import me.portfolioof.application.entity.RiskAssessment;
import me.portfolioof.application.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserDAO userDAO;
    private final RiskAssessmentService riskAssessmentService;
    private final NotificationService notificationService;

    public UserServiceImpl(UserDAO userDAO, RiskAssessmentServiceImpl riskAssessmentService, NotificationServiceImpl notificationService) {
        this.userDAO = userDAO;
        this.riskAssessmentService = riskAssessmentService;
        this.notificationService = notificationService;
    }

    @Override
    public User retrieveUser(Long uid) {
        return userDAO.findById(uid).orElseThrow(RuntimeException::new);
    }

    @Override
    public User updateUser(User user) {
        return userDAO.save(user);
    }

    @Override
    public RiskAssessment assessRisk(Long uid) {
        User user = userDAO.findById(uid).orElseThrow(RuntimeException::new);
        RiskAssessment assessment = riskAssessmentService.assess(user);
        notificationService.notify(user, assessment);

        return assessment;
    }
}
