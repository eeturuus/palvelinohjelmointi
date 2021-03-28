package fi.haagahelia.course;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;

import fi.haagahelia.course.domain.Book;
import fi.haagahelia.course.domain.BookRepository;
import fi.haagahelia.course.domain.Category;
import fi.haagahelia.course.domain.CategoryRepository;
import fi.haagahelia.course.domain.User;
import fi.haagahelia.course.domain.UserRepository;


@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
//	@Override
//	 protected SpringApplicationBuilder configure(SpringApplicationBuilder
//	application) {
//	 return application.sources(BookstoreApplication.class);
//	 }
	
	
	
	@Bean
	public CommandLineRunner bookDemo(BookRepository repository, CategoryRepository crepository, UserRepository userRepository) {
		return (args) -> {
			log.info("save a couple of books");
			crepository.deleteAll();
			crepository.save(new Category("Classics"));
			crepository.save(new Category("Sci-Fi"));
			crepository.save(new Category("Crime"));
			crepository.save(new Category("Non-Fiction"));
			
			repository.deleteAll();
			repository.save(new Book("Ernest Hemingway", "A Farewell to Arms", 1929, "1232323-21", 19.90, crepository.findByName("Classics").get(0)));
			repository.save(new Book("George Orwell", "Animal Farm", 1945, "2212343-5", 24.90, crepository.findByName("Classics").get(0)));
			userRepository.deleteAll();
			
			User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER", "user@gmail.com");	//lisää email molempiin mahdollisesti
			User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN", "admin@gmail.com");
			userRepository.save(user1);
			userRepository.save(user2);
			
			log.info("fetch all books");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}

		};
	}
}
