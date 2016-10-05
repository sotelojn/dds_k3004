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
public class CgpJpaController implements Serializable {

    public CgpJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Cgp cgp) throws PreexistingEntityException, Exception {
        if (cgp.getPOIsCollection() == null) {
            cgp.setPOIsCollection(new ArrayList<POIs>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Servicios servicio = cgp.getServicio();
            if (servicio != null) {
                servicio = em.getReference(servicio.getClass(), servicio.getCodigoServicio());
                cgp.setServicio(servicio);
            }
            Collection<POIs> attachedPOIsCollection = new ArrayList<POIs>();
            for (POIs POIsCollectionPOIsToAttach : cgp.getPOIsCollection()) {
                POIsCollectionPOIsToAttach = em.getReference(POIsCollectionPOIsToAttach.getClass(), POIsCollectionPOIsToAttach.getPoiId());
                attachedPOIsCollection.add(POIsCollectionPOIsToAttach);
            }
            cgp.setPOIsCollection(attachedPOIsCollection);
            em.persist(cgp);
            if (servicio != null) {
                servicio.getCgpCollection().add(cgp);
                servicio = em.merge(servicio);
            }
            for (POIs POIsCollectionPOIs : cgp.getPOIsCollection()) {
                Cgp oldCodCgpOfPOIsCollectionPOIs = POIsCollectionPOIs.getCodCgp();
                POIsCollectionPOIs.setCodCgp(cgp);
                POIsCollectionPOIs = em.merge(POIsCollectionPOIs);
                if (oldCodCgpOfPOIsCollectionPOIs != null) {
                    oldCodCgpOfPOIsCollectionPOIs.getPOIsCollection().remove(POIsCollectionPOIs);
                    oldCodCgpOfPOIsCollectionPOIs = em.merge(oldCodCgpOfPOIsCollectionPOIs);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findCgp(cgp.getCgpId()) != null) {
                throw new PreexistingEntityException("Cgp " + cgp + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Cgp cgp) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Cgp persistentCgp = em.find(Cgp.class, cgp.getCgpId());
            Servicios servicioOld = persistentCgp.getServicio();
            Servicios servicioNew = cgp.getServicio();
            Collection<POIs> POIsCollectionOld = persistentCgp.getPOIsCollection();
            Collection<POIs> POIsCollectionNew = cgp.getPOIsCollection();
            if (servicioNew != null) {
                servicioNew = em.getReference(servicioNew.getClass(), servicioNew.getCodigoServicio());
                cgp.setServicio(servicioNew);
            }
            Collection<POIs> attachedPOIsCollectionNew = new ArrayList<POIs>();
            for (POIs POIsCollectionNewPOIsToAttach : POIsCollectionNew) {
                POIsCollectionNewPOIsToAttach = em.getReference(POIsCollectionNewPOIsToAttach.getClass(), POIsCollectionNewPOIsToAttach.getPoiId());
                attachedPOIsCollectionNew.add(POIsCollectionNewPOIsToAttach);
            }
            POIsCollectionNew = attachedPOIsCollectionNew;
            cgp.setPOIsCollection(POIsCollectionNew);
            cgp = em.merge(cgp);
            if (servicioOld != null && !servicioOld.equals(servicioNew)) {
                servicioOld.getCgpCollection().remove(cgp);
                servicioOld = em.merge(servicioOld);
            }
            if (servicioNew != null && !servicioNew.equals(servicioOld)) {
                servicioNew.getCgpCollection().add(cgp);
                servicioNew = em.merge(servicioNew);
            }
            for (POIs POIsCollectionOldPOIs : POIsCollectionOld) {
                if (!POIsCollectionNew.contains(POIsCollectionOldPOIs)) {
                    POIsCollectionOldPOIs.setCodCgp(null);
                    POIsCollectionOldPOIs = em.merge(POIsCollectionOldPOIs);
                }
            }
            for (POIs POIsCollectionNewPOIs : POIsCollectionNew) {
                if (!POIsCollectionOld.contains(POIsCollectionNewPOIs)) {
                    Cgp oldCodCgpOfPOIsCollectionNewPOIs = POIsCollectionNewPOIs.getCodCgp();
                    POIsCollectionNewPOIs.setCodCgp(cgp);
                    POIsCollectionNewPOIs = em.merge(POIsCollectionNewPOIs);
                    if (oldCodCgpOfPOIsCollectionNewPOIs != null && !oldCodCgpOfPOIsCollectionNewPOIs.equals(cgp)) {
                        oldCodCgpOfPOIsCollectionNewPOIs.getPOIsCollection().remove(POIsCollectionNewPOIs);
                        oldCodCgpOfPOIsCollectionNewPOIs = em.merge(oldCodCgpOfPOIsCollectionNewPOIs);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cgp.getCgpId();
                if (findCgp(id) == null) {
                    throw new NonexistentEntityException("The cgp with id " + id + " no longer exists.");
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
            Cgp cgp;
            try {
                cgp = em.getReference(Cgp.class, id);
                cgp.getCgpId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cgp with id " + id + " no longer exists.", enfe);
            }
            Servicios servicio = cgp.getServicio();
            if (servicio != null) {
                servicio.getCgpCollection().remove(cgp);
                servicio = em.merge(servicio);
            }
            Collection<POIs> POIsCollection = cgp.getPOIsCollection();
            for (POIs POIsCollectionPOIs : POIsCollection) {
                POIsCollectionPOIs.setCodCgp(null);
                POIsCollectionPOIs = em.merge(POIsCollectionPOIs);
            }
            em.remove(cgp);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Cgp> findCgpEntities() {
        return findCgpEntities(true, -1, -1);
    }

    public List<Cgp> findCgpEntities(int maxResults, int firstResult) {
        return findCgpEntities(false, maxResults, firstResult);
    }

    private List<Cgp> findCgpEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Cgp.class));
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

    public Cgp findCgp(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Cgp.class, id);
        } finally {
            em.close();
        }
    }

    public int getCgpCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Cgp> rt = cq.from(Cgp.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
