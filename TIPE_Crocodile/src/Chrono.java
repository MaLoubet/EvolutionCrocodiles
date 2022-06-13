public class Chrono extends Thread{
	
	private int MILLI;
	private int SECONDES = 0;
	
	public void run(){
		
		while(true){
			
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				System.out.println("reerf");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			MILLI++;			
			
			if(MILLI==1000){SECONDES++;}
			if(MILLI==2000)SECONDES++;
			if(MILLI==3000)SECONDES++;
			if (MILLI== 3000)MILLI=0;
		}
	}
	
	public int getMILLI(){
		return MILLI;
	}
	public int getSECONDES(){
		return SECONDES;
	}
}