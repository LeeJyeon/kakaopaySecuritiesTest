package kakapaySecurities.testprogram.service.task;

import kakapaySecurities.testprogram.domain.result.MaxPersonByYear;
import kakapaySecurities.testprogram.domain.result.NoTransactionPerson;
import kakapaySecurities.testprogram.repository.DealHistoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class TaskService2 {

    private final DealHistoryRepository dealHistoryRepository;

    public List<NoTransactionPerson> query() {

        List<NoTransactionPerson> result = new ArrayList<>();

        dealHistoryRepository.getNoTransaction("2018");
        dealHistoryRepository.getNoTransaction("2019");

        result.addAll( dealHistoryRepository.getNoTransaction("2018") );
        result.addAll( dealHistoryRepository.getNoTransaction("2019") );

        return result;
    }
}
