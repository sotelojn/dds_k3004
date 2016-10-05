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
public class ServiciosJpaController implements Serializable {

    public ServiciosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Servicios servicios) throws PreexistingEntityException, Exception {
        if (servicios.getBancosCollection() == null) {
            servicios.setBancosCollection(new ArrayList<Bancos>());
        }
        if (servicios.getCgpCollection() == null) {
            servicios.setCgpCollection(new ArrayList<Cgp>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Bancos> attachedBancosCollection = new ArrayList<Bancos>();
            for (Bancos bancosCollectionBancosToAttach : servicios.getBancosCollection()) {
                bancosCollectionBancosToAttach = em.getReference(bancosCollectionBancosToAttach.getClass(), bancosCollectionBancosToAttach.getBancoId());
                attachedBancosCollection.add(bancosCollectionBancosToAttach);
            }
            servicios.setBancosCollection(attachedBancosCollection);
            Collection<Cgp> attachedCgpCollection = new ArrayList<Cgp>();
            for (Cgp cgpCollectionCgpToAttach : servicios.getCgpCollection()) {
                cgpCollectionCgpToAttach = em.getReference(cgpCollectionCgpToAttach.getClass(), cgpCollectionCgpToAttach.getCgpId());
                attachedCgpCollection.add(cgpCollectionCgpToAttach);
            }
            servicios.setCgpCollection(attachedCgpCollection);
            em.persist(servicios);
            for (Bancos bancosCollectionBancos : servicios.getBancosCollection()) {
                Servicios oldServicioBancoOfBancosCollectionBancos = bancosCollectionBancos.getServicioBanco();
                bancosCollectionBancos.setServicioBanco(servicios);
                bancosCollectionBancos = em.merge(bancosCollectionBancos);
                if (oldServicioBancoOfBancosCollectionBancos != null) {
                    oldServicioBancoOfBancosCollectionBancos.getBancosCollection().remove(bancosCollectionBancos);
                    oldServicioBancoOfBancosCollectionBancos = em.merge(oldServicioBancoOfBancosCollectionBancos);
                }
            }
            for (Cgp cgpCollectionCgp : servicios.getCgpCollection()) {
                Servicios oldServicioOfCgpCollectionCgp = cgpCollectionCgp.getServicio();
                cgpCollectionCgp.setServicio(servicios);
                cgpCollectionCgp = em.merge(cgpCollectionCgp);
                if (oldServicioOfCgpCollectionCgp != null) {
                    oldServicioOfCgpCollectionCgp.getCgpCollection().remove(cgpCollectionCgp);
                    oldServicioOfCgpCollectionCgp = em.merge(oldServicioOfCgpCollectionCgp);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findServicios(servicios.getCodigoServicio()) != null) {
                throw new PreexistingEntityException("Servicios " + servicios + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Servicios servicios) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Servicios persistentServicios = em.find(Servicios.class, servicios.getCodigoServicio());
            Collection<Bancos> bancosCollectionOld = persistentServicios.getBancosCollection();
            Collection<Bancos> bancosCollectionNew = servicios.getBancosCollection();
            Collection<Cgp> cgpCollectionOld = persistentServicios.getCgpCollection();
            Collection<Cgp> cgpCollectionNew = servicios.getCgpCollection();
            Collection<Bancos> attachedBancosCollectionNew = new ArrayList<Bancos>();
            for (Bancos bancosCollectionNewBancosToAttach : bancosCollectionNew) {
                bancosCollectionNewBancosToAttach = em.getReference(bancosCollectionNewBancosToAttach.getClass(), bancosCollectionNewBancosToAttach.getBancoId());
                attachedBancosCollectionNew.add(bancosCollectionNewBancosToAttach);
            }
            bancosCollectionNew = attachedBancosCollectionNew;
            servicios.setBancosCollection(bancosCollectionNew);
            Collection<Cgp> attachedCgpCollectionNew = new ArrayList<Cgp>();
            for (Cgp cgpCollectionNewCgpToAttach : cgpCollectionNew) {
                cgpCollectionNewCgpToAttach = em.getReference(cgpCollectionNewCgpToAttach.getClass(), cgpCollectionNewCgpToAttach.getCgpId());
                attachedCgpCollectionNew.add(cgpCollectionNewCgpToAttach);
            }
            cgpCollectionNew = attachedCgpCollectionNew;
            servicios.setCgpCollection(cgpCollectionNew);
            servicios = em.merge(servicios);
            for (Bancos bancosCollectionOldBancos : bancosCollectionOld) {
                if (!bancosCollectionNew.contains(bancosCollectionOldBancos)) {
                    bancosCollectionOldBancos.setServicioBanco(null);
                    bancosCollectionOldBancos = em.merge(bancosCollectionOldBancos);
                }
            }
            for (Bancos bancosCollectionNewBancos : bancosCollectionNew) {
                if (!bancosCollectionOld.contains(bancosCollectionNewBancos)) {
                    Servicios oldServicioBancoOfBancosCollectionNewBancos = bancosCollectionNewBancos.getServicioBanco();
                    bancosCollectionNewBancos.setServicioBanco(servicios);
                    bancosCollectionNewBancos = em.merge(bancosCollectionNewBancos);
                    if (oldServicioBancoOfBancosCollectionNewBancos != null && !oldServicioBancoOfBancosCollectionNewBancos.equals(servicios)) {
                        oldServicioBancoOfBancosCollectionNewBancos.getBancosCollection().remove(bancosCollectionNewBancos);
                        oldServicioBancoOfBancosCollectionNewBancos = em.merge(oldServicioBancoOfBancosCollectionNewBancos);
                    }
                }
            }
            for (Cgp cgpCollectionOldCgp : cgpCollectionOld) {
                if (!cgpCollectionNew.contains(cgpCollectionOldCgp)) {
                    cgpCollectionOldCgp.setServicio(null);
                    cgpCollectionOldCgp = em.merge(cgpCollectionOldCgp);
                }
            }
            for (Cgp cgpCollectionNewCgp : cgpCollectionNew) {
                if (!cgpCollectionOld.contains(cgpCollectionNewCgp)) {
                    Servicios oldServicioOfCgpCollectionNewCgp = cgpCollectionNewCgp.getServicio();
                    cgpCollectionNewCgp.setServicio(servicios);
                    cgpCollectionNewCgp = em.merge(cgpCollectionNewCgp);
                    if (oldServicioOfCgpCollectionNewCgp != null && !oldServicioOfCgpCollectionNewCgp.equals(servicios)) {
                        oldServicioOfCgpCollectionNewCgp.getCgpCollection().remove(cgpCollectionNewCgp);
                        oldServicioOfCgpCollectionNewCgp = em.merge(oldServicioOfCgpCollectionNewCgp);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = servicios.getCodigoServicio();
                if (findServicios(id) == null) {
                    throw new NonexistentEntityException("The servicios with id " + id + " no longer exists.");
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
            Servicios servicios;
            try {
                servicios = em.getReference(Servicios.class, id);
                servicios.getCodigoServicio();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The servicios with id " + id + " no longer exists.", enfe);
            }
            Collection<Bancos> bancosCollection = servicios.getBancosCollection();
            for (Bancos bancosCollectionBancos : bancosCollection) {
                bancosCollectionBancos.setServicioBanco(null);
                bancosCollectionBancos = em.merge(bancosCollectionBancos);
            }
            Collection<Cgp> cgpCollection = servicios.getCgpCollection();
            for (Cgp cgpCollectionCgp : cgpCollection) {
                cgpCollectionCgp.setServicio(null);
                cgpCollectionCgp = em.merge(cgpCollectionCgp);
            }
            em.remove(servicios);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Servicios> findServiciosEntities() {
        return findServiciosEntities(true, -1, -1);
    }

    public List<Servicios> findServiciosEntities(int maxResults, int firstResult) {
        return findServiciosEntities(false, maxResults, firstResult);
    }

    private List<Servicios> findServiciosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Servicios.class));
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

    public Servicios findServicios(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Servicios.class, id);
        } finally {
            em.close();
        }
    }

    public int getServiciosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Servicios> rt = cq.from(Servicios.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
