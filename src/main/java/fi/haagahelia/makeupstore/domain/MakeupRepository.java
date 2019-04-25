package fi.haagahelia.makeupstore.domain;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface MakeupRepository extends CrudRepository <Makeup, Long> {
	List<Makeup> findByName(String name);
}
