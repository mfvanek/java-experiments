package io.github.mfvanek.revolut.live.coding;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

// ThreadSafe
public class UrlShortenerImpl implements UrlShortener {

    private final int limit;
    private final String baseUrl;
    private ConcurrentMap<String, String> storage = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger();

    public UrlShortenerImpl(int limit, String baseUrl) {
        this.limit = limit;
        this.baseUrl = baseUrl;
    }

    public UrlShortenerImpl() {
        this(100, "https://www.rev.me/");
    }

    @Override
    public String shorten(String originalUrl) { // O(1) // TODO move to URIs and get rid of manual validation
        // TODO Add validation for URLs
        final int currentLimit = counter.getAndIncrement();
        if (currentLimit >= limit) {
            throw new QuotaExceededException();
        }
        final String result = baseUrl + toShortKey();
        storage.putIfAbsent(result, originalUrl); // TODO handle collisions
        return result;
    }

    @Override
    public String getOriginalUrl(String shortenedUrl) { // O(1)
        // TODO Add validation for URLs
        final String result = storage.get(shortenedUrl);
        if (result == null) {
            throw new UrlNotFoundException(shortenedUrl);
        }
        return result;
    }

    private String toShortKey() {
        return UUID.randomUUID().toString().substring(0, 7); // 7 characters
    }

    public static class UrlNotFoundException extends RuntimeException {

        public UrlNotFoundException(String url) {
            super("Could not find url: " + url);
        }
    }

    public static class QuotaExceededException extends RuntimeException {}
}
