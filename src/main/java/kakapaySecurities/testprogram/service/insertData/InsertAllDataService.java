package kakapaySecurities.testprogram.service.insertData;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvToBeanBuilder;
import kakapaySecurities.testprogram.domain.AccountMaster;
import kakapaySecurities.testprogram.domain.Branch;
import kakapaySecurities.testprogram.domain.DealHistory;
import kakapaySecurities.testprogram.domain.DealHistoryAllString;
import kakapaySecurities.testprogram.repository.AccountMasterRepository;
import kakapaySecurities.testprogram.repository.BranchRepository;
import kakapaySecurities.testprogram.repository.DealHistoryRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.persistence.Id;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
@AllArgsConstructor
@Slf4j
public class InsertAllDataService {

    @Autowired
    private final AccountMasterRepository accountMasterRepository;
    @Autowired
    private final BranchRepository branchRepository;
    @Autowired
    private final DealHistoryRepository dealHistoryRepository;

    /* insert AccountMaster Table */
    public ResponseEntity<String> insertAccountMaster(String filePath) throws IOException {

        List<AccountMaster> newLine = new CsvToBeanBuilder<AccountMaster>(new FileReader(filePath))
                .withType(AccountMaster.class)
                .build()
                .parse();

        accountMasterRepository.saveAll(
                newLine.subList(1, newLine.size())
        );

        return new ResponseEntity<String>("Success AccountMaster", HttpStatus.OK);
    }

    /* insert Branch Table */
    public ResponseEntity<String> insertBranch(String filePath) throws FileNotFoundException {

        List<Branch> newLine = new CsvToBeanBuilder<Branch>(new FileReader(filePath))
                .withType(Branch.class)
                .build()
                .parse();

        branchRepository.saveAll(
                newLine.subList(1, newLine.size())
        );

        return new ResponseEntity<String>("Success Branch", HttpStatus.OK);
    }

    /* insert DealHistory Table */
    public ResponseEntity<String> insertDealHistory(String filePath) throws FileNotFoundException {

        List<DealHistoryAllString> newLine = new CsvToBeanBuilder<DealHistoryAllString>(new FileReader(filePath))
                .withType(DealHistoryAllString.class)
                .build()
                .parse();

        /* type convert */
        List<DealHistory> typeNewLine = new ArrayList<>();

        for (DealHistoryAllString dealHistoryAllString : newLine.subList(1,newLine.size())) {
            typeNewLine.add(convertType(dealHistoryAllString));
        }
        /* type convert */

        dealHistoryRepository.saveAll( typeNewLine );

        return new ResponseEntity<String>("Success DealHistory", HttpStatus.OK);
    }


    public DealHistory convertType(DealHistoryAllString dealHistoryAllString) {
        DealHistory tmp = new DealHistory();

        tmp.setDealYmd(dealHistoryAllString.getDealYmd());
        tmp.setAccountNo(dealHistoryAllString.getAccountNo());
        tmp.setDealNo(Integer.parseInt(dealHistoryAllString.getDealNo()));
        tmp.setAmount(Long.parseLong(dealHistoryAllString.getAmount()));
        tmp.setFee(Long.parseLong(dealHistoryAllString.getFee()));
        tmp.setCancleYn(dealHistoryAllString.getCancleYn());

        return tmp;
    }


}
