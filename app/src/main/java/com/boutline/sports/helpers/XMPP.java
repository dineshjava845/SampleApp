package com.boutline.sports.helpers;

/**
 * Created by Administrator on 5/19/2015.
 */
public class XMPP {/*

    private String serverAddress;
    private XMPPTCPConnection connection;
    private String mUserName;
    private String mPassword;

    boolean isConnected;

    public XMPP(String serverAddress, String userName, String password) {
        this.serverAddress = serverAddress;
        this.mUserName = userName;
        this.mPassword = password;
    }

    public void connect() {
        new connectToXMPPServerInBackground().execute();

    }

    private class connectToXMPPServerInBackground extends AsyncTask<Void, Void, Boolean>
    {
        public LoginAsyncResponse delegate = null;

        @Override
        protected Boolean doInBackground(Void... params) {
            isConnected = false;

            XMPPTCPConnectionConfiguration.Builder config = XMPPTCPConnectionConfiguration.builder();
            config.setUsernameAndPassword(mUserName, mPassword);
            config.setServiceName("localhost");
            config.setHost(serverAddress);
            config.setPort(5222);
            config.setResource("test");
            config.setDebuggerEnabled(true);
            config.setSecurityMode(XMPPTCPConnectionConfiguration.SecurityMode.disabled);
            config.setCompressionEnabled(false);
            config.setConnectTimeout(10000);

            SASLDigestMD5Mechanism mechanism = new SASLDigestMD5Mechanism();
            SASLAuthentication.registerSASLMechanism(mechanism);
            SASLAuthentication.blacklistSASLMechanism("SCRAM-SHA-1");
            SASLAuthentication.blacklistSASLMechanism("DIGEST-MD5");
            SASLAuthentication.unBlacklistSASLMechanism("PLAIN");

            connection = new XMPPTCPConnection(config.build());

            try {
                connection.connect();
                Log.e("connection status", "success");
                connection.login();
                Log.e("Logged in?",connection.isAuthenticated()+"");

                isConnected = true;
            }
            catch (Exception e) {
                Log.e("Exception", e.toString());
            }
            return isConnected;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            delegate.processFinish(aBoolean);
        }
    }*/
}
