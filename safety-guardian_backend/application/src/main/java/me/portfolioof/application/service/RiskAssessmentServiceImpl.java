package me.portfolioof.application.service;

import me.portfolioof.application.DAO.CrimeDataDAO;
import me.portfolioof.application.entity.CrimeData;
import me.portfolioof.application.entity.RiskAssessment;
import me.portfolioof.application.entity.User;
import me.portfolioof.library.enums.RiskLevel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class RiskAssessmentServiceImpl implements RiskAssessmentService {
    private final CrimeDataDAO crimeDataDAO;

    public RiskAssessmentServiceImpl(CrimeDataDAO crimeDataDAO) {
        this.crimeDataDAO = crimeDataDAO;
    }

    @Override
    public RiskAssessment assess(double x, double y, double range) {
        RiskAssessment assessment = new RiskAssessment();
        assessment.setCrimeDataList(crimeDataDAO.findByDistance(x, y, range).orElseThrow(RuntimeException::new));
        assessment.setRiskLevel(calculateRisk(assessment.getCrimeDataList()));
        assessment.setX(x);
        assessment.setY(y);
        assessment.setRange(range);

        return assessment;
    }

    @Override
    public RiskAssessment assess(User user) {
        return assess(user.getLatestX(), user.getLatestY(), user.getDetectRange());
    }

    private RiskLevel calculateRisk(List<CrimeData> crimeDataList) {
        AtomicReference<Float> sum = new AtomicReference<>(0.0F);
        crimeDataList.forEach(crimeData -> sum.updateAndGet(v -> v + crimeData.getCrimeType().getWeight()));

        for (RiskLevel riskLevel : RiskLevel.values()) {
            if (sum.get() > riskLevel.getVal())
                continue;
            return riskLevel;
        }

        return RiskLevel.EXTREME;
    }
}
