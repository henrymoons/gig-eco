package com.moon.gigeco.domain.gigworker;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GigworkerRepositoryCustom {
	Page<Gigworker> findGigworkers(String sortBy, Pageable pageable);
}
