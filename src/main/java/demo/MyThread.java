package main.java.demo;

class Tread01 extends Thread{
	String[][] taskGroup = {{}};
    public Tread01(String[][] taskGroup) {
		this.taskGroup=taskGroup;
	}

	public void run(){
    	TestGenerator tg = new TestGenerator();
    	//int[] times01 = {6900};
    	//int[] times01={10};
    	int[] timeIndexes01 = {0};
        System.out.println("ִ��Tread01");  
        tg.testGenerate(timeIndexes01,taskGroup); 
            
    }
}

class Tread02 extends Thread{
	String[][] taskGroup = {{}};
	public Tread02(String[][] taskGroup) {
		this.taskGroup=taskGroup;
	}
    public void run(){
    	TestGenerator tg = new TestGenerator();
    	//int[] times02 = {40,50,60,120,180,240,300,2100,3300};
    	//int[] times02 = {50};
    	int[] timeIndexes02 = {1};
        System.out.println("ִ��Tread02");  
        tg.testGenerate(timeIndexes02,taskGroup); 


    }
}


public class MyThread {

	static String[][] taskGroup = {
			{"1","MoreTriangle","Randoop","10"},
			{"2","MoreTriangle","Randoop","30"},
			{"3","MoreTriangle","Randoop","60"},
			{"4","MoreTriangle","Randoop","120"},
			{"5","MoreTriangle","Randoop","240"},
			{"6","MoreTriangle","Randoop","300"},
			{"7","MoreTriangle","Randoop","480"},
			};
	
     public static void main(String [] args){
         Tread01 t1 = new Tread01(taskGroup);  
         Tread02 t2 = new Tread02(taskGroup);
         t1.start();
         t2.start();
     }
     
     /*
      * start Thread to generate test code
      */
     public static void startThread(String [][] taskGroup){
         Tread01 t1 = new Tread01(taskGroup);  
         Tread02 t2 = new Tread02(taskGroup);
         t1.start();
         t2.start();
     }
}
