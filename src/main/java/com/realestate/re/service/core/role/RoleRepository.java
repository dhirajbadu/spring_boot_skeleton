package com.realestate.re.service.core.role;

import com.realestate.re.service.common.enums.Status;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findById(long id);

	Role findByStatusAndId(Status status , long id);

	List<Role> findAllByIdIn(List<Long> roleId);

	List<Role> findAllByStatusIn(List<Status> statusList , Pageable pageable);

	long countAllByStatusIn(List<Status> statusList);

    Role findByTitle(String title);
}
