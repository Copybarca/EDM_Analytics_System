package org.edm.dao;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.edm.models.Client;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ClientDAO {

    SessionFactory sessionFactory;
    Session session = null;
    Client client;
    @Autowired
    public ClientDAO(Client client, SessionFactory sessionFactory){
        this.sessionFactory = sessionFactory;
        this.client=client;
    }

    public List<Client> index(){
        List<Client> list = null;

        try{
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            //List<Client> list2 = session.createQuery("from Client", Client.class).getResultList();

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Client> cq = cb.createQuery(Client.class);
            Root<Client> root = cq.from(Client.class);
            CriteriaQuery<Client> all = cq.select(root);
            TypedQuery<Client> allQuery = session.createQuery(all);
            list = allQuery.getResultList();

            session.getTransaction().commit();
        }catch (HibernateException e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return list;
    }

    public void save(Client client){
        try{
            Client clientToSave = new Client(client.getName(), client.getSurname(), client.getEmail(), client.getTelephone());
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            session.persist(clientToSave);
            session.getTransaction().commit();
        }catch(HibernateException e){
            e.printStackTrace();
        }finally {
            session.close();
        }
    }

    public Client show(int id){
        Client client = null;
        try{
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            client = session.get(Client.class,id);

            session.getTransaction().commit();
        }catch (HibernateException e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return client;
    }

    public void update(int id, Client client){
        try{
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            Client clientToUpdate =session.get(Client.class,id);
            clientToUpdate.setName(client.getName());
            clientToUpdate.setSurname(client.getSurname());
            clientToUpdate.setEmail(client.getEmail());
            clientToUpdate.setTelephone(client.getTelephone());
            session.getTransaction().commit();

        }catch (HibernateException e ){
            e.printStackTrace();
        }finally {
            session.close();
        }

    }


}
