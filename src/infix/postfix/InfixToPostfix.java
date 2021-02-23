package infix.postfix;

import java.util.Stack;

public class InfixToPostfix {
    public String infixToPost(String infixString){
        String postfix = "";
        //step 1
        Stack<Character> stack = new Stack<>();
        stack.push('(');
        infixString = infixString.concat("" + ')');
        //step 2
        for (int i = 0; i < infixString.length() ; i++) {
            char character = infixString.charAt(i);
            //step 3
            if(Character.isAlphabetic(character)){
                postfix = postfix.concat("" + character);
            }
            //step 4
            else if(character == '('){
                stack.push(character);
            }
            //step 5
            else if(isOperator(character)){
                if(stack.peek() != '('){
                    while(!stack.empty()){
                        if(precedence(stack.peek())>= precedence(character)){
                            postfix = postfix.concat("" + stack.pop());
                        }
                        else{
                            stack.push(character);
                            break;
                        }
                    }
                }
                else{
                    stack.push(character);
                    // have to break the loop if operator pushed into stack
                    break;
                }
            }
            //step 6
            else if(character == ')'){
                while(!stack.empty()){
                    if(stack.peek() != '('){
                        postfix = postfix.concat("" + stack.pop());
                    }
                    else{
                        stack.pop();
                        //have to break the loop, if left parenthesis popped out
                        break;
                    }
                }
            }

        }
        return postfix;
    }
    private boolean isOperator(char character){
        boolean response = false;
        switch (character){
            case '^':
            case '/':
            case '*':
            case '+':
            case '-':
                response = true;
        }
        return response;
    }
    private int precedence(char operator){
        int response = 0;
        switch (operator){
            case '^':
                response = 3;
                break;
            case '/':
            case '*':
                response = 2;
                break;
            case '+':
            case '-':
                response = 1;
        }
        return response;
    }
}
