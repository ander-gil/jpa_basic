package net.ander.projects.IntroSpringDataJpa.persistence.repository;

import net.ander.projects.IntroSpringDataJpa.persistence.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressCrudRepository extends JpaRepository<Address, Long> {
}
