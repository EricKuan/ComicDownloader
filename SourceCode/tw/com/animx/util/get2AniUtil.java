package tw.com.animx.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Properties;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import tw.com.animx.mainGuiDesigner;

public class get2AniUtil {

	public static String[] REPLACECHAR = { "~", "\\*", "%", "&", ":", ">", "<", "/", "\\}", "\\{", "\\|" };

	public static Properties p = new Properties();
	public static int fileCount = 0;

	public synchronized ArrayList<String> getPhotoUrl(String target) throws IOException {
		// http://www.2animx.com/index-look-name-%E7%9B%A3%E7%8D%84%E5%AD%B8%E5%9C%92-cid-6003-id-207901-p-1
		int i = 1;
		ArrayList<String> result = new ArrayList<String>();
		while (true) {
			String imgTar = target;
			imgTar = imgTar + "-p-";
			imgTar = imgTar + i;
			// System.out.println(imgTar);
			Connection conn = getConnection(imgTar);
			Document doc = conn.get();
			Elements elem = doc.select("#img_ad_img").select("img");
			// "/upload/0w/5b/582/51/1.jpg"
			String imgSrc = elem.get(0).attr("src");
			if (imgSrc != null && imgSrc.length() > 1) {
				String url = "http://www.2animx.com" + imgSrc;
				// System.out.println(url);
				result.add(url);
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
		BufferedReader in = null;
		try {
			URL url = new URL(sURL);
			HttpURLConnection URLConn = null;
			URLConn = (HttpURLConnection) url.openConnection();
			// 設定header
			URLConn.setRequestProperty("User-agent", "Mozilla/5.0 (Windows; U; Windows NT 6.0; zh-TW; rv:1.9.1.2) "
					+ "Gecko/20090729 Firefox/3.5.2 GTB5 (.NET CLR 3.5.30729)");
			URLConn.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
			URLConn.setRequestProperty("Accept-Language", "zh-tw,en-us;q=0.7,en;q=0.3");
			URLConn.setRequestProperty("Accept-Charse", "Big5,utf-8;q=0.7,*;q=0.7");
			// URLConn.setDoInput(true);
			// URLConn.setDoOutput(true);
			// 建立連線
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

			byte[] tmp = new byte[1024];
			int len;
			// 開始寫檔
			while ((len = rd.read(tmp)) != -1) {
				fos.write(tmp, 0, len);
			}
			fos.flush();
			fos.close();
			rd.close();

		} catch (IOException e) {
			doSuccess = false;
			e.printStackTrace();
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				in = null;
			}
		}
		return doSuccess;
	}

	public synchronized Connection getConnection(String baseUrl) {
		Connection conn = Jsoup.connect(baseUrl);
		// 寫入 cookie 讓需要驗證的漫畫能夠正常取得路徑
		conn.cookie("isAdult", "1");
		return conn;
	}
	
	public synchronized void logMessage(String message) {
		System.out.println(message);
		mainGuiDesigner.printLog(message);
	}
	
	/**
	 * 濾除特殊字元
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