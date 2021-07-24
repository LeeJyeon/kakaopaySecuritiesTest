package kakapaySecurities.testprogram.domain;


import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvCustomBindByName;
import com.opencsv.bean.CsvNumber;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@IdClass(DealHistoryPk.class)
public class DealHistory extends DealHistoryPk {

    @Id
    @CsvBindByPosition(position = 0)
    String dealYmd;
    @Id
    @CsvBindByPosition(position = 1)
    String accountNo;
    @Id
    @CsvBindByPosition(position = 2)
    Integer dealNo;

    @CsvBindByPosition(position = 3)
    Long amount;
    @CsvBindByPosition(position = 4)
    Long fee;
    @CsvBindByPosition(position = 5)
    String cancleYn;


}
