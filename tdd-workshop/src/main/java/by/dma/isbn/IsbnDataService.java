package by.dma.isbn;

/**
 * Created by IntelliJ IDEA.
 *
 * @author dzmitry.marudau
 * @since 2022.11
 */
public interface IsbnDataService {
    Book lookup(String isbn);
}
