package com.boutline.sports.helpers;

import android.util.Log;

import org.jivesoftware.smack.AccountManager;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.filter.MessageTypeFilter;
import org.jivesoftware.smack.filter.PacketFilter;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;
import org.jivesoftware.smack.packet.Presence;
import org.jivesoftware.smack.util.StringUtils;
import org.jivesoftware.smackx.Form;
import org.jivesoftware.smackx.ReportedData;
import org.jivesoftware.smackx.search.UserSearchManager;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by administrator on 9/8/15.
 */
public class BoXmppConnection
{
    public static final String HOST = "boutchat.cloudapp.net";
    public static final int PORT = 5222;
    public static final String SERVICE = "boutline.com";//its called domain also
    public static  String USERNAME = "febin";
    public static  String PASSWORD = "febin";
    public static String NAME ="";
    public static String EMAIL = "";

    private String TAG = "XMPPBOUTLINE";
    private XMPPConnection connection = null;

    private static BoXmppConnection instance = null;

    public synchronized static BoXmppConnection getInstance() {
        if(instance==null){
            instance = new BoXmppConnection();
        }
        return instance;
    }

    /*public void setConnection(XMPPConnection connection){
        this.connection = connection;
    }*/

    public XMPPConnection getConnection() {
        return this.connection;
    }


    /**
     * This methods creates an xmpp connection
     */
    public void connect()
    {

        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                // Create a connection
                ConnectionConfiguration connConfig = new ConnectionConfiguration(HOST, PORT, SERVICE);
                connection = new XMPPConnection(connConfig);

                try {
                    connection.connect();
                    Log.i("XMPPBOUTLINE", "Connected to " + connection.getHost());
                    //if(!ifUserExist(USERNAME))

                        registerToXmpp(USERNAME , PASSWORD);
                    /*else
                    {
                        //do something to show username doesnt exist
                    }*/

                } catch (XMPPException ex) {
                    Log.e("XMPPBOUTLINE", "Failed to connect to "
                            + connection.getHost());
                    Log.e("XMPPBOUTLINE", ex.toString());
                    //setConnection(null);
                }
            }
        });
        t.start();

    }


    public boolean loginToXmpp(String username,String password)
    {
        boolean isConnected = false;
                // Create a connection
                ConnectionConfiguration connConfig = new ConnectionConfiguration(HOST, PORT, SERVICE);
                connection = new XMPPConnection(connConfig);

                try {
                    connection.connect();
                    Log.i("XMPPBOUTLINE", "Connected to " + connection.getHost());
                } catch (XMPPException ex) {
                    Log.e("XMPPBOUTLINE", "Failed to connect to "
                            + connection.getHost());
                    Log.e("XMPPBOUTLINE", ex.toString());
                    //setConnection(null);
                }
                try {
                    // SASLAuthentication.supportSASLMechanism("PLAIN", 0);
                    connection.login(USERNAME, PASSWORD);
                    Log.i("XMPPBOUTLINE",
                            "Logged in as " + connection.getUser());

                    // Set the status to available
                    Presence presence = new Presence(Presence.Type.available);
                    connection.sendPacket(presence);
                    setConnection(connection);
                    isConnected = true;

                } catch (XMPPException ex) {
                    Log.e(TAG, "Failed to log in as "
                            + USERNAME);
                    Log.e(TAG, ex.toString());
                    setConnection(null);
                }
        return isConnected;

    }


    public void registerToXmpp(String username,String password)
    {
        /*AccountManager accountManager=new AccountManager(connection);
        try {
            accountManager.createAccount("username", "password");
        } catch (XMPPException e1) {
            e1.printStackTrace();
        }*/
            try {
                AccountManager am = new AccountManager(connection);
                if (am.supportsAccountCreation ()) {
                    Log.i("Registration Details:", "UaerName = "
                            + username + "  Password is =="
                            + password);
                    Map<String, String> mp = new HashMap<String, String>();

                    // adding or set elements in Map by put method key and value
                    // pair
                    mp.put("username", username);
                    mp.put("password", password);
                    mp.put("name", NAME);
                    mp.put("email", EMAIL);

                    // am.createAccount(mConfig.userName, mConfig.password);
                    am.createAccount(username, password, mp);
                }
                else
                    Log.v(TAG , "does not support account creation");
            }catch (Exception e)
            {
                e.printStackTrace();
            }
    }


    public boolean ifUserExist(String username)
    {
        try {
            UserSearchManager search = new UserSearchManager(connection);
            Form searchForm = search
                    .getSearchForm("search." + connection.getServiceName());

            Form answerForm = searchForm.createAnswerForm();
            answerForm.setAnswer("username", true);
            answerForm.setAnswer("search", username);
            ReportedData data = search
                    .getSearchResults(answerForm, "search." + connection.getServiceName());

            if (data.getRows() != null) {
                /*for (ReportedData.Row row : data.getRows()) {
                    for (String value : row.getValues("jid")) {
                        Log.i("Iteartor values......", " " + value);
                    }
                }*/
                //Toast.makeText(_service, "Username Exists", Toast.LENGTH_SHORT).show();

                Log.v(TAG , "username is there");
                return true;
            }
            else {
                Log.v(TAG , "username is not registered");
                return false;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return false;
    }


    public void setConnection(XMPPConnection connection) {
        this.connection = connection;
        if (connection != null) {
            // Add a packet listener to get messages sent to us
            PacketFilter filter = new MessageTypeFilter(Message.Type.chat);
            connection.addPacketListener(new PacketListener() {
                @Override
                public void processPacket(Packet packet) {
                    Message message = (Message) packet;
                    if (message.getBody() != null) {
                        String fromName = StringUtils.parseBareAddress(message
                                .getFrom());
                        Log.i(TAG, "Text Recieved " + message.getBody()+ " from " + fromName );

                    }
                }
            }, filter);
        }
    }



}
