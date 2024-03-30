public class K4 {
    public static int solveForK4(int y1, int y3) {
        return Integer.reverseBytes((int) FEALData.L0 ^ (int) FEALData.R0 ^ y1 ^ y3 ^ (int) FEALData.L4);
    }
}
