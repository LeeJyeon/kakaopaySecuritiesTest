package kakapaySecurities.testprogram.service.task;

import kakapaySecurities.testprogram.domain.result.MaxPersonByYear;
import kakapaySecurities.testprogram.repository.DealHistoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TaskService1 {

    private final DealHistoryRepository dealHistoryRepository;

    public List<MaxPersonByYear> query() {

        List<MaxPersonByYear> result = new ArrayList<>();

        result.add(dealHistoryRepository.getMaxPersonByYear("2018"));
        result.add(dealHistoryRepository.getMaxPersonByYear("2019"));

        return result;
    }

}
