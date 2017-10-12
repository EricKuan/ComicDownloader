package tw.com.animx;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.Connection;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import tw.com.animx.util.get2AniUtil;

class get2AniThread implements Runnable {
	private String url = null;
	private int lastCount = 0;
	private get2AniUtil util ;

	public get2AniThread(String url) {
		this.url = url;
	}
	
	public get2AniThread(String url, int count) {
		this.url = url;
		this.lastCount = count;
		this.util = new get2AniUtil();
	}

	public void getPhoto() throws IOException {
		String Path = null;
		String title;
		String chapterTitle = null;
		try {
			title = util.getTitle(url);
		} catch (IOException e) {
			System.out.println("Can't get Title, please check url again!");
			return;
		}
		Path = "comic" + File.separator + title;
		util.logMessage(Path);
		Connection conn = util.getConnection(url);
		Document doc = conn.get();
		Elements elem = doc.select("#oneCon1").select("li");
//		System.out.println(new String(elem.toString().getBytes("UTF-8"), "UTF-8"));
		Set<String> result  = new LinkedHashSet<String>();
		for (Element obj : elem) {
			String tagetUrl = obj.select("a").attr("href");
			chapterTitle = obj.select("a").attr("title");
			chapterTitle = util.specialCharFilter(chapterTitle);
			String newPath = Path +  File.separator + chapterTitle;
			if(tagetUrl!=null && tagetUrl.length()>1){
				result.add(tagetUrl + "_" + newPath);
			}
		}
		util.logMessage("Total: " + result.size());
		
		//圖片路徑 String Array
		String[] photoList = result.toArray(new String[result.size()]);
		int i=0;
		//計算是否大於漫畫總數量
		if(lastCount!=0){
			i--;
			i=photoList.length - lastCount;
			if(i<0){
				i=0;
			}
		}
		util.logMessage("Downlod : " + (i+1) + " ~ " + result.size());
		for (;i<photoList.length;i++) {
			try {
				String[] comicData = photoList[i].split("_");
				ArrayList<String> urlSet = util.getPhotoUrl(comicData[0]);
				int countPhoto = 1;
				for (String targetUrl : urlSet) {
					String[] fileNameSplit =  targetUrl.split("/");
					String fileName = fileNameSplit[fileNameSplit.length-1];
//					System.out.println(title + ", " + targetUrl + ", " + fileName + ", " + comicData[1]);
					for(String s:get2AniUtil.getReplaceChar()){
						if(StringUtils.isNotBlank(comicData[1])) {
							comicData[1] = comicData[1].replaceAll(s, "");
						}
					}
					util.savePhoto(targetUrl, fileName, comicData[1]);
					mainGuiDesigner.printLog(comicData[1] + " : " + urlSet.size() + "/" + countPhoto++);
					
				}
				String[] end = comicData[1].split("/");
				String chapterOutput =  end[end.length-1] + "下載完成"; 
				util.logMessage(chapterOutput);
			} catch (IOException e) {
				System.out.println(e.getMessage());
				return;
			}
			
		}
		util.logMessage(title + " Download End!");
	}

	

	public void run() {
		try {
			getPhoto();
		} catch (IOException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		get2AniPhoto.minusThreadCount();
	}
}
