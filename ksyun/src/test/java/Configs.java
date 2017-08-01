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
         * 设置服务地址
         * 中国（北京）| ks3-cn-beijing.ksyun.com
         * 中国（上海）| ks3-cn-shanghai.ksyun.com
         * 中国（香港）| ks3-cn-hk-1.ksyun.com
         * 如果使用自定义域名，设置endpoint为自定义域名，同时设置domainMode为true
         */
        config.setEndpoint("ks3-cn-beijing.ksyun.com");   //此处以北京region为例
        /**
         *true：表示以自定义域名访问
         *false：表示以KS3的外网域名或内网域名访问，默认为false
         */
        config.setDomainMode(false);
        config.setProtocol(Ks3ClientConfig.PROTOCOL.http);
        /**
         *true表示以   endpoint/{bucket}/{key}的方式访问
         *false表示以  {bucket}.endpoint/{key}的方式访问
         */
        config.setPathStyleAccess(false);

        HttpClientConfig hconfig = new HttpClientConfig();
        //在HttpClientConfig中可以设置httpclient的相关属性，比如代理，超时，重试等。
        config.setHttpClientConfig(hconfig);
    }

    private static Ks3 client = new Ks3Client("AKLTydEdcz82T6S7JobkZ3a_0A","OLZuf1xUDljk0BKjzMiS9cUR7e39d/D1gTd1GYj0JSteEfcokT3al9jDwmova5M9jg==",config);
    /* 或者：client.setKs3config(config); */

    public static Ks3 getClient(){
        init();
        return client;
    }
    
}
