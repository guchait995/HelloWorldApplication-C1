package com.test.common.repo;

import com.test.common.model.Entity;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

public class AbstractRepo<E extends Entity> extends AbstractDAO<E> {
    /**
     * Creates a new DAO with a given session provider.
     *
     * @param sessionFactory a session provider
     */
    public AbstractRepo(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public <T extends Entity> T save(T entity) {
        currentSession().saveOrUpdate(requireNonNull(entity));
        return entity;
    }

    public <T extends Entity> T delete(T entity) {
        currentSession().delete(requireNonNull(entity));
        currentSession().flush();
        return entity;
    }
    public Optional<E> findById(Long id) {
        return Optional.ofNullable(get(id));
    }

    public void delete(List<? extends Entity> entities) {
        if (entities == null) {
            return;
        }
        if (entities.size() == 0) {
            return;
        }
        entities.forEach(currentSession()::delete);
        currentSession().flush(); // this is needed because Hibernate does not execute delete statements until 1) commit 2) query 3) flush
    }
}
