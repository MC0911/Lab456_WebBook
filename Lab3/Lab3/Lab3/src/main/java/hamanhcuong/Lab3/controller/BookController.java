package hamanhcuong.Lab3.controller;

import hamanhcuong.Lab3.entity.Book;
import hamanhcuong.Lab3.entity.Category;
import hamanhcuong.Lab3.services.BookService;
import hamanhcuong.Lab3.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String showAllBooks(Model model) {
        List<Book> books = bookService.getallBooks();
        model.addAttribute("books", books);
        return "book/list";
    }
    @GetMapping("/add")
    public String addBookForm(Model model)
    {
        model.addAttribute("book", new Book());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "book/add";
    }
    @PostMapping("/add")
    public String addBook (@Valid @ModelAttribute("book") Book book, BindingResult bindingResult, Model model)
    {
        if (bindingResult.hasErrors())
        {
            model.addAttribute("categories", categoryService.getAllCategories());
            return "book/add";
        }

        bookService.addBook(book);
        return "redirect:/books";
    }
    @GetMapping("/edit/{id}")
    public String editBookForm (@PathVariable("id") Long id, Model model)
    {
        Book editBook = null;
        Book book = bookService.getBookById(id);
        if (book.getId().equals(id))
        {
            editBook = book;
        }
        if (editBook!= null)
        {
            model.addAttribute("book", editBook);
            model.addAttribute("categories", categoryService.getAllCategories());
            return "book/edit";
        }else {
            return "not-found";
        }
    }

    @PostMapping("/edit/{id}")
    public String editBook (@ModelAttribute("book") Long id)
    {
        Book book = bookService.getBookById(id);
        if (book.getId() == id)
        {
            bookService.updateBook(book);
        }
        return "redirect:/books";
    }
    @GetMapping("/delete/{id}")
    public String deleteBook (@PathVariable("id") Long id)
    {
        Book book = bookService.getBookById(id);
        if (book.getId() == id)
        {
            bookService.deleteBook(id);
        }
        return "redirect:/books";
    }
}