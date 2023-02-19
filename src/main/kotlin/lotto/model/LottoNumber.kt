package lotto.model

import lotto.view.ERROR_OUT_OF_RANGE

class LottoNumber(val number: Int) : Comparable<LottoNumber> {

    init {
        require(number in START_LOTTO_RANGE..END_LOTTO_RANGE) { ERROR_OUT_OF_RANGE }
    }

    override fun compareTo(other: LottoNumber): Int {
        return number.compareTo(other.number)
    }

    override fun equals(other: Any?): Boolean {
        return number == (other as LottoNumber).number
    }

    companion object {
        private const val START_LOTTO_RANGE = 1
        private const val END_LOTTO_RANGE = 45
        val RANGE_LOTTO_NUMBER = START_LOTTO_RANGE..END_LOTTO_RANGE
    }
}
