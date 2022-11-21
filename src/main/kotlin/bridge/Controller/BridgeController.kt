package bridge.Controller

import bridge.BridgeMaker
import bridge.BridgeRandomNumberGenerator
import bridge.Model.BridgeData.bridgeLocation
import bridge.Model.BridgeData.bridgeShape
import bridge.Model.BridgeData.bridgeSize
import bridge.Model.BridgeData.isPlay
import bridge.Model.BridgeData.resetData
import bridge.Model.BridgeGame
import bridge.Model.BridgeGame.Companion.downResult
import bridge.Model.BridgeGame.Companion.finalResult
import bridge.Model.BridgeGame.Companion.tryCount
import bridge.Model.BridgeGame.Companion.upResult
import bridge.Model.BridgeResult
import bridge.Model.Referee
import bridge.View.InputView
import bridge.View.OutputView
import bridge.util.Constant.LOSE
import bridge.util.Constant.QUIT
import bridge.util.Constant.RETRY

class BridgeController {
    private var inputView = InputView()
    private var outputView = OutputView()
    private var bridgeGame = BridgeGame()
    private var bridgeSelect = ""

    fun startGame() {
        outputView.printStart()
        getBridgeSize()
        makeBridge()
        processGame()
    }

    fun processGame() {
        while (isPlay) {
            if (checkLastRound()) {
                break
            }
            bridgeSelect = getBridgeSelect()
            moveBridge()
        }
        if (finalResult == LOSE) {
            getGameCommand()
        }
    }

    fun getBridgeSize() {
        outputView.printBridgeSize()
        bridgeSize = inputView.readBridgeSize()
    }

    fun makeBridge() {
        var bridgeRandomNumberGenerator = BridgeRandomNumberGenerator()
        var bridgeMaker = BridgeMaker(bridgeRandomNumberGenerator)
        bridgeShape = bridgeMaker.makeBridge(bridgeSize)
    }

    fun getBridgeSelect(): String {
        outputView.printBridgeSelect()
        return inputView.readMoving()
    }

    fun moveBridge() {
        val result = bridgeGame.compareState(bridgeSelect)
        if (result == BridgeResult.UP_WIN || result == BridgeResult.DOWN_WIN) {
            bridgeGame.move()
        } else {
            bridgeGame.miss()
            isPlay = false
        }
        printRoundResult(result)
    }

    fun printRoundResult(result: BridgeResult) {
        bridgeGame.makeUpDownResult(result)
        outputView.printMap(upResult, downResult)
    }

    fun checkLastRound(): Boolean {
        var referee = Referee()
        if (referee.judgeLastBridge(bridgeLocation)) {
            printFinalResult()
            return true
        }
        return false
    }

    fun getGameCommand() {
        outputView.printGameOver()
        var command = inputView.readGameCommand()
        when (command) {
            RETRY -> {
                retryGame()
                processGame()
            }

            QUIT -> {
                printFinalResult()
            }
        }
    }

    fun retryGame() {
        bridgeGame.retry()
        resetData()
        isPlay = true
    }

    fun printFinalResult() {
        outputView.printFinalResult()
        outputView.printMap(upResult, downResult)
        outputView.printResult(finalResult, tryCount)
    }

}