package io.github.mfvanek.revolut.live.coding;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class UrlShortenerImplTest {

    private UrlShortener urlShortener = new UrlShortenerImpl();

    @Test
    void shouldStartWithBaseUrl() {
        assertThat(urlShortener.shorten("qq"))
            .startsWith("https://www.rev.me/");
    }

    @Test
    void shouldHaveFixedLength() {
        assertThat(urlShortener.shorten("qq"))
            .hasSize(19 + 7);
    }

    @Test
    void shouldThrowErrorWhenNotFound() {
        assertThatThrownBy(() -> urlShortener.getOriginalUrl("unknown"))
            .isInstanceOf(UrlShortenerImpl.UrlNotFoundException.class)
            .hasMessage("Could not find url: unknown");
    }

    @Test
    void shouldThrowErrorWhenOverLimit() {
        final UrlShortener urlShortener = new UrlShortenerImpl(2, "base");
        urlShortener.shorten("qq");
        urlShortener.shorten("qq");
        assertThatThrownBy(() -> urlShortener.shorten("qq"))
            .isInstanceOf(UrlShortenerImpl.QuotaExceededException.class);
    }

    @Test
    void shouldReturnLongUrlWhenFound() {
        final String shortUrl = urlShortener.shorten("qq");
        assertThat(urlShortener.getOriginalUrl(shortUrl))
            .isEqualTo("qq");
    }
}
