package kakapaySecurities.testprogram.settingFile;

import kakapaySecurities.testprogram.domain.common.FilePath;
import kakapaySecurities.testprogram.service.insertData.InsertAllDataService;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;

@Component
@AllArgsConstructor
@Slf4j
public class SetFile implements ApplicationListener<ApplicationStartedEvent> {

    @Autowired
    private final InsertAllDataService insertAllDataService;

    @SneakyThrows
    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {

        log.info("src/main/resources/file/과제1_데이터_계좌정보.csv");
        log.info("src/main/resources/file/과제1_데이터_관리점정보.csv");
        log.info("src/main/resources/file/과제1_데이터_거래내역.csv");

        insertAllDataService.insertAccountMaster("src/main/resources/file/과제1_데이터_계좌정보.csv");
        insertAllDataService.insertBranch("src/main/resources/file/과제1_데이터_관리점정보.csv");
        insertAllDataService.insertDealHistory("src/main/resources/file/과제1_데이터_거래내역.csv");


    }
}
