public class BitOperations {

    public static int getBit(int num, int n) {
        return (num >> (31 - n)) & 1;
    }

    public static int f(int num) {
        return (FEAL.f((num)));
    }

    public static int generate12BitKeyForInnerBytes(int k) {
        return (((k >> 6) & 0x3F) << 16) + ((k & 0x3F) << 8);
    }

    public static int generate20BitKeyForOuterBytes(int k, int key_tilda) {
        int a0 = (((k & 0xF) >> 2) << 6) + ((key_tilda >> 16) & 0xFF);
        int a1 = ((k & 0x3) << 6) + ((key_tilda >> 8) & 0xFF);
        int b0 = (k >> 12) & 0xFF;
        int b3 = (k >> 4) & 0xFF;
        int b1 = b0 ^ a0;
        int b2 = b3 ^ a1;

        return (b0 << 24) + (b1 << 16) + (b2 << 8) + b3;
    }

    static void splitPairs(int index, FEALData data) {
        data.setL0((int) Long.parseLong(data.getPlaintext()[index].substring(0, 8), 16));
        data.setR0((int) Long.parseLong(data.getPlaintext()[index].substring(8), 16));
        data.setL4((int) Long.parseLong(data.getCiphertext()[index].substring(0, 8), 16));
        data.setR4((int) Long.parseLong(data.getCiphertext()[index].substring(8), 16));
    }

    public static void validate(int key0, int key1, int key2, int key3, FEALData fealData) {
        int y0 = BitOperations.f(fealData.getL0() ^ fealData.getR0() ^ key0);
        int y1 = BitOperations.f(fealData.getL0() ^ y0 ^ key1);
        int y2 = BitOperations.f(fealData.getL0() ^ fealData.getR0() ^ y1 ^ key2);
        int y3 = BitOperations.f(fealData.getL0() ^ y0 ^ y2 ^ key3);

        int key4 = fealData.getL0() ^ fealData.getR0() ^ y1 ^ y3 ^ fealData.getL4();
        int key5 = fealData.getR0() ^ y1 ^ y3 ^ y0 ^ y2 ^ fealData.getR4();

        int[] key = { key0, key1, key2, key3, key4, key5 };
        byte[] data = new byte[8];

        for (int w = 0; w < fealData.getPAIRS(); w++) {
            for (int i = 0; i < 8; i++) {
                data[i] = (byte) (Integer.parseInt(fealData.getCiphertext()[w].substring(i * 2, (i * 2) + 2), 16)
                        & 255);
            }
            FEAL.decrypt(data, key);

            StringBuilder sb = new StringBuilder(data.length * 2);
            for (byte b : data)
                sb.append(String.format("%02x", b));

            if (!fealData.getPlaintext()[w].contentEquals(sb))
                return;
        }
        FileManager.write(key0, key1, key2, key3, key4, key5);
    }
}
