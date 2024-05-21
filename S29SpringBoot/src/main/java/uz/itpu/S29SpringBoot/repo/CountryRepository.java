package uz.itpu.S29SpringBoot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.itpu.S29SpringBoot.entity.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {

}
