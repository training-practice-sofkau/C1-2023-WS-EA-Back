package ec.com.clean.architecture.example.model.book;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Book {

    private String id;
    private String isbn;
    private String title;

    private List<String> authors;
    private List<String> categories;

    private Integer year;
    private Boolean available;


}
