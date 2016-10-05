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
public class LocalComercialJpaController implements Serializable {

    public LocalComercialJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(LocalComercial localComercial) throws PreexistingEntityException, Exception {
        if (localComercial.getPOIsCollection() == null) {
            localComercial.setPOIsCollection(new ArrayList<POIs>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<POIs> attachedPOIsCollection = new ArrayList<POIs>();
            for (POIs POIsCollectionPOIsToAttach : localComercial.getPOIsCollection()) {
                POIsCollectionPOIsToAttach = em.getReference(POIsCollectionPOIsToAttach.getClass(), POIsCollectionPOIsToAttach.getPoiId());
                attachedPOIsCollection.add(POIsCollectionPOIsToAttach);
            }
            localComercial.setPOIsCollection(attachedPOIsCollection);
            em.persist(localComercial);
            for (POIs POIsCollectionPOIs : localComercial.getPOIsCollection()) {
                LocalComercial oldCodLocalOfPOIsCollectionPOIs = POIsCollectionPOIs.getCodLocal();
                POIsCollectionPOIs.setCodLocal(localComercial);
                POIsCollectionPOIs = em.merge(POIsCollectionPOIs);
                if (oldCodLocalOfPOIsCollectionPOIs != null) {
                    oldCodLocalOfPOIsCollectionPOIs.getPOIsCollection().remove(POIsCollectionPOIs);
                    oldCodLocalOfPOIsCollectionPOIs = em.merge(oldCodLocalOfPOIsCollectionPOIs);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findLocalComercial(localComercial.getLocalId()) != null) {
                throw new PreexistingEntityException("LocalComercial " + localComercial + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(LocalComercial localComercial) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            LocalComercial persistentLocalComercial = em.find(LocalComercial.class, localComercial.getLocalId());
            Collection<POIs> POIsCollectionOld = persistentLocalComercial.getPOIsCollection();
            Collection<POIs> POIsCollectionNew = localComercial.getPOIsCollection();
            Collection<POIs> attachedPOIsCollectionNew = new ArrayList<POIs>();
            for (POIs POIsCollectionNewPOIsToAttach : POIsCollectionNew) {
                POIsCollectionNewPOIsToAttach = em.getReference(POIsCollectionNewPOIsToAttach.getClass(), POIsCollectionNewPOIsToAttach.getPoiId());
                attachedPOIsCollectionNew.add(POIsCollectionNewPOIsToAttach);
            }
            POIsCollectionNew = attachedPOIsCollectionNew;
            localComercial.setPOIsCollection(POIsCollectionNew);
            localComercial = em.merge(localComercial);
            for (POIs POIsCollectionOldPOIs : POIsCollectionOld) {
                if (!POIsCollectionNew.contains(POIsCollectionOldPOIs)) {
                    POIsCollectionOldPOIs.setCodLocal(null);
                    POIsCollectionOldPOIs = em.merge(POIsCollectionOldPOIs);
                }
            }
            for (POIs POIsCollectionNewPOIs : POIsCollectionNew) {
                if (!POIsCollectionOld.contains(POIsCollectionNewPOIs)) {
                    LocalComercial oldCodLocalOfPOIsCollectionNewPOIs = POIsCollectionNewPOIs.getCodLocal();
                    POIsCollectionNewPOIs.setCodLocal(localComercial);
                    POIsCollectionNewPOIs = em.merge(POIsCollectionNewPOIs);
                    if (oldCodLocalOfPOIsCollectionNewPOIs != null && !oldCodLocalOfPOIsCollectionNewPOIs.equals(localComercial)) {
                        oldCodLocalOfPOIsCollectionNewPOIs.getPOIsCollection().remove(POIsCollectionNewPOIs);
                        oldCodLocalOfPOIsCollectionNewPOIs = em.merge(oldCodLocalOfPOIsCollectionNewPOIs);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = localComercial.getLocalId();
                if (findLocalComercial(id) == null) {
                    throw new NonexistentEntityException("The localComercial with id " + id + " no longer exists.");
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
            LocalComercial localComercial;
            try {
                localComercial = em.getReference(LocalComercial.class, id);
                localComercial.getLocalId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The localComercial with id " + id + " no longer exists.", enfe);
            }
            Collection<POIs> POIsCollection = localComercial.getPOIsCollection();
            for (POIs POIsCollectionPOIs : POIsCollection) {
                POIsCollectionPOIs.setCodLocal(null);
                POIsCollectionPOIs = em.merge(POIsCollectionPOIs);
            }
            em.remove(localComercial);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<LocalComercial> findLocalComercialEntities() {
        return findLocalComercialEntities(true, -1, -1);
    }

    public List<LocalComercial> findLocalComercialEntities(int maxResults, int firstResult) {
        return findLocalComercialEntities(false, maxResults, firstResult);
    }

    private List<LocalComercial> findLocalComercialEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(LocalComercial.class));
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

    public LocalComercial findLocalComercial(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(LocalComercial.class, id);
        } finally {
            em.close();
        }
    }

    public int getLocalComercialCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<LocalComercial> rt = cq.from(LocalComercial.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
