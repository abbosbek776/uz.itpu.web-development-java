package s19springmvcboot.webdev.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import s19springmvcboot.webdev.domain.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {
}
