package tw.com.animx;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import tw.com.animx.util.get2AniUtil;

public class get2AniPhoto implements Runnable {
	private static int threadcount = 0;
	private static String[] comic; 
	private static int last;
	private static get2AniUtil util ;
	
	get2AniPhoto(String[] list, int count){
		comic = list;
		last = count;
		util = new get2AniUtil();
	}
	
	public static void startDownload() throws Exception {
		Executor executor = Executors.newFixedThreadPool(10);
		for(String url:comic){
			get2AniThread get = new get2AniThread(url,last);
			executor.execute(get);
			threadcount++;
			try {
				Thread.sleep(3000L);
			} catch (InterruptedException e) {
				System.out.println(e.getMessage());
				throw e;
			}
		}
		while(threadcount>0){
			util.logMessage(threadcount + " thread Still Running!");
			Thread.sleep(10000L);
		}
		util.logMessage("Mission Completed");
		executor = null;
		
		//重置畫面
		mainGuiDesigner.finishJob();
	}

	// 控制Thread
	public static void minusThreadCount() {
		get2AniPhoto.threadcount--;
	}

	@Override
	public void run() {
		try {
			startDownload();
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
}
