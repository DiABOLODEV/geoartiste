package fr.istic.geoartiste.webservices;



import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import android.content.ContentResolver;
import android.content.ContextWrapper;




import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestHandle;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.ResponseHandlerInterface;

import fr.istic.geoartiste.utils.Constants;
import fr.istic.geoartiste.utils.TraceLog;

public class RestClient extends AsyncHttpClient {

    private static final String DEFAULT_BASE_URL = "http://geo-artiste.irisa.fr/api/";
   
    private ContextWrapper mContextWrapper;
    private ContentResolver mContentResolver;
    private String mCustomBaseUrl = "";
   
    private static final String GET = "GET";
    private static final String POST = "POST";
    private static final String DELETE = "DELETE";
    
    /**
     * 
     * @param cw the contextwrapper
     * @param cr the contentresolver
     */
	public RestClient (ContextWrapper cw,ContentResolver cr){
        super();
        mContextWrapper = cw;
        mContentResolver = cr;
    }
   	
	/**
	 * Allow to change base url
	 * @param customBaseUrl new base url
	 */
    public void setCustomBaseUrl(String customBaseUrl){
        mCustomBaseUrl = customBaseUrl;
    }
   
    /**
     * Construct absolute url with base url and relative url
     * @param relativeUrl the relative url
     * @return the absolute url
     */
    private String getAbsoluteUrl(String relativeUrl){
        return ((mCustomBaseUrl.length() > 0) ? mCustomBaseUrl : DEFAULT_BASE_URL) + relativeUrl;
    }
   
    /**
     * Add additional parameters to the request
     * @param params request parameters
     * @return all the parameters
     */
    private RequestParams addParameters(RequestParams params){
        if(params == null) params = new RequestParams();/*
        params.put("device_id",Utils.getIdentifiantTerminal(mContextWrapper, mContentResolver));
        params.put("application_version", Utils.getApplicationVersionName(mContextWrapper));
        params.put("screen_scale", String.valueOf(Utils.getDensity(mContextWrapper)));
        params.put("application_package", Utils.getApplicationPackageName(mContextWrapper) + ".android");
        params.put("current_language", Utils.getCurrentLanguage());*/
        return params;
    }

    /**
     * Display url for debugging
     * @param type GET/POST/DELETE
     * @param relativeUrl url 
     * @param params url parameters
     */
    private void debug(String type, String relativeUrl, RequestParams params){
    	TraceLog.debug(type + " : " + getAbsoluteUrl(relativeUrl) + "?" + (params != null ? params.toString() : ""));
    }
    
    @Override
	public RequestHandle get(String url,ResponseHandlerInterface responseHandler){
		RequestParams params = addParameters(null);
		debug(GET,url,params);
		return super.get(getAbsoluteUrl(url),params,responseHandler);
	}
    
    @Override 
    public RequestHandle get(String url, RequestParams params, ResponseHandlerInterface responseHandler) {
    	params = addParameters(params);
    	debug(GET,url,params);
    	return super.get(getAbsoluteUrl(url),params,responseHandler);
    }
    

    @Override
    public RequestHandle post(String url, ResponseHandlerInterface responseHandler) {
    	RequestParams params = addParameters(null);
    	debug(POST,url,params);
    	return super.post(getAbsoluteUrl(url),params,responseHandler);
    }
    
    @Override
    public RequestHandle post(String url, RequestParams params, ResponseHandlerInterface responseHandler) {
    	params = addParameters(params);
    	debug(POST,url,params);
    	return super.post(getAbsoluteUrl(url),params,responseHandler);
    }
    
    @Override
    public RequestHandle delete(String url,ResponseHandlerInterface responseHandler){
    	debug(DELETE,url,null);
        return super.delete(getAbsoluteUrl(url),responseHandler);
    }
    
    /**
     * Permet de faire une requête http de maniere
     * synchrone
     * @param url
     * @return
     */
    public String syncGet(String url){
	
	String r = "";
	
	//On ajoutes les parametres obligatoire
	HttpGet httpGet = new HttpGet(url+'?'+addParameters(null));
	HttpParams httpParameters = new BasicHttpParams();

	//On definie les timeout
	int timeoutConnection = Constants.CONF_TIMEOUT;
	HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
	
	int timeoutSocket = Constants.CONF_TIMEOUT;
	HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);

	DefaultHttpClient httpClient = new DefaultHttpClient(httpParameters);
	
	try {
	    HttpResponse response = httpClient.execute(httpGet);
	    StatusLine statusLine = response.getStatusLine();
	    if(statusLine.getStatusCode() == HttpStatus.SC_OK){
	        ByteArrayOutputStream out = new ByteArrayOutputStream();
	        response.getEntity().writeTo(out);
	        out.close();
	        r = out.toString();
	    } else{
	        //Closes the connection.
	        response.getEntity().getContent().close();
	        throw new IOException(statusLine.getReasonPhrase());
	    }
	} catch (ClientProtocolException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	}
	
	return r;
	   
	
    }
}
