package com.cpf.chajson;


import java.util.Stack;

public class Json {

    private int cursor=0;

    private Stack<Integer> braceStack=new Stack();

    private Stack<Integer> bracketStack=new Stack();

    private Stack<Integer> termStack=new Stack();

    private Stack<Integer> preTermStack=new Stack();


    private Stack<Integer> currentStack;


    public Object parse(String jsonStr)
    {

        char[] jsonCharArray= jsonStr.toCharArray();

        for(int i=0;i<jsonCharArray.length;i++)
        {
            char currentChar=jsonCharArray[i];


            if(currentChar==' ')
            {
                continue;
            }

            //check
            if(currentStack==braceStack)
            {
                Assert.check(currentChar=='"');
            }


            if(currentChar=='{')
            {
                braceStack.push(i);
                currentStack=braceStack;
                System.out.println(currentChar);
            }

            if(currentChar=='[')
            {
                bracketStack.push(i);
                currentStack=bracketStack;
                System.out.println(currentChar);
            }

            if(currentChar=='"')
            {
                if(termStack.size()>0)
                {
                    termStack.pop();
                    System.out.println();
                    System.out.println("term:end");
                }else {
                    termStack.push(i);
                    currentStack=bracketStack;
                    System.out.println("term:start");
                }

            }



            if((currentChar>='a'&&currentChar<='z')||(currentChar>='0'&&currentChar<='9'))
            {
                System.out.print(currentChar);
            }



        }


        return null;
    }

    public static void main(String args[])
    {
        Json json=new Json();
        json.parse("{\"a\":\"1\"}");
    }


}
