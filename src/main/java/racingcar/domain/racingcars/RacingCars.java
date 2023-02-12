package racingcar.domain.racingcars;

import racingcar.domain.car.Car;
import racingcar.domain.numbergenerator.NumberGenerator;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RacingCars {
    private static final int MIN_NUMBER_OF_CARS = 2;
    private static final int MOVEMENT_CONDITION = 4;

    private final List<Car> racingCars;

    public RacingCars(List<Car> racingCars) {
        validate(racingCars);
        this.racingCars = racingCars;
    }

    private void validate(List<Car> repository) {
        if (isOutOfNumberLimit(repository)) {
            throw new IllegalArgumentException("[ERROR] 자동차는 2대 이상 입력되어야 합니다.");
        }
        if (hasDuplication(repository)) {
            throw new IllegalArgumentException("[ERROR] 자동차 이름은 중복될 수 없습니다.");
        }
    }

    private boolean isOutOfNumberLimit(List<Car> repository) {
        return repository.size() < MIN_NUMBER_OF_CARS;
    }

    private boolean hasDuplication(List<Car> repository) {
        return repository.stream()
                .distinct()
                .count() != repository.size();
    }

    public Map<String, Integer> moveForwardOrStay() {
        NumberGenerator numberGenerator = new NumberGenerator();
        return racingCars.stream()
                .map(car -> checkMovementCondition(car, numberGenerator.generateRandomNumber()))
                .collect(Collectors.toMap(Car::getName,
                        Car::getPosition,
                        (oldValue, newValue) -> oldValue,
                        LinkedHashMap::new));
    }

    private Car checkMovementCondition(Car car, int randomNumber) {
        if (randomNumber >= MOVEMENT_CONDITION) {
            car.move();
        }
        return car;
    }
}
