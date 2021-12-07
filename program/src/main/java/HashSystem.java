public final class HashSystem {

    // 8 hard coded constants in hex
    private static final int h0 = 0x6a09e667;
    private static final int h1 = 0xbb67ae85;
    private static final int h2 = 0x3c6ef372;
    private static final int h3 = 0xa54ff53a;
    private static final int h4 = 0x510e527f;
    private static final int h5 = 0x9b05688c;
    private static final int h6 = 0x1f83d9ab;
    private static final int h7 = 0x5be0cd19;

    /**
     * The documentation for the algorithm on the SHA-256 hashing algorithm can be
     * found in the link.
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
        System.out.println("The hash is now: " + HASH);

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
        System.out.println(HASH.length());

        StringBuilder sb = new StringBuilder(HASH);
        sb.delete(504, 512);
        HASH = sb.toString();
        HASH += inputLength;

        System.out.println("The hash is now: " + HASH);
        System.out.println("The hash is now: " + HASH.length());



        return "";
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
}
