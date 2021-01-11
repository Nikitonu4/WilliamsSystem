package crypto;

import java.util.Random;

public class MathsMethods {

    static long randomIntInRange(long min, long max) { //генератор чисел по промежутку
        Random rand = new Random();
        long randomNum = rand.nextInt((int) ((max - min) + 1)) + min;

        return randomNum;
    }

    // вспомогательные методы
    static long jacobySign(long a, long b) // a - целое, n - натуральное > 1, нечетное
    {
        if (b % 2 == 0) { // символ Якоби существует только для нечетных
            return 0;
        }

        if (a == 0) return 0; // J(0,n) = 0
        if (a == 1) return 1; // J(1,n) = 1

        if (gcd(a, b) != 1) { //проверка взаимной простоты
            return 0;
        }

        int r = 1;

        if (a < 0) { // переход к положительным числам
            a = -a;
            if (b % 4 == 3) {
                r = -r;
            }
        }

        while (a != 0) {
            long t = 0;
            while (a % 2 == 0) {
                t++;
                a /= 2;
            }
            if (t % 2 != 0) {
                long mod = b % 8;
                if (mod == 3 || mod == 5) {
                    r = -r;
                }
            }

            long modA = a % 4;

            if ((modA == b % 4) && modA == 3) {
                r = -r;
            }
            long c = a;
            a = b % c;
            b = c;
        }
        return r;
    }

    static boolean isPrime(long n) { //проверка простоты
        boolean r = true;
        for (int i = 2; i <= (long) Math.sqrt(n); i++) {
            if (n % i == 0) {
                r = false;
                break;
            }
        }
        return r;
    }

    private static long gcd(long a, long b) { // НОД
        if (b == 0)
            return Math.abs(a);
        return gcd(b, a % b);
    }

    static long powModuleInt(long x, long power, long module) { // возведение в положительную степень по модулю простого числа
        if (module == 0)
            return 1;
        long answer = 1;

        for (long i = 0; i < power; i++) {
            answer *= x;
            answer %= module;
        }
        return answer;
    }

    static long getExtendGCD(long a, long n) { //расширенный алгоритм Евклида
        long n1 = n;
        long s1 = 1, s2 = 0;
        long t1 = 0, t2 = 1;
        while (n != 0) {
            long quotient = a / n;
            long r = a % n;
            a = n;
            n = r;
            long tempS = s1 - s2 * quotient;
            s1 = s2;
            s2 = tempS;
            long tempR = t1 - t2 * quotient;
            t1 = t2;
            t2 = tempR;
        }
        if (s1 < 0) {
            s1 += n1;
        }
        return s1;
    }
}
