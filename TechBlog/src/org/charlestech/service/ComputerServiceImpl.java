package org.charlestech.service;

import javax.jws.WebService;

/*
 * @Description Implementation for ComputerService
 * @author Charles Chen
 * @date 14-1-27
 * @version 1.0
 */
@WebService(endpointInterface = "org.charlestech.service.ComputerService")
public class ComputerServiceImpl implements ComputerService {
    public double add(double a, double b) {
        return a + b;
    }

    public double multiple(double a, double b) {
        return a * b;
    }

    public double minus(double a, double b) {
        return a - b;
    }
}
