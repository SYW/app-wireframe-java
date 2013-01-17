package shopyourway.domain.utils;

import com.google.common.base.Joiner;

public class PlatformUtils {
    public static <T> String toCommaSeparatedString(Iterable<T> iterator) {
        return Joiner.on(',').join(iterator);
    }
}
