package handin;

import java.util.Stack;

public class MatchingBrackets {
    private Stack<Character> stack;
    private String errors;

    public MatchingBrackets()
    {
        stack = new Stack<Character>();
        errors = ""; //String to store errors in the evaluation of brackets
    }

    public String stringEvaluation(String s)
    {
        errors = ""; //reset string errors to empty string
        
        for (int k = 0; k < s.length(); k++) //loop through the input string
        {
            char newC = s.charAt(k);

            if (isOpeningBracket(newC))
                stack.push(newC); //push opening bracket onto the stack
            else if (isClosingBracket(newC))
            {
                char topC = stack.pop(); //pop opening bracket if the next bracket is a closing bracket

                if (!isBracketsMatched(topC, newC)) //if brackets do not match, push opening back to stack
                    stack.push(topC);
            }
        }

        //clear stack if stack is not empty
        if (!stack.isEmpty())
            clearStack();

        return errors;
    }
    
    //check if bracket is opening
    private boolean isOpeningBracket(char c)
    {
        return (c == '(' || c == '{' || c == '<' || c == '[');
    }
    
    //check if bracket is closing
    private boolean isClosingBracket(char c)
    {
        return (c == ')' || c == '}' || c == '>' || c == ']');
    }

    //method to compare opening and closing brackets and add errors to the string "errors" if brackets do not match
    private boolean isBracketsMatched(char topC, char newC)
    {
        boolean matching = false;
        
        if ((topC == '(' && newC == ')') || (topC == '{' && newC == '}') || (topC == '<' && newC == '>') || (topC == '[' && newC == ']'))
            matching = true;
        else
            addErrors(newC);
        
        return matching;
        
//        return ((topC == '(' && newC == ')') || (topC == '{' && newC == '}') || (topC == '<' && newC == '>') || (topC == '[' && newC == ']'));
    }
    
    //method to add errors depending on the what brackets it is
    private void addErrors(char c)
    {
        switch (c)
        {
            case ')': errors += '('; break;
            case '}': errors += '{'; break;
            case '>': errors += '<'; break;
            case ']': errors += '['; break;
            case '(': errors += ')'; break;
            case '{': errors += '}'; break;
            case '<': errors += '>'; break;
            case '[': errors += ']'; break;
        }
    }

    //method to clear stack and add errors to the string "errors" if stack is non-empty
    private void clearStack()
    {
        while (!stack.isEmpty())
            addErrors(stack.pop());
    }

    public static void main(String[] args)
    {
        MatchingBrackets mb = new MatchingBrackets();
        
        //input list of strings
        String[] stringList = {"{((2 x 5)+(3*-2 + 5))}", 
            "{ (2 x 5)+(3*-2 + 5))}",
            "List<List<E>>",
            "List<List<E>",
            "{(<<eeeek>>){}{…}(e(e)e){hello}}",
            "{(< eeeek>>){}{…} e(e)e){hello}"
        };
        
        //loop through the strings and evaluate them
        for (String aString: stringList)
        {
            String s = mb.stringEvaluation(aString);
            if (!s.isEmpty())
                System.out.println(aString + " - Will not evaluate. Missing brackets: " + s);
            else
                System.out.println(aString + " - Will evaluate successfully.");
        }
    }
}
