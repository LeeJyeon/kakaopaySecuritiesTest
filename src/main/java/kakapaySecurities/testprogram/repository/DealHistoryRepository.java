package kakapaySecurities.testprogram.repository;

import kakapaySecurities.testprogram.domain.DealHistory;
import kakapaySecurities.testprogram.domain.DealHistoryPk;
import kakapaySecurities.testprogram.domain.result.MaxPersonByYear;
import kakapaySecurities.testprogram.domain.result.NoTransactionPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DealHistoryRepository extends JpaRepository<DealHistory, DealHistoryPk> {

    @Query(value =
            "SELECT s.ymd year,\n" +
                    "       s1.account_name name,\n" +
                    "       s.account_no acctno,\n" +
                    "       s.amount sumamt\n" +
                    "FROM   (SELECT t.account_no,\n" +
                    "               t.ymd,\n" +
                    "               t.amount,\n" +
                    "               Rank()\n" +
                    "                 OVER(\n" +
                    "                   partition BY t.ymd\n" +
                    "                   ORDER BY t.amount DESC ) rnk\n" +
                    "        FROM   (SELECT account_no,\n" +
                    "                       Substr(deal_ymd, 1, 4) ymd,\n" +
                    "                       Sum(amount - fee)      amount\n" +
                    "                FROM   deal_history\n" +
                    "                WHERE  cancle_yn = 'N'\n" +
                    "                       AND deal_ymd BETWEEN ?1||'0101' AND ?1||'1231'\n" +
                    "                GROUP  BY account_no,\n" +
                    "                          Substr(deal_ymd, 1, 4)) t) s\n" +
                    "       LEFT OUTER JOIN account_master s1\n" +
                    "                    ON s.account_no = s1.account_no\n" +
                    "WHERE  s.rnk = 1", nativeQuery = true)
    MaxPersonByYear getMaxPersonByYear(String year);

    @Query(value =
            "SELECT ?1           year ,\n" +
                    "       account_name name ,\n" +
                    "       account_no   acctno\n" +
                    "FROM   account_master a\n" +
                    "WHERE  NOT EXISTS\n" +
                    "       (\n" +
                    "              SELECT 'X'\n" +
                    "              FROM   deal_history b\n" +
                    "              WHERE  a.account_no = b.account_no\n" +
                    "              AND    b.cancle_yn = 'N'\n" +
                    "              AND    b.deal_ymd BETWEEN ?1\n" +
                    "                            ||'0101'\n" +
                    "              AND    ?1\n" +
                    "                            ||'1231')", nativeQuery = true)
    List<NoTransactionPerson> getNoTransaction(String year);


}
