package ca.carleton.jameslg;

import ca.carleton.jameslg.models.AddressBook;
import ca.carleton.jameslg.models.AddressBookRepository;
import ca.carleton.jameslg.models.BuddyInfo;
import ca.carleton.jameslg.models.BuddyInfoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("ca.carleton.jameslg.models")
@EntityScan("ca.carleton.jameslg.models")
public class AccessingDataJpaApplication {
	private static final Logger log = LoggerFactory.getLogger(AccessingDataJpaApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AccessingDataJpaApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(AddressBookRepository addressBookRepository, BuddyInfoRepository buddyInfoRepository) {
		return (args) -> {
			// save a few buddies
			AddressBook book1 = new AddressBook();
			book1.addBuddy(new BuddyInfo("bob", "10 cool street", "123 456 7890", book1));
			book1.addBuddy(new BuddyInfo("rob", "11 cool street", "234 567 8901", book1));

			AddressBook book2 = new AddressBook();
			book2.addBuddy(new BuddyInfo("joe", "12 cool street", "345 678 9012", book2));
			book2.addBuddy(new BuddyInfo("shmoe", "13 cool street", "456 789 01234", book2));

			addressBookRepository.save(book1);
			addressBookRepository.save(book2);

			// fetch all buddies
			log.info("Buddies found with findAll():");
			log.info("-------------------------------");
			for (BuddyInfo buddy : buddyInfoRepository.findAll()) {
				log.info(buddy.toString());
			}
			log.info("");

			// fetch buddies by name
			log.info("Buddy found with findByName('shmoe'):");
			log.info("--------------------------------------------");
			buddyInfoRepository.findByName("shmoe").forEach(bauer -> {
				log.info(bauer.toString());
			});
			log.info("");
		};
	}

}
