package kakapaySecurities.testprogram.service.task;

import kakapaySecurities.testprogram.domain.Branch;
import kakapaySecurities.testprogram.domain.result.BranchStatistic;
import kakapaySecurities.testprogram.repository.BranchRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class TaskService4 {

    private final BranchRepository branchRepository;

    public BranchStatistic query(String brName) {

        BranchStatistic result;

        Optional<String> branchQuery = branchRepository.checkByBrName(brName);

        if (brName.equals("분당점") || branchQuery.isEmpty()) {
            // 폐점
            log.error("폐쇄점조회 : "+brName);
            result = null;
        } else if (brName.equals("판교점")) {

            /* 분당점 + 판교점 */
            BranchStatistic tmp = branchRepository.getBranchStatistic("분당점", true).get();
            BranchStatistic tmp2 = branchRepository.getBranchStatistic("판교점", true).get();

            result = new BranchStatistic() {
                @Override
                public String getBrName() {
                    return tmp2.getBrName();
                }

                @Override
                public String getBrCode() {
                    return tmp2.getBrCode();
                }

                @Override
                public Long getSumAmt() {
                    return tmp.getSumAmt() + tmp2.getSumAmt();
                }
            };

        } else {
            result =  branchRepository.getBranchStatistic(brName,true).get();
        }

        return result;
    }
}
