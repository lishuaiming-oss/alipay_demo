package com.alipay.demo.config;


import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.CertAlipayRequest;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  公钥证书模式下的配置文件
 * @programe: alipay
 * @Description: 读取配置文件
 * @Author: li shuai ming
 * @Create: 2021/4/27-17:23
 */
@Component
@RequestMapping("user")
public class Alipay {

    @RequestMapping("pay")
    public void pay() throws AlipayApiException {
        //构造client
        CertAlipayRequest   certAlipayRequest   =   new   CertAlipayRequest ();
        //设置网关地址
        certAlipayRequest . setServerUrl ( "https://openapi.alipay.com/gateway.do" );
        //设置应用Id
        certAlipayRequest . setAppId ("2021000117644576 ");
        //设置应用私钥
        certAlipayRequest . setPrivateKey ( "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCVwaxrhttu0N1LQH3SvYC6jEX/W8zD6NcQX7ojb4TzvuYGyEXdTfMPN6A5oec3oEIfc0XhBfZ+QZQ2wI7ydU86SIo5nQokfnX8CECtN+VKwW1L7KfB/8BYgRBybhLr3bGiUbB8pPGxyuDJvbFyBp5JXDpqhvpM3cuwQz9pWEYV3feO+y+JTrcgE0OoEt2x7JNzVJZfvM8YsVZlApssnvq0efTa+t2RUdF4PauIIQD8vGqZI3w1yot22fuxFPNz7ke901hUqBr3bU32z3PUcyxKIldgXAA+U2illo8hdkgR8v55jxMRbGhhBSsS/62nTXz3HECX3Bz2XGfupaN3q04ZAgMBAAECggEAKN29C2q1nrvoZ4ylyUhlRILa5yBJ9HwplBJLXTglc854PwmP5yBKX7CyTIfd7NQStzphq1JRPYrvwzEw0QEqKaF59tbf2a2YzLoDzEEglRHLnZhq/wmnl39A2bkn+K16t+zK2Hi6a+YNCYB2V8Null5QoplK3Ib5+pOfTkQEfBq2/1ZWSg417yhR8OaVLEAGu9EG7UbIykru2XHoV2MF6Uqwwxsk0+iTV2mK4BSL5QI6ajC8fkQBvr+O5nBdsv4l+k4aemB7xcIJAIW2zU7VjTEKBD9saqGKJwelDCKhCLXePsueeylaRYr0eRLmSeukuNj1HQ12CuCjRPfSBVaKsQKBgQDX8gDCtaHIUHRYB5qVamh36XRyxj1iZVQ+NebFggA0gb47sBojsQKm7C8SDhGkutUWuLB2eKvJoCpnT3+otbP3+Qg2e4WwEOZOIKwXgyWBQZKB/gcNKbpOKPHCokxb1Ep9UiAU13Me5jClgsSDHJZT01xvO6fpYWCmLz4PNORVtwKBgQCxiL9BIis+b+kcZKC75lUOXxt56VkW6bSRc2gTmdpNx8RXAHN6nqvvSQ16l3EK7dGT8TzuvEEN88ETsbKMe/uLBi5KqoqYnhH7ZBlK+4H1+9BCjnUBIvEY/XLBGwY8ArLExB8oRW2AAU+2O3DwwcUYAzPooqbJl8AtipGQSyD6rwKBgG/EUaB1YcbL3Wuyg8/Rw0BNFHhTInJK/88zFr4jCvgiLtO10dGnTDgZBkPSBE++Y4vfC3ZF/qPcwhW2j9xCL2OpARcx13/JP2owfuGsfzznM20UjZHChyrtBjQ2zE67EdLLLs+as3rKqWMAy7UQlr3MJ4tq1P116RAaY4R7W7MBAoGAWuwJ5zW0p/lwjZYLq6+/B+k1jS4w7kcqrwNH3iIBZ6xTpasZeUciDx5+xLYistIxb28xIaNQPfEEhS2Qn9ovbXfw5VSKPrdVxkXxnjFI0c3xTuTej4Jxr7a9uqA4hStsmtFGk71a4x/Izy+KHANhCXc6cn46pSwOxEQfYKLZdQkCgYAm5mZ0cIhYXizaUl0kB8PQHrCNn4Z9cx/CstoJBeXWjksO9XPwIo4q3fVOsgiStKUJLxSOGTOwxdbr+HahIJTn4P1IwgFRRqJXUSI4fBMUkIFQRKIrZsYNGEAHkQq4O3Gueitkn1ie7CZLTJibCp39rd8PQAbTAbiHLTFdAL4FaQ==" );
        //设置请求格式，固定值json
        certAlipayRequest . setFormat ( "json" );
        //设置字符集
        certAlipayRequest . setCharset ( "UTF-8" );
        //设置签名类型
        certAlipayRequest . setSignType ( "RSA2" );
        //设置应用公钥证书路径
        certAlipayRequest . setCertPath ( "E:\\workspace\\hryt\\sign\\appCertPublicKey_2021000117644576.crt" );
        //设置支付宝公钥证书路径
        certAlipayRequest . setAlipayPublicCertPath ( "E:\\workspace\\hryt\\sign\\alipayCertPublicKey_RSA2.crt" );
        //设置支付宝根证书路径
        certAlipayRequest . setRootCertPath ( "E:\\workspace\\hryt\\sign\\alipayRootCert.crt" );
        //构造client
        AlipayClient alipayClient   =   new DefaultAlipayClient( certAlipayRequest );

        //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
        AlipayTradeAppPayRequest request   =   new   AlipayTradeAppPayRequest ();
        //SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
        AlipayTradeAppPayModel model   =   new   AlipayTradeAppPayModel ();
        model . setBody ( "我是测试数据" );
        model . setSubject ( "App支付测试Java" );
        model . setOutTradeNo ( "20150320010101001" );
        model . setTimeoutExpress ( "30m" );
        model . setTotalAmount ( "0.01" );
        model . setProductCode ( "QUICK_MSECURITY_PAY" );
        request . setBizModel ( model );
        request . setNotifyUrl ( "http://localhost:8080/success.html" );

            //这里和普通的接口调用不同，使用的是sdkExecute
            AlipayTradeAppPayResponse response   =   alipayClient . sdkExecute ( request );
            System . out . println ( response . getBody ()); //就是orderString 可以直接给客户端请求，无需再做处理。
            //String result = response . getBody ();
        }


}
