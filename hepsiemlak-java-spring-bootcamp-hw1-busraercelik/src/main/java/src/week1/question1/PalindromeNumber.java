package src.week1.question1;

import java.util.*;

public class PalindromeNumber {

    static Map<Integer, Set<Long>> integerMap = new HashMap<>();
    static Set<Long> integerList = new LinkedHashSet<>();

    static void findPalindromeNum(long num, int originalNum) {
        long sum = num + getReversedNum(num);
        integerList.add(num);
        integerList.add(getReversedNum(num));
        integerList.add(sum);
        long reversedOfSum = getReversedNum(sum);

        integerList.add(reversedOfSum);

        if (sum == reversedOfSum) {
            // found a palindrome
            integerMap.put(originalNum, new LinkedHashSet<>(integerList));
            integerList.clear();
        } else {
            findPalindromeNum(sum, originalNum);
        }

    }

    static long getReversedNum(long num) {
        long reversedNum = 0;

        while (num != 0) {
            long remainder = num % 10;
            reversedNum = reversedNum * 10 + remainder;
            num /= 10;
        }
        return reversedNum;
    }

    public static void main(String[] args) {
        for (int i=10 ; i<= 100; i++) {
            findPalindromeNum(i, i);
        }

// find max try count
        Map.Entry<Integer, Set<Long>> integerListEntry = integerMap.entrySet()
                .stream()
                .max(Comparator.comparing(e -> {
                    return e.getValue().size();
                }))
                .orElse(null);
// print all transactions
        System.out.println("Longest chain is: ");
        System.out.println(integerListEntry);
        System.out.println("Longest chain found for number : " + integerListEntry.getKey());
        System.out.println("Size of longest chain: " + integerListEntry.getValue().size());
    }
}
