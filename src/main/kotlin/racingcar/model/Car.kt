package racingcar.model

import racingcar.exceptions.CarExceptions

class Car(private var name: String) {
    private val exception = CarExceptions()
    private var position: Int = 0

    fun forward() {
        position++
    }

    fun compareTo(car: Car): Boolean {
        return this.position >= car.position
    }

    override fun toString(): String {
        return name + " : " + "-".repeat(position)
    }

    fun getName() = name
}
