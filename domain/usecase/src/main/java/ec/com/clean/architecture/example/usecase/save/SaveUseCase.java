package ec.com.clean.architecture.example.usecase.save;

import ec.com.clean.architecture.example.model.book.Book;
import ec.com.clean.architecture.example.model.book.gateways.BookGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class SaveUseCase {
    private final BookGateway gateway;

    public Mono<Book> save(Book book){
       return gateway.saveBook(book);
    }
}
