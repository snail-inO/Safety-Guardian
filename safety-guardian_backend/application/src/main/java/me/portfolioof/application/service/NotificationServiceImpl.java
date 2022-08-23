package me.portfolioof.application.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import me.portfolioof.application.entity.RiskAssessment;
import me.portfolioof.application.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Value("${service_sid}")
    private String service_sid;

    public NotificationServiceImpl(@Value("${account_sid}") String account_sid, @Value("${auth_token}") String auth_token) {
        Twilio.init(account_sid, auth_token);
    }

    @Override
    public boolean notify(User user, RiskAssessment riskAssessment) {
        if (riskAssessment.getRiskLevel().ordinal() < user.getNoticeThreshold().ordinal())
            return false;
        String content = "[Safety Guardian] Alert! Your current location's risk level has reached your account setting: " +
                user.getNoticeThreshold() + ".\n\nCurrent risk level: " + riskAssessment.getRiskLevel();
        Message.creator(new PhoneNumber(user.getPhone()), service_sid, content).create();

        return true;
    }
}
