import com.ksyun.ks3.service.request.PutObjectRequest;

import java.io.File;

/**
 *
 */
public class FileUploadTest {

    public static void main(String[] args){
        FileUploadTest test = new FileUploadTest();
        test.putObjectSimple();
    }

    public void putObjectSimple(){
        PutObjectRequest request = new PutObjectRequest("dangdang3x", "test.jpg", new File("E:\\test.jpg"));
        //上传一个公开文件
        //request.setCannedAcl(CannedAccessControlList.PublicRead);
        Configs.getClient().putObject(request);
    }
}
