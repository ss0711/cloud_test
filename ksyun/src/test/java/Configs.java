import com.ksyun.ks3.http.HttpClientConfig;
import com.ksyun.ks3.service.Ks3;
import com.ksyun.ks3.service.Ks3Client;
import com.ksyun.ks3.service.Ks3ClientConfig;

/**
 *
 */
public class Configs {

    private static Ks3ClientConfig config = new Ks3ClientConfig();

    private static void init(){
        /**
         * ���÷����ַ
         * �й���������| ks3-cn-beijing.ksyun.com
         * �й����Ϻ���| ks3-cn-shanghai.ksyun.com
         * �й�����ۣ�| ks3-cn-hk-1.ksyun.com
         * ���ʹ���Զ�������������endpointΪ�Զ���������ͬʱ����domainModeΪtrue
         */
        config.setEndpoint("ks3-cn-beijing.ksyun.com");   //�˴��Ա���regionΪ��
        /**
         *true����ʾ���Զ�����������
         *false����ʾ��KS3�����������������������ʣ�Ĭ��Ϊfalse
         */
        config.setDomainMode(false);
        config.setProtocol(Ks3ClientConfig.PROTOCOL.http);
        /**
         *true��ʾ��   endpoint/{bucket}/{key}�ķ�ʽ����
         *false��ʾ��  {bucket}.endpoint/{key}�ķ�ʽ����
         */
        config.setPathStyleAccess(false);

        HttpClientConfig hconfig = new HttpClientConfig();
        //��HttpClientConfig�п�������httpclient��������ԣ����������ʱ�����Եȡ�
        config.setHttpClientConfig(hconfig);
    }

    private static Ks3 client = new Ks3Client("AKLTydEdcz82T6S7JobkZ3a_0A","OLZuf1xUDljk0BKjzMiS9cUR7e39d/D1gTd1GYj0JSteEfcokT3al9jDwmova5M9jg==",config);
    /* ���ߣ�client.setKs3config(config); */

    public static Ks3 getClient(){
        init();
        return client;
    }
    
}
