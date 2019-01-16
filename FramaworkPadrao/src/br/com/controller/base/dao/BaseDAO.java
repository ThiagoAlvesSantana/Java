package br.com.controller.base.dao;

import java.io.Serializable;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class BaseDAO implements IBaseDAO {

    private static Transaction transaction;
    private static SessionFactory sessionFactory;
    private static Session session;

    @Override
    public void save(Object object) {
        session = HibernateConnection.getSession();
        transaction = session.beginTransaction();
        try {
            transaction.begin();
            session.save(object);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void saveOrUpdate(Object object) {
        session = HibernateConnection.getSession();
        transaction = session.beginTransaction();
        try {
            transaction.begin();
            session.saveOrUpdate(object);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void update(Object object) {
        session = HibernateConnection.getSession();
        transaction = session.beginTransaction();
        try {
            transaction.begin();
            session.update(object);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(Object object) {
        session = HibernateConnection.getSession();
        transaction = session.beginTransaction();
        try {
            transaction.begin();
            session.delete(object);
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public Object getByID(Object object, Integer id) {
         System.out.println("Class "+object.getClass());
         System.out.println("ID  "+id);
         session.get(object.getClass(),id);
         return null;
    
        
        /*session = HibernateConnection.getSession();
        transaction = session.beginTransaction();
        try {
            transaction.begin();
            
            transaction.commit();
        } catch (HibernateException ex) {
            transaction.rollback();
        } finally {
            session.close();
        }
        return object; */
    }

    @Override
    public void deleteAll() {
        List<Object> objects = findAll();
        objects.forEach((object) -> {
            delete(object);
        });
    }

    @Override
    public List<Object> findAll() {
        return null;
    }

    @Override
    public List<Object> findAllByExample(Object object) {
        return null;
    }

    @Override
    public Object findById(Serializable id) {
        return null;
    }

    @Override
    public void clear() {
        session.clear();
    }

    @Override
    public void flush() {
        session.flush();
    }
}
