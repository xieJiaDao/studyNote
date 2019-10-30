package com.ioc.simpleImplementation.humen;

import com.ioc.simpleImplementation.car.Car;

public abstract class HumenWithCar implements Humen {
    protected Car car;

    public HumenWithCar(Car car) {
        this.car = car;
    }

    public abstract void goHome();
}
