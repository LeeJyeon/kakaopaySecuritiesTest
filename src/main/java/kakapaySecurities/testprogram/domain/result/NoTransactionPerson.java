package kakapaySecurities.testprogram.domain.result;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"year","name","acctNo"})
public interface NoTransactionPerson {
    String getYear();
    String getName();
    String getAcctNo();
}
