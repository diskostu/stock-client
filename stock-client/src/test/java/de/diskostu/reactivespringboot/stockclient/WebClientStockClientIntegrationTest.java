package de.diskostu.reactivespringboot.stockclient;

import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class WebClientStockClientIntegrationTest {

    private static final String SYMBOL = "SYMBOL";
    private WebClient webClient = WebClient.builder().build();

    @Test
    void shouldRetrieveStockPricesFromTheService() {
        // given
        final WebClientStockClient webClientStockClient = new WebClientStockClient(webClient);

        // when
        final Flux<StockPrice> prices = webClientStockClient.pricesFor(SYMBOL);

        // then
        assertNotNull(prices);
        final Flux<StockPrice> fivePrices = prices.take(5);
        assertEquals(5, fivePrices.count().block());
        assertEquals(SYMBOL, fivePrices.blockFirst().getSymbol());
    }
}