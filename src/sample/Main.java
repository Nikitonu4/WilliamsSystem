package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Random;
import java.util.Scanner;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
        System.out.println("Введите число для шифра: ");
        Scanner sc = new Scanner(System.in);
        long M = sc.nextLong();
        long[] keys = generateKeys(M);

        long[] send = encryption(M, keys[2], keys[3]);
        System.out.println("Зашифровано! Криптограмма: " + send[0]);

        double decryptionText = decryption(send, keys[2], keys[3], keys[4]);
        System.out.println("РАСШИФРОВАННОЕ СООБЩЕНИЕ: " + decryptionText);
        System.out.println("OK");
    }


    public static void main(String[] args) {
        launch(args);

    }

    private static long[] generateKeys(long M) { //генерация всех ключей
        long[] keys = new long[5];
        long p, q, n, s, k;
        p = generateP(M);

        q = generateQ(p, M);

        n = p * q;

        while (true) {
            p = generateP(M);
            q = generateQ(p, M);
            n = p * q;
            if (jacobySign(M, n) == -1 || jacobySign(M, n) == 1)
                break;
        }

        s = generateS(n, p, q);
        k = ((p - 1) * (q - 1) / 4 + 1) / 2;

        System.out.println("p: " + p + " - в секрете");
        System.out.println("q: " + q + " - в секрете");
        System.out.println("n: " + n + " - публикуется");
        System.out.println("s: " + s + " - публикуется");
        System.out.println("k: " + k + " - в секрете");

        System.out.println("Генерация ключей завершена!");
        keys[0] = p;
        keys[1] = q;
        keys[2] = n;
        keys[3] = s;
        keys[4] = k;
        return keys;
    }

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

    private static long randomIntInRange(long min, long max) { //генератор чисел по промежутку
        Random rand = new Random();
        long randomNum = rand.nextInt((int) ((max - min) + 1)) + min;

        return randomNum;
    }

    // вспомогательные методы
    private static long jacobySign(long a, long b) // a - целое, n - натуральное > 1, нечетное
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

    private static boolean isPrime(long n) { //проверка простоты
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

    private static long powModuleInt(long x, long power, long module) { // возведение в положительную степень по модулю простого числа
        if (module == 0)
            return 1;
        long answer = 1;

        for (long i = 0; i < power; i++) {
            answer *= x;
            answer %= module;
        }
        return answer;
    }

    private static long getExtendGCD(long a, long n) { //расширенный алгоритм Евклида
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
