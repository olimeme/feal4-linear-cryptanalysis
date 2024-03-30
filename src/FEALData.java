public class FEALData {
    private final int PAIRS = 200;
    private String[] plaintext = new String[PAIRS];
    private String[] ciphertext = new String[PAIRS];
    private int L0, R0, R4, L4;

    public void splitPairs(int index) {
        this.L0 = (int) Long.parseLong(plaintext[index].substring(0, 8), 16);
        this.R0 = (int) Long.parseLong(plaintext[index].substring(8), 16);
        this.L4 = (int) Long.parseLong(ciphertext[index].substring(0, 8), 16);
        this.R4 = (int) Long.parseLong(ciphertext[index].substring(8), 16);
    }

    public void setL0(int L0) {
        this.L0 = L0;
    }

    public void setR0(int R0) {
        this.R0 = R0;
    }

    public void setR4(int R4) {
        this.R4 = R4;
    }

    public void setL4(int L4) {
        this.L4 = L4;
    }

    public int getL0() {
        return L0;
    }

    public int getR0() {
        return R0;
    }

    public int getR4() {
        return R4;
    }

    public int getL4() {
        return L4;
    }

    public int getPAIRS() {
        return PAIRS;
    }

    public String[] getPlaintext() {
        return plaintext;
    }

    public String[] getCiphertext() {
        return ciphertext;
    }
}
