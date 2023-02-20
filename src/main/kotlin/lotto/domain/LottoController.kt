package lotto.domain

import lotto.model.Lotto
import lotto.model.LottoNumber
import lotto.model.UserLotto
import lotto.model.WinningLotto
import lotto.model.generator.LottoGenerator
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
    //    outputView.printPurchase(numberOfLotto)
        val myLotto = getUserLotto(2, numberOfLotto)
        outputView.printUserLotto(myLotto)

        wrapUp(myLotto, money)
    }

    private fun wrapUp(myLotto: UserLotto, money: Int) {
        val winningLotto = getWinningLotto(getWinningNumber())
        val ranks = myLotto.getWinningStatistics(winningLotto)
        val rates = WinningCalculator.getEarningRate(money, WinningCalculator.getWinningMoney(ranks))
        outputView.printResult(ranks, rates)
    }

    fun getUserLotto(passiveNumber: Int, number: Int): UserLotto {
        val lotto = mutableListOf<Lotto>()
        getAutoLotto(number-passiveNumber, lotto)
        return UserLotto(lotto)
    }

    private fun getAutoLotto(number: Int, lotto: MutableList<Lotto>) {
        repeat(number) {
            lotto.add(Lotto.create(generator.generate()))
        }
    }

    private fun getMoney(): Int {
        return inputView.getNumber { outputView.printInsertMoneyMessage() }
    }

    private fun getWinningLotto(lotto: Lotto): WinningLotto {
        return validateInput {
            WinningLotto(lotto, getBonusNumber())
        } ?: getWinningLotto(lotto)
    }

    private fun getBonusNumber(): LottoNumber {
        return validateInput {
            LottoNumber.create(inputView.getNumber { outputView.printInsertBonusNumber() })
        } ?: getBonusNumber()
    }

    private fun getWinningNumber(): Lotto {
        return validateInput {
            Lotto.create(inputView.getNumberList { outputView.printInsertWinningNumber() })
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
