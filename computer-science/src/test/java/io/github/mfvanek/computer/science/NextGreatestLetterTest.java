package io.github.mfvanek.computer.science;

import org.junit.jupiter.api.Test;

import static io.github.mfvanek.computer.science.NextGreatestLetter.nextGreatestLetter;
import static org.assertj.core.api.Assertions.assertThat;

class NextGreatestLetterTest {

    @Test
    void shouldWork() {
        assertThat(nextGreatestLetter(new char[]{'c', 'f', 'j'}, 'c'))
                .isEqualTo('f');
    }
}
