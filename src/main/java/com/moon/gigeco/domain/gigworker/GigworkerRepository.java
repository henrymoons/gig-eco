package com.moon.gigeco.domain.gigworker;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GigworkerRepository extends JpaRepository<Gigworker, Long>, GigworkerRepositoryCustom {

}
