
// cipher FEAL-4,as described in the course,to find the six secret sub-keys that have been used.
// This is a VERY hard assignment and will take a considerable amount of time,so please start working 
// on it as soon as possible.

// The linear cryptanalysis attack can be implemented in the programming language of your choice.
// Your program will have to loop through a lot of different possible values,so it should be reasonably
//  efficient.The source code for the FEAL-4 cipher(from which the six secret sub-keys have been removed)
//  in C and Java is provided below in the files FEAL.c and FEAL.java,so 
//  you may wish to make use of this code and implement your attack in one of these languages.An executable 
//  version of this code which has the secret key built into it was used to generate the 200 random 
//  plaintext/ciphertext pairs which can be found below in the file known.txt.

// Your task is to discover as many of the bits as possible of the six 32-bit sub-keys K0-K5 used in this
//  cipher.The more bits,the more marks you will get.However,you will get some marks for even finding a few
//   bits of the sub-keys,as this is a very difficult task.You should submit your code along with a written 
//   report describing how you went about the cryptanalysis and the results obtained through the Loop page 
//   for this module by 10 am Monday 29 th July.

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class App {
    private static void solveForK1(int key0, FEALData data) {
        for (int k1 = 0; k1 < 4096; k1++) {
            int keyTilda = BitManipulation.generate12BitKeyForInnerBytes(k1);
            int firstA1 = BitManipulation.calcInnerBytesKey1(0, keyTilda, key0, data);

            for (int w1 = 1; w1 < data.getPAIRS(); w1++) {
                if (firstA1 != BitManipulation.calcInnerBytesKey1(w1, keyTilda, key0, data))
                    break;

                if (w1 == data.getPAIRS() - 1) {
                    for (int k2 = 0; k2 < 1048576; k2++) {
                        int key1 = BitManipulation.generate20BitKeyForOuterBytes(k2, keyTilda);
                        int firstA2 = BitManipulation.calcOuterBytesKey1(0, key0, key1, data);

                        for (int w2 = 1; w2 < data.getPAIRS(); w2++) {
                            if (firstA2 != BitManipulation.calcOuterBytesKey1(w2, key0, key1, data))
                                break;

                            if (w2 == data.getPAIRS() - 1)
                                solveForK2(key0, key1, data);
                        }
                    }
                }
            }
        }
    }

    private static void solveForK2(int key0, int key1, FEALData data) {
        for (int k1 = 0; k1 < 4096; k1++) {
            int keyTilda = BitManipulation.generate12BitKeyForInnerBytes(k1);
            int firstA1 = BitManipulation.calcInnerBytesKey2(0, keyTilda, key0, key1, data);

            for (int w1 = 1; w1 < data.getPAIRS(); w1++) {
                if (firstA1 != BitManipulation.calcInnerBytesKey2(w1, keyTilda, key0, key1, data))
                    break;

                if (w1 == data.getPAIRS() - 1) {
                    for (int k2 = 0; k2 < 1048576; k2++) {
                        int key2 = BitManipulation.generate20BitKeyForOuterBytes(k2, keyTilda);
                        int firstA2 = BitManipulation.calcOuterBytesKey2(0, key0, key1, key2, data);

                        for (int w2 = 1; w2 < data.getPAIRS(); w2++) {
                            if (firstA2 != BitManipulation.calcOuterBytesKey2(w2, key0, key1, key2, data))
                                break;

                            if (w2 == data.getPAIRS() - 1)
                                solveForK3(key0, key1, key2, data);
                        }
                    }
                }
            }
        }
    }

    private static void solveForK3(int key0, int key1, int key2, FEALData data) {
        for (int k1 = 0; k1 < 4096; k1++) {
            int keyTilda = BitManipulation.generate12BitKeyForInnerBytes(k1);
            int firstA1 = BitManipulation.calcInnerBytesKey3(0, keyTilda, key0, key1, key2, data);

            for (int w1 = 1; w1 < data.getPAIRS(); w1++) {
                if (firstA1 != BitManipulation.calcInnerBytesKey3(w1, keyTilda, key0, key1, key2, data))
                    break;

                if (w1 == data.getPAIRS() - 1) {
                    for (int k2 = 0; k2 < 1048576; k2++) {
                        int key3 = BitManipulation.generate20BitKeyForOuterBytes(k2, keyTilda);
                        int firstA2 = BitManipulation.calcOuterBytesKey3(0, key0, key1, key2, key3, data);

                        for (int w2 = 1; w2 < data.getPAIRS(); w2++) {
                            if (firstA2 != BitManipulation.calcOuterBytesKey3(w2, key0, key1, key2, key3, data))
                                break;

                            if (w2 == data.getPAIRS() - 1)
                                validate(key0, key1, key2, key3, data);
                        }
                    }
                }
            }
        }
    }

    private static void validate(int key0, int key1, int key2, int key3, FEALData fealData) {
        int y0 = BitManipulation.f(fealData.getL0() ^ fealData.getR0() ^ key0);
        int y1 = BitManipulation.f(fealData.getL0() ^ y0 ^ key1);
        int y2 = BitManipulation.f(fealData.getL0() ^ fealData.getR0() ^ y1 ^ key2);
        int y3 = BitManipulation.f(fealData.getL0() ^ y0 ^ y2 ^ key3);

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

        // System.out.println("0x" + Integer.toHexString(key0) + "\t0x" +
        // Integer.toHexString(key1) + "\t0x"
        // + Integer.toHexString(key2) + "\t0x" + Integer.toHexString(key3) + "\t0x" +
        // Integer.toHexString(key4)
        // + "\t0x" + Integer.toHexString(key5));
    }

    public static void main(String[] args) throws Exception {
        FEALData data = new FEALData();
        boolean isPlainText = true;
        String fileName = "known.txt";
        String line = null;
        int i = 0;
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine()) != null && i < data.getPAIRS()) {
                if (line.length() < 12)
                    continue;
                if (isPlainText) {
                    data.getPlaintext()[i] = line.substring(12, 28);
                } else {
                    data.getCiphertext()[i] = line.substring(12, 28);
                    i++;
                }
                isPlainText = !isPlainText;
            }
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");
        } catch (IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");
            ex.printStackTrace();
        }

        for (int j = 0; j < data.getPAIRS(); j++) {
            System.out.println(data.getPlaintext()[j]);
            System.out.println(data.getCiphertext()[j]);
        }
        System.out.println("Starting the decryption");

        for (int k1 = 0; k1 < 4096; k1++) {
            int keyTilda = BitManipulation.generate12BitKeyForInnerBytes(k1);
            int firstA1 = BitManipulation.calcInnerBytesKey0(0, keyTilda, data);

            for (int w1 = 1; w1 < data.getPAIRS(); w1++) {
                if (firstA1 != BitManipulation.calcInnerBytesKey0(w1, keyTilda, data))
                    break;

                if (w1 == data.getPAIRS() - 1) {
                    for (int k2 = 0; k2 < 1048576; k2++) {
                        int key0 = BitManipulation.generate20BitKeyForOuterBytes(k2, keyTilda);
                        int firstA2 = BitManipulation.calcOuterBytesKey0(0, key0, data);

                        for (int w2 = 1; w2 < data.getPAIRS(); w2++) {
                            if (firstA2 != BitManipulation.calcOuterBytesKey0(w2, key0, data))
                                break;

                            if (w2 == data.getPAIRS() - 1)
                                solveForK1(key0, data);
                        }
                    }
                }
            }
        }
    }
}
