package me.portfolioof.application.test;

import me.portfolioof.application.entity.RiskAssessment;
import me.portfolioof.application.entity.User;
import me.portfolioof.application.service.NotificationServiceImpl;
import me.portfolioof.library.enums.RiskLevel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource("classpath:twilio.properties")
public class NotificationServiceTest {

    @Autowired
    private NotificationServiceImpl notificationService;

    @Test
    public void testNotify() {
        User user = Mockito.mock(User.class);
        Mockito.when(user.getPhone()).thenReturn("+13159923143");
        Mockito.when(user.getNoticeThreshold()).thenReturn(RiskLevel.EXTREME);

        RiskAssessment riskAssessment = Mockito.mock(RiskAssessment.class);
        Mockito.when(riskAssessment.getRiskLevel()).thenReturn(RiskLevel.SAFE);
        Assertions.assertFalse(notificationService.notify(user, riskAssessment));

        Mockito.when(riskAssessment.getRiskLevel()).thenReturn(RiskLevel.HIGH);
        Assertions.assertFalse(notificationService.notify(user, riskAssessment));

        Mockito.when(riskAssessment.getRiskLevel()).thenReturn(RiskLevel.EXTREME);
        Assertions.assertTrue(notificationService.notify(user, riskAssessment));
    }
}
