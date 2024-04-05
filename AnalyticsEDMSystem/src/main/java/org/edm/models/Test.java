package org.edm.models;

import java.util.*;

public class Test {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        int first = 0;
        int second =0;
        int res = 0;
        Stack<Integer> st = new Stack<>();
        for(Character x : s.toCharArray()){
            if(Character.isDigit(x)){
                st.push(Integer.parseInt(String.valueOf(x)));
            }else{

                switch(x){
                    case'+':
                        first=st.peek();
                        st.pop();
                        second = st.peek();
                        st.pop();
                        res = first+second;
                        st.push(res);
                        break;
                    case'-':
                        second = st.peek();
                        st.pop();
                        first=st.peek();
                        st.pop();
                        res = first-second;
                        st.push(res);
                        break;
                    case'/':
                        second = st.peek();
                        st.pop();
                        first=st.peek();
                        st.pop();
                        res = first/second;
                        st.push(res);
                        break;
                    case'*':
                        second = st.peek();
                        st.pop();
                        first=st.peek();
                        st.pop();
                        res = first*second;
                        st.push(res);
                        break;
                }
            }
        }
        System.out.println(st.peek());

    }



}
