package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class LottoTest {
    @ParameterizedTest
    @MethodSource("matchLottoNumbers")
    fun `일치하는 번호 개수에 따라 Rank를 구한다`(numbers: List<Int>, result: Rank) {
        val winning = WinningLotto(listOf(1, 2, 3, 4, 5, 6), 7)
        assertThat(
            Lotto.create(numbers).getCountOfMatch(winning)
        ).isEqualTo(result)
    }

    @Test
    fun `로또 숫자 개수가 6개가 맞지 않는 경우 생성 시 오류가 발생한다`() {
        val list = listOf(1, 2, 3, 4, 5)
        assertThrows<IllegalArgumentException> { Lotto.create(list) }
    }

    @Test
    fun `로또 번호 내에 중복된 번호가 있는 경우 생성 시 오류가 발생한다`() {
        val list = listOf(1, 2, 3, 4, 4, 5)
        assertThrows<IllegalArgumentException> { Lotto.create(list) }
    }

    @Test
    fun `로또 번호 내에 범위를 벗어난 번호가 있는 경우 생성 시 오류가 발생한다`() {
        val list = listOf(1, 2, 3, 4, 5, 66)
        assertThrows<IllegalArgumentException> { Lotto.create(list) }
    }

    companion object {
        @JvmStatic
        private fun matchLottoNumbers(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    listOf(1, 2, 3, 9, 10, 11), Rank.FIFTH
                ),
                Arguments.of(
                    listOf(1, 2, 3, 4, 5, 6), Rank.FIRST
                ),
                Arguments.of(
                    listOf(1, 2, 3, 4, 5, 9), Rank.THIRD
                ),
                Arguments.of(
                    listOf(1, 2, 3, 4, 5, 7), Rank.SECOND
                )
            )
        }
    }
}
