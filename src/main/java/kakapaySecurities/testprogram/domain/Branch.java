package kakapaySecurities.testprogram.domain;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Branch {
    @Id
    @CsvBindByPosition(position = 0)
    String branchCode;
    @CsvBindByPosition(position = 1)
    String branchName;


}