package uz.itpu.S29SpringBoot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import uz.itpu.S29SpringBoot.entity.Country;
import uz.itpu.S29SpringBoot.repo.CountryRepository;

@Component
public class DataLoader implements CommandLineRunner {
  @Autowired
  private CountryRepository countryRepository;

  @Override
  public void run(String... args) throws Exception {
    Country country1 = new Country(54, "Bolivia");
    countryRepository.save(country1);
  }
}
