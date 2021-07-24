package kakapaySecurities.testprogram.repository;

import kakapaySecurities.testprogram.domain.Branch;
import kakapaySecurities.testprogram.domain.result.BranchStatistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BranchRepository extends JpaRepository<Branch, String> {
    @Query(value = "SELECT a.branch_name                 brname,\n" +
            "       a.branch_code                 brcode,\n" +
            "       Nvl(Sum(c.amount - c.fee), 0) sumamt\n" +
            "FROM   branch a\n" +
            "       LEFT OUTER JOIN account_master b\n" +
            "                    ON a.branch_code = b.manage_branch_code\n" +
            "       LEFT OUTER JOIN deal_history c\n" +
            "                    ON b.account_no = c.account_no\n" +
            "                       AND c.cancle_yn = 'N'\n" +
            "                       AND deal_ymd BETWEEN ?1||'0101' AND ?1||'1231'\n" +
            "GROUP  BY a.branch_name,\n" +
            "          a.branch_code\n" +
            "ORDER  BY 3 DESC ", nativeQuery = true)
    List<BranchStatistic> getBranchStatistic(String year);

    @Query(value = "SELECT a.branch_code                 brcode,\n" +
            "       a.branch_name                 brname,\n" +
            "       Nvl(Sum(c.amount - c.fee), 0) sumamt\n" +
            "FROM   branch a\n" +
            "       LEFT OUTER JOIN account_master b\n" +
            "                    ON a.branch_code = b.manage_branch_code\n" +
            "       LEFT OUTER JOIN deal_history c\n" +
            "                    ON b.account_no = c.account_no\n" +
            "                       AND c.cancle_yn = 'N'\n" +
            "WHERE  branch_name = ?1\n" +
            "GROUP  BY a.branch_code,\n" +
            "          a.branch_name ", nativeQuery = true)
    Optional<BranchStatistic> getBranchStatistic(String brName, boolean specificFlag);

    @Query(value = "SELECT 'X'\n" +
            "FROM   branch\n" +
            "WHERE  branch_name = ?1 ", nativeQuery = true)
    Optional<String> checkByBrName(String brName);
}
