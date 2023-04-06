package ai.openfabric.api.util;

import java.util.Random;

public class ApplicationUtil {

    public static String generateRandomName() {
        int leftLimit = 97;
        int rightLimit = 122;
        int len = 6;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .limit(len)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

}
