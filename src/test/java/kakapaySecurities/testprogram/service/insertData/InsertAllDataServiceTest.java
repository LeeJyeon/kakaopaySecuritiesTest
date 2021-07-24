package kakapaySecurities.testprogram.service.insertData;

import kakapaySecurities.testprogram.repository.AccountMasterRepository;
import kakapaySecurities.testprogram.repository.BranchRepository;
import kakapaySecurities.testprogram.repository.DealHistoryRepository;

import static org.assertj.core.api.Assertions.*;

import kakapaySecurities.testprogram.service.insertData.InsertAllDataService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;


@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
public class InsertAllDataServiceTest {


    @Autowired
    InsertAllDataService insertAllDataService;
    @Autowired
    AccountMasterRepository accountMasterRepository;
    @Autowired
    BranchRepository branchRepository;
    @Autowired
    DealHistoryRepository dealHistoryRepository;


    @Test
    @Commit
    public void testInsertNumber() throws IOException {
        insertAllDataService.insertAccountMaster("/Users/jihyunlee/Desktop/testprogram/file/과제1_데이터_계좌정보.csv");
        assertThat( accountMasterRepository.count() ).isEqualTo( 11 );

        insertAllDataService.insertBranch("/Users/jihyunlee/Desktop/testprogram/file/과제1_데이터_관리점정보.csv");
        assertThat( branchRepository.count() ).isEqualTo( 5 );

        insertAllDataService.insertDealHistory("/Users/jihyunlee/Desktop/testprogram/file/과제1_데이터_거래내역.csv");
        assertThat(dealHistoryRepository.count()).isEqualTo(102);
    }

}