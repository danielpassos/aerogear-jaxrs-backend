package me.passos.talks.aerogear;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
public class Product {

    @Id
    @SequenceGenerator(name = "seq", sequenceName = "PRODUCT_SEQ", allocationSize = 1, initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
    private long id;

    @Column
    private String name;

    @Column
    private BigDecimal price;

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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

}
