package com.alipay.demo.config;

import java.io.FileWriter;
import java.io.IOException;

/**
 * 配置文件
 * @programe: alipay
 * @Description:
 * @Author: li shuai ming
 * @Create: 2021/4/29-9:11
 */
public class AlipayConfig {

//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

    // 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
    public static String app_id = "2021000117644791";

    // 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCeCS7P+IgBedG9mr0J+mLgRmOCY+G+7cWHLrXxdJ3M7ndwK+1eGKo5VosVv5RY7OppIJ7dLG/3+3w9IF04rFMk6X+vGB+uf/jRuFGxJVLgWSKEkY45dMul3faQ7ChR6XvftetdyfCZtscfbTTHz8QUqA1AFfVSw3UL4c1SqWug0kHlspC8gg83htw+3Jq1I0CE1xOY5ZP/G8kwqw0yUEqvSH+7gkV1QeaS9EzgZON9uHxpU3X5OsjX5EG0ibYSvUoVUF7HgB4y//rJINjcmhqkP6EUFLgPczYwkj6wjCyrQpmK4rJMWxepArIly/mP4l/wIKSM+Q9RRsqrez5FgxjPAgMBAAECggEBAIKR7bZwjdZCUvNHnLZYxEaugVrisUoxY0kcY8C57AK8Z7wq3vQmhsXW5hUy8xtBqy7uf7MXGu+VPYG3cq33Wr6dmRwOX2aqXubPVdd5ke7AVk+Xfx2jjPpe2G/GXk3HDcy4nl0gEoS9yekCLmCEHWo/qqrvOYCG8va9Ogn45m643G8xmJED+o3+896LhWD2b1tirY9/pvGMQ8q4hJ2ac4ncRXMjF4frEnFA7fvwhKaCjbJuX5zpZ0uWhrmUn8jfc9QTtE/N8+hHrIes2UsAA1XyIgZJ+TuYO6pHg5kp0GMYFjnFiqP6Ps98B209USuJDiylwMfq4RHCNKYGzvnM2BECgYEA0vw2JBdHidMoYPO6cSKW5OM+beFqKmtOfIjXMDnO4SMlTAI1tXI2yNeLPSIcpXYswTB26pgAjcXV+hFbS9eVa+/ZiTduM7V+JwUWtpCQRfRLBYbaXBQxfAH8iDV/4dCHJL5etpK6mKEOYljQZNxGcSbXngihn/W0/ZRZYgKlSxcCgYEAv8DvWIta6LSxdNU2up8JE+CP2CN9QRKfEZchy7kOo0mUVqkt4CFdeVQLD5QUL4dqM0bpkwJc165oRMWWzzFBI79fbe9V17gyOGDApOQXCfTZCiAHQg0XwbmotIszgepnJILH4g4XRBvg22Ffdb1ODoCSKeDVoGunBwA+E4ldUwkCgYEAkLSlGYnR6gimeCCSLkhP//qP9eMV4rQoZ6efF5d+edGWIhSd663DP2r+N9aumRl9Rp9L4EiSkKowwUuoN1NMS3R82eznq5V+SZrm4h4ypZgQ4GCGYYMEEY53OqCe7i0Mk7nx0fy++DH2YKOTgqnFspO2bNB0sfZexVE7IMq5vc8CgYB7x455SNOxPdsDgRLoGvsgIqiYLCiRlfSqxTVirZ2+WCPyYRklAkS1qkPNlZgGKlKqsQ7b//1wmLvkEOxN6QyUh9eRPpraOqPocsRIbJhWrABiodQtlggM3M0DZowkQ+y6y9U9njz0kU5wLwYRGddGU0aG1ATynitewJBRyi09CQKBgQCYKiJwa45ffCDxfrzVHa4lcYAokL1M3CCsuFhgXlU/zcojPXdwm04tZJpLQtjON2UAmxda9GyWW8hFE+nD0Hi8fM1xkCXGbKbRvsRuQQ7Z0wA1v3WMX62IYaz9DHjz6WYcgUsHUA38g1WoRuVv7ys7Yc7EP0fgMy2kpKRXNxSO1Q==";

    // 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAkmDqbUKlKYpE4zRM/3b3CxRhDTS7n6rjRYNr/ITgxOXn0Fm8M1PrGpBZlCNRODqfmyrwx7kFIfVqUG0uv2XvTqAMs32yztbFNLIiys/JcGf7YeVfnuB8rXfBHeTOAsocyOClNiYUv1sH6QEwKXS7bh7Fp9E2veP8fiXtvMDK+72HFP68JrNq1m5NwQFjdE8sOv7nA19Fa2h+ekJIHuRZp31DQi449U1BNsMYtok0ONDBlBjPTLwiVhNXj46ygfQhE+roeNrwgZssL9ssSgdmdPmmdXPlb0tFWG7KgeESTaxGyFYA9xrk4h2obSzW6s1av83IlhBaDAz5QMt45QPKLQIDAQAB";

    // 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String notify_url = "http://localhost:8080/alipay/notifyUrl";

    // 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
    public static String return_url = "http://localhost:8080/alipay/returnUrl";

    // 签名方式
    public static String sign_type = "RSA2";

    // 字符编码格式
    public static String charset = "utf-8";

    // 支付宝网关
    public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    // 日志
    public static String log_path = "E:\\";


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
