package ec.com.clean.architecture.example.mongo;

import ec.com.clean.architecture.example.model.book.Book;
import ec.com.clean.architecture.example.mongo.data.BookData;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;

public interface MongoDBRepository extends ReactiveMongoRepository<BookData, String> {
}
