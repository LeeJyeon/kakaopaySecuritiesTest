package kakapaySecurities.testprogram.repository;

import kakapaySecurities.testprogram.domain.AccountMaster;
import kakapaySecurities.testprogram.domain.DealHistory;
import kakapaySecurities.testprogram.domain.result.BranchStatistic;
import kakapaySecurities.testprogram.service.insertData.InsertAllDataService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
public class BranchRepositoryTest {

    @Autowired
    InsertAllDataService insertAllDataService;
    @Autowired
    BranchRepository branchRepository;
    @Autowired
    DealHistoryRepository dealHistoryRepository;
    @Autowired
    AccountMasterRepository accountMasterRepository;

    @Before
    public void preparation() throws IOException {
        insertAllDataService.insertAccountMaster("/Users/jihyunlee/Desktop/testprogram/file/과제1_데이터_계좌정보.csv");
        insertAllDataService.insertBranch("/Users/jihyunlee/Desktop/testprogram/file/과제1_데이터_관리점정보.csv");
        insertAllDataService.insertDealHistory("/Users/jihyunlee/Desktop/testprogram/file/과제1_데이터_거래내역.csv");
    }

    @Test
    public void task3TestByResultSize() {
        List<BranchStatistic> queryResult2018 = branchRepository.getBranchStatistic("2018");
        List<BranchStatistic> queryResult2019 = branchRepository.getBranchStatistic("2019");

        assertThat(branchRepository.count()).isEqualTo(queryResult2018.size());
        assertThat(branchRepository.count()).isEqualTo(queryResult2019.size());
    }

    @Test
    public void task3TestByResultOrder() {
        List<BranchStatistic> queryResult2018 = branchRepository.getBranchStatistic("2018");

        for (int i = 1; i < queryResult2018.size(); i++) {
            if (queryResult2018.get(i - 1).getSumAmt() < queryResult2018.get(i).getSumAmt()) {
                fail();
            }
        }
    }

    @Test
    public void task3TestByAllData() {
        List<BranchStatistic> queryResult2018 = branchRepository.getBranchStatistic("2018");

        List<DealHistory> allQueryData = dealHistoryRepository.findAll();

        HashMap<String, Long> map2018 = new HashMap<>();

        for (DealHistory element : allQueryData) {
            if (element.getDealYmd().substring(0, 4).equals("2018")) {
                Optional<AccountMaster> accountInfo = accountMasterRepository.findById(element.getAccountNo());
                changeAmount(map2018, accountInfo.get().getManageBranchCode(), element);
            }
        }

        for (BranchStatistic branchStatistic : queryResult2018) {
            if (map2018.containsKey(branchStatistic.getBrCode())) {
                assertThat(map2018.get(branchStatistic.getBrCode()))
                        .isEqualTo(branchStatistic.getSumAmt());
            }
        }

    }

    /* 지점 금액 합산 */
    public void changeAmount(HashMap<String, Long> map, String branch, DealHistory dealHistory) {
        if (dealHistory.getCancleYn().equals("Y")) {
            return;
        }

        if (map.containsKey(branch)) {
            Long tmpAmount = map.get(branch)
                    + dealHistory.getAmount()
                    - dealHistory.getFee();
            map.put(branch, tmpAmount);
        } else {
            map.put(branch, dealHistory.getAmount() - dealHistory.getFee());
        }
    }

    @Test
    public void task4TestBySumAllYear() {
        BranchStatistic result = branchRepository.getBranchStatistic("판교점", true).get();

        List<BranchStatistic> tmp2018 = branchRepository.getBranchStatistic("2018");
        List<BranchStatistic> tmp2019 = branchRepository.getBranchStatistic("2019");
        List<BranchStatistic> tmp2020 = branchRepository.getBranchStatistic("2020");

        Long sumAmt = 0L;

        for (int i = 0; i < tmp2018.size(); i++) {
            if (tmp2018.get(i).getBrName().equals("판교점")) {
                sumAmt += tmp2018.get(i).getSumAmt();
            }
            if (tmp2019.get(i).getBrName().equals("판교점")) {
                sumAmt += tmp2019.get(i).getSumAmt();
            }
            if (tmp2020.get(i).getBrName().equals("판교점")) {
                sumAmt += tmp2020.get(i).getSumAmt();
            }
        }

        assertThat(sumAmt).isEqualTo(result.getSumAmt());

    }
}