package org.gmdev.utils;

public class GeneticUtils {

    public static byte[] getByteArrayFromBytesString(String bytesString, int size) {
        if (bytesString == null)
            throw new IllegalArgumentException("bytesString cannot be null");
        if (size <= 0)
            throw new IllegalArgumentException("size cannot be less or equal to zero");

        byte[] byteArray = new byte[size];
        for (int i = 0; i < size; i++) {
            String stringChar = Character.toString(bytesString.charAt(i));
            byteArray[i] = Byte.parseByte(stringChar, 2);
        }

        return byteArray;
    }

}
