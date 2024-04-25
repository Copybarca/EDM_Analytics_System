package org.edm.dao;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.ParameterExpression;
import jakarta.persistence.criteria.Root;
import org.edm.models.Client;
import org.edm.models.Detail;
import org.edm.models.Sensor;
import org.edm.models.SensorIndicator;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DetailDAO {
    SessionFactory factory = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Detail.class)
            .buildSessionFactory();
    Session session = null;

    Detail detail;

    @Autowired
    public DetailDAO(Detail detail) {
        this.detail = detail;
    }

    public List<Detail> index(){
        List<Detail> list = new ArrayList<>();

        try{
            System.out.println("Началось");
            session = factory.getCurrentSession();
            session.beginTransaction();
            System.out.println("Фабрика создана");
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Detail> cq = cb.createQuery(Detail.class);
            System.out.println("Образение к сущности Детаил");
            Root<Detail> root = cq.from(Detail.class);
            System.out.println("создание корня");
            CriteriaQuery<Detail> all = cq.select(root);
            System.out.println("создание щапроса");
            TypedQuery<Detail> allQuery = session.createQuery(all);
            System.out.println("создание щапроса");
            list = allQuery.getResultList();
            System.out.println("Конец");
            session.getTransaction().commit();
        }catch (HibernateException e){
            e.printStackTrace();
        }
        return list;
    }

    public void save(Detail detail){
        try{
            Detail detailToSave = new Detail(detail.getMachines_id(), detail.getOrders_id(), detail.getDate(), detail.getQuality());
            session = factory.getCurrentSession();
            session.beginTransaction();
            session.persist(detailToSave);
            session.getTransaction().commit();
        }catch(HibernateException e){
            e.printStackTrace();
        }
    }

    public Detail show(int id){
        Detail detail = null;
        try{
            session = factory.getCurrentSession();
            session.beginTransaction();

            detail = session.get(Detail.class,id);

            session.getTransaction().commit();
        }catch (HibernateException e){
            e.printStackTrace();
        }
        return detail;
    }

    public void update(int id, Detail detail){
        try{
            session = factory.getCurrentSession();
            session.beginTransaction();
            Detail detailToUpdate =session.get(Detail.class,id);
            detailToUpdate.setMachines_id(detail.getMachines_id());
            detailToUpdate.setOrders_id(detail.getOrders_id());
            detailToUpdate.setDate(detail.getDate());
            detailToUpdate.setQuality(detail.getQuality());
            session.getTransaction().commit();

        }catch (HibernateException e ){
            e.printStackTrace();
        }
    }
    public List<Detail> indexByMachinesId(Integer machinesId){// вытягивает из бд все объекы данного типа
        List<Detail> list= null;

        try{
            session = factory.getCurrentSession();
            session.beginTransaction();

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Detail> criteriaQuery = criteriaBuilder.createQuery(Detail.class);
            Root<Detail> rootEntry = criteriaQuery.from(Detail.class);

            ParameterExpression<Integer> param = criteriaBuilder.parameter(Integer.class);

            CriteriaQuery<Detail> all = criteriaQuery.select(rootEntry).where(criteriaBuilder.equal(rootEntry.get("machines_id"),machinesId));
            TypedQuery<Detail> allQuery = session.createQuery(all);
            list = allQuery.getResultList();

            session.getTransaction().commit();
        }catch(HibernateException e){
            e.printStackTrace();
        }
        return list;

    }

}
