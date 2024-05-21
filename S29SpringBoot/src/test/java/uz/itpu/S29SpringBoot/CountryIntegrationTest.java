package uz.itpu.S29SpringBoot;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import uz.itpu.S29SpringBoot.repo.CountryRepository;

@Sql(scripts = {"/data.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@DataJpaTest
@AutoConfigureTestDatabase
class CountryIntegrationTest {

  @Autowired
  private CountryRepository countryRepository;

  @Test
  void testExactDataExists() {
    assertEquals(12L, countryRepository.findAll().size());
  }
}
