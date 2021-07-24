package kakapaySecurities.testprogram.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import kakapaySecurities.testprogram.domain.result.BranchStatistic;
import kakapaySecurities.testprogram.domain.result.MaxPersonByYear;
import kakapaySecurities.testprogram.domain.result.NoTransactionPerson;
import kakapaySecurities.testprogram.domain.result.ResponseMessage;
import kakapaySecurities.testprogram.service.task.TaskService1;
import kakapaySecurities.testprogram.service.task.TaskService2;
import kakapaySecurities.testprogram.service.task.TaskService3;
import kakapaySecurities.testprogram.service.task.TaskService4;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
@AllArgsConstructor
@Slf4j
public class SolveController {

    private final TaskService1 taskService1;
    private final TaskService2 taskService2;
    private final TaskService3 taskService3;
    private final TaskService4 taskService4;

    @ResponseBody
    @GetMapping("/problem1")
    public List<MaxPersonByYear> solve1() {
        return taskService1.query();
    }

    @ResponseBody
    @GetMapping("/problem2")
    public List<NoTransactionPerson> solve2() {
        return taskService2.query();
    }

    @ResponseBody
    @GetMapping("/problem3")
    public List<HashMap<String, Object>> solve3() throws JsonProcessingException {
        return taskService3.query();
    }

    @ResponseBody
    @GetMapping("/problem4")
    public ResponseEntity<Object>  solve4(@RequestBody Map<String,String> input ) {

        BranchStatistic branchStatistic = taskService4.query( input.get("brName") );

        if (Objects.isNull(branchStatistic)) {
            ResponseMessage msg = new ResponseMessage() {
                @Override
                public String getCode() {
                    return "404";
                }

                @Override
                public String get메세지() {
                    return "br code not found error";
                }
            };
            return new ResponseEntity<>(msg,HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(branchStatistic, HttpStatus.OK);
        }
    }
}
