package kakapaySecurities.testprogram.domain.result;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"year","name","acctNo","sumAmt"})
public interface MaxPersonByYear {
    String getYear();
    String getName();
    String getAcctNo();
    Long getSumAmt();
}
