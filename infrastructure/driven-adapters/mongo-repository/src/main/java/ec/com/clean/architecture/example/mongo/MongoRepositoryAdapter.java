package ec.com.clean.architecture.example.mongo;

import ec.com.clean.architecture.example.model.book.Book;
import ec.com.clean.architecture.example.model.book.gateways.BookGateway;
import ec.com.clean.architecture.example.mongo.data.BookData;
import lombok.RequiredArgsConstructor;
import org.reactivecommons.utils.ObjectMapper;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

//Here you implement the class that acts as a Gateway in the model layer
@Repository
@RequiredArgsConstructor
public class MongoRepositoryAdapter implements BookGateway {
    private final MongoDBRepository bookDataRepo;
    private final ObjectMapper mapper;

    @Override
    public Flux<Book> getAllBooks() {
        return this.bookDataRepo
                .findAll().switchIfEmpty(Flux.empty())
                .map(bookData -> mapper.map(bookData, Book.class));
    }

    @Override
    public Mono<Book> getBookById(String id) {
        return this.bookDataRepo
                .findById(id)
                .switchIfEmpty(Mono.empty())
                .map(bookData -> mapper.map(bookData, Book.class));
    }

    @Override
    public Mono<Book> saveBook(Book book) {
        return this.bookDataRepo.save(mapper.map(book, BookData.class))
                .switchIfEmpty(Mono.empty())
                .map(bookData -> mapper.map(bookData, Book.class));
    }

    @Override
    public Mono<String> deleteBook(String id) {
        return null;
    }
// implements ModelRepository from domain {

    /**public MongoRepositoryAdapter(MongoDBRepository repository, ObjectMapper mapper) {

         *  Could be use mapper.mapBuilder if your domain model implement builder pattern
         *  super(repository, mapper, d -> mapper.mapBuilder(d,ObjectModel.ObjectModelBuilder.class).build());
         *  Or using mapper.map with the class of the object model

        super(repository, mapper, d -> mapper.map(d, Object.class/* change for domain model));
    } */

}
