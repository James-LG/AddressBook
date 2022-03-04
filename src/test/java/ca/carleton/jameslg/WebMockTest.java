package ca.carleton.jameslg;

import ca.carleton.jameslg.controllers.AddressBookController;
import ca.carleton.jameslg.models.AddressBook;
import ca.carleton.jameslg.models.AddressBookRepository;
import ca.carleton.jameslg.models.BuddyInfoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AddressBookController.class)
public class WebMockTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AddressBookRepository addressBookRepository;

    @MockBean
    private BuddyInfoRepository buddyInfoRepository;

    @Test
    public void greetingShouldReturnBooksFromRepository() throws Exception {
        List<AddressBook> books = new ArrayList<>();
        AddressBook book = new AddressBook();
        book.setId(42);
        books.add(book);
        when(addressBookRepository.findAll()).thenReturn(books);

        this.mockMvc.perform(get("/leaf"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("42")));
    }
}
