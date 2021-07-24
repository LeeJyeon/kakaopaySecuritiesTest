package kakapaySecurities.testprogram.domain;


import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class AccountMaster {

    @Id
    @CsvBindByPosition(position = 0)
    String accountNo;
    @CsvBindByPosition(position = 1)
    String accountName;
    @CsvBindByPosition(position = 2)
    String manageBranchCode;



}
