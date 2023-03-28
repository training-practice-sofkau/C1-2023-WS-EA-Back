package ec.com.clean.architecture.example.usecase.getall;

import ec.com.clean.architecture.example.model.book.Book;
import ec.com.clean.architecture.example.model.book.gateways.BookGateway;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;

@AllArgsConstructor
public class GetAllUseCase {
    private final BookGateway gateway;
    public Flux<Book> get(){
        return gateway.getAllBooks();
    }

}
