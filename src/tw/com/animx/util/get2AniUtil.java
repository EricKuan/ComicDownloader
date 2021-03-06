package tw.com.animx.util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import tw.com.animx.mainGuiDesigner;

public class get2AniUtil {

	private final static String[] REPLACECHAR = { "~", "\\*", "%", "&", ":", ">", "<", "/", "\\}", "\\{", "\\|" };
	public final static String JPG = "jpg";
	public final static String PNG = "png";

	public static String[] getReplaceChar() {
		return REPLACECHAR;
	}
	
	public synchronized ArrayList<String> getPhotoUrl(String target) throws IOException {
		int i = 1;
		ArrayList<String> result = new ArrayList<String>();
		while (true) {
			String imgTar = target;
			imgTar = imgTar + "-p-";
			imgTar = imgTar + i;
			 System.out.println(imgTar);
			Connection conn = getConnection(imgTar);
			Document doc = conn.get();
			Element elem = doc.select("img[name=ComicPic]").first();
			String imgSrc = null;
			if(elem!=null) {
				imgSrc = elem.attr("src");
			}
			if (imgSrc != null && imgSrc.length() > 1 
					&& (imgSrc.endsWith(JPG) || imgSrc.endsWith(JPG.toUpperCase()) 
							|| imgSrc.endsWith(PNG) || imgSrc.endsWith(PNG.toUpperCase()))) {
				result.add(imgSrc);
				i++;
			} else {
				break;
			}
		}
		return result;
	}

	public synchronized String getTitle(String target) throws IOException {
		String title = null;
		Connection conn = getConnection(target);
		Document doc = conn.get();
		title = doc.title().split("-")[0];
		title = specialCharFilter(title);
		return title;
	}

	public synchronized boolean savePhoto(String sURL, String fileName, String path) {

		boolean doSuccess = true;
		try {
			URL url = new URL(sURL);
			HttpURLConnection URLConn = null;
			URLConn = (HttpURLConnection) url.openConnection();
			URLConn.setRequestProperty("User-agent", "Mozilla/5.0 (Windows; U; Windows NT 6.0; zh-TW; rv:1.9.1.2) "
					+ "Gecko/20090729 Firefox/3.5.2 GTB5 (.NET CLR 3.5.30729)");
			URLConn.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
			URLConn.setRequestProperty("Accept-Language", "zh-tw,en-us;q=0.7,en;q=0.3");
			URLConn.setRequestProperty("Accept-Charse", "Big5,utf-8;q=0.7,*;q=0.7");
			// URLConn.setDoInput(true);
			// URLConn.setDoOutput(true);
			
			URLConn.connect();

			java.io.BufferedInputStream rd = new java.io.BufferedInputStream(URLConn.getInputStream());

			String saveFileName = fileName;

			java.io.File f = new java.io.File(path);
			f = new java.io.File(f.getAbsolutePath());
			if (!f.exists())
				f.mkdirs();
			f = new java.io.File(f.getAbsolutePath() + java.io.File.separator + saveFileName);
			if (f.exists()) {
				return true;
			}
			fileName = saveFileName;
			
			java.io.BufferedOutputStream fos = new java.io.BufferedOutputStream(new java.io.FileOutputStream(f));

			try {
				byte[] tmp = new byte[1024];
				int len;

				while ((len = rd.read(tmp)) != -1) {
					fos.write(tmp, 0, len);
				}
				fos.flush();
			} catch (Exception e) {
//				e.printStackTrace();
				System.out.println(e.getMessage());
			} finally {
				fos.close();
			}
			rd.close();

		} catch (IOException e) {
			doSuccess = false;
//			e.printStackTrace();
			System.out.println(e.getMessage());
		} 
		return doSuccess;
	}

	public synchronized Connection getConnection(String baseUrl) {
		Connection conn = Jsoup.connect(baseUrl);
		conn.cookie("isAdult", "1");
		return conn;
	}
	
	public synchronized void logMessage(String message) {
		System.out.println(message);
		mainGuiDesigner.printLog(message);
	}
	
	/**
	 * 移除非法檔案名稱字元(for windows) 
	 * @param message
	 * @return
	 */
	public synchronized String specialCharFilter(String message) {
		String result = message;
		for(String reg:REPLACECHAR){
			result = result.replaceAll(reg, "");
		}
		return result;
	}
}
