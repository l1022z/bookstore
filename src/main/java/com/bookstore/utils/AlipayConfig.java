package com.bookstore.utils;

import java.io.FileWriter;
import java.io.IOException;

public class AlipayConfig {
    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2016102200737713";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCJjOvM250XdKYxNdqgvgq5KbvVBPDQP9PzhnpAaNz50ApOGXiLsDLqNCSssWDVB1V86pgKYgct33FMLUD4x7+qQY1v1gRmXegPFs0KoRfi055LeJLP67iXrdIN4VDKOtj9AGeY5DXe9AJSggFZ8NGxQC7DCPyY849Tw6Zn8PTCi9iPGRLFy8z2x6LqqswWWO/bbkp7oZ5QCrHsKmNQf9/wla7ptC+Z9asb1LhUXF8COiLpe64d/vyXJH0wyMoJ2wQMPxL3xYegrOWNAu1sfnzrCop5m/VehKR6S0PiMImDyMZO+E0upf4xeGJPEh7P0W3e/XhH4l8WrHlo6X7Yu14xAgMBAAECggEAM3ehO3PpIb+p97vY5FB3nrT47Un9+U4ItpRY5gTWujqXwIVTT6FMS8na9ysfulYWLLlyzl/Do+x5bjF4oB3Nn50LmQ8RcmQs0oiLjDCeyWa5Ui/5bwcaeNa3RC5Mln7SdkhSeyjY52WSAOFYNBkhvRrw5FVWfryTopoQh8/nJWuzhOVp3Z/Abfojx0tvjsEEPHZkgxr4joVYdEO/k++A4hVSy0vw90xm4cRhlxctCXEkdMk//Y0Q0vo/nIUOAzGmGHVVPa0UKkwnQyJgtTcSvF7isMYgyjzNS2jKUrXxVS6vjlMskvHTF6EcjgZiQk6pgxqMG9lioCD3SMvvtRSqaQKBgQDhMb23O6CgizVE5xB8KtCsCEJsyeP+b8pdCMmFgcDFViPVYkwenSHQrS3S/ukJ67a5baVjuliIUf3Z78y91Gk9whW9hZxx0VhO9NZ4T+f0D/xR+EZcsLISSA5KSH8JY4azwyP9uR1Bnswimg5IYBpoDHo69DrKSpX8Jo2BT64S9wKBgQCcXekuniIXKwHdYhbjuNThnVE6x3CKwGQBBT4Ko3k2cIm58/3Z39qTELlcGapTrr3lUa9dFrMFAR2B4X856SfD4zDv26jozQJu471Bpg8cz8OLq7CSWUlw+aeWSH3IQH7Sv+pUHStNQC1NJDU2yWcD920tryG4O/vaghyYiDEmFwKBgQDg4pKJtGMxUDOPLoz44qzmz4y+JVOo2KynAcp1p+EBcIdyZaqMe/Cz+u2jIutH+/vK62NZ11LoN7Eg/edhvwimIvcg/5f+1cexebHgDjQeOASLOzvQ/qeluA2FQyaSErEHPZ3ol/9Oc6159IzE3CwisisySomXUKq9VybC9vZoewKBgCpZEK7sasUlg6LGTvardxQHPBmUK/mX8z2Hgu70qP5CajpvRv+7rYGI1PqJhOSW/Zzei4TFggRH8grb3E39xCQ8ImPDdLJf21nW2cmuxIcECayI+hAYmFg9dNKxnUcfmHlROpA/Ohv41ov+016cGwcq+mmV9abkr+fUtxPkRpNPAoGADMTVnrLEiwC/PZuh1Wu2qiCWh/3azrKhTllWa4O/6FYZkAOeO0NnelyxrIln+e3nqKPSAPTl14opzePny83hDZ/ke4FOsGJzqNO72gpUQ70/NviyHtlExBVJPgkAcmgWz0DINGaLR+9icjaqIo1r4I8Bh1BfaOWn3Gixrmr664o=";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAh4f3aln/wTN2RIF5qYb7EFMDDISKMRm9Om1ccZBcJaKAvu+lgFz5drD6iPx4cBMnc2rwUMFK0rlrCdIPrTICwiZKP8SxWLMRBYFUDwbOeRAdqDN3IB/L1LDADeOTtgngbFUrF5Ywy9VNbVyl5wJqNbydc64W7K43wU6jBmYOaiVA4I4yjkjLR2XcOkg9RyyDBQOxTnRkLNMQnw/ZDGLUEThnl5Y5EzvK4ielWkLD4KFzRMqNSm57DeP4T4yw4XtoYN1AbqPp6yENfdlCvtsN63SzX1RKL86xodKgHCkPdGht5dlm7VlhE5bAh8m8LyTKPqvBeZ1y+5U62+wG2EkOzwIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://工程公网访问地址/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://localhost:8080/bookstore_war/client/order/paysuccess";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 支付宝网关
    public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /**
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
