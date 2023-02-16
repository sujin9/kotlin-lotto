package lotto.domain

import lotto.model.Lotto
import lotto.model.LottoNumber
import lotto.model.UserLotto
import lotto.model.WinningLotto
import lotto.model.generator.LottoGenerator
import lotto.view.ERROR_NOT_DIVIDED
import lotto.view.InputView
import lotto.view.OutputView

class LottoController(
    private val inputView: InputView = InputView(),
    private val outputView: OutputView = OutputView(),
    private val generator: LottoGenerator = LottoGenerator()
) {
    fun start() {
        val money = getMoney()
        val numberOfLotto = getNumberOfLotto(money)
        outputView.printPurchase(numberOfLotto)
        val myLotto = getUserLotto(numberOfLotto)
        outputView.printUserLotto(myLotto)

        val winningLotto = getWinningLotto(getWinningNumber())
        val ranks = myLotto.getWinningStatistics(winningLotto)
        val rates = WinningCalculator.getEarningRate(money, WinningCalculator.getWinningMoney(ranks))
        outputView.printResult(ranks, rates)
    }

    private fun getUserLotto(number: Int): UserLotto {
        val lottos = mutableListOf<Lotto>()
        repeat(number) {
            lottos.add(Lotto(generator.generate()))
        }

        return UserLotto(lottos)
    }

    private fun getMoney(): Int {
        outputView.printInsertMoneyMessage()
        val money = inputView.getNumber()
        val result = runCatching {
            require(isDivided(money)) { ERROR_NOT_DIVIDED }
        }.onFailure {
            println(it.message)
        }.isSuccess

        return if (result) money else getMoney()
    }

    private fun getWinningLotto(lotto: Lotto): WinningLotto {
        return validateInput {
            WinningLotto(lotto, getBonusNumber())
        } ?: getWinningLotto(lotto)
    }

    private fun getBonusNumber(): LottoNumber {
        outputView.printInsertBonusNumber()

        return validateInput {
            LottoNumber(inputView.getNumber())
        } ?: getBonusNumber()
    }

    private fun getWinningNumber(): Lotto {
        outputView.printInsertWinningNumber()

        return validateInput {
            Lotto(inputView.getNumberList())
        } ?: getWinningNumber()
    }

    private fun <T> validateInput(create: () -> T): T? {
        return runCatching {
            create()
        }.onFailure {
            println(it.message)
        }.getOrNull()
    }

    fun getNumberOfLotto(money: Int): Int {
        return money / LOTTO_PRICE
    }

    fun isDivided(money: Int): Boolean {
        return money % LOTTO_PRICE == 0
    }

    companion object {
        private const val LOTTO_PRICE = 1000
    }
}
