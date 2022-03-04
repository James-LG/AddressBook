package ca.carleton.jameslg.controllers;

import ca.carleton.jameslg.models.AddressBook;
import ca.carleton.jameslg.models.AddressBookRepository;
import ca.carleton.jameslg.models.BuddyInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller()
@RequestMapping("/leaf")
public class AddressBookController {
    private final AddressBookRepository addressBookRepository;

    @Autowired
    public AddressBookController(
            AddressBookRepository addressBookRepository) {
        this.addressBookRepository = addressBookRepository;
    }

    @GetMapping("")
    public String greeting(Model model) {
        Iterable<AddressBook> addressBooks = addressBookRepository.findAll();
        model.addAttribute("addressBooks", addressBooks);
        return "books";
    }

    @GetMapping("/books/{id}")
    public String bookDetails(@PathVariable("id") int id, Model model) {
        AddressBook book = addressBookRepository.findById(id);
        model.addAttribute("book", book);
        model.addAttribute("buddyInfo", new BuddyInfo());
        return "bookDetails";
    }

    @PostMapping("/books/{bookId}/createBuddy")
    public String buddySubmit(
            @PathVariable("bookId") int bookId,
            @ModelAttribute BuddyInfo buddyInfo,
            Model model) {
        model.addAttribute("buddyInfo", buddyInfo);

        AddressBook book = addressBookRepository.findById(bookId);
        book.addBuddy(buddyInfo);
        addressBookRepository.save(book);

        return String.format("redirect:/leaf/books/%s", bookId);
    }

    @PostMapping("/books")
    public String bookSubmit() {
        AddressBook newBook = new AddressBook();
        addressBookRepository.save(newBook);

        return String.format("redirect:/leaf/books/%s", newBook.getId());
    }

}
