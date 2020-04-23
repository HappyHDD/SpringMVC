package MyMVS.persist;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("from Product p where (p.cost > :mincost) and  (p.cost < :maxcost) ")
    List<Product> filterProduct(@Param("mincost") int min, @Param("maxcost") int max);

    Page<Product> findAllByCostBetween(Integer min, Integer max, Pageable pageable);

    Page<Product> findAllByCostGreaterThanEqual(Integer min, Pageable pageable);

    Page<Product> findAllByCostLessThanEqual(Integer max, Pageable pageable);

}
