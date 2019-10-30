package com.ioc.simpleImplementation.humen;

import com.ioc.simpleImplementation.car.Car;

public class ZhangSan extends HumenWithCar  {
    public ZhangSan(Car car) {
        super(car);
    }

    public void goHome() {
        System.out.println("张三回家");
        car.start();
        car.turnLeft();
        car.turnRight();
        car.stop();
    }
}
