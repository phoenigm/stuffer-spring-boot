package ru.phoenigm.stuffer.repository.custom;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.phoenigm.stuffer.domain.Review;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

@Repository
public class ReviewRepositoryEntityManagerImpl implements ReviewRepository {
    @Autowired
    private EntityManager entityManager;

    @Override
    public Double getAverageRatingByDriverId(Long id) {
        String query = "select avg (r.driver.id) from Review r";
        Query result = entityManager.createQuery(query);
        return (Double) result.getSingleResult();
    }

    @Override
    public Review findById(Long id) {
        return entityManager.find(Review.class, id);
    }

    @Override
    public List<Review> findAll() {
        String jpql = "select r from Review r";
        Query query = entityManager.createQuery(jpql, Review.class);
        return query.getResultList();
    }

    @Override
    public Review save(Review model) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        Long id = (Long) entityManager.unwrap(Session.class).save(model);
        model.setId(id);
        transaction.commit();
        return model;
    }

    @Override
    public void updateById(Long id, Review model) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        model.setId(id);
        entityManager.merge(model);
        transaction.commit();
    }

    @Override
    public void deleteById(Long id) {
        EntityTransaction transaction = entityManager.getTransaction();
        Review review = entityManager.find(Review.class, id);
        transaction.begin();
        entityManager.remove(review);
        transaction.commit();
    }
}
