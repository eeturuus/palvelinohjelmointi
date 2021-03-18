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
import fi.haagahelia.course.domain.UserRepository;


@SpringBootApplication
public class BookstoreApplication extends SpringBootServletInitializer {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);
	
	@Override
	 protected SpringApplicationBuilder configure(SpringApplicationBuilder
	application) {
	 return application.sources(BookstoreApplication.class);
	 }
	
	public static void main(String[] args) throws Exception {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner bookDemo(BookRepository repository, CategoryRepository crepository, UserRepository userRepository) {
		return (args) -> {
			log.info("save a couple of books");
			crepository.save(new Category("Classics"));
			crepository.save(new Category("Sci-Fi"));
			crepository.save(new Category("Crime"));
			crepository.save(new Category("Non-Fiction"));
			
			//repository.save(new Book("Ernest Hemingway", "A Farewell to Arms", 1929, "1232323-21", 19.90, crepository.findByName("Classics").get(0)));
			//repository.save(new Book("George Orwell", "Animal Farm", 1945, "2212343-5", 24.90, crepository.findByName("Classics").get(0)));
//			UserRepository.deleteAll();
//			User user1= new User("user", "ssss")
			log.info("fetch all books");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}

		};
	}
}
