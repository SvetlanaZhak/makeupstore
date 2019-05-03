package fi.haagahelia.makeupstore;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.haagahelia.makeupstore.domain.Category;
import fi.haagahelia.makeupstore.domain.CategoryRepository;
import fi.haagahelia.makeupstore.domain.Makeup;
import fi.haagahelia.makeupstore.domain.MakeupRepository;
import fi.haagahelia.makeupstore.domain.User;
import fi.haagahelia.makeupstore.domain.UserRepository;

@SpringBootApplication
public class MakeupstoreApplication {
	
	private static final Logger log = LoggerFactory.getLogger(MakeupstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(MakeupstoreApplication.class, args);
	}
@Bean
public CommandLineRunner makeupDemo(MakeupRepository repository, CategoryRepository drepository, UserRepository urepository) {
	return (args) -> {
		log.info("save a couple of items");
		drepository.save(new Category("Body product"));
		drepository.save(new Category("Face product"));
		
		repository.save(new Makeup("Nuxe", "Huile Prodigieuse Multi-Usage Dry Oil", "50 ml", 15.95, drepository.findByName("Body product").get(0)));
		repository.save(new Makeup("Elizabeth Arden", "Eight Hour Cream Nourishing Lip Balm ", "50 ml", 19.95, drepository.findByName("Face product").get(0)));
		repository.save(new Makeup("Revolution", "Makeup Luxury Banana Powder", "42 g", 14.25, drepository.findByName("Face product").get(0)));
		repository.save(new Makeup("Paese", "Rice Powder", "15 g", 9.95, drepository.findByName("Face product").get(0)));
		repository.save(new Makeup("Britney Spears", "Midnight Fantasy Fragrance Mist", "236 ml", 6.25, drepository.findByName("Body product").get(0)));
		repository.save(new Makeup("Fresh Juice", "Litchi & Ginger Body Scrub", "200 ml", 3.95, drepository.findByName("Body product").get(0)));
		repository.save(new Makeup("Morphe", "The Jaclyn Hill Eyeshadow Palette", "12.8 ounces", 43.45, drepository.findByName("Face product").get(0)));
		repository.save(new Makeup("Urban Decay", "Run To Born Eyeshadow Palette", "0.42 oz", 46.45, drepository.findByName("Face product").get(0)));
		repository.save(new Makeup("Too Faced", "Born This Way Foundation", "30 ml", 33.95, drepository.findByName("Face product").get(0)));
		repository.save(new Makeup("Ameliorate", "Smoothing Body Exfoliant", "150 ml", 20.45, drepository.findByName("Body product").get(0)));
		
		User user1 = new User("user", "$2a$04$RViIFdkClmAyI1mQg/32v.Qg2C2grOqxiX5r18rxSWRX.oKZNkRe6", "USER");
		User user2 = new User("admin", "$2a$04$TUKy8DAFdqAKqONx7SR4ze9R3eOr3tmX7K0HKh2qypgGzNAgLrbbK", "ADMIN");
		urepository.save(user1);
		urepository.save(user2);
		
		log.info("fetch all items");
		for (Makeup makeup : repository.findAll()) {
			log.info(makeup.toString());
		}

	};
}
}
