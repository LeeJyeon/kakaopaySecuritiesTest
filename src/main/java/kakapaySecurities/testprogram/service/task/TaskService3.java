package kakapaySecurities.testprogram.service.task;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kakapaySecurities.testprogram.domain.result.BranchStatistic;
import kakapaySecurities.testprogram.repository.BranchRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class TaskService3 {

    private final BranchRepository branchRepository;
    private final ObjectMapper mapper;

    public List<HashMap<String, Object>> query() throws JsonProcessingException {

        HashMap<String , Object > tmp = new HashMap<>();
        HashMap<String , Object > tmp2 = new HashMap<>();

        tmp.put("year", "2018");
        tmp.put("dataList", branchRepository.getBranchStatistic("2018"));
        tmp2.put("year", "2019");
        tmp2.put("dataList", branchRepository.getBranchStatistic("2019"));

        List<HashMap<String, Object>> result = new ArrayList<>();
        result.add(tmp);
        result.add(tmp2);

        log.info(mapper.writeValueAsString(result));

        return result;

    }
}
