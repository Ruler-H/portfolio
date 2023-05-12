package hruler.portfolio.repository;

import hruler.portfolio.domain.cafe.Cafe;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CafeRepository {

    private final EntityManager em;

    public void save(Cafe cafe) {
        em.persist(cafe);
    }

    public Cafe findOne(Long id) {
        return em.find(Cafe.class, id);
    }

    public List<Cafe> findAll() {
        return em.createQuery("select c from Cafe c", Cafe.class)
                .getResultList();
    }

    public List<Cafe> findByName(String name) {
        return em.createQuery("select c from Cafe c where c.name = :name")
                .setParameter("name", name)
                .getResultList();
    }



}
