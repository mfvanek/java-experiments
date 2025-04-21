package io.github.mfvanek.yandex.interview;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Yandex interview 21 april 2025
 * <p>
 * Банкомат.
 * Инициализируется набором купюр и умеет выдавать купюры для заданной суммы, либо отвечать отказом.
 * При выдаче купюры списываются с баланса банкомата.
 * Допустимые номиналы: 50₽, 100₽, 500₽, 1000₽, 5000₽.
 * <p>
 * Другие валюты и номиналы должны легко добавляться разработчиками в будущем.
 * Многопоточные сценарии могут быть добавлены позже (например резервирование).
 */
public class ATM {

    private final AtomicReference<Map<Integer, Integer>> allCash = new AtomicReference<>(Map.of());

    public Map<Integer, Integer> loadCash(Map<Integer, Integer> newCash) {
        return allCash.getAndSet(new HashMap<>(newCash));
    }

    public Map<Integer, Integer> getCash(final int sumToWithdraw) {
        // можем ли выдать
        // сумма кратна минимальному номиналу
        if (!canWithdrawCash(sumToWithdraw)) {
            throw new IllegalArgumentException("Cannot withdraw requested amount " + sumToWithdraw);
        }

        int remain = sumToWithdraw;
        final Map<Integer, Integer> result = new HashMap<>();
        Map<Integer, Integer> cashMap = allCash.get();
        List<Integer> banknotes = cashMap.keySet().stream().sorted(Comparator.reverseOrder()).toList();
        for (int k : banknotes) {

            if (remain <= 0) break;

            final int availableCount = cashMap.get(k);
            if (availableCount <= 0) continue;

            if (remain >= k) {
                int c = remain / k;
                if (c <= availableCount) {
                    result.put(k, c);
                    remain = remain - k * c;
                } else {
                    result.put(k, availableCount);
                    remain = remain - k * availableCount;
                }
            }
        }

        // TODO check remain

        reduceAmount(result);
        return result;
    }

    // visible for testing
    boolean canWithdrawCash(int sumToWithdraw) {
        int sum = allCash.get().entrySet().stream().mapToInt(e -> e.getKey() * e.getValue()).sum();
        if (sum < sumToWithdraw) {
            return false;
        }
        final int minBanknote = allCash.get().keySet().stream().mapToInt(e -> e).min().getAsInt();
        return sumToWithdraw % minBanknote == 0;
    }

    void reduceAmount(Map<Integer, Integer> toReduce) {
        Map<Integer, Integer> cashMap = allCash.get();
        toReduce.entrySet().forEach(e -> {
            cashMap.put(e.getKey(), cashMap.get(e.getKey()) - e.getValue());
        });
        allCash.set(cashMap);
    }
}
