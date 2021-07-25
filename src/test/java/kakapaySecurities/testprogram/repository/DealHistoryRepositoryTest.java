package kakapaySecurities.testprogram.repository;

import kakapaySecurities.testprogram.domain.DealHistory;
import kakapaySecurities.testprogram.domain.result.MaxPersonByYear;
import kakapaySecurities.testprogram.domain.result.NoTransactionPerson;
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
import java.util.*;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
public class DealHistoryRepositoryTest {

    @Autowired
    InsertAllDataService insertAllDataService;

    @Autowired
    DealHistoryRepository dealHistoryRepository;

    @Before
    public void preparation() throws IOException {
        insertAllDataService.insertAccountMaster("src/main/resources/file/과제1_데이터_계좌정보.csv");
        insertAllDataService.insertBranch("src/main/resources/file/과제1_데이터_관리점정보.csv");
        insertAllDataService.insertDealHistory("src/main/resources/file/과제1_데이터_거래내역.csv");
    }


    @Test
    public void task1TestByStaticValue() {

        MaxPersonByYear queryResult2018 = dealHistoryRepository.getMaxPersonByYear("2018");

        assertThat(queryResult2018.getYear()).isEqualTo("2018");
        assertThat(queryResult2018.getName()).isEqualTo("테드");
        assertThat(queryResult2018.getAcctNo()).isEqualTo("11111114");
        assertThat(queryResult2018.getSumAmt()).isEqualTo(28992000);

        MaxPersonByYear queryResult2019 = dealHistoryRepository.getMaxPersonByYear("2019");

        assertThat(queryResult2019.getYear()).isEqualTo("2019");
        assertThat(queryResult2019.getName()).isEqualTo("에이스");
        assertThat(queryResult2019.getAcctNo()).isEqualTo("11111112");
        assertThat(queryResult2019.getSumAmt()).isEqualTo(40998400);
    }

    @Test
    public void task1TestByCompareAllData() {

        List<DealHistory> queryAll = dealHistoryRepository.findAll();

        HashMap<String, Long> map2018 = new HashMap<>();
        HashMap<String, Long> map2019 = new HashMap<>();

        for (DealHistory element : queryAll) {
            if (element.getDealYmd().substring(0, 4).equals("2018")) {
                changeAmount(map2018,element);
            } else if (element.getDealYmd().substring(0, 4).equals("2019")) {
                changeAmount(map2019,element);
            }
        }

        String maxAccount2018 =
                Collections.max(map2018.entrySet(), (entry1, entry2) -> (int) (entry1.getValue() - entry2.getValue())).getKey();

        String maxAccount2019 =
                Collections.max(map2019.entrySet(), (entry1, entry2) -> (int) (entry1.getValue() - entry2.getValue())).getKey();


        /* compare to sql result */
        MaxPersonByYear queryResult2018 = dealHistoryRepository.getMaxPersonByYear("2018");

        assertThat(queryResult2018.getAcctNo()).isEqualTo(maxAccount2018);
        assertThat(queryResult2018.getSumAmt()).isEqualTo(map2018.get(maxAccount2018));

        MaxPersonByYear queryResult2019 = dealHistoryRepository.getMaxPersonByYear("2019");
        assertThat(queryResult2019.getAcctNo()).isEqualTo(maxAccount2019);
        assertThat(queryResult2019.getSumAmt()).isEqualTo(map2019.get(maxAccount2019));

    }

    /* 계좌에 따른 금액 합산 */
    public void changeAmount(HashMap<String, Long> map, DealHistory dealHistory) {
        if (dealHistory.getCancleYn().equals("Y")) {
            return;
        }

        if (map.containsKey(dealHistory.getAccountNo())) {
            Long tmpAmount = map.get(dealHistory.getAccountNo())
                    + dealHistory.getAmount()
                    - dealHistory.getFee();
            map.put(dealHistory.getAccountNo(), tmpAmount);
        } else {
            map.put(dealHistory.getAccountNo() , dealHistory.getAmount() - dealHistory.getFee());
        }
    }

    @Test
    public void task2TestByExistsData() {
        List<NoTransactionPerson> queryResult2018 = dealHistoryRepository.getNoTransaction("2018");

        List<DealHistory> queryAll = dealHistoryRepository.findAll();

        for (int i = 0; i < queryResult2018.size(); i++) {
            for (DealHistory element : queryAll) {
                if (element.getCancleYn().equals("N") &&
                        element.getDealYmd().substring(0, 4).equals("2018") &&
                        element.getAccountNo().equals(queryResult2018.get(i))) {
                    fail();
                }
            }
        }

    }

}
