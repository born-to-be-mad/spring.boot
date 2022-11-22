package by.dma.isbn;

/**
 * Created by IntelliJ IDEA.
 *
 * @author dzmitry.marudau
 * @since 2022.11
 */
public class StockManager {

    private final IsbnDataService mainService;

    private final IsbnDataService fallbackService;

    public StockManager(IsbnDataService mainService, IsbnDataService fallbackService) {
        this.mainService = mainService;
        this.fallbackService = fallbackService;
    }

    public String getLocatorCode(String isbn) {
        var book = mainService.lookup(isbn);
        if (book == null) {
            book = fallbackService.lookup(isbn);
        }
        return isbn.substring(isbn.length() - 4)
               + book.getAuthor().charAt(0)
               + book.getTitle().split(" ").length;
    }
}
