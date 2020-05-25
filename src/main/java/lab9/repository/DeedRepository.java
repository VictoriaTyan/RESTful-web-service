package lab9.repository;

import lab9.model.Deed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeedRepository extends JpaRepository<Deed, Integer> {

}
