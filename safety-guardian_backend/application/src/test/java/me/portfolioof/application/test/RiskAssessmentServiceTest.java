package me.portfolioof.application.test;

import me.portfolioof.application.DAO.CrimeDataDAO;
import me.portfolioof.application.entity.CrimeData;
import me.portfolioof.application.entity.CrimeType;
import me.portfolioof.application.entity.RiskAssessment;
import me.portfolioof.application.service.RiskAssessmentServiceImpl;
import me.portfolioof.library.enums.RiskLevel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RiskAssessmentServiceTest {
    @Mock
    private CrimeDataDAO crimeDataDAO = Mockito.mock(CrimeDataDAO.class);
    @Mock
    private RiskAssessmentServiceImpl riskAssessmentService = new RiskAssessmentServiceImpl(crimeDataDAO);

    @Test
    public void calculateRisk() {
        List<CrimeData> crimeDataList = new ArrayList<>();

        CrimeData crimeData = new CrimeData();
        crimeData.setId(0L);
        crimeData.setCrimeType(new CrimeType(0, "", 1F));
        crimeDataList.add(crimeData);
        Assertions.assertEquals(ReflectionTestUtils.invokeMethod(riskAssessmentService, "calculateRisk", crimeDataList), RiskLevel.SAFE);

        CrimeData crimeData1 = new CrimeData(crimeData);
        crimeData1.setCrimeType(new CrimeType(1, "1", 2F));
        crimeDataList.add(crimeData1);
        Assertions.assertEquals(ReflectionTestUtils.invokeMethod(riskAssessmentService, "calculateRisk", crimeDataList), RiskLevel.SAFE);

        CrimeData crimeData2 = new CrimeData(crimeData);
        crimeData2.setCrimeType(new CrimeType(1, "1", 10F));
        crimeDataList.add(crimeData2);
        Assertions.assertEquals(ReflectionTestUtils.invokeMethod(riskAssessmentService, "calculateRisk", crimeDataList), RiskLevel.MODERATE);

        CrimeData crimeData3 = new CrimeData(crimeData);
        crimeData3.setCrimeType(new CrimeType(1, "1", 20F));
        crimeDataList.add(crimeData3);
        Assertions.assertEquals(ReflectionTestUtils.invokeMethod(riskAssessmentService, "calculateRisk", crimeDataList), RiskLevel.HIGH);

        CrimeData crimeData4 = new CrimeData(crimeData);
        crimeData4.setCrimeType(new CrimeType(1, "1", 20F));
        crimeDataList.add(crimeData4);
        Assertions.assertEquals(ReflectionTestUtils.invokeMethod(riskAssessmentService, "calculateRisk", crimeDataList), RiskLevel.EXTREME);

        CrimeData crimeData5 = new CrimeData(crimeData);
        crimeData5.setCrimeType(new CrimeType(1, "1", 20F));
        crimeDataList.add(crimeData5);
        Assertions.assertEquals(ReflectionTestUtils.invokeMethod(riskAssessmentService, "calculateRisk", crimeDataList), RiskLevel.EXTREME);
    }


    @Test
    public void assess() {
        List<CrimeData> crimeDataList = new ArrayList<>();
        CrimeData crimeData = new CrimeData();
        crimeData.setId(0L);
        crimeData.setCrimeType(new CrimeType(0, "", 1F));
        crimeDataList.add(crimeData);

        RiskAssessment assessment = new RiskAssessment(null, 1.0, 2.0, 3.0, RiskLevel.SAFE, crimeDataList);

        Mockito.when(crimeDataDAO.findByDistance(1.0, 2.0, 3.0)).thenReturn(Optional.of(crimeDataList));
        Assertions.assertEquals(riskAssessmentService.assess(1.0, 2.0, 3.0), assessment);
        Mockito.verify(crimeDataDAO, Mockito.times(1)).findByDistance(1.0, 2.0, 3.0);
    }
}
