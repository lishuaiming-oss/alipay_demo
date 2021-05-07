package com.alipay.demo.controller;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.*;
import com.alipay.demo.config.AlipayProperties;
import com.alipay.demo.config.AlipayProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @programe: alipay
 * @Description:
 * @Author: li shuai ming
 * @Create: 2021/4/28-15:50
 */
@RestController
@RequestMapping("alipay")
public class AliTradeController {


    @RequestMapping("pay")
    public void pay(HttpServletRequest request, HttpServletResponse response) throws Exception{
        //获得初始化的AlipayClient 传值
        // 实例化客户端
        // 1、获得初始化的AlipayClient
        String gatewayUrl = AlipayProperties.getGatewayUrl();
        String appId = AlipayProperties.getAppId();
        String privateKey = AlipayProperties.getPrivateKey();
        String format = "json";
        String charset = AlipayProperties.getCharset();
        String alipayPublicKey = AlipayProperties.getPublicKey();
        String signType = AlipayProperties.getSignType();
        String returnUrl = AlipayProperties.getReturnUrl();
        String notifyUrl = AlipayProperties.getNotifyUrl();
        AlipayClient alipayClient = new DefaultAlipayClient(gatewayUrl, appId, privateKey, "json", charset, alipayPublicKey, signType);
        System.out.println("appId"+appId);
        //设置请求参数
        //SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
        //创建PC场景下单并支付请求对象
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        //设置同步返回地址，HTTP/HTTPS开头字符串
        alipayRequest.setReturnUrl(returnUrl);
        //支付宝服务器主动通知商户服务器里指定的页面http/https路径。
        alipayRequest.setNotifyUrl(notifyUrl);//在公共参数中设置回跳和通知地址

        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = new String(request.getParameter("WIDout_trade_no").getBytes("ISO-8859-1"),"UTF-8");
        //付款金额，必填
        String total_amount = new String(request.getParameter("WIDtotal_amount").getBytes("ISO-8859-1"),"UTF-8");
        //订单名称，必填
        String subject = new String(request.getParameter("WIDsubject").getBytes("ISO-8859-1"),"UTF-8");
        //商品描述，可空
        String body = new String(request.getParameter("WIDbody").getBytes("ISO-8859-1"),"UTF-8");

        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"total_amount\":\""+ total_amount +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"body\":\""+ body +"\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        //若想给BizContent增加其他可选请求参数，以增加自定义超时时间参数timeout_express来举例说明
        //alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
        //		+ "\"total_amount\":\""+ total_amount +"\","
        //		+ "\"subject\":\""+ subject +"\","
        //		+ "\"body\":\""+ body +"\","
        //		+ "\"timeout_express\":\"10m\","
        //		+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
        //请求参数可查阅【电脑网站支付的API文档-alipay.trade.page.pay-请求参数】章节

        //请求 返回页面格式
        //调用SDK生成表单 （包含二维码）
        String result = alipayClient.pageExecute(alipayRequest).getBody();

        //输出html页面
        response.setContentType("text/html; CHARSET=utf-8");
        response.setCharacterEncoding("utf-8");
        ServletOutputStream out = response.getOutputStream();
        //out.println(result);
        out.write(result.getBytes());
        //这种方式是跳转到支付宝的公共扫码页面做支付
    }
    @RequestMapping("query")
    public void query(HttpServletRequest request, HttpServletResponse response) throws Exception{
        // 1、获得初始化的AlipayClient
        String gatewayUrl = AlipayProperties.getGatewayUrl();
        String appId = AlipayProperties.getAppId();
        String privateKey = AlipayProperties.getPrivateKey();
        String format = "json";
        String charset = AlipayProperties.getCharset();
        String alipayPublicKey = AlipayProperties.getPublicKey();
        String signType = AlipayProperties.getSignType();
        String returnUrl = AlipayProperties.getReturnUrl();
        String notifyUrl = AlipayProperties.getNotifyUrl();
        AlipayClient alipayClient = new DefaultAlipayClient(gatewayUrl, appId, privateKey, "json", charset, alipayPublicKey, signType);
        //设置请求参数
        AlipayTradeQueryRequest alipayRequest = new AlipayTradeQueryRequest();

        //商户订单号，商户网站订单系统中唯一订单号
        String out_trade_no = new String(request.getParameter("WIDTQout_trade_no").getBytes("ISO-8859-1"),"UTF-8");
        //支付宝交易号
        String trade_no = new String(request.getParameter("WIDTQtrade_no").getBytes("ISO-8859-1"),"UTF-8");
        //请二选一设置

        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","+"\"trade_no\":\""+ trade_no +"\"}");

