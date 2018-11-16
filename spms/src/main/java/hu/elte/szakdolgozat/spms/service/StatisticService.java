package hu.elte.szakdolgozat.spms.service;

import hu.elte.szakdolgozat.spms.model.entity.spms.Period;
import hu.elte.szakdolgozat.spms.model.entity.spms.Plan;
import hu.elte.szakdolgozat.spms.model.ui.StatisticChartModel;
import hu.elte.szakdolgozat.spms.repository.spms.PeriodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatisticService {

    @Autowired
    private PeriodRepository periodRepository;

    public StatisticChartModel createStatisticChartModel() {
        StatisticChartModel statisticChartModel = new StatisticChartModel();

        Optional<Period> period= periodRepository.findByActive(true);
        if (period.isPresent()) {
            List<Plan> planList = period.get().getPlans();
            for(Plan pl : planList) {
                statisticChartModel.addToExistingOrPutNew(pl.getStatus().name().replaceAll("\\_", " "), 1);
            }
        }

        return statisticChartModel;
    }
}
