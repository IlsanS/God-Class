/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.States;

import GUI.Mediator;

/**
 *
 * @author Sebastien
 */
public class InitState implements State
{

    @Override
    public void doAction(Mediator mediator)
    {
        System.out.println("Initialisation");
        mediator.init();
    }
    
}
