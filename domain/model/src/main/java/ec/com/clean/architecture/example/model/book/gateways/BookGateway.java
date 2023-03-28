package ec.com.clean.architecture.example.model.book.gateways;

import ec.com.clean.architecture.example.model.book.Book;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BookGateway { //This generates with the name Model+'Repository' - change it to Model+'Gateway'
    Flux<Book> getAllBooks();
    Mono<Book> getBookById(String id);
    Mono<Book> saveBook(Book book);
    Mono<String> deleteBook(String id);



}
