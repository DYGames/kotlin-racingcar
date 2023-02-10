package racingcar.entity

class Position(private var position: Int) {
    fun addPosition(value: Int) {
        position += value
    }

    fun compareTo(position: Position): Boolean {
        return this.position >= position.position
    }

    override fun toString(): String {
        return "-".repeat(position)
    }
}