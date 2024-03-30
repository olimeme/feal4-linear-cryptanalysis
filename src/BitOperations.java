public class BitOperations {
    public static void splitPairs(int wordIndex) {
        FEALData.R0 = Long.parseLong(FEALData.plaintext[wordIndex].substring(8), 16);
        FEALData.L0 = Long.parseLong(FEALData.plaintext[wordIndex].substring(0, 8), 16);
        FEALData.R4 = Long.parseLong(FEALData.cyphertext[wordIndex].substring(8), 16);
        FEALData.L4 = Long.parseLong(FEALData.cyphertext[wordIndex].substring(0, 8), 16);
    }

    public static int getBit(long word, int bitIndex) {
        return (int) ((word >> bitIndex) & 1);
    }

    public static int get12BitKeyForInnerBytes(int k1) {
        return (((k1 >> 6) & 0x3F) << 16) + ((k1 & 0x3F) << 8);
    }

    public static int get20BitKeyForOutterBytes(int k2, int keyTilda) {
        int a0 = (((k2 & 0xF) >> 2) << 6) + ((keyTilda >> 16) & 0xFF);
        int a1 = ((k2 & 0x3) << 6) + ((keyTilda >> 8) & 0xFF);
        int b0 = (k2 >> 12) & 0xFF;
        int b3 = (k2 >> 4) & 0xFF;
        int b1 = b0 ^ a0;
        int b2 = b3 ^ a1;

        return (b0 << 24) + (b1 << 16) + (b2 << 8) + b3;
    }

    public static void validate(int key0, int key1, int key2, int key3) {
        int y0 = FEAL.f((int) FEALData.L0 ^ (int) FEALData.R0 ^ key0);
        int y1 = FEAL.f((int) FEALData.L0 ^ y0 ^ key1);
        int y2 = FEAL.f((int) FEALData.L0 ^ (int) FEALData.R0 ^ y1 ^ key2);
        int y3 = FEAL.f((int) FEALData.L0 ^ y0 ^ y2 ^ key3);

        key0 = Integer.reverseBytes(key0);
        key1 = Integer.reverseBytes(key1);
        key2 = Integer.reverseBytes(key2);
        key3 = Integer.reverseBytes(key3);
        int key4 = K4.solveForK4(y1, y3);
        int key5 = K5.solveForK5(y0, y1, y2, y3);

        int key[] = { key0, key1, key2, key3, key4, key5 };
        byte[] data1 = new byte[8];

        for (int w = 0; w < FEALData.PAIRS; w++) {
            for (int i = 0; i < 8; i++)
                data1[i] = (byte) (Integer.parseInt(FEALData.cyphertext[w].substring(i * 2, (i * 2) + 2), 16) & 255);

            FEAL.decrypt(data1, key);

            StringBuilder sb = new StringBuilder(data1.length * 2);
            for (byte b : data1)
                sb.append(String.format("%02x", b));

            if (!FEALData.plaintext[w].equals(sb.toString()))
                return;
        }

        System.out.print("K0 0x" + Integer.toHexString(key0));
        System.out.print("\tK1 0x" + Integer.toHexString(key1));
        System.out.print("\tK2 0x" + Integer.toHexString(key2));
        System.out.print("\tK3 0x" + Integer.toHexString(key3));
        System.out.print("\tK4 0x" + Integer.toHexString(key4));
        System.out.println("\tK5 0x" + Integer.toHexString(key5));
        System.out.println("------------------- SOLVED --------------------");
    }
}
