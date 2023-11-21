package org.example.repositories;

import org.example.Models.Enemy;
import org.example.connection.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class EnemyDaoImpl implements EnemyDao{
    private Session session;
    public EnemyDaoImpl() {
        this.session = HibernateUtil.getSession();
    }

    @Override
    public List<Enemy> getAll() {
        return session.createQuery("from Enemy").getResultList();
    }

    @Override
    public Enemy getById(int id) {
        return session.find(Enemy.class, id);
    }

    @Override
    public void insert(Enemy enemy) {
        try {
            session.beginTransaction();
            session.persist(enemy);
            session.getTransaction().commit();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    @Override
    public void update(Enemy enemy) {
        try {
            session.beginTransaction();
            session.merge(enemy);
            session.getTransaction().commit();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(Enemy enemy) {
        try {
            session.beginTransaction();
            session.remove(enemy);
            session.getTransaction().commit();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
