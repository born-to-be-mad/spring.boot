package by.dma.isbn;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by IntelliJ IDEA.
 *
 * @author dzmitry.marudau
 * @since 2022.11
 */
class StockManagementTest {

    IsbnDataService mainService;
    IsbnDataService fallbackService;

    StockManager stockManager;

    @BeforeEach
    void setUp() {
        mainService = mock(IsbnDataService.class);
        fallbackService = mock(IsbnDataService.class);
        stockManager = new StockManager(mainService, fallbackService);
    }

    @Test
    void testCanGetCorrectLocatorCode() {
        var isbn = "0140177396";

        when(mainService.lookup(isbn))
                .thenReturn(new Book(isbn, "Of Mice and Men", "J. Steinbeck"));

        assertThat(stockManager.getLocatorCode("0140177396"))
                .as("The Odyssey - Homer")
                .isEqualTo("7396J4");
    }

    @Test
    void testCanGetCorrectLocatorCodeWhenMainServiceIsUsed() {
        var isbn = "0140177396";
        when(mainService.lookup(isbn))
                .thenReturn(new Book(isbn, "Of Mice and Men", "J. Steinbeck"));

        assertThat(stockManager.getLocatorCode(isbn))
                .as("The Odyssey - Homer")
                .isEqualTo("7396J4");

        verify(mainService, times(1)).lookup(isbn);
        verify(fallbackService, never()).lookup(isbn);
    }

    @Test
    void testCanGetCorrectLocatorCodeWhenFallbackServiceIsUsed() {
        var isbn = "0140177396";
        when(fallbackService.lookup(isbn))
                .thenReturn(new Book(isbn, "Of Mice and Men", "J. Steinbeck"));
        assertThat(stockManager.getLocatorCode("0140177396"))
                .as("The Odyssey - Homer")
                .isEqualTo("7396J4");

        verify(mainService, times(1)).lookup(isbn);
        verify(fallbackService, times(1)).lookup(isbn);
    }

}
