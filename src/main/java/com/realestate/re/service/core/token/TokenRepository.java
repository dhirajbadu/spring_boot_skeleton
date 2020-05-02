package com.realestate.re.service.core.token;

import com.realestate.re.service.common.enums.Status;
import com.realestate.re.service.common.enums.TokenAssociation;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TokenRepository extends JpaRepository<Token, Long> {

	Token findById(long id);

	Token findByStatusAndId(Status status , long id);

	List<Token> findAllByStatusIn(List<Status> statusList , Pageable pageable);

	long countAllByStatusIn(List<Status> statusList);

	Token findByToken(String token);

	List<Token> findAllByAssociationKeyAndAssociationAndUsed(String key , TokenAssociation association , boolean isused);

	List<Token> findAllByAssociationKeyAndAssociation(String key , TokenAssociation association);

}
