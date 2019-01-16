package br.com.controller.base.bo;

import br.com.controller.base.dao.BaseDAO;
import java.io.Serializable;
import java.util.List;

public class BaseBO extends BaseDAO {

    private BaseDAO dao;

    public BaseDAO getDAO() {
        if (dao == null) {
            dao = new BaseDAO();
        }
        return dao;
    }

    @Override
    public void save(Object object) {
        getDAO().save(object);
    }

    @Override
    public void saveOrUpdate(Object object) {
        getDAO().saveOrUpdate(object);
    }

    @Override
    public void update(Object object) {
        getDAO().update(object);
    }

    @Override
    public void delete(Object object) {
        getDAO().delete(object);
    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Object getByID(Object object, Integer id) {
        return getDAO().getByID(object, id);
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
}
