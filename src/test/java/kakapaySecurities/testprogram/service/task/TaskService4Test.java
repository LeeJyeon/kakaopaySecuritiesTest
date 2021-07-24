package kakapaySecurities.testprogram.service.task;

import kakapaySecurities.testprogram.repository.BranchRepository;
import kakapaySecurities.testprogram.repository.DealHistoryRepository;
import kakapaySecurities.testprogram.service.insertData.InsertAllDataService;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
public class TaskService4Test {

    @Autowired
    InsertAllDataService insertAllDataService;
    @Autowired
    TaskService4 taskService4;
    @Autowired
    BranchRepository branchRepository;


    @Before
    public void preparation() throws IOException {
        insertAllDataService.insertAccountMaster("/Users/jihyunlee/Desktop/testprogram/file/과제1_데이터_계좌정보.csv");
        insertAllDataService.insertBranch("/Users/jihyunlee/Desktop/testprogram/file/과제1_데이터_관리점정보.csv");
        insertAllDataService.insertDealHistory("/Users/jihyunlee/Desktop/testprogram/file/과제1_데이터_거래내역.csv");
    }

    @Test
    public void task4TestForBranchIntegration() {

        assertThat(taskService4.query("분당점")).isNull();

        Long amount = 0L;

        amount += branchRepository.getBranchStatistic("판교점", true).get().getSumAmt();
        amount += branchRepository.getBranchStatistic("분당점", true).get().getSumAmt();

        assertThat(taskService4.query("판교점").getSumAmt())
                .isEqualTo(amount);
    }

}