package s19springmvcboot.webdev;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import s19springmvcboot.webdev.domain.Product;
import s19springmvcboot.webdev.repo.ProductRepository;

@Component
public class DataLoader implements CommandLineRunner {

  @Autowired
  private ProductRepository repository;

  @Override
  public void run(String... args) throws Exception {
//    wait(1000);
    List<Product> products = new ArrayList<>();
    for (int i = 0; i < 10; i++) {
      Product product = new Product(i,
          "Product#" + i,
          "Desc",
          Double.valueOf("100" + i * 10),
          true);
      products.add(product);
    }
    repository.saveAll(products);
  }
}
