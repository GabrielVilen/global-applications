/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package se.kth.iv1201projekt.util;

/**
 *
 * @author Kim
 */
public class NotLoggedInException extends Exception {
    
    public NotLoggedInException(){
        super();
    }
    
    public NotLoggedInException(String message){
        super(message);
    }
    
    public NotLoggedInException(String message, Throwable throwable){
        super(message, throwable);
    }
}
