package test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionAPI {

	
	public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		ReflectionAPI t = new ReflectionAPI();
		
		Method method[] = t.getClass().getMethods();
		
		String x = "Testing in ";
		String y = "Progress ";
		for(int i=0;i<method.length;i++){
			//System.out.println(method[i].getName());
			
			if(method[i].getName().equals("abc")){
				method[i].invoke(t,x,y);
			}
		}
	}
	
	public void abc(String a, String b){
		System.out.println("ABC");
		System.out.println(a);
		System.out.println(b);
	}
	
	public void xyz(){
		System.out.println("xyz");
	}


}
