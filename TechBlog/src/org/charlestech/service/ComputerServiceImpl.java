package org.charlestech.service;

/*
 * @Description Implementation for ComputerService
 * @author Charles Chen
 * @date 14-1-27
 * @version 1.0
 */
public class ComputerServiceImpl implements ComputerService {
    @Override
    public double add(double a, double b) {
        return a + b;
    }

    @Override
    public double multiple(double a, double b) {
        return a * b;
    }

    @Override
    public double minus(double a, double b) {
        return a - b;
    }
}
