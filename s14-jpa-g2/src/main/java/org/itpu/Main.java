package org.itpu;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;
import org.itpu.entity.Salary;

import java.util.List;

public class Main {

    @PersistenceContext
    EntityManager entityManager;

    public static void main(String[] args) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        System.out.println("Don't forget to launch Postgres if you use it before running this code!");

        entityManager.getTransaction().begin();
        Salary salary = new Salary();
        salary.setTitle("some title");
        entityManager.persist(salary);

        entityManager.getTransaction().commit();

        Salary salary1 = entityManager.find(Salary.class, 1L);

        System.out.println("salary1 = " + salary1.hashCode() + " = " + salary1);

        System.out.println("pause");

        entityManager.clear();

        Salary salary0 = entityManager.find(Salary.class, 1L);

        System.out.println("salary0 = " + salary0.hashCode() + " = " + salary0);



        entityManager.close();
        entityManagerFactory.close();

        System.out.println("----------");

        EntityManagerFactory entityManagerFactory2 = Persistence.createEntityManagerFactory("default");
        EntityManager entityManager2 = entityManagerFactory2.createEntityManager();

        Salary salary2 = entityManager2.find(Salary.class, 1L);

        if (salary2 != null) {
            System.out.println("salary2 = " + salary2.hashCode() + " = " + salary2);
        }

        List<Salary> resultList = entityManager2.createNativeQuery("select * from salaries;", Salary.class).getResultList();

        System.out.println("pause");
        entityManager2.close();
        entityManagerFactory2.close();



        System.out.println("pause");
    }
}
