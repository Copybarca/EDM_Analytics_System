package org.edm.dao;

import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.edm.models.Employee;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmployeeDAO {

    SessionFactory sessionFactory;
    Session session = null;

    Employee employee;
    @Autowired
    public EmployeeDAO(Employee employee, SessionFactory sessionFactory) {
        this.employee = employee;
        this.sessionFactory = sessionFactory;
    }
    public List<Employee> index(){
        List<Employee> list = null;

        try{
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
            Root<Employee> root = cq.from(Employee.class);
            CriteriaQuery<Employee> all = cq.select(root);
            TypedQuery<Employee> allQuery = session.createQuery(all);
            list = allQuery.getResultList();

            session.getTransaction().commit();
        }catch (HibernateException e ){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return list;
    }

    public Employee show(int id){
        Employee emp = null;
        try{
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();

            emp = session.get(Employee.class, id);

            session.getTransaction().commit();
        }catch (HibernateException e){
            e.printStackTrace();
        }finally {
            session.close();
        }
        return emp;
    }
    public void update(int id, Employee emp){
        try{
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            Employee empToUpdate = session.get(Employee.class,id);
            empToUpdate.setAge(emp.getAge());
            empToUpdate.setName(emp.getName());
            empToUpdate.setPost(emp.getPost());
            empToUpdate.setSalary(emp.getSalary());
            empToUpdate.setSeniority(emp.getSeniority());
            empToUpdate.setSurname(emp.getSurname());
            session.getTransaction().commit();

        }catch(HibernateException e){
            e.printStackTrace();
        }finally {
            session.close();
        }
    }
    public void save(Employee employee){

        try{
            Employee emp = new Employee(employee.getName(), employee.getSurname(), employee.getSeniority(), employee.getSalary(), employee.getPost(), employee.getAge());
            session = sessionFactory.getCurrentSession();
            session.beginTransaction();
            session.persist(emp);

            session.getTransaction().commit();
        }catch (HibernateException e){
            e.printStackTrace();
        }finally {
            session.close();
        }
    }


}
