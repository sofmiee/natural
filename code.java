import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Natural {

    public static List<String> factorize(BigInteger n) {
        List<String> factors = new ArrayList<>();

        if (n.compareTo(BigInteger.ONE) <= 0) { //BigInteger.ONE это 1, compareTo сравнивает числа n<1 -> -1, n=1 -> 0
            throw new IllegalArgumentException("Число должно быть натуральным и больше 1."); //выкидываем ошибку
        }

        //проверка делимости на 2
        int count = 0;
        while (n.mod(BigInteger.TWO).equals(BigInteger.ZERO)) { //mod - остаток
            count++;
            n = n.divide(BigInteger.TWO); //частное двух чисел
        }
        if (count > 0) {
            factors.add("2^" + count);
        }

        //проверка делимости на нечетные числа
        BigInteger i = BigInteger.valueOf(3);
        BigInteger limit = n.sqrt().add(BigInteger.ONE);
        while (i.compareTo(limit) <= 0) {
            count = 0;
            while (n.mod(i).equals(BigInteger.ZERO)) {
                count++;
                n = n.divide(i);
            }
            if (count > 0) {
                factors.add(i + "^" + count);
                limit = n.sqrt().add(BigInteger.ONE); //обновляем лимит после деления
            }
            i = i.add(BigInteger.TWO); //переходим к следующему нечетному числу
        }

        //если n осталось больше 1, то оно простое
        if (n.compareTo(BigInteger.ONE) > 0) {
            factors.add(n + "^1");
        }

        return factors;
    }

    public static void main(String[] args) {
        BigInteger number = new BigInteger("922337203685477578");
        List<String> factors = factorize(number);
        System.out.println("Факторы числа " + number + ": " + factors);
    }
}
