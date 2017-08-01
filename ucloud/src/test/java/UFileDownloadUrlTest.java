import cn.ucloud.ufile.DownloadUrl;
import cn.ucloud.ufile.UFileClient;
import cn.ucloud.ufile.UFileConfig;
import cn.ucloud.ufile.UFileRequest;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 测试：生成访问公有或私有bucket的url
 */
public class UFileDownloadUrlTest {
	public static void main(String args[]) {
		String bucketName = "dangdangprivate";
		String key = "test.jpg";
		String saveAsPath = "E:\\test.jpg";
		String configPath = "";
		
		//加载配置项
//		UFileConfig.getInstance().loadConfig(configPath);

		UFileConfig.getInstance().setUcloudPublicKey("6ZxsvhXpdYdX5k8kWRmD+Iw2lu1oZ/jYzmd46iQ3k9Lq7A3ZKlQDdw==");
		UFileConfig.getInstance().setUcloudPrivateKey("f3c03dfda0ee9d0385872ed1bef969db8de88417");
		UFileConfig.getInstance().setProxySuffix(".up.ufileos.com");
		UFileConfig.getInstance().setDownloadProxySuffix(".dl.ufileos.com");

		UFileRequest request = new UFileRequest();
		request.setBucketName(bucketName);
		request.setKey(key);

		// url链接的有效期为一天，从当前unix时间戳开始，86400秒后结束

		System.out.println("DownloadUrl Test BEGIN ...");

		boolean isPrivate = true;
		UFileClient ufileClient = new UFileClient();
		int ttl = 86400;
		/*
		 * 针对私有的Bucket，为了防止盗链，建议生成的链接的有效期为ttl > 0; 如果 ttl == 0, 那么生成的链接永久有效
		 */
		String url = downloadUrl(ufileClient, request, ttl, isPrivate);
		System.out.println(url);

		downloadFileFromUrl(url, saveAsPath);

		System.out.println("DownloadUrl Test END ...\n\n");
	}

	private static void downloadFileFromUrl(String urlStr, String saveAsPath) {
		URL url;
		InputStream inputStream = null;
		OutputStream outputStream = null;
		try {
			url = new URL(urlStr);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			inputStream = conn.getInputStream();
			outputStream = new BufferedOutputStream(new FileOutputStream(
					saveAsPath));
			int bufSize = 1024 * 4;
			byte[] buffer = new byte[bufSize];
			int bytesRead;
			while ((bytesRead = inputStream.read(buffer)) > 0) {
				outputStream.write(buffer, 0, bytesRead);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (outputStream != null) {
				try {
					outputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static String downloadUrl(UFileClient ufileClient,
                                      UFileRequest request, int ttl, boolean isPrivate) {
		DownloadUrl downloadUrl = new DownloadUrl();
		return downloadUrl.getUrl(ufileClient, request, ttl, isPrivate);
	}

}
