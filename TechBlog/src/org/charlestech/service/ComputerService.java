package org.charlestech.service;

import javax.jws.WebService;

/*
 * @Description Operating Simple Data Computing
 * @author Charles Chen
 * @date 14-1-27
 * @version 1.0
 */
@WebService
public interface ComputerService {
    double add(double a, double b);

    double multiple(double a, double b);

    double minus(double a, double b);
}
