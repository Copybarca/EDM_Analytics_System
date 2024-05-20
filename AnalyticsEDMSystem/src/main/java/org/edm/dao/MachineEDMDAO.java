package org.edm.dao;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.edm.models.MachineEDM;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class MachineEDMDAO {
    SessionFactory  sessionFactory;
    //factory.close() тут не делается. А лучше бы его делать, когда соединение больше не нужно(при закрытии приложения на сервере?)
    Session session = null;
    MachineEDM mac;
    @Autowired
    public MachineEDMDAO(MachineEDM mac, SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        this.mac = mac;
    }
    public List<MachineEDM> index(){// вытягивает из бд все объекы данного типа
        List<MachineEDM> list= null;

        try{
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<MachineEDM> cq=cb.createQuery(MachineEDM.class);
            Root<MachineEDM> rootEntry = cq.from(MachineEDM.class);
            CriteriaQuery<MachineEDM> all = cq.select(rootEntry);
            TypedQuery<MachineEDM> allQuery = session.createQuery(all);
            list = allQuery.getResultList();

            session.getTransaction().commit();


        }catch(HibernateException e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return list;

    }

    public  void save(MachineEDM machineEDM){// сохраняет объект в бд

        try{
            MachineEDM edm = new MachineEDM(machineEDM.getTitle(), machineEDM.getFirm(), machineEDM.getType(), machineEDM.getSerialNumber());
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            session.persist(edm);
            session.getTransaction().commit();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            session.close();
        }

    }
    public MachineEDM show(int id){//Вытягивает из бд данные для объекта и возвращает объект
        MachineEDM machine = null;
        try{
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            machine = session.get(MachineEDM.class, id);

            session.getTransaction().commit();


        }catch(HibernateException e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return machine;
    }

    public void update(int id, MachineEDM machine){
        try{
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            MachineEDM machineToUpdate =session.get(MachineEDM.class, id);
            machineToUpdate.setFirm(machine.getFirm());
            machineToUpdate.setTitle(machine.getTitle());
            machineToUpdate.setType(machine.getType());
            machineToUpdate.setSerialNumber(machine.getSerialNumber());

            session.getTransaction().commit();


        }catch(HibernateException e){
            e.printStackTrace();
        }finally {
            session.close();
        }
    }




}
