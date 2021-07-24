package kakapaySecurities.testprogram.repository;

import kakapaySecurities.testprogram.domain.AccountMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountMasterRepository extends JpaRepository<AccountMaster,String> {
}
