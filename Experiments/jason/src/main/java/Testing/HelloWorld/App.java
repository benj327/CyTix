package   main.java.Testing;

import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser; 

public class App {
    /* public static void main (String args[]) throws Exception{
        System.out.println("yes");
    } */
    public static void main (String args[]) throws Exception{
        ExpressionParser parser = new SpelExpressionParser();  
        Expression exp = parser.parseExpression("'Hello World!'"); 
    
        String message = (String) exp.getValue();  
    
        System.out.println("Hello " + message + "!");     
    }
}
