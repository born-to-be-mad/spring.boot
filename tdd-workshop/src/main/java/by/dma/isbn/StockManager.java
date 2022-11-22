package by.dma.isbn;

/**
 * Created by IntelliJ IDEA.
 *
 * @author dzmitry.marudau
 * @since 2022.11
 */
public class StockManager {

    private final IsbnDataService service;

    public StockManager(IsbnDataService service) {
        this.service = service;
    }

    public String getLocatorCode(String isbn) {
        var book = service.lookup(isbn);
        return isbn.substring(isbn.length() - 4)
               + book.getAuthor().charAt(0)
               + book.getTitle().split(" ").length;
    }
}
