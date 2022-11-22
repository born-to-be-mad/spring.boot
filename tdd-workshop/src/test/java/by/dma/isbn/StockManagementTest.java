package by.dma.isbn;

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

    @Test
    void testCanGetCorrectLocatorCode() {
        var isbn = "0140177396";
        IsbnDataService mainService = mock(IsbnDataService.class);
        IsbnDataService fallbackService = mock(IsbnDataService.class);
        when(mainService.lookup(isbn))
                .thenReturn(new Book(isbn, "Of Mice and Men", "J. Steinbeck"));

        var service = new StockManager(mainService, fallbackService);
        assertThat(service.getLocatorCode("0140177396"))
                .as("The Odyssey - Homer")
                .isEqualTo("7396J4");
    }

    @Test
    void testCanGetCorrectLocatorCodeWhenMainServiceIsUsed() {
        var isbn = "0140177396";
        IsbnDataService mainService = mock(IsbnDataService.class);
        IsbnDataService fallbackService = mock(IsbnDataService.class);
        when(mainService.lookup(isbn))
                .thenReturn(new Book(isbn, "Of Mice and Men", "J. Steinbeck"));

        var manager = new StockManager(mainService, fallbackService);
        assertThat(manager.getLocatorCode(isbn))
                .as("The Odyssey - Homer")
                .isEqualTo("7396J4");

        verify(mainService, times(1)).lookup(isbn);
        verify(fallbackService, never()).lookup(isbn);
    }

    @Test
    void testCanGetCorrectLocatorCodeWhenFallbackServiceIsUsed() {
        var isbn = "0140177396";
        IsbnDataService mainService = mock(IsbnDataService.class);
        IsbnDataService fallbackService = mock(IsbnDataService.class);
        when(fallbackService.lookup(isbn))
                .thenReturn(new Book(isbn, "Of Mice and Men", "J. Steinbeck"));

        var manager = new StockManager(mainService, fallbackService);
        assertThat(manager.getLocatorCode("0140177396"))
                .as("The Odyssey - Homer")
                .isEqualTo("7396J4");

        verify(mainService, times(1)).lookup(isbn);
        verify(fallbackService, times(1)).lookup(isbn);
    }

}
