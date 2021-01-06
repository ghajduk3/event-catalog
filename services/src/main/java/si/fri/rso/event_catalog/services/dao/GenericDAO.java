package si.fri.rso.event_catalog.services.dao;



import si.fri.rso.event_catalog.services.exceptions.*;


import javax.inject.Inject;
import javax.persistence.*;
import javax.persistence.EntityNotFoundException;
import javax.ws.rs.InternalServerErrorException;
import java.util.List;

public abstract class GenericDAO<E,PK> {

    @Inject
    protected EntityManager em;

    protected Class<? extends E> entityClass;

    public GenericDAO() {

    }

    public GenericDAO(Class<? extends E> entityClass) {
        this.entityClass = entityClass;
    }

    public List<E> findAll() throws UnsupportedOperationException{
        throw new UnsupportedOperationException();
    }

    public E findById(PK id) throws EntityNotFoundException, InternalServerException {
        E entity;

        try {
            entity = em.find(entityClass, id);
        } catch (javax.persistence.EntityNotFoundException | NoResultException e) {
            throw new EntityNotFoundException("Entity was not found");
        } catch (Exception e) {
            e.printStackTrace();
            throw new InternalServerException("Something happened with the server" + e.getMessage());
        }

        return entity;
    }

    public E createNew(E instance) throws EntityExistsException,InvalidEntityException,Exception {
        try{
            beginTx();
            em.persist(instance);
            em.flush();
            commitTx();
        }catch (Exception e){
            rollbackTx();
            throw new InternalServerErrorException();
        }
        if(instance == null){
            throw new InvalidEntityException();
        }
        return instance;
    }

    public E update(E instance, PK id) throws UnsupportedOperationException, InvalidEntityException, InternalServerException {
        throw new UnsupportedOperationException();
    }

    public Boolean deleteById(PK id) throws UnsupportedOperationException, InvalidParameterException, InternalServerException {
        E entity = findById(id);
        if(entity != null){
            try{
                beginTx();
                em.remove(entity);
                commitTx();
            }catch (Exception e){
                rollbackTx();
                throw new InternalServerException();
            }
        }else{
            return false;
        }
        return true;
    }


    protected void beginTx() {
        if (!em.getTransaction().isActive()) {
            em.getTransaction().begin();
        }
    }

    protected void commitTx() {
        if (em.getTransaction().isActive()) {
            em.getTransaction().commit();
        }
    }

    protected void rollbackTx() {
        if (em.getTransaction().isActive()) {
            em.getTransaction().rollback();
        }

    }
}
