package Hibernate_Library;

import lombok.*;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Book implements IBaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;
    private int yearWritten;

    @Formula(value = "(year(now())-yearWritten)")
    private int howOld;

    private int numberOfPages;
    private int numberOfAvailableCopies;

    @Formula(value = "(select count(*) from booklent b where id=b.book_id and b.dateReturned is null)")
    private Integer numberOfBorrowedCopies;

//    odnosimy się do books z Author
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(mappedBy = "books", fetch = FetchType.EAGER)
    private Set<Author> authors;

//    jednen tytuł może być wiele razy wypozyczony
//    odnosimy się do book z klasy BookLent
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "book", fetch = FetchType.EAGER)
    private Set<BookLent> currentLents;
}
