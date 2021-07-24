package kakapaySecurities.testprogram.domain;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Id;
import java.io.Serializable;

@Data
@Setter
@Getter
public class DealHistoryPk implements Serializable  {
    @Id
    String dealYmd;
    @Id
    String accountNo;
    @Id
    Integer dealNo;
}
