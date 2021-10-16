package com.longnq.webservices;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;

import org.apache.wss4j.common.ext.WSPasswordCallback;


public class PasswordCallbackImpl implements CallbackHandler {

	Map<String, String> credentials = new HashMap<String, String>();
	
	public PasswordCallbackImpl() {
		credentials.put("user1", "123456");
		credentials.put("user2", "123456");
		credentials.put("user3", "123456");
	}
	
	
	@Override
	public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
		
		for(Callback callback : callbacks) {
			WSPasswordCallback passwordCallback = (WSPasswordCallback) callback;
			String password = credentials.get(passwordCallback.getIdentifier());
			if(password != null) {
				passwordCallback.setPassword(password);
			}
		}
		
	}

}
