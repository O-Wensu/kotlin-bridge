package bridge.View

import bridge.Model.BridgeResult

/**
 * 사용자에게 게임 진행 상황과 결과를 출력하는 역할을 한다.
 */
class OutputView {
    private val GAME_START = "다리 건너기 게임을 시작합니다."
    private val BRIDGE_SIZE = "다리의 길이를 입력해주세요."
    private val BRIDGE_SELECT = "이동할 칸을 선택해주세요. (위: U, 아래: D)"
    private val GAME_OVER = "게임을 다시 시도할지 여부를 입력해주세요. (재시도: R, 종료: Q)"

    fun printStart() {
        println(GAME_START)
        println()
    }

    fun printBridgeSize() {
        println(BRIDGE_SIZE)
    }

    fun printBridgeSelect() {
        println()
        println(BRIDGE_SELECT)
    }

    fun printGameOver() {
        println()
        println(GAME_OVER)
    }

    /**
     * 현재까지 이동한 다리의 상태를 정해진 형식에 맞춰 출력한다.
     *
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    fun printMap(upResult: List<String>, downResult: List<String>) {
        println(upResult.joinToString(separator = " | ", prefix = "[ ", postfix = " ]"))
        println(downResult.joinToString(separator = " | ", prefix = "[ ", postfix = " ]"))
    }

    /**
     * 게임의 최종 결과를 정해진 형식에 맞춰 출력한다.
     *
     * 출력을 위해 필요한 메서드의 인자(parameter)는 자유롭게 추가하거나 변경할 수 있다.
     */
    fun printResult() {}
}
