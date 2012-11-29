package com.lce.atg.cr2;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.NetworkOnMainThreadException;

public class RequestTask extends AsyncTask<String, String, String> {
	private Handler handler ;

	public RequestTask(Handler handler) {
		this.handler = handler;
	}

	@Override
	protected String doInBackground(String... uri) {
		HttpClient httpclient = new DefaultHttpClient();
		HttpResponse response;
		String responseString = null;
		try {
			response = httpclient.execute(new HttpGet(uri[0]));
			StatusLine statusLine = response.getStatusLine();
			if (statusLine.getStatusCode() == HttpStatus.SC_OK) {
				ByteArrayOutputStream out = new ByteArrayOutputStream();
				response.getEntity().writeTo(out);
				out.close();
				responseString = out.toString();
			} else {
				// Closes the connection.
				response.getEntity().getContent().close();
				// throw new IOException(statusLine.getReasonPhrase());
			}
		} catch (ClientProtocolException e) {

		} catch (IOException e) {
		} catch (NetworkOnMainThreadException nomte) {
		}
		return responseString;
	}

	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);

		// Send JSON response to message handler
		Message message = handler.obtainMessage();
		Bundle bundle = new Bundle();
		bundle.putString("SOLDIER.BUDDY.RESPONSE", result);
		message.setData(bundle);
		handler.sendMessage(message);
	}

}
