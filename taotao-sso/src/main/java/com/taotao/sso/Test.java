package com.taotao.sso;

public class Test {

	public static void main(String[] args) {
		String str = "112323456789";
		int subLength = str.length() - 4;
		System.out.println(tt(subLength) + str.substring(subLength));
	}

	public static String tt(int n){
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<n; i++){
			sb.append("*");
		}
		return sb.toString();
	}
}
