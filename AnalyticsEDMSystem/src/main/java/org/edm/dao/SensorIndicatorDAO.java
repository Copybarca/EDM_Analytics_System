package org.edm.dao;


import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.ParameterExpression;
import jakarta.persistence.criteria.Root;
import org.edm.models.SensorIndicator;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SensorIndicatorDAO {

    SessionFactory factory = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(SensorIndicator.class)
            .buildSessionFactory();
    Session session = null;
    SensorIndicator sensorIndicator;
    @Autowired
    public SensorIndicatorDAO(SensorIndicator sensorIndicator){
        this.sensorIndicator= sensorIndicator;
    }
    public List<SensorIndicator> indexBySensorId(int sensorId){
        List<SensorIndicator> list = null;

        try{
            session = factory.getCurrentSession();
            session.beginTransaction();

            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<SensorIndicator> criteriaQuery = criteriaBuilder.createQuery(SensorIndicator.class);
            Root<SensorIndicator> root = criteriaQuery.from(SensorIndicator.class);

            ParameterExpression<Integer> parameter = criteriaBuilder.parameter(Integer.class);

            CriteriaQuery<SensorIndicator> all = criteriaQuery.select(root).where(criteriaBuilder.equal(root.get("sensors_id"),sensorId));
            TypedQuery<SensorIndicator> allQuery = session.createQuery(all);
            list = allQuery.getResultList();
            session.getTransaction().commit();

        }catch(HibernateException e){
            e.printStackTrace();
        }
        return list;
    }



}
