package kakapaySecurities.testprogram.controller;

import kakapaySecurities.testprogram.domain.common.FilePath;
import kakapaySecurities.testprogram.service.insertData.InsertAllDataService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;

@Controller
@AllArgsConstructor
@Slf4j
public class InitialController {
    @Autowired
    private final InsertAllDataService insertAllDataService;

    @PostMapping("/")
    public ResponseEntity<String> insertAllData(@RequestBody FilePath filePath) throws IOException {

        log.info(filePath.getFilePath1());
        log.info(filePath.getFilePath2());
        log.info(filePath.getFilePath3());

        insertAllDataService.insertDealHistory(filePath.getFilePath1());
        insertAllDataService.insertAccountMaster(filePath.getFilePath2());
        insertAllDataService.insertBranch(filePath.getFilePath3());

        return new ResponseEntity<String>("저장완료", HttpStatus.OK);
    }

}
