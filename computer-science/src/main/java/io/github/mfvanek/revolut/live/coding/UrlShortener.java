package io.github.mfvanek.revolut.live.coding;

/*
For a given URL, generate a short URL and retrieve the original by the generated one.

Input:
https://www.revolut.com/rewards-personalised-cashback-and-discounts/

Expected output:
https://www.rev.me/<url identifier>
*/
public interface UrlShortener {

    String shorten(String originalUrl);

    String getOriginalUrl(String shortenedUrl);
}
