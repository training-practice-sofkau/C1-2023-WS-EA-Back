package ec.com.clean.architecture.example.api;

import ec.com.clean.architecture.example.model.book.Book;
import ec.com.clean.architecture.example.usecase.getall.GetAllUseCase;
import ec.com.clean.architecture.example.usecase.getbyid.GetByIdUseCase;
import ec.com.clean.architecture.example.usecase.save.SaveUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterRest {
    /*@Bean
    public RouterFunction<ServerResponse> routerFunction(Handler handler) {
        return route(GET("/api/usecase/path"), handler::listenGETUseCase)
                .andRoute(POST("/api/usecase/otherpath"), handler::listenPOSTUseCase)
                .and(route(GET("/api/otherusercase/path"), handler::listenGETOtherUseCase));
    }*/

    @Bean
    @RouterOperation(path = "/books", produces = {
            MediaType.APPLICATION_JSON_VALUE},
            beanClass = GetAllUseCase.class, method = RequestMethod.GET, beanMethod = "get",
            operation = @Operation(operationId = "getAllBooks", tags = "Book usecases", responses = {
                    @ApiResponse(responseCode = "200", description = "Success",
                            content = @Content(schema = @Schema(implementation = Book.class))),
                    @ApiResponse(responseCode = "204", description = "Nothing to show")}))
    public RouterFunction<ServerResponse> getAllBooks(GetAllUseCase getAllBooksUsecase){
        return route(GET("/books"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getAllBooksUsecase.get(), Book.class))
                        .onErrorResume(throwable -> ServerResponse.noContent().build()));
    }

    @Bean
    @RouterOperation(path = "/books/{id}", produces = {
            MediaType.APPLICATION_JSON_VALUE},
            beanClass = GetByIdUseCase.class, method = RequestMethod.GET, beanMethod = "apply",
            operation = @Operation(operationId = "getBookById", tags = "Book usecases",
                    parameters = {
                            @Parameter(name = "id", description = "Book ID", required = true, in = ParameterIn.PATH)
                    },
                    responses = {
                    @ApiResponse(responseCode = "200", description = "Book found",
                            content = @Content(schema = @Schema(implementation = Book.class))),
                    @ApiResponse(responseCode = "404", description = "Book not found")}))
    public RouterFunction<ServerResponse> getBookById(GetByIdUseCase getBookByIdUsecase){
        return route(GET("/books/{id}"),
                request -> getBookByIdUsecase.apply(request.pathVariable("id"))
                        .flatMap(bookDTO -> ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(bookDTO))
                        .onErrorResume(throwable -> ServerResponse.notFound().build()));
    }

    @Bean
    @RouterOperation(path = "/books", produces = {
            MediaType.APPLICATION_JSON_VALUE},
            beanClass = SaveUseCase.class, method = RequestMethod.POST, beanMethod = "save",
            operation = @Operation(operationId = "save", tags = "Book usecases",
                    parameters = {
                            @Parameter(name = "book", in = ParameterIn.PATH, schema = @Schema(implementation = Book.class))
                    },
                    responses = {@ApiResponse(responseCode = "201", description = "Success", content = @Content(schema = @Schema(implementation = Book.class))),
                            @ApiResponse(responseCode = "406", description = "Not acceptable. Try again")}

                    ,requestBody = @RequestBody(required = true, description = "Save a book following the schema", content = @Content(schema = @Schema(implementation = Book.class)))
                    )
    )
    public RouterFunction<ServerResponse> saveBook(SaveUseCase saveBookUsecase){
        return route(POST("/books").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(Book.class)
                        .flatMap(bookDTO -> saveBookUsecase.save(bookDTO)
                                .flatMap(result -> ServerResponse.status(201)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(result))

                                .onErrorResume(throwable -> ServerResponse.status(HttpStatus.NOT_ACCEPTABLE).build())));
    }




}
