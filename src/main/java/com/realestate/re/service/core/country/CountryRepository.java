package com.realestate.re.service.core.country;

import com.realestate.re.service.common.enums.Status;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

	Country findById(long id);

	Country findByStatusAndId(Status status , long id);

	List<Country> findAllByStatusIn(List<Status> statusList , Pageable pageable);

	long countAllByStatusIn(List<Status> statusList);

}
