package org.example.repositories;

import org.example.Models.Player;
import org.example.connection.HibernateUtil;
import org.hibernate.Session;

import java.util.List;

public class PlayerDaoImpl implements PlayerDao{
    private Session session;
    private static PlayerDaoImpl instance;
    public static synchronized PlayerDaoImpl getInstance() {
        if(instance == null) {
            instance = new PlayerDaoImpl();
        }
        return instance;
    }

    public PlayerDaoImpl() {
        this.session = HibernateUtil.getSession();
    }

    @Override
    public List<Player> getAll() {
        return session.createQuery("from Player").getResultList();
    }

    @Override
    public Player getById(int id) {
        return session.find(Player.class, id);
    }

    @Override
    public void insert(Player player) {
        try {
            session.beginTransaction();
            session.persist(player);
            session.getTransaction().commit();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    @Override
    public void update(Player player) {
        try {
            session.beginTransaction();
            session.merge(player);
            session.getTransaction().commit();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(Player player) {
        try {
            session.beginTransaction();
            session.remove(player);
            session.getTransaction().commit();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
