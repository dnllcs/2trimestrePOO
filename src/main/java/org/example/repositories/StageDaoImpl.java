package org.example.repositories;

import org.example.Screens.Stage;
import org.example.connection.HibernateUtil;
import org.hibernate.Session;

import javax.transaction.Transactional;
import java.util.List;

public class StageDaoImpl implements StageDao{
    private Session session;
    private static StageDaoImpl instance;
    public static synchronized StageDaoImpl getInstance() {
        if(instance == null) {
            instance = new StageDaoImpl();
        }
        return instance;
    }
    public StageDaoImpl() {
        this.session = HibernateUtil.getSession();
    }

    @Override
    public List<Stage> getAll() {
        return session.createQuery("from Stage").getResultList();
    }

    @Override
    public Stage getById(int id) {
        return session.find(Stage.class, id);
    }

    @Override
    @Transactional
    public void insert(Stage stage) {
        try {
            session.beginTransaction();
            session.persist(stage);
            session.getTransaction().commit();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public Long countRows() {
        Long returnValue = (Long) session.createQuery("select count(*) from Stage").uniqueResult();
        System.out.println("NUMBER OF ROWS:" + returnValue );
        return returnValue;
    }
    @Override
    public void update(Stage stage) {
        try {
            session.beginTransaction();
            session.merge(stage);
            session.getTransaction().commit();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(Stage stage) {
        try {
            session.beginTransaction();
            session.remove(stage);
            session.getTransaction().commit();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
