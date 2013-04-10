package com.jk.net.app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;

public class PageConvertPublisher {

		int MIN_FILE_SIZE = 100;
		final static int ERR_NOT_FOUND_HTML = 1;
		final static int ERR_INTR_JSP_PAGE = 2;
		final static int ERR_GET_SMALL_SIZE_FILE = 3;
		final static int ERR_MAX_TRY_NO_GET = 4;
		final static int ERR_WRITING_EXCEPTION = 5;
		int intCount = 0;
		int nErrCode;
		
		public PageConvertPublisher(){
			
		}
		
		public String saveToTempFile(String strFromUrl, String strToFile) throws Exception {
			int nTimeOut = 60* 1000;
			StringBuffer sbResult = new StringBuffer();
			
			URL url = null;
			HttpURLConnection httpUrlConn = null;
			String line = "";
			StringBuffer  sbContent = new StringBuffer();
			
			try{
				
				url = new URL(strFromUrl);
				httpUrlConn = (HttpURLConnection) url.openConnection();
				httpUrlConn.setReadTimeout(nTimeOut);
				
				sbResult.append(Integer.toString(httpUrlConn.getResponseCode()));
				
				if( httpUrlConn.getResponseCode() == 200 ){
					System.out.println("encoding:" +  httpUrlConn.getContentEncoding() );
					System.out.println("length:" +  httpUrlConn.getContentLength() );
					System.out.println("contentType:" + httpUrlConn.getContentType() );
					
					//contentType:text/html; charset=UTF-8
					//contentType:image/jpeg
					
					
					BufferedReader reader = new BufferedReader( new InputStreamReader( httpUrlConn.getInputStream()) );
					
					//
					while( (line = reader.readLine()) != null ){
						sbContent.append(line + "\n");
						
					}
					System.out.println( sbContent.toString() );
					
					// byteBuffer ¿˙¿Â
					int bytelength = sbContent.toString().getBytes().length;
					
					System.out.println( "[bytelength]" + bytelength );
					
					ByteBuffer buffer = ByteBuffer.allocate(bytelength);
					buffer.put(sbContent.toString().getBytes());
					buffer.flip();
					
					File file = new File(strToFile);
					FileOutputStream fout = new FileOutputStream(file.getAbsoluteFile());
					FileChannel fileChannel = fout.getChannel();
					BufferedWriter buffWriter = new BufferedWriter( Channels.newWriter(fileChannel, "UTF-8")  );
					
					fileChannel.write(buffer, 0);
					
					buffWriter.flush();
					buffWriter.close();
					fileChannel.close();
					buffer.clear();
					
				}
			
			}catch(SocketTimeoutException ex){	
				ex.printStackTrace();
				throw ex;
			}catch(Exception e){
				e.printStackTrace();
				throw e;
			}finally{
				if(httpUrlConn!=null){
					try{httpUrlConn.disconnect();}catch(Exception e){}
				}
			}
			
			return sbResult.toString();
			
		}
	
}
