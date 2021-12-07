import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class HashSystem {

    // 8 hard coded constants in hex
    // They represent the first 32 bits of the fractional parts
    // of the square roots of the first 8 prime numbers: 2, 3, 5, 7, 11, 13, 17, 19
    private static final int h0 = 0x6a09e667;
    private static final int h1 = 0xbb67ae85;
    private static final int h2 = 0x3c6ef372;
    private static final int h3 = 0xa54ff53a;
    private static final int h4 = 0x510e527f;
    private static final int h5 = 0x9b05688c;
    private static final int h6 = 0x1f83d9ab;
    private static final int h7 = 0x5be0cd19;

    // 64 hard coded constants in hex
    // This array of values represents the first 32 bits of the fractional parts
    // of the cube roots of the first 64 prime numbers (2 - 311)
    private static final int[] k = {0x428a2f98, 0x71374491, 0xb5c0fbcf, 0xe9b5dba5, 0x3956c25b, 0x59f111f1, 0x923f82a4, 0xab1c5ed5,
            0xd807aa98, 0x12835b01, 0x243185be, 0x550c7dc3, 0x72be5d74, 0x80deb1fe, 0x9bdc06a7, 0xc19bf174,
            0xe49b69c1, 0xefbe4786, 0x0fc19dc6, 0x240ca1cc, 0x2de92c6f, 0x4a7484aa, 0x5cb0a9dc, 0x76f988da,
            0x983e5152, 0xa831c66d, 0xb00327c8, 0xbf597fc7, 0xc6e00bf3, 0xd5a79147, 0x06ca6351, 0x14292967,
            0x27b70a85, 0x2e1b2138, 0x4d2c6dfc, 0x53380d13, 0x650a7354, 0x766a0abb, 0x81c2c92e, 0x92722c85,
            0xa2bfe8a1, 0xa81a664b, 0xc24b8b70, 0xc76c51a3, 0xd192e819, 0xd6990624, 0xf40e3585, 0x106aa070,
            0x19a4c116, 0x1e376c08, 0x2748774c, 0x34b0bcb5, 0x391c0cb3, 0x4ed8aa4a, 0x5b9cca4f, 0x682e6ff3,
            0x748f82ee, 0x78a5636f, 0x84c87814, 0x8cc70208, 0x90befffa, 0xa4506ceb, 0xbef9a3f7, 0xc67178f2};

    /**
     * The documentation for the algorithm on the SHA-256 hashing algorithm can be
     * found in the link. The algorithm is maintained the National Institute of Standards
     * and Technology under FIPS 180-4.
     * @see <a href="https://csrc.nist.gov/publications/detail/fips/180/4/final">...</a>
     * @param password The password that will be hashed
     * @return the hash of the password
     */
    public static String hashSHA256(String password) {

        String HASH = "";

        // Convert the password string into Binary
        int n = password.length();
        for (int i = 0; i < n; i++) {
            int val = Integer.valueOf(password.charAt(i));

            String bin = "";
            while (val > 0) {
                if (val % 2 == 1) {
                    bin += '1';
                } else {
                    bin += '0';
                }
                val /= 2;
            }
            bin = reverse(bin);
            HASH += bin;
        }

        // Append a Single 1
        HASH += 1;

        // Pad with 0's until the HASH is a multiple of 512
        for (int i = HASH.length(); i % 512 != 0; i++) {
            HASH += "0";
        }

        // Get the length of the original password input and make it a 64-bit binary
        // integer, then append it to the end of HASH
        String inputLength = Integer.toBinaryString(password.length());
        for (int j = inputLength.length(); j < 8; j++) {
            inputLength += "0";
        }
        inputLength = reverse(inputLength);

        StringBuilder sb = new StringBuilder(HASH);
        sb.delete(504, 512);
        HASH = sb.toString();
        HASH += inputLength;

        return HASH;
    }

    /**
     * Takes an input string and reverses the characters
     * @param input string to be reversed
     * @return string in reverse order
     */
    public static String reverse(String input) {
        char[] a = input.toCharArray();
        int l, r = 0;
        r = a.length - 1;

        for (l = 0; l < r; l++, r--) {
            char temp = a[l];
            a[l] = a[r];
            a[r] = temp;
        }
        return String.valueOf(a);
    }

    /**
     * Entry point function for hashing a String using the SHA-256 Algorithm.
     * @param password the String that will be hashed
     * @return the hashed password
     */
    public static String SHA256(String password) {
        String hash = "FAIL";
        try {
            hash = toHexString(getSHA(password));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hash;
    }

    /**
     * Converts the input into a byte array using the SHA-256 Algorithm
     * from java.security.MessageDigest
     * @param input the password that will be hashed
     * @return The hashed password in byte array form
     * @throws NoSuchAlgorithmException
     */
    public static byte[] getSHA(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * Converts a byte array into a more easily readable 32 bit hexadecimal value
     * @param hash the hash to be converted to hex
     * @return the new hash in hexadecimal as a string
     */
    public static String toHexString(byte[] hash) {
        BigInteger number = new BigInteger(1, hash);
        StringBuilder hexString = new StringBuilder(number.toString(16));

        while (hexString.length() < 32) {
            hexString.insert(0, '0');
        }
        return hexString.toString();
    }
}
