import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.Set;

public class StackDemo1 {
    public static void main(String[] args) {
        Calc calc = new Calc();
        double result = calc.calc("(1+2)*2/2+8*2-9");
        System.out.println("result :"+result);
    }
}

class Calc{
    /**
     * 
     * @param expression
     * @return
     */
    public double calc(String expression){
        expression = expression.replace(" ", "");
        char[] middle = toMiddlePattern(expression);
        return calcMiddlePattern(middle);
    }

    /**
     *  中缀式转后缀式
     *  从左至右依次遍历中缀表达式各个字符（需要准备一个字符栈存储操作符和括号）
     *  1、字符为 运算数 ：
     *   直接送入后缀表达式（注：需要先分析出完整的运算数）。
     *  2、字符为 左括号 ：
     *    直接入栈（注：左括号入栈后优先级降至最低）。
     *  3、字符为 右括号 ：
     *   直接出栈，并将出栈字符依次送入后缀表达式，直到栈顶字符为左括号（左括号也要出栈，但不送入后缀表达式）。
     *   总结：只要满足 栈顶为左括号 即可进行最后一次出栈。
     *  4、字符为 操作符 ：
     *   若栈空，直接入栈。
     *   若栈非空，判断栈顶操作符，若栈顶操作符优先级低于该操作符，该操作符入栈；否则一直出栈，并将出栈字符依次送入后缀表达式，直到栈空或栈顶操作符优先级低于该操作符，该操作符再入栈。
     *  总结：只要满足 栈空 或者 优先级高于栈顶操作符 即可停止出栈，并将该操作符入栈。
     *  5、重复以上步骤直至遍历完成中缀表达式，接着判断字符栈是否为空，非空则直接出栈，并将出栈字符依次送入后缀表达式。
     *  注：中缀表达式遍历完成，栈中可能还有字符未输出，故需要判断栈空。
     * @param expression
     * @return
     */
    public char[] toMiddlePattern(String expression){
        StringBuilder builder = new StringBuilder();
        char[] chars = expression.toCharArray();
        Deque<Character> stack = new ArrayDeque<>();
        Map<String,Integer> opratePiority = Map.of("+",1,"-",1,"*",2,"/",2,"(",0);
        for(char c : chars){
            if(c>='0' && c<='9'){
                builder.append(c);
            }else if(c=='('){
                stack.push(c);
            }else if(c==')'){
                char ca='0';
                do{
                    ca = stack.pop();
                    if(ca!='('){
                        builder.append(ca);
                    }
                }while(ca!='(' && !stack.isEmpty());
            }else{
                if(stack.isEmpty()){
                    stack.push(c);
                }else{
                    Integer pi = opratePiority.get(c+"");
                    if(pi!=null){
                        Integer pi2 = -1;
                        char c2 = stack.pop();
                        pi2 = opratePiority.get(c2+"");
                        if(pi2<pi){
                            stack.push(c2);
                            stack.push(c);                        
                        }else{
                            builder.append(c2);
                            while(!stack.isEmpty() && pi2>=pi){
                                c2 = stack.pop();
                                if(c2=='('){
                                    stack.push(c2);
                                    break;
                                }
                                pi2 = opratePiority.get(c2+"");
                                if(pi2>=pi){
                                    builder.append(c2);
                                }
                            }
                            stack.push(c);
                        }
                    }
                }
            }
        }
        while(!stack.isEmpty()){
            builder.append(stack.pop());
        }
        return builder.toString().toCharArray();
    }

    /**
     * 
     * 计算后缀式表达式表达值
     * 
     * 1、字符为运算数：
     *  入栈
     * 2、如果为操作符：
     *  出栈t1, 出栈t2
     * 将t2与t1做操作符运算。
     */
    public double calcMiddlePattern(char[] middlePattern){
        Deque<Double> stack = new ArrayDeque<>();
        Set<Character> set = Set.of('+','-','*','/');
        for(char c : middlePattern){
            if(set.contains(c)){
                Double c1 = stack.pop();
                Double c2 = stack.pop();
                Double i2 = 0.0;
                switch (c) {
                    case '+':
                        i2=c2+c1;
                        break;
                    case '-':
                        i2=c2-c1;
                        break;
                    case '*':
                        i2=c2*c1;
                        break;
                    case '/':
                        i2=c2/c1;
                        break;
                    default:
                        break;
                }
                stack.push(i2);
            }else{
                double i = c-'0';
                stack.push(i);
            }
        }
        return stack.pop();
    }
}
