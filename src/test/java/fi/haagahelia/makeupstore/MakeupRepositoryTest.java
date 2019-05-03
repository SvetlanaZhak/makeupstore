package fi.haagahelia.makeupstore;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import fi.haagahelia.makeupstore.domain.Category;
import fi.haagahelia.makeupstore.domain.Makeup;
import fi.haagahelia.makeupstore.domain.MakeupRepository;

@RunWith(SpringRunner.class)
@DataJpaTest

public class MakeupRepositoryTest {
	@Autowired
	private TestEntityManager entityManager;
	@Autowired
    private MakeupRepository repository;

    @Test
    public void findByIdShouldReturnMakeup() {
        List<Makeup> makeups = repository.findById(6);
        
        assertThat(makeups).hasSize(1);
        assertThat(makeups.get(0).getId()).isEqualTo(6);
    }
    @Test
    public void saveMakeup() {
    	Category category = new Category("Brushes");
    	Makeup makeup = new Makeup("Revolution", "Makeup Contouring Brushes", "3 pcs", 26.95, category);
    	entityManager.persistAndFlush(category);
    	entityManager.persistAndFlush(makeup);
    	assertThat(makeup.getId()).isNotNull();
    } 
    @Test
    public void deleteMakeup() {
    	Category category = new Category("Brushes");
    Makeup makeup = new Makeup("Revolution", "Makeup Contouring Brushes", "3 pcs", 26.95, category);
    entityManager.persistAndFlush(category);
   	entityManager.persistAndFlush(makeup);
   	repository.deleteById(makeup.getId());
   
    }
}
