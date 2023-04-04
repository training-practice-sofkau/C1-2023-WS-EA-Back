package ec.com.clean.architecture.example.usecase.getall;

import ec.com.clean.architecture.example.model.book.Book;
import ec.com.clean.architecture.example.model.book.gateways.BookGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.Objects;

@ExtendWith(MockitoExtension.class)
class GetAllUseCaseTest {
    @Mock
    BookGateway bookGatewayMock;
    GetAllUseCase useCase;

    @BeforeEach
    void init(){
        useCase = new GetAllUseCase(bookGatewayMock);
    }

    @Test
    @DisplayName("getAlbumsUseCase()")
    void get() {
        Mockito.when(bookGatewayMock.getAllBooks()).thenReturn(Flux.just(new Book(), new Book()));

        var execute = useCase.get();

        StepVerifier.create(execute)
                .expectNextMatches(Objects::nonNull)
                .expectNextCount(1).verifyComplete();

        Mockito.verify(bookGatewayMock).getAllBooks();
    }
}