package cz.mateusz.dstructures.encryption;

public class CesarCipher {

    private char[] encoder = new char[26]; //Encryption array
    private char[] decoder = new char[26]; //Decryption array;

    public CesarCipher(int rotation) {
        for(int k = 0; k < 26; k++) {
            encoder[k] = (char) ('A' + (k + rotation) % 26);
            decoder[k] = (char) ('A' + (k - rotation + 26) % 26);
        }
    }

    public static void main(String... args) {
        CesarCipher cipher = new CesarCipher(3);
        System.out.println(cipher.encoder);
        System.out.println(cipher.decoder);
    }
}
