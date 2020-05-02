package com.realestate.re.service.core.city;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.realestate.re.service.common.enums.Status;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

	City findById(long id);

	City findByStatusAndId(Status status, long id);

	List<City> findAllByStatusIn(List<Status> statusList, Pageable pageable);

	long countAllByStatusIn(List<Status> statusList);

	@Query("select c from City c where c.country.id=?1")
	List<City> findByCountryId(long countryId);

}
