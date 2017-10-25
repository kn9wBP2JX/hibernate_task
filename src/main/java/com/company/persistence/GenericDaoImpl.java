package com.company.persistence;

import com.company.utils.HibernateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import java.lang.reflect.ParameterizedType;

public abstract class GenericDaoImpl<T> implements GenericDao<T> {
    static final Logger log = LoggerFactory.getLogger(GenericDaoImpl.class.getName());
    private static EntityManager entityManager = HibernateUtil.getEntityManager();
    private Class<T> type;

    @SuppressWarnings("unchecked")
    public GenericDaoImpl() {
        type = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public void setEntityManager(EntityManager entityManager) {
        GenericDaoImpl.entityManager = entityManager;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Override
    public T save(T entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
        return entity;
    }

    @Override
    public boolean delete(T entity) {
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
        entityManager.getTransaction().commit();
        return true;
    }

    @Override
    public T update(T entity) {
        entityManager.getTransaction().begin();
        entityManager.merge(entity);
        entityManager.getTransaction().commit();
        return entity;
    }

    @Override
    public T find(Long id) {
        return entityManager.find(type, id);
    }

    public GenericDaoImpl(Class<T> type) {
        this.type = type;
    }

    public GenericDaoImpl(EntityManager entityManager, Class<T> type) {
        GenericDaoImpl.entityManager = entityManager;
        this.type = type;
    }
}
