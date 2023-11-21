package org.example.repositories;

import org.example.Models.Enemy;
import org.example.Models.GraphicalElement;
import org.example.Models.Projectile;
import org.example.Screens.Stage;
import org.example.connection.HibernateUtil;
import org.hibernate.Session;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UniversalDao {

    private static UniversalDao instance;
    private Session session;
    public UniversalDao() {
        this.session = HibernateUtil.getSession();
    }
    public static synchronized UniversalDao getInstance() {
        if(instance == null) {
            instance = new UniversalDao();
        }
        return instance;
    }

    public void save(Stage stage) {
        session.beginTransaction();
        session.saveOrUpdate(stage);
        session.getTransaction().commit();
    }
    public Stage getStageById(int id) {
        return session.find(Stage.class, id);
    }
    public Long countRows() {
        Long returnValue = (Long) session.createQuery("select count(*) from Stage").uniqueResult();
        System.out.println("NUMBER OF ROWS:" + returnValue );
        return returnValue;
    }
    public List<Enemy> getEnemies() {
        session.beginTransaction();
        List<Enemy> list = session.createQuery("from Enemy", Enemy.class).getResultList();
        session.getTransaction().commit();
        return list;
    }
}