package com.ioc.simpleImplementation.humen;

import com.ioc.simpleImplementation.car.Car;

public class LiSi extends HumenWithCar {
    public LiSi(Car car) {
        super(car);
    }

    public void goHome() {
        System.out.println("李四回家");
        car.start();
        car.turnLeft();
        car.turnRight();
        car.stop();
    }
}
