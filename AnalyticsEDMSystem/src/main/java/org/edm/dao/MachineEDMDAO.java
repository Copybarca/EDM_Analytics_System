package org.edm.dao;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.edm.models.MachineEDM;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.List;

@Component
public class MachineEDMDAO {
    SessionFactory factory = new Configuration() //Лучше создать отдельный класс и конфигать его внутри. А юда просто внедрять
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(MachineEDM.class)
            .buildSessionFactory();
    //factory.close() тут не делается. А лучше бы его делать, когда соединение больше не нужно(при закрытии приложения на сервере?)
    Session session = null;
    MachineEDM mac;

    @Autowired
    public MachineEDMDAO(MachineEDM mac) {
        this.mac = mac;
    }
    @PreDestroy
    public void destroy(){

    }
    public List<MachineEDM> index(){
        List<MachineEDM> list= null;

        try{
            session = factory.getCurrentSession();
            session.beginTransaction();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<MachineEDM> cq=cb.createQuery(MachineEDM.class);
            Root<MachineEDM> rootEntry = cq.from(MachineEDM.class);
            CriteriaQuery<MachineEDM> all = cq.select(rootEntry);
            TypedQuery<MachineEDM> allQuery = session.createQuery(all);
            list = allQuery.getResultList();

            session.getTransaction().commit();


        }catch(Exception e){
            e.printStackTrace();
        }
        return list;

    }

    public  void save(MachineEDM machineEDM){

        try{
            session = factory.getCurrentSession();
            MachineEDM edm = new MachineEDM(machineEDM.getType(), machineEDM.getModel(), machineEDM.getMark());
            session.beginTransaction();
            session.persist(edm);
            session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
        }

    }




}
