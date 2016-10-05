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
public class UsuariosJpaController implements Serializable {

    public UsuariosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuarios usuarios) throws PreexistingEntityException, Exception {
        if (usuarios.getRolCollection() == null) {
            usuarios.setRolCollection(new ArrayList<Rol>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Rol> attachedRolCollection = new ArrayList<Rol>();
            for (Rol rolCollectionRolToAttach : usuarios.getRolCollection()) {
                rolCollectionRolToAttach = em.getReference(rolCollectionRolToAttach.getClass(), rolCollectionRolToAttach.getRolId());
                attachedRolCollection.add(rolCollectionRolToAttach);
            }
            usuarios.setRolCollection(attachedRolCollection);
            em.persist(usuarios);
            for (Rol rolCollectionRol : usuarios.getRolCollection()) {
                rolCollectionRol.getUsuariosCollection().add(usuarios);
                rolCollectionRol = em.merge(rolCollectionRol);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findUsuarios(usuarios.getUsuarioId()) != null) {
                throw new PreexistingEntityException("Usuarios " + usuarios + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuarios usuarios) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuarios persistentUsuarios = em.find(Usuarios.class, usuarios.getUsuarioId());
            Collection<Rol> rolCollectionOld = persistentUsuarios.getRolCollection();
            Collection<Rol> rolCollectionNew = usuarios.getRolCollection();
            Collection<Rol> attachedRolCollectionNew = new ArrayList<Rol>();
            for (Rol rolCollectionNewRolToAttach : rolCollectionNew) {
                rolCollectionNewRolToAttach = em.getReference(rolCollectionNewRolToAttach.getClass(), rolCollectionNewRolToAttach.getRolId());
                attachedRolCollectionNew.add(rolCollectionNewRolToAttach);
            }
            rolCollectionNew = attachedRolCollectionNew;
            usuarios.setRolCollection(rolCollectionNew);
            usuarios = em.merge(usuarios);
            for (Rol rolCollectionOldRol : rolCollectionOld) {
                if (!rolCollectionNew.contains(rolCollectionOldRol)) {
                    rolCollectionOldRol.getUsuariosCollection().remove(usuarios);
                    rolCollectionOldRol = em.merge(rolCollectionOldRol);
                }
            }
            for (Rol rolCollectionNewRol : rolCollectionNew) {
                if (!rolCollectionOld.contains(rolCollectionNewRol)) {
                    rolCollectionNewRol.getUsuariosCollection().add(usuarios);
                    rolCollectionNewRol = em.merge(rolCollectionNewRol);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = usuarios.getUsuarioId();
                if (findUsuarios(id) == null) {
                    throw new NonexistentEntityException("The usuarios with id " + id + " no longer exists.");
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
            Usuarios usuarios;
            try {
                usuarios = em.getReference(Usuarios.class, id);
                usuarios.getUsuarioId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuarios with id " + id + " no longer exists.", enfe);
            }
            Collection<Rol> rolCollection = usuarios.getRolCollection();
            for (Rol rolCollectionRol : rolCollection) {
                rolCollectionRol.getUsuariosCollection().remove(usuarios);
                rolCollectionRol = em.merge(rolCollectionRol);
            }
            em.remove(usuarios);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuarios> findUsuariosEntities() {
        return findUsuariosEntities(true, -1, -1);
    }

    public List<Usuarios> findUsuariosEntities(int maxResults, int firstResult) {
        return findUsuariosEntities(false, maxResults, firstResult);
    }

    private List<Usuarios> findUsuariosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuarios.class));
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

    public Usuarios findUsuarios(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuarios.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuariosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuarios> rt = cq.from(Usuarios.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
