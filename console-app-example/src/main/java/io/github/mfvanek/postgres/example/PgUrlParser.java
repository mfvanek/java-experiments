package io.github.mfvanek.postgres.example;

import java.util.Set;
import java.util.stream.Collectors;

import lombok.experimental.UtilityClass;

@UtilityClass
final class PgUrlParser {

    static final String URL_HEADER = "jdbc:postgresql://";

    static String extractAllHostsWithPort(final String pgUrl) {
        final int lastIndex = pgUrl.lastIndexOf('/');
        if (lastIndex >= URL_HEADER.length()) {
            return pgUrl.substring(URL_HEADER.length(), lastIndex);
        }
        return pgUrl.substring(URL_HEADER.length());
    }

    static String extractDatabaseName(final Set<String> pgUrls) {
        final String pgUrl = pgUrls.iterator().next();
        final int lastIndexOfSlash = pgUrl.lastIndexOf('/');
        final String dbNameWithParams = pgUrl.substring(lastIndexOfSlash);
        final int lastIndex = dbNameWithParams.lastIndexOf('?');
        if (lastIndex >= 0) {
            return dbNameWithParams.substring(0, lastIndex);
        }
        return dbNameWithParams;
    }

    public static String buildCommonUrlToPrimary(final Set<String> pgUrls) {
        return URL_HEADER + pgUrls.stream()
                .map(PgUrlParser::extractAllHostsWithPort)
                .sorted()
                .collect(Collectors.joining(",")) +
                extractDatabaseName(pgUrls) +
                "?targetServerType=primary";
    }
}
