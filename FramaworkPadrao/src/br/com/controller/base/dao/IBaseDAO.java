package br.com.controller.base.dao;

import java.io.Serializable;
import java.util.List;

public interface IBaseDAO {

    public void save(Object object);

    public void saveOrUpdate(Object object);

    public void update(Object object);

    public void delete(Object object);

    public Object getByID(Object object, Integer id);

    public void deleteAll();

    public List<Object> findAll();

    public List<Object> findAllByExample(Object object);

    public Object findById(Serializable id);

    public void clear();

    public void flush();
}
