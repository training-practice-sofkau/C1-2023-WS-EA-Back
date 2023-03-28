package ec.com.clean.architecture.example.mongo.data;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Document(collection = "books")
@NoArgsConstructor
public class BookData {

    @Id
    private String id;
    private String isbn;
    private String title;
    private List<String> authors = new ArrayList<>();
    private List<String> categories = new ArrayList<>();
    private Integer year;
    private Boolean available = true;

    public BookData (String isbn, String title, Integer year){
        this.id = UUID.randomUUID().toString().substring(0, 10);
        this.isbn = isbn;
        this.title = title;
        this.authors = new ArrayList<>();
        this.categories = new ArrayList<>();
        this.year = year;
        this.available = true;
    }
}