        //请求
        String result = alipayClient.execute(alipayRequest).getBody();
        System.out.println(result);


        //输出
        response.setContentType("text/html; CHARSET=utf-8");
        response.setCharacterEncoding("utf-8");
        ServletOutputStream out = response.getOutputStream();
        out.write(result.getBytes());
    }
    @RequestMapping("refund")
    public void refund(HttpServletRequest request, HttpServletResponse response) throws Exception{

        // 1、获得初始化的AlipayClient
        String gatewayUrl = AlipayProperties.getGatewayUrl();
        String appId = AlipayProperties.getAppId();
        String privateKey = AlipayProperties.getPrivateKey();
        String format = "json";
        String charset = AlipayProperties.getCharset();
        String alipayPublicKey = AlipayProperties.getPublicKey();
        String signType = AlipayProperties.getSignType();
        String returnUrl = AlipayProperties.getReturnUrl();
        String notifyUrl = AlipayProperties.getNotifyUrl();
        AlipayClient alipayClient = new DefaultAlipayClient(gatewayUrl, appId, privateKey, "json", charset, alipayPublicKey, signType);
        //设置请求参数
        AlipayTradeRefundRequest alipayRequest = new AlipayTradeRefundRequest();

        //商户订单号，商户网站订单系统中唯一订单号
        String out_trade_no = new String(request.getParameter("WIDTRout_trade_no").getBytes("ISO-8859-1"),"UTF-8");
        //支付宝交易号
        String trade_no = new String(request.getParameter("WIDTRtrade_no").getBytes("ISO-8859-1"),"UTF-8");
        //请二选一设置
        //需要退款的金额，该金额不能大于订单金额，必填
        String refund_amount = new String(request.getParameter("WIDTRrefund_amount").getBytes("ISO-8859-1"),"UTF-8");
        //退款的原因说明
        String refund_reason = new String(request.getParameter("WIDTRrefund_reason").getBytes("ISO-8859-1"),"UTF-8");
        //标识一次退款请求，同一笔交易多次退款需要保证唯一，如需部分退款，则此参数必传
        String out_request_no = new String(request.getParameter("WIDTRout_request_no").getBytes("ISO-8859-1"),"UTF-8");

        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"trade_no\":\""+ trade_no +"\","
                + "\"refund_amount\":\""+ refund_amount +"\","
                + "\"refund_reason\":\""+ refund_reason +"\","
                + "\"out_request_no\":\""+ out_request_no +"\"}");

        //请求
        String result = alipayClient.execute(alipayRequest).getBody();

        //输出
        response.setContentType("text/html; CHARSET=utf-8");
        response.setCharacterEncoding("utf-8");
        ServletOutputStream out = response.getOutputStream();
        out.write(result.getBytes());
    }
    @RequestMapping("refundquery")
    public void refundquery(HttpServletRequest request, HttpServletResponse response) throws Exception{
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayProperties.GATEWAY_URL, AlipayProperties.APP_ID, AlipayProperties.PRIVARY_KEY, "json", AlipayProperties.CHARSET, AlipayProperties.PUBLIC_KEY, AlipayProperties.SIGN_TYPE);

        //设置请求参数
        AlipayTradeFastpayRefundQueryRequest alipayRequest = new AlipayTradeFastpayRefundQueryRequest();

        //商户订单号，商户网站订单系统中唯一订单号
        String out_trade_no = new String(request.getParameter("WIDRQout_trade_no").getBytes("ISO-8859-1"),"UTF-8");
        //支付宝交易号
        String trade_no = new String(request.getParameter("WIDRQtrade_no").getBytes("ISO-8859-1"),"UTF-8");
        //请二选一设置
        //请求退款接口时，传入的退款请求号，如果在退款请求时未传入，则该值为创建交易时的外部交易号，必填
        String out_request_no = new String(request.getParameter("WIDRQout_request_no").getBytes("ISO-8859-1"),"UTF-8");

        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                +"\"trade_no\":\""+ trade_no +"\","
                +"\"out_request_no\":\""+ out_request_no +"\"}");

        //请求
        String result = alipayClient.execute(alipayRequest).getBody();

        //输出
        response.setContentType("text/html; CHARSET=utf-8");
        response.setCharacterEncoding("utf-8");
        ServletOutputStream out = response.getOutputStream();
        out.write(result.getBytes());
    }
    @RequestMapping("close")
    public void close(HttpServletRequest request, HttpServletResponse response) throws Exception{
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayProperties.GATEWAY_URL, AlipayProperties.APP_ID, AlipayProperties.PRIVARY_KEY, "json", AlipayProperties.CHARSET, AlipayProperties.PUBLIC_KEY, AlipayProperties.SIGN_TYPE);

        //设置请求参数
        AlipayTradeCloseRequest alipayRequest = new AlipayTradeCloseRequest();
        //商户订单号，商户网站订单系统中唯一订单号
        String out_trade_no = new String(request.getParameter("WIDTCout_trade_no").getBytes("ISO-8859-1"),"UTF-8");
        //支付宝交易号
        String trade_no = new String(request.getParameter("WIDTCtrade_no").getBytes("ISO-8859-1"),"UTF-8");
        //请二选一设置

        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\"," +"\"trade_no\":\""+ trade_no +"\"}");

        //实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
        String result = alipayClient.execute(alipayRequest).getBody();

        //输出
        response.setContentType("text/html; CHARSET=utf-8");
        response.setCharacterEncoding("utf-8");
        ServletOutputStream out = response.getOutputStream();
        out.write(result.getBytes());
    }
 // post请求notifyUrl  返回异步通知参数
    @RequestMapping("notifyUrl")
    public void notify(HttpServletRequest request, HttpServletResponse response) throws Exception{
        //输出
        response.setContentType("text/html; CHARSET=utf-8");
        response.setCharacterEncoding("utf-8");
        ServletOutputStream out = response.getOutputStream();
        //获取支付宝POST过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayProperties.PUBLIC_KEY, AlipayProperties.CHARSET, AlipayProperties.SIGN_TYPE); //调用SDK验证签名

        //——请在这里编写您的程序（以下代码仅作参考）——

	/* 实际验证过程建议商户务必添加以下校验：
	1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
	2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
	3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
	4、验证APP_ID是否为该商户本身。
	*/
        if(signVerified) {//验证成功
            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //交易状态
            String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");

            if(trade_status.equals("TRADE_FINISHED")){
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序

                //注意：
                //退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
            }else if (trade_status.equals("TRADE_SUCCESS")){
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序

                //注意：
                //付款完成后，支付宝系统发送该交易状态通知
            }

            out.println("success");

        }else {//验证失败
            out.println("fail");

            //调试用，写文本函数记录程序运行情况是否正常
            //String sWord = AlipaySignature.getSignCheckContentV1(params);
            //AlipayProperties.logResult(sWord);
        }
    }
   @RequestMapping("returnUrl")
    public void returnUrl(HttpServletRequest request, HttpServletResponse response) throws Exception{
        //输出
        response.setContentType("text/html; CHARSET=utf-8");
        response.setCharacterEncoding("utf-8");
        ServletOutputStream out = response.getOutputStream();

        /* *
         * 功能：支付宝服务器同步通知页面
         * 日期：2017-03-30
         * 说明：
         * 以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
         * 该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
         */

        //获取支付宝GET过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }
        //阿里公钥验证签名 私钥解密
        boolean signVerified = AlipaySignature.rsaCheckV1(params, AlipayProperties.PUBLIC_KEY, AlipayProperties.CHARSET, AlipayProperties.SIGN_TYPE); //调用SDK验证签名

        //——请在这里编写您的程序（以下代码仅作参考）——
        //判断签名状态
        if(signVerified) {
            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //付款金额
            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");

           // out.println("trade_no:"+trade_no+"<br/>out_trade_no:"+out_trade_no+"<br/>total_amount:"+total_amount);
            // 支付宝get请求带来的数据输出订单信息
            out.write(("trade_no:"+trade_no+"<br/>out_trade_no:"+out_trade_no+"<br/>total_amount:"+total_amount).getBytes());
        }else {
            out.println("验签失败");
        }
        //——请在这里编写您的程序（以上代码仅作参考）——
    }
}
