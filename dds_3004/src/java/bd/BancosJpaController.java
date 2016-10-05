/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

import bd.exceptions.NonexistentEntityException;
import bd.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author sebaaz
 */
public class BancosJpaController implements Serializable {

    public BancosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Bancos bancos) throws PreexistingEntityException, Exception {
        if (bancos.getPOIsCollection() == null) {
            bancos.setPOIsCollection(new ArrayList<POIs>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Servicios servicioBanco = bancos.getServicioBanco();
            if (servicioBanco != null) {
                servicioBanco = em.getReference(servicioBanco.getClass(), servicioBanco.getCodigoServicio());
                bancos.setServicioBanco(servicioBanco);
            }
            Collection<POIs> attachedPOIsCollection = new ArrayList<POIs>();
            for (POIs POIsCollectionPOIsToAttach : bancos.getPOIsCollection()) {
                POIsCollectionPOIsToAttach = em.getReference(POIsCollectionPOIsToAttach.getClass(), POIsCollectionPOIsToAttach.getPoiId());
                attachedPOIsCollection.add(POIsCollectionPOIsToAttach);
            }
            bancos.setPOIsCollection(attachedPOIsCollection);
            em.persist(bancos);
            if (servicioBanco != null) {
                servicioBanco.getBancosCollection().add(bancos);
                servicioBanco = em.merge(servicioBanco);
            }
            for (POIs POIsCollectionPOIs : bancos.getPOIsCollection()) {
                Bancos oldCodBancoOfPOIsCollectionPOIs = POIsCollectionPOIs.getCodBanco();
                POIsCollectionPOIs.setCodBanco(bancos);
                POIsCollectionPOIs = em.merge(POIsCollectionPOIs);
                if (oldCodBancoOfPOIsCollectionPOIs != null) {
                    oldCodBancoOfPOIsCollectionPOIs.getPOIsCollection().remove(POIsCollectionPOIs);
                    oldCodBancoOfPOIsCollectionPOIs = em.merge(oldCodBancoOfPOIsCollectionPOIs);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findBancos(bancos.getBancoId()) != null) {
                throw new PreexistingEntityException("Bancos " + bancos + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Bancos bancos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Bancos persistentBancos = em.find(Bancos.class, bancos.getBancoId());
            Servicios servicioBancoOld = persistentBancos.getServicioBanco();
            Servicios servicioBancoNew = bancos.getServicioBanco();
            Collection<POIs> POIsCollectionOld = persistentBancos.getPOIsCollection();
            Collection<POIs> POIsCollectionNew = bancos.getPOIsCollection();
            if (servicioBancoNew != null) {
                servicioBancoNew = em.getReference(servicioBancoNew.getClass(), servicioBancoNew.getCodigoServicio());
                bancos.setServicioBanco(servicioBancoNew);
            }
            Collection<POIs> attachedPOIsCollectionNew = new ArrayList<POIs>();
            for (POIs POIsCollectionNewPOIsToAttach : POIsCollectionNew) {
                POIsCollectionNewPOIsToAttach = em.getReference(POIsCollectionNewPOIsToAttach.getClass(), POIsCollectionNewPOIsToAttach.getPoiId());
                attachedPOIsCollectionNew.add(POIsCollectionNewPOIsToAttach);
            }
            POIsCollectionNew = attachedPOIsCollectionNew;
            bancos.setPOIsCollection(POIsCollectionNew);
            bancos = em.merge(bancos);
            if (servicioBancoOld != null && !servicioBancoOld.equals(servicioBancoNew)) {
                servicioBancoOld.getBancosCollection().remove(bancos);
                servicioBancoOld = em.merge(servicioBancoOld);
            }
            if (servicioBancoNew != null && !servicioBancoNew.equals(servicioBancoOld)) {
                servicioBancoNew.getBancosCollection().add(bancos);
                servicioBancoNew = em.merge(servicioBancoNew);
            }
            for (POIs POIsCollectionOldPOIs : POIsCollectionOld) {
                if (!POIsCollectionNew.contains(POIsCollectionOldPOIs)) {
                    POIsCollectionOldPOIs.setCodBanco(null);
                    POIsCollectionOldPOIs = em.merge(POIsCollectionOldPOIs);
                }
            }
            for (POIs POIsCollectionNewPOIs : POIsCollectionNew) {
                if (!POIsCollectionOld.contains(POIsCollectionNewPOIs)) {
                    Bancos oldCodBancoOfPOIsCollectionNewPOIs = POIsCollectionNewPOIs.getCodBanco();
                    POIsCollectionNewPOIs.setCodBanco(bancos);
                    POIsCollectionNewPOIs = em.merge(POIsCollectionNewPOIs);
                    if (oldCodBancoOfPOIsCollectionNewPOIs != null && !oldCodBancoOfPOIsCollectionNewPOIs.equals(bancos)) {
                        oldCodBancoOfPOIsCollectionNewPOIs.getPOIsCollection().remove(POIsCollectionNewPOIs);
                        oldCodBancoOfPOIsCollectionNewPOIs = em.merge(oldCodBancoOfPOIsCollectionNewPOIs);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = bancos.getBancoId();
                if (findBancos(id) == null) {
                    throw new NonexistentEntityException("The bancos with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Bancos bancos;
            try {
                bancos = em.getReference(Bancos.class, id);
                bancos.getBancoId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The bancos with id " + id + " no longer exists.", enfe);
            }
            Servicios servicioBanco = bancos.getServicioBanco();
            if (servicioBanco != null) {
                servicioBanco.getBancosCollection().remove(bancos);
                servicioBanco = em.merge(servicioBanco);
            }
            Collection<POIs> POIsCollection = bancos.getPOIsCollection();
            for (POIs POIsCollectionPOIs : POIsCollection) {
                POIsCollectionPOIs.setCodBanco(null);
                POIsCollectionPOIs = em.merge(POIsCollectionPOIs);
            }
            em.remove(bancos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Bancos> findBancosEntities() {
        return findBancosEntities(true, -1, -1);
    }

    public List<Bancos> findBancosEntities(int maxResults, int firstResult) {
        return findBancosEntities(false, maxResults, firstResult);
    }

    private List<Bancos> findBancosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Bancos.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Bancos findBancos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Bancos.class, id);
        } finally {
            em.close();
        }
    }

    public int getBancosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Bancos> rt = cq.from(Bancos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
