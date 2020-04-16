package MyMVS.persist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("from Product p where (p.cost > :mincost) and  (p.cost < :maxcost) ")
    List<Product> filterProduct(@Param("mincost") int min, @Param("maxcost") int max);

}
