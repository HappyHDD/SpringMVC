package DB.persist;

import javax.persistence.*;

@Entity
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private int cost;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Customer customer;

    public Transaction(int cost) {
        this.cost = cost;
    }

    public Transaction() {
    }

    public Transaction(int cost, Product product, Customer customer) {
        this.cost = cost;
        this.product = product;
        this.customer = customer;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }



    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }


    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", cost=" + cost +
                '}';
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
