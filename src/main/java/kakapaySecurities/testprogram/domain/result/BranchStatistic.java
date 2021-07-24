package kakapaySecurities.testprogram.domain.result;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Setter;

@JsonPropertyOrder({"brName","brCode","sumAmt"})
public interface BranchStatistic {
    String getBrName();
    String getBrCode();
    Long getSumAmt();
}
