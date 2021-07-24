package kakapaySecurities.testprogram.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kakapaySecurities.testprogram.domain.result.ResponseMessage;
import kakapaySecurities.testprogram.service.insertData.InsertAllDataService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.HashMap;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
public class SolveControllerTest {

    @Autowired
    InsertAllDataService insertAllDataService;
    @Autowired
    SolveController solveController;
    @Autowired
    ObjectMapper mp;

    @Before
    public void preparation() throws IOException {
        insertAllDataService.insertAccountMaster("/Users/jihyunlee/Desktop/testprogram/file/과제1_데이터_계좌정보.csv");
        insertAllDataService.insertBranch("/Users/jihyunlee/Desktop/testprogram/file/과제1_데이터_관리점정보.csv");
        insertAllDataService.insertDealHistory("/Users/jihyunlee/Desktop/testprogram/file/과제1_데이터_거래내역.csv");
    }

    @Test
    public void task4TestForResponse() throws JsonProcessingException {

        HashMap<String, String> input = new HashMap<>();
        input.put("brName", "분당점");

        ResponseEntity<Object> objectResponseEntity = solveController.solve4(input);
        assertThat(objectResponseEntity.getStatusCodeValue()).isEqualTo(404);

    }
}