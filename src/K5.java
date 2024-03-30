public class K5 {
    public static int solveForK5(int y0, int y1, int y2, int y3) {
        return Integer.reverseBytes((int) FEALData.R0 ^ y1 ^ y3 ^ y0 ^ y2 ^ (int) FEALData.R4);
    }
}
