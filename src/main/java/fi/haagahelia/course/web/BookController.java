package fi.haagahelia.course.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import fi.haagahelia.course.domain.Book;
import fi.haagahelia.course.domain.BookRepository;
import fi.haagahelia.course.domain.CategoryRepository;

@Controller
public class BookController {
	@Autowired
	private BookRepository repository;
	
	@Autowired
	private CategoryRepository crepository;
	
	@RequestMapping(value="/login")
	public String login() {
		return "login";
	}
	
    @RequestMapping(value= {"/booklist"})
    public String bookList(Model model) {
    	System.out.println("OLLAAN BOOKLIST ENDPOINTISSA");
        model.addAttribute("books", repository.findAll());
        return "booklist";
    }
  
    // RESTful service to get all books
    @RequestMapping(value="/books", method = RequestMethod.GET)
    public @ResponseBody List<Book> bookListRest() {
    	return (List<Book>) repository.findAll();
    }
    
    // RESTful service to get book by id
    @RequestMapping(value="/book/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Book> findBookRest(@PathVariable("id") Long bookId) {
		return repository.findById(bookId);
	}
    
    @RequestMapping(value = "/add")
    public String addBook(Model model){
    	model.addAttribute("book", new Book());
    	model.addAttribute("categories", crepository.findAll());
        return "addbook";
    }     
    
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Book book){
        repository.save(book);
        return "redirect:booklist";
    }    

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteBook(@PathVariable("id") Long bookId, Model model) {
    	repository.deleteById(bookId);
        return "redirect:../booklist"; //redirect:booklist
    }
    
    @RequestMapping(value="/modify/{id}", method=RequestMethod.GET)
	public String modifyBook(@PathVariable("id") Long bookId, Model model) {
		Optional<Book> book = repository.findById(bookId);
		model.addAttribute("book", book);
		model.addAttribute("categories", crepository.findAll());
		System.out.println("OLLAAN MODIFYBOOK METODISSA");
		System.out.println(book);
    	return "modifybook";
	}
}