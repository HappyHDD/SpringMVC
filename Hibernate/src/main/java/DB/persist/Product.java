package DB.persist;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @Column
    private int cost;

    @OneToMany(
            mappedBy = "product",
            cascade = CascadeType.ALL
    )
    private List<Transaction> transactions = new ArrayList<>();

    public Product(String name, int cost) {
        this.name = name;
        this.cost = cost;
    }

    public Product(String name, int cost, List<Transaction> transactions) {
        this.name = name;
        this.cost = cost;
        this.transactions = transactions;
    }

    public Product() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                '}';
    }
}
