package be.thomasmore.travelmore.repository;

import be.thomasmore.travelmore.domain.Reis;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class ReisRepository {
    @PersistenceContext(unitName = "travelMorePU")
    private EntityManager entityManager;

    public List<Reis> findAllByVertrekAndBestemming(int bestemmingsLandID, int vertrekLandID) {
        return entityManager.createNamedQuery(Reis.FIND_ALL_BY_VERTREK_BESTEMMING, Reis.class).setParameter("bestemmingsLandID", bestemmingsLandID).setParameter("vertrekLandID", vertrekLandID).getResultList();
    }

    public List<Reis> findAll() {
        return entityManager.createNamedQuery(Reis.FIND_ALL, Reis.class).getResultList();
    }

    public void addReis(Reis reis) {
        entityManager.persist(reis);
        entityManager.flush();
    }

    public Reis findReisById(int id){
        return entityManager.createNamedQuery(Reis.FIND_BY_ID, Reis.class).setParameter("id",id).getSingleResult();
    }

    public void delete(Reis reisParam){
        Reis reis = entityManager.find(Reis.class, reisParam.getId());
        entityManager.remove(reis);
        entityManager.flush();
    }

    public List<Reis> findByFilters(String queryParam){
        String query = queryParam.substring(0, queryParam.length() - 5);
        return entityManager.createQuery(query).getResultList();
    }
}
