package Hibernate_Library;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Formula;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Author implements IBaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String surname;

    private LocalDate dateOfBirth;

//    z powodu relcaji many to many mamy tabele pośredniczącą o nazwie Author_Book
//    zapytanie musi dotyczyć tabeli ośredniczącej i zliczać wystąpienia
    @Formula(value = "(SELECT count(*) FROM Author_Book ab WHERE ab.authors_id = id)")
    private int numberOfBooks;

    @EqualsAndHashCode.Exclude
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Book> books = new HashSet<>();

}
