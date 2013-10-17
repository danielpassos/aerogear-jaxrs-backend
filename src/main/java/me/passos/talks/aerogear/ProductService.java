package me.passos.talks.aerogear;

import org.jboss.aerogear.unifiedpush.JavaSender;
import org.jboss.aerogear.unifiedpush.SenderClient;
import org.jboss.aerogear.unifiedpush.message.UnifiedMessage;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

@Stateless
@Path("/product")
public class ProductService {

    @PersistenceContext(unitName = "products")
    private EntityManager em;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Product> getProducts() {
        return em.createQuery("from Product p order by p.name", Product.class).getResultList();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Product addProduct(Product product) {
        em.persist(product);
        return product;
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id:[0-9][0-9]*}")
    public Product updateProduct(@PathParam("id") String id, Product product) {
        em.merge(product);
        return product;
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id:[0-9][0-9]*}")
    public void removeBook(@PathParam("id") Long id) {
        Product product = em.createQuery("SELECT c FROM Product c WHERE c.id = :id", Product.class)
                .setParameter("id", Long.valueOf(id))
                .getSingleResult();
        em.remove(product);
    }

}
