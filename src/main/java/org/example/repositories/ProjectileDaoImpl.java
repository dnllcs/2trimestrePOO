package org.example.repositories;

import org.example.Models.Projectile;
import org.example.connection.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class ProjectileDaoImpl implements ProjectileDao{
    private Session session;
    public ProjectileDaoImpl() {
        this.session = HibernateUtil.getSession();
    }

    @Override
    public List<Projectile> getAll() {
        return session.createQuery("from Projectile").getResultList();
    }

    @Override
    public Projectile getById(int id) {
        return session.find(Projectile.class, id);
    }

    @Override
    public void insert(Projectile projectile) {
        try {
            session.beginTransaction();
            session.persist(projectile);
            session.getTransaction().commit();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    @Override
    public void update(Projectile projectile) {
        try {
            session.beginTransaction();
            session.merge(projectile);
            session.getTransaction().commit();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(Projectile projectile) {
        try {
            session.beginTransaction();
            session.remove(projectile);
            session.getTransaction().commit();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
