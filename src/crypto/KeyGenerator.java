package crypto;

public class KeyGenerator extends MathsMethods{ //генерация всех ключей
        private long p, q, n, s, k;

    public KeyGenerator(long M) {
        this.p = generateP(M);

        this.q = generateQ(p, M);

        this.n = p * q;

        while (true) {
            this.p = generateP(M);
            this.q = generateQ(p, M);
            this.n = p * q;
            if (jacobySign(M, n) == -1 || jacobySign(M, n) == 1)
                break;
        }

        this.s = generateS(n, p, q);
        this.k = ((p - 1) * (q - 1) / 4 + 1) / 2;
        System.out.println("Генерация ключей завершена!");
    }

//    private static long[] generateKeys(long M) {
//        long[] keys = new long[5];



//        keys[0] = p;
//        keys[1] = q;
//        keys[2] = n;
//        keys[3] = s;
//        keys[4] = k;
//        return keys;
//    }

    // Генераторы ключей
    private static long generateP(long M) { //генерируем p
        long p = randomIntInRange(M + 1, M + 1000); // генерируем простое число от M+1
        while (!isPrime(p) || p % 8 != 3) { //проверяем пока оно не простое
            p = randomIntInRange(M + 1, M + 1000);
        }
        return p;
    }

    private static long generateQ(long p, long M) { //генерируем q
        long q = randomIntInRange(M + 1, M + 1000);
        while (!isPrime(q) || (q % 8 != 7) || q == p) { //проверяем пока оно не простое
            q = randomIntInRange(M + 1, M + 1000);
        }
        return q;
    }

    private static long generateS(long n, long p, long q) { // генерируем число s
        long s = randomIntInRange(2, 10);
        while (jacobySign(s, n) != -1) {
            s = randomIntInRange(2, 10);
        }
        return s;
    }

    public long getP() {
        return p;
    }

    public long getQ() {
        return q;
    }

    public long getN() {
        return n;
    }

    public long getS() {
        return s;
    }

    public long getK() {
        return k;
    }

    @Override
    public String toString() {
        return "p: " + this.p + " - в секрете\n"+"q: " + this.q + " - в секрете\n"+"n: " + this.n + " - публикуется\n"+
                "s: " + this.s + " - публикуется\n"+"k: " + this.k + " - в секрете\n";
    }
}
