/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1201projekt.util;

/**
 * This exception is used to represent a failed attempt to login.
 * @author Kim
 */
public class LoginErrorException extends Exception {
    
    public LoginErrorException(){
        super();
    }
    
    public LoginErrorException(String message){
        super(message);
    }
    
    public LoginErrorException(String message, Throwable throwable){
        super(message, throwable);
    }
}
