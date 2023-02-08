package racingcar.controller

import racingcar.Util
import racingcar.exceptions.WorldExceptions
import racingcar.model.Car
import racingcar.view.InputView
import racingcar.view.OutputView
import java.util.Random

class World {
    val cars = mutableListOf<Car>()
    var attemptCount = 0
    private val outputView = OutputView()
    private val inputView = InputView()
    private val exceptions = WorldExceptions()

    fun init() {
        Util.tryAndRerun {
            outputView.printMessage(OutputView.MSG_INPUT_CAR_NAME)
            val names = inputView.carNames()
            for (name in names) {
                cars.add(Car(name))
            }
            exceptions.validateCarCount(cars)
        }

        Util.tryAndRerun {
            outputView.printMessage(OutputView.MSG_INPUT_ATTEMPT_COUNT)
            attemptCount = inputView.attemptCount()
            exceptions.validateAttemptCount(attemptCount)
        }
    }

    fun run() {
        for (i in 0 until cars.size) {
            processStep(cars[i], generateRandom())
        }
        outputView.printMessage(OutputView.MSG_STEP_RESULT)
        outputView.stepResult(cars)
    }

    fun quit() {
        outputView.winner(determineWinner())
    }

    fun determineWinner(): List<Car> {
        val sortedCars = cars.sortedWith { car, car2 -> if (car.compareTo(car2)) -1 else 1 }
        return sortedCars.filter { it.compareTo(sortedCars[0]) }
    }

    fun processStep(car: Car, number: Int) {
        exceptions.validateProcessStep(car, number)
        if (number >= 4) {
            car.forward()
        }
    }

    private fun generateRandom(): Int {
        return Random().nextInt(10)
    }
}