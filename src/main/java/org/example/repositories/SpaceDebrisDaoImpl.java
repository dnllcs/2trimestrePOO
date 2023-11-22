package org.example.repositories;

import org.example.Models.SpaceDebris;
import org.example.connection.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class SpaceDebrisDaoImpl implements SpaceDebrisDao{
    private Session session;
    private static SpaceDebrisDaoImpl instance;
    public static synchronized SpaceDebrisDaoImpl getInstance() {
        if(instance == null) {
            instance = new SpaceDebrisDaoImpl();
        }
        return instance;
    }
    public SpaceDebrisDaoImpl() {
        this.session = HibernateUtil.getSession();
    }
    @Override
    public List<SpaceDebris> getAll() {
        return session.createQuery("from SpaceDebris").getResultList();
    }
    @Override
    public SpaceDebris getById(int id) {
        return session.find(SpaceDebris.class, id);
    }
    @Override
    public void insert(SpaceDebris spaceDebris) {
        try {
            session.beginTransaction();
            session.persist(spaceDebris);
            session.getTransaction().commit();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    @Override
    public void update(SpaceDebris spaceDebris) {
        try {
            session.beginTransaction();
            session.merge(spaceDebris);
            session.getTransaction().commit();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(SpaceDebris spaceDebris) {
        try {
            session.beginTransaction();
            session.remove(spaceDebris);
            session.getTransaction().commit();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
