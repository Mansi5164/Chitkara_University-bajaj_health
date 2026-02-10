package com.mansi.springboot_api.Util;

import java.util.ArrayList;
import java.util.List;

public class MathUtil {

    // ---------- Fibonacci ----------
    public static List<Integer> fibonacci(int n){
        List<Integer> list = new ArrayList<>();

        int a = 0, b = 1;

        for(int i = 0; i < n; i++){
            list.add(a);
            int c = a + b;
            a = b;
            b = c;
        }
        return list;
    }

    // ---------- Prime ----------
    public static List<Integer> prime(List<Integer> arr){

        List<Integer> res = new ArrayList<>();

        for(Integer num : arr){
            if(isPrime(num)){
                res.add(num);
            }
        }
        return res;
    }

    private static boolean isPrime(int n){
        if(n < 2) return false;

        for(int i = 2; i*i <= n; i++){
            if(n % i == 0)
                return false;
        }
        return true;
    }

    // ---------- HCF ----------
    public static int hcf(List<Integer> arr){
        int result = arr.get(0);

        for(int i = 1; i < arr.size(); i++){
            result = gcd(result, arr.get(i));
        }
        return result;
    }

    private static int gcd(int a, int b){
        if(b == 0) return a;
        return gcd(b, a % b);
    }

    // ---------- LCM ----------
    public static int lcm(List<Integer> arr){
        int result = arr.get(0);

        for(int i = 1; i < arr.size(); i++){
            result = (result * arr.get(i)) / gcd(result, arr.get(i));
        }
        return result;
    }
}
