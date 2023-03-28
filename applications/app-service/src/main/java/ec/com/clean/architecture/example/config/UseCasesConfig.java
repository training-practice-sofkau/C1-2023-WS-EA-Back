package ec.com.clean.architecture.example.config;

import ec.com.clean.architecture.example.model.book.gateways.BookGateway;
import ec.com.clean.architecture.example.usecase.getall.GetAllUseCase;
import ec.com.clean.architecture.example.usecase.getbyid.GetByIdUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(basePackages = "ec.com.clean.architecture.example.usecase")
/*        includeFilters = {
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = "^.+UseCase$")
        },
        useDefaultFilters = false)*/
public class UseCasesConfig {
        @Bean
        public GetAllUseCase getAllBooksUseCase(BookGateway gateway){
                return new GetAllUseCase(gateway);
        }

        @Bean
        public GetByIdUseCase getBookByIdUseCase(BookGateway gateway){
                return new GetByIdUseCase(gateway);
        }
}
