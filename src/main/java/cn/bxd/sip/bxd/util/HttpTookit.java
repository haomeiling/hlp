package cn.bxd.sip.bxd.util;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.HttpStatus;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

public final class HttpTookit {
	
	private static int TIME_OUT = 30 * 1000;
	
	/**
	 * 执行一个HTTP GET请求，返回请求响应的HTML
	 * @param url
	 *            请求的URL地址
	 * @param queryString
	 *            请求的查询参数,可以为null
	 * @param charset
	 *            字符集
	 * @param pretty
	 *            是否美化
	 * @return 返回请求响应的HTML
	 */
	public static String httpPost( String url, String inputData, String dllParam, String dllparm,
			String cardInfo ){
		// 定义状态码
		int statusCode = 0;
		// 定义目标地址的内容
		String responseString = null;
		HttpClient client = new HttpClient( );
		PostMethod post = new PostMethod( url );
		client.getParams( ).setContentCharset( "UTF-8" );
		client.getParams( ).setHttpElementCharset( "UTF-8" );
		client.getHttpConnectionManager( ).getParams( ).setConnectionTimeout( 60000 );
		client.getHttpConnectionManager( ).getParams( ).setSoTimeout( 60000 );
		post.addRequestHeader( "Content-Type", "application/x-www-form-urlencoded; charset=utf-8" );
		post.setParameter( "inputData", inputData );
		post.setParameter( "dllParam", dllParam );
		post.setParameter( "dllparm", dllparm );
		post.setParameter( "cardInfo", cardInfo );
		post.setParameter( "cardApp", "true" );
		post.getParams( ).setParameter( HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler( ) );
		try{
			statusCode = client.executeMethod( post );
			if ( statusCode != HttpStatus.SC_OK ){
				System.out.println( "医保接口：调用交易出错，错误原因:" + post.getStatusText( ) );
			}
			responseString = post.getResponseBodyAsString( );
		}
		catch ( Exception e ){
			System.out.println( "医保接口：调用交易出错，错误原因:" + post.getStatusText( ) + ",异常信息："
					+ e.getMessage( ) );
		}
		finally{
			post.releaseConnection( );
		}
		return responseString;
	}
	
	public static String httpPost( String url, String cardInfo, String inputData, String license ){
		HttpClient client = new HttpClient( );
		PostMethod post = new PostMethod( url );
		post.addParameter( "cardInfo", cardInfo );
		post.addParameter( "inputData", inputData );
		post.addParameter( "license", license );
		int status = 0;
		String result;
		try{
			status = client.executeMethod( post );
			if ( status == 200 ){
				result = post.getResponseBodyAsString( );
			}
			else{
				result = "网络出现通讯异常，请联系信息中心！";
			}
		}
		catch ( HttpException e ){
			result = "网络出现http异常，请联系信息中心！";
			e.printStackTrace( );
		}
		catch ( IOException e ){
			result = "参保地服务长时间无响应，请联系信息中心！";
			e.printStackTrace( );
		}
		finally{
			post.releaseConnection( );
		}
		return result;
	}
	
	public static String getNextInt( ){
		Random r = new Random( );
		String sessionkey = "" + r.nextInt( 10 ) + r.nextInt( 10 ) + r.nextInt( 10 )
				+ r.nextInt( 10 ) + r.nextInt( 10 ) + r.nextInt( 10 );
		return sessionkey;
	}
	
	public static String HttpPostSoap( String ADDRESS, String reqxml ) throws Exception{
		PostMethod postMethod = new PostMethod( ADDRESS );
		byte[] b = reqxml.getBytes( "GBK" );
		InputStream is = new ByteArrayInputStream( b, 0, b.length );
		RequestEntity res = new InputStreamRequestEntity( is, b.length,
				"application/soap+xml; charset=GBK" );
		postMethod.setRequestEntity( res );
		HttpClient httpClient = new HttpClient( );
		int statusCode = httpClient.executeMethod( postMethod );
		String soapRequestResponse = postMethod.getResponseBodyAsString( );
		return soapRequestResponse;
	}
	
	/**
	 * 发送请求数据到服务端接口地址
	 * @param urlstr
	 * @param postdata
	 * @return
	 */
	public static String sendRequest( String urlstr, String postdata ){
		StringBuilder tempStr = new StringBuilder( );
		InputStream in = null;
		BufferedReader rd = null;
		HttpURLConnection urlConn = null;
		try{
			URL url = new URL( urlstr );
			urlConn = (HttpURLConnection)url.openConnection( );
			urlConn.setRequestMethod( "GET" );
			urlConn.setDoOutput( true );
			urlConn.setReadTimeout( TIME_OUT );
			// 组装请求包体json
			urlConn.getOutputStream( ).write( postdata.getBytes( "utf-8" ) );
			urlConn.getOutputStream( ).flush( );
			in = urlConn.getInputStream( );
			rd = new BufferedReader( new InputStreamReader( in, "utf-8" ) );
			String tmps = null;
			while ( ( tmps = rd.readLine( ) ) != null ){
				tempStr.append( tmps.trim( ) );
			}
		}
		catch ( MalformedURLException e ){
			e.printStackTrace( );
		}
		catch ( IOException e ){
			e.printStackTrace( );
		}
		finally{
			if ( rd != null ){
				try{
					rd.close( );
				}
				catch ( IOException e ){
					e.printStackTrace( );
				}
			}
			if ( in != null ){
				try{
					in.close( );
				}
				catch ( IOException e ){
					e.printStackTrace( );
				}
			}
			if ( urlConn != null )
				urlConn.disconnect( );
		}
		return tempStr.toString( );
	}
}
