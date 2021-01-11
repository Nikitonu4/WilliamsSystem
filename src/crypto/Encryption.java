package crypto;


public class Encryption extends MathsMethods {


    private static long[] encryption(long M, long n, long s) { //зашифрование
        System.out.println("\nЗАШИФРОВКА ТЕКСТА: " + M);
        long[] send = new long[3];
        long C, C1, C2, M1;

        if (jacobySign(M, n) == -1) { /* символ Якоби принимает значения -1 или 1
                                           (0 на этапе подбора p и q отсекается) */
            C1 = 1;                     // (-1) в любой нечетной степени == -1 (-1^1 = -1)
        } else if (jacobySign(M, n) == 1) {
            C1 = 0;                        // соответственно -1^0 = 1
        } else {
            System.out.println("Символ Якоби равен нулю");
            return null;
        }

        // вычисляем M1
        double pow = (Math.pow(s, C1)) % n;
        M1 = (long) ((pow * M) % n);

        // вычисляем C2, которое определяет знак при расшифровке
        C2 = M1 % 2;

        //вычисляем криптограмму
        C = powModuleInt(M1, 2, n);

        System.out.println("M1: " + M1);
        System.out.println("C1: " + C1);
        System.out.println("C2: " + C2);
        System.out.println("C: " + C);

        send[0] = C;
        send[1] = C1;
        send[2] = C2;

        return send;
    }
}
