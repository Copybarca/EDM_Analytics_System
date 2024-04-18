package org.edm.dao;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.ParameterExpression;
import jakarta.persistence.criteria.Root;
import org.edm.models.MachineEDM;
import org.edm.models.Sensor;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Queue;

@Component
public class SensorDAO {

    SessionFactory factory = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Sensor.class)
            .buildSessionFactory();
    Session session = null;
    private Sensor sensor;
    @Autowired
    public SensorDAO (Sensor sensor){
        this.sensor=sensor;
    }

    public List<Sensor> index(){// вытягивает из бд все объекы данного типа
        List<Sensor> list= null;

        try{
            session = factory.getCurrentSession();
            session.beginTransaction();

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Sensor> cq=cb.createQuery(Sensor.class);
            Root<Sensor> rootEntry = cq.from(Sensor.class);
            CriteriaQuery<Sensor> all = cq.select(rootEntry);
            TypedQuery<Sensor> allQuery = session.createQuery(all);
            list = allQuery.getResultList();

            session.getTransaction().commit();


        }catch(HibernateException e){
            e.printStackTrace();
        }
        return list;

    }

    public  void save(Sensor sensor){// сохраняет объект в бд

        try{
            Sensor sensorToSave = new Sensor(sensor.getMachines_id(), sensor.getTitle());
            session = factory.getCurrentSession();
            session.beginTransaction();
            session.persist(sensorToSave);
            session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
        }

    }
    public Sensor show(int id){//Вытягивает из бд данные для объекта и возвращает объект
        Sensor sensor = null;
        try{
            session = factory.getCurrentSession();

            session.beginTransaction();
            sensor = session.get(Sensor.class, id);

            session.getTransaction().commit();


        }catch(HibernateException e){
            e.printStackTrace();
        }
        return sensor;
    }

    public List<Sensor> indexByMachinesId(Integer machinesId){// вытягивает из бд все объекы данного типа
        List<Sensor> list= null;

        try{
            session = factory.getCurrentSession();
            session.beginTransaction();

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Sensor> criteriaQuery = criteriaBuilder.createQuery(Sensor.class);
            Root<Sensor> rootEntry = criteriaQuery.from(Sensor.class);

            ParameterExpression<Integer> param = criteriaBuilder.parameter(Integer.class);

            CriteriaQuery<Sensor> all = criteriaQuery.select(rootEntry).where(criteriaBuilder.equal(rootEntry.get("machines_id"),machinesId));
            TypedQuery<Sensor> allQuery = session.createQuery(all);
            list = allQuery.getResultList();

            session.getTransaction().commit();
        }catch(HibernateException e){
            e.printStackTrace();
        }
        return list;

    }
    public void update(int id, Sensor sensor){
        try{
            session = factory.getCurrentSession();
            session.beginTransaction();

            Sensor machineToUpdate =session.get(Sensor.class, id);
            machineToUpdate.setMachines_id(sensor.getMachines_id());
            machineToUpdate.setTitle(machineToUpdate.getTitle());

            session.getTransaction().commit();


        }catch(HibernateException e){
            e.printStackTrace();
        }
    }

}
