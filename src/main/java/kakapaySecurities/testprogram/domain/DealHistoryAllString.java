package kakapaySecurities.testprogram.domain;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Id;

@Getter
public class DealHistoryAllString {

    @CsvBindByPosition(position = 0)
    String dealYmd;
    @CsvBindByPosition(position = 1)
    String accountNo;
    @CsvBindByPosition(position = 2)
    String dealNo;
    @CsvBindByPosition(position = 3)
    String amount;
    @CsvBindByPosition(position = 4)
    String fee;
    @CsvBindByPosition(position = 5)
    String cancleYn;
}
