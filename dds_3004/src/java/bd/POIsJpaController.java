/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

import bd.exceptions.NonexistentEntityException;
import bd.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author sebaaz
 */
public class POIsJpaController implements Serializable {

    public POIsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(POIs POIs) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Bancos codBanco = POIs.getCodBanco();
            if (codBanco != null) {
                codBanco = em.getReference(codBanco.getClass(), codBanco.getBancoId());
                POIs.setCodBanco(codBanco);
            }
            Cgp codCgp = POIs.getCodCgp();
            if (codCgp != null) {
                codCgp = em.getReference(codCgp.getClass(), codCgp.getCgpId());
                POIs.setCodCgp(codCgp);
            }
            Colectivos codColectivo = POIs.getCodColectivo();
            if (codColectivo != null) {
                codColectivo = em.getReference(codColectivo.getClass(), codColectivo.getColectivoId());
                POIs.setCodColectivo(codColectivo);
            }
            LocalComercial codLocal = POIs.getCodLocal();
            if (codLocal != null) {
                codLocal = em.getReference(codLocal.getClass(), codLocal.getLocalId());
                POIs.setCodLocal(codLocal);
            }
            em.persist(POIs);
            if (codBanco != null) {
                codBanco.getPOIsCollection().add(POIs);
                codBanco = em.merge(codBanco);
            }
            if (codCgp != null) {
                codCgp.getPOIsCollection().add(POIs);
                codCgp = em.merge(codCgp);
            }
            if (codColectivo != null) {
                codColectivo.getPOIsCollection().add(POIs);
                codColectivo = em.merge(codColectivo);
            }
            if (codLocal != null) {
                codLocal.getPOIsCollection().add(POIs);
                codLocal = em.merge(codLocal);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPOIs(POIs.getPoiId()) != null) {
                throw new PreexistingEntityException("POIs " + POIs + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(POIs POIs) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            POIs persistentPOIs = em.find(POIs.class, POIs.getPoiId());
            Bancos codBancoOld = persistentPOIs.getCodBanco();
            Bancos codBancoNew = POIs.getCodBanco();
            Cgp codCgpOld = persistentPOIs.getCodCgp();
            Cgp codCgpNew = POIs.getCodCgp();
            Colectivos codColectivoOld = persistentPOIs.getCodColectivo();
            Colectivos codColectivoNew = POIs.getCodColectivo();
            LocalComercial codLocalOld = persistentPOIs.getCodLocal();
            LocalComercial codLocalNew = POIs.getCodLocal();
            if (codBancoNew != null) {
                codBancoNew = em.getReference(codBancoNew.getClass(), codBancoNew.getBancoId());
                POIs.setCodBanco(codBancoNew);
            }
            if (codCgpNew != null) {
                codCgpNew = em.getReference(codCgpNew.getClass(), codCgpNew.getCgpId());
                POIs.setCodCgp(codCgpNew);
            }
            if (codColectivoNew != null) {
                codColectivoNew = em.getReference(codColectivoNew.getClass(), codColectivoNew.getColectivoId());
                POIs.setCodColectivo(codColectivoNew);
            }
            if (codLocalNew != null) {
                codLocalNew = em.getReference(codLocalNew.getClass(), codLocalNew.getLocalId());
                POIs.setCodLocal(codLocalNew);
            }
            POIs = em.merge(POIs);
            if (codBancoOld != null && !codBancoOld.equals(codBancoNew)) {
                codBancoOld.getPOIsCollection().remove(POIs);
                codBancoOld = em.merge(codBancoOld);
            }
            if (codBancoNew != null && !codBancoNew.equals(codBancoOld)) {
                codBancoNew.getPOIsCollection().add(POIs);
                codBancoNew = em.merge(codBancoNew);
            }
            if (codCgpOld != null && !codCgpOld.equals(codCgpNew)) {
                codCgpOld.getPOIsCollection().remove(POIs);
                codCgpOld = em.merge(codCgpOld);
            }
            if (codCgpNew != null && !codCgpNew.equals(codCgpOld)) {
                codCgpNew.getPOIsCollection().add(POIs);
                codCgpNew = em.merge(codCgpNew);
            }
            if (codColectivoOld != null && !codColectivoOld.equals(codColectivoNew)) {
                codColectivoOld.getPOIsCollection().remove(POIs);
                codColectivoOld = em.merge(codColectivoOld);
            }
            if (codColectivoNew != null && !codColectivoNew.equals(codColectivoOld)) {
                codColectivoNew.getPOIsCollection().add(POIs);
                codColectivoNew = em.merge(codColectivoNew);
            }
            if (codLocalOld != null && !codLocalOld.equals(codLocalNew)) {
                codLocalOld.getPOIsCollection().remove(POIs);
                codLocalOld = em.merge(codLocalOld);
            }
            if (codLocalNew != null && !codLocalNew.equals(codLocalOld)) {
                codLocalNew.getPOIsCollection().add(POIs);
                codLocalNew = em.merge(codLocalNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = POIs.getPoiId();
                if (findPOIs(id) == null) {
                    throw new NonexistentEntityException("The pOIs with id " + id + " no longer exists.");
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
            POIs POIs;
            try {
                POIs = em.getReference(POIs.class, id);
                POIs.getPoiId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The POIs with id " + id + " no longer exists.", enfe);
            }
            Bancos codBanco = POIs.getCodBanco();
            if (codBanco != null) {
                codBanco.getPOIsCollection().remove(POIs);
                codBanco = em.merge(codBanco);
            }
            Cgp codCgp = POIs.getCodCgp();
            if (codCgp != null) {
                codCgp.getPOIsCollection().remove(POIs);
                codCgp = em.merge(codCgp);
            }
            Colectivos codColectivo = POIs.getCodColectivo();
            if (codColectivo != null) {
                codColectivo.getPOIsCollection().remove(POIs);
                codColectivo = em.merge(codColectivo);
            }
            LocalComercial codLocal = POIs.getCodLocal();
            if (codLocal != null) {
                codLocal.getPOIsCollection().remove(POIs);
                codLocal = em.merge(codLocal);
            }
            em.remove(POIs);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<POIs> findPOIsEntities() {
        return findPOIsEntities(true, -1, -1);
    }

    public List<POIs> findPOIsEntities(int maxResults, int firstResult) {
        return findPOIsEntities(false, maxResults, firstResult);
    }

    private List<POIs> findPOIsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(POIs.class));
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

    public POIs findPOIs(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(POIs.class, id);
        } finally {
            em.close();
        }
    }

    public int getPOIsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<POIs> rt = cq.from(POIs.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
