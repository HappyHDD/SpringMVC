package DB;

import DB.persist.Product;
import DB.persist.Customer;
import DB.persist.Transaction;
import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        EntityManager em = factory.createEntityManager();

        Product product1 = new Product("one", 100);
        Customer customer1 = new Customer("Никита");

        Transaction transaction = new Transaction(product1.getCost(), product1 , customer1);

        try {
            em.getTransaction().begin();
            em.getTransaction().commit();
            System.out.println("Успешно");
        } catch (Exception ex) {
            System.out.println("Ошибка");
            System.out.println(ex.toString());
            em.getTransaction().rollback();
        }
        System.out.println(transaction);



    }

    public  List<Transaction> CustomerBuy(Customer customer, EntityManager em){
        List<Transaction> products = em.createQuery("from Transaction t where t.customer_id = " +  customer.getId()).getResultList();
        return products;
    }

    public  void CustomerDel(Customer customer, EntityManager em){
        em.createQuery("DELETE from Customer c where c.id = " +  customer.getId()).executeUpdate();
    }

    public  void ProductDel(Product product, EntityManager em){
        em.createQuery("DELETE from Product p where p.id = " +  product.getId()).executeUpdate();
    }

}
