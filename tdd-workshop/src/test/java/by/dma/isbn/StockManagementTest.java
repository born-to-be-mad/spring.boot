package by.dma.isbn;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by IntelliJ IDEA.
 *
 * @author dzmitry.marudau
 * @since 2022.11
 */
class StockManagementTest {

    @Test
    void testCanGetCorrectLocatorCode() {
        IsbnDataService dataService = isbn -> new Book(isbn, "Of Mice and Men", "J. Steinbeck");

        var service = new StockManager(dataService);
        assertThat(service.getLocatorCode("0140177396"))
                .as("The Odyssey - Homer")
                .isEqualTo("7396J4");
    }

}
