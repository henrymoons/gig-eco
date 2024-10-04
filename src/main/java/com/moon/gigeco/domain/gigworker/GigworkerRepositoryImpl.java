package com.moon.gigeco.domain.gigworker;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import static com.moon.gigeco.domain.gigworker.QGigworker.gigworker;

public class GigworkerRepositoryImpl implements GigworkerRepositoryCustom {

    @Autowired
    private JPAQueryFactory queryFactory;

    @Override
    public Page<Gigworker> findGigworkers(String sortBy, Pageable pageable) {
        OrderSpecifier<?> sortOrder = getSortOrder(sortBy);

//        QueryResults<Gigworker> result = queryFactory
//                .select(Projections.constructor(Gigworker.class, gigworker.id, gigworker.name, gigworker.views))
//                .from(gigworker)
//        List<Gigworker> profiles = queryFactory.selectFrom(gigworker)
        QueryResults<Gigworker> result = queryFactory.selectFrom(gigworker)
                .orderBy(sortOrder)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetchResults();

        long total = queryFactory.selectFrom(gigworker).fetchCount();

//        return new PageImpl<>(profiles, pageable, total);
        return new PageImpl<>(result.getResults(), pageable, total);
    }

    private OrderSpecifier<?> getSortOrder(String sortBy) {
        if (sortBy.equals("name")) {
            return gigworker.name.asc();
        } else if (sortBy.equals("views")) {
            return gigworker.views.desc();
        } else {
            return gigworker.createdAt.desc();
        }
    }
}
