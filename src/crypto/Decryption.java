package crypto;

public class Decryption extends MathsMethods{


    private static long decryption(long[] send, long n, long s, long k) {  //расшифровка
        long startTime = System.currentTimeMillis();
        long C = send[0];
        long C1 = send[1];
        long C2 = send[2];
        System.out.println("\nРАСШИФРОВКА КРИПТОТЕКСТА" +
                " С КЛЮЧАМИ (C, C1, C2): (" + C + ", " + C1 + ", " + C2 + ")");
        long M2 = powModuleInt(C, k, n);

        if (C2 == 0) { //M2 должно быть четным
            if (M2 % 2 != 0) {
                M2 = Math.floorMod(-M2, n);
            }
        } else { //M2 должно быть нечетным
            if (M2 % 2 == 0) {
                M2 = Math.floorMod(-M2, n);
            }
        }

        // вычисляем исходный текст
        long M;

        if (C1 == 1) {
            long pow = getExtendGCD(s, n); //обратный элемент
            M = Math.floorMod(pow * M2, n);
        } else {
            M = M2 % n;
        }

        System.out.println("M2: " + M2);
        System.out.println("M: " + M);
        long endTime = System.currentTimeMillis();
        System.out.println("Время расшифровки: " + (endTime - startTime) + " мс");
        return M;
    }
}
