/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import model.Dizajner;
import operacije.LoginOperacija;

/**
 *
 * @author N
 */
public class Controller {
    private static Controller instance;

    private Controller() {
    }
    
    public static Controller getInstance(){
        if (instance==null){
            instance=new Controller();
        }
        return instance;
    }

    public Dizajner login(Dizajner d) throws Exception {
        LoginOperacija o =new LoginOperacija();
        o.izvrsi(d,null);
        System.out.println("klasa controller:"+o.getDizajner());
        return o.getDizajner();
    }
    
    
}
