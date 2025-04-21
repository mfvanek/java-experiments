package io.github.mfvanek.yandex.interview;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ATMTest {

    final ATM atm = new ATM();

    @Test
    void canLoadATM() {
        assertThat(atm.loadCash(Map.ofEntries(
                Map.entry(1000, 2),
                Map.entry(100, 5)
        )))
                .isEmpty();

        assertThat(atm.canWithdrawCash(2499))
                .isFalse();
        assertThat(atm.canWithdrawCash(2500))
                .isTrue();
        assertThat(atm.canWithdrawCash(2600))
                .isFalse();
        assertThat(atm.canWithdrawCash(2501))
                .isFalse();

//        assertThat(atm.canWithdrawCash(600))
//                .isFalse(); // TODO
    }

    @Test
    void canWithdrawCash() {
        atm.loadCash(Map.ofEntries(
                Map.entry(1000, 2),
                Map.entry(100, 5)
        ));

        // TODO need to fix
        assertThat(atm.getCash(100))
                .hasSize(1)
                .containsExactly(Map.entry(100, 1));

        assertThat(atm.getCash(1000))
                .hasSize(1)
                .containsExactly(Map.entry(1000, 1));

//        assertThat(atm.getCash(2500))
//                .hasSize(2)
//                .contains(Map.entry(1000, 2), Map.entry(100, 5));

        assertThatThrownBy(() -> atm.getCash(10))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Cannot withdraw requested amount 10");
    }

    @Test
    void shouldReduceAmountInAtm() {
        atm.loadCash(Map.ofEntries(
                Map.entry(1000, 2),
                Map.entry(100, 5)
        ));

        assertThat(atm.getCash(2500))
                .hasSize(2)
                .contains(Map.entry(1000, 2), Map.entry(100, 5));

        assertThatThrownBy(() -> atm.getCash(100))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Cannot withdraw requested amount 100");
    }
}
