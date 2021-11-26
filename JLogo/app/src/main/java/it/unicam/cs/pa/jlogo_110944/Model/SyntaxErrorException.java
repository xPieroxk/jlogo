package it.unicam.cs.pa.jlogo_110944.Model;

/**
 * Thrown when the arguments of a logo command are wrong.
 */
public class SyntaxErrorException extends Exception{

    public SyntaxErrorException(String errorMessage){
        super(errorMessage);
    }
}
