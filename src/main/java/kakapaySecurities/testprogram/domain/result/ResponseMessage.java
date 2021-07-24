package kakapaySecurities.testprogram.domain.result;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"code","메세지"})
public interface ResponseMessage {
    String getCode();
    String get메세지();

}
