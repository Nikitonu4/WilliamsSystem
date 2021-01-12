package crypto;


public class Encryption extends MathsMethods {

    private long C, C1, C2, M1;

    public Encryption(long M, long n, long s) { //зашифрование
        System.out.println("\nЗАШИФРОВКА ТЕКСТА: " + M);
        if (jacobySign(M, n) == -1) { /* символ Якоби принимает значения -1 или 1
                                           (0 на этапе подбора p и q отсекается) */
            C1 = 1;                     // (-1) в любой нечетной степени == -1 (-1^1 = -1)
        } else if (jacobySign(M, n) == 1) {
            C1 = 0;                        // соответственно -1^0 = 1
        }

        // вычисляем M1
        double pow = (Math.pow(s, C1)) % n;
        M1 = (long) ((pow * M) % n);

        // вычисляем C2, которое определяет знак при расшифровке
        C2 = M1 % 2;

        //вычисляем криптограмму
        C = powModuleInt(M1, 2, n);
    }

    public long getC() {
        return C;
    }

    public long getC1() {
        return C1;
    }

    public long getC2() {
        return C2;
    }

    @Override
    public String toString() {
        return "M1: " + this.M1 + "\nC1: " + this.C1 + "\nC2: " + this.C2 +"\nC: " + this.C;
    }
}

