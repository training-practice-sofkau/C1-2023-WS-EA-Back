package ec.com.clean.architecture.example.usecase.getbyid;

import ec.com.clean.architecture.example.model.book.Book;
import ec.com.clean.architecture.example.model.book.gateways.BookGateway;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class GetByIdUseCase {

    private final BookGateway gateway;

    public Mono<Book> apply(String id){
        return gateway.getBookById(id);
    }
}
