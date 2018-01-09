package com.ithc.action;

public class Fibonacci {
	public static long F(int N){
		if(N==0) return 0;
		if(N==1) return 1;
		return F(N-1)+F(N-2);
	}
	public static void main(String[] args) {
		System.out.println(F(5));
	}
}
