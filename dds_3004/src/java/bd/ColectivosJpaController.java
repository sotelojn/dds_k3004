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
public class ColectivosJpaController implements Serializable {

    public ColectivosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Colectivos colectivos) throws PreexistingEntityException, Exception {
        if (colectivos.getPOIsCollection() == null) {
            colectivos.setPOIsCollection(new ArrayList<POIs>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<POIs> attachedPOIsCollection = new ArrayList<POIs>();
            for (POIs POIsCollectionPOIsToAttach : colectivos.getPOIsCollection()) {
                POIsCollectionPOIsToAttach = em.getReference(POIsCollectionPOIsToAttach.getClass(), POIsCollectionPOIsToAttach.getPoiId());
                attachedPOIsCollection.add(POIsCollectionPOIsToAttach);
            }
            colectivos.setPOIsCollection(attachedPOIsCollection);
            em.persist(colectivos);
            for (POIs POIsCollectionPOIs : colectivos.getPOIsCollection()) {
                Colectivos oldCodColectivoOfPOIsCollectionPOIs = POIsCollectionPOIs.getCodColectivo();
                POIsCollectionPOIs.setCodColectivo(colectivos);
                POIsCollectionPOIs = em.merge(POIsCollectionPOIs);
                if (oldCodColectivoOfPOIsCollectionPOIs != null) {
                    oldCodColectivoOfPOIsCollectionPOIs.getPOIsCollection().remove(POIsCollectionPOIs);
                    oldCodColectivoOfPOIsCollectionPOIs = em.merge(oldCodColectivoOfPOIsCollectionPOIs);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findColectivos(colectivos.getColectivoId()) != null) {
                throw new PreexistingEntityException("Colectivos " + colectivos + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Colectivos colectivos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Colectivos persistentColectivos = em.find(Colectivos.class, colectivos.getColectivoId());
            Collection<POIs> POIsCollectionOld = persistentColectivos.getPOIsCollection();
            Collection<POIs> POIsCollectionNew = colectivos.getPOIsCollection();
            Collection<POIs> attachedPOIsCollectionNew = new ArrayList<POIs>();
            for (POIs POIsCollectionNewPOIsToAttach : POIsCollectionNew) {
                POIsCollectionNewPOIsToAttach = em.getReference(POIsCollectionNewPOIsToAttach.getClass(), POIsCollectionNewPOIsToAttach.getPoiId());
                attachedPOIsCollectionNew.add(POIsCollectionNewPOIsToAttach);
            }
            POIsCollectionNew = attachedPOIsCollectionNew;
            colectivos.setPOIsCollection(POIsCollectionNew);
            colectivos = em.merge(colectivos);
            for (POIs POIsCollectionOldPOIs : POIsCollectionOld) {
                if (!POIsCollectionNew.contains(POIsCollectionOldPOIs)) {
                    POIsCollectionOldPOIs.setCodColectivo(null);
                    POIsCollectionOldPOIs = em.merge(POIsCollectionOldPOIs);
                }
            }
            for (POIs POIsCollectionNewPOIs : POIsCollectionNew) {
                if (!POIsCollectionOld.contains(POIsCollectionNewPOIs)) {
                    Colectivos oldCodColectivoOfPOIsCollectionNewPOIs = POIsCollectionNewPOIs.getCodColectivo();
                    POIsCollectionNewPOIs.setCodColectivo(colectivos);
                    POIsCollectionNewPOIs = em.merge(POIsCollectionNewPOIs);
                    if (oldCodColectivoOfPOIsCollectionNewPOIs != null && !oldCodColectivoOfPOIsCollectionNewPOIs.equals(colectivos)) {
                        oldCodColectivoOfPOIsCollectionNewPOIs.getPOIsCollection().remove(POIsCollectionNewPOIs);
                        oldCodColectivoOfPOIsCollectionNewPOIs = em.merge(oldCodColectivoOfPOIsCollectionNewPOIs);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = colectivos.getColectivoId();
                if (findColectivos(id) == null) {
                    throw new NonexistentEntityException("The colectivos with id " + id + " no longer exists.");
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
            Colectivos colectivos;
            try {
                colectivos = em.getReference(Colectivos.class, id);
                colectivos.getColectivoId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The colectivos with id " + id + " no longer exists.", enfe);
            }
            Collection<POIs> POIsCollection = colectivos.getPOIsCollection();
            for (POIs POIsCollectionPOIs : POIsCollection) {
                POIsCollectionPOIs.setCodColectivo(null);
                POIsCollectionPOIs = em.merge(POIsCollectionPOIs);
            }
            em.remove(colectivos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Colectivos> findColectivosEntities() {
        return findColectivosEntities(true, -1, -1);
    }

    public List<Colectivos> findColectivosEntities(int maxResults, int firstResult) {
        return findColectivosEntities(false, maxResults, firstResult);
    }

    private List<Colectivos> findColectivosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Colectivos.class));
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

    public Colectivos findColectivos(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Colectivos.class, id);
        } finally {
            em.close();
        }
    }

    public int getColectivosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Colectivos> rt = cq.from(Colectivos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
