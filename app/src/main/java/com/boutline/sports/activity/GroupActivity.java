package com.boutline.sports.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.boutline.sports.R;
import com.boutline.sports.adapters.ChatListAdapter;
import com.boutline.sports.helpers.ResideMenu;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;


public class GroupActivity extends Activity {

    @InjectView(R.id.meActivityToolbar)
    Toolbar meActivityToolbar;
    @InjectView(R.id.groupToolbarTextView)
    TextView groupToolbarTextView;
    @InjectView(R.id.editText)
    EditText editText;
    @InjectView(R.id.imgButton)
    ImageButton imgButton;
    @InjectView(R.id.dateText1)TextView dateText1;
    @InjectView(R.id.uname1)TextView    uname1;
    @InjectView(R.id.uname2)TextView    uname2;
    @InjectView(R.id.uname3)TextView    uname3;
    @InjectView(R.id.uname4)TextView    uname4;

    @InjectView(R.id.time1)TextView     time1;
    @InjectView(R.id.time2)TextView     time2;
    @InjectView(R.id.time3)TextView     time3;
    @InjectView(R.id.time4)TextView     time4;

    @InjectView(R.id.fname1)TextView    fname1;
    @InjectView(R.id.fname2)TextView    fname2;
    @InjectView(R.id.fname3)TextView    fname3;

    @InjectView(R.id.bo1)TextView       bo1;

    @InjectView(R.id.predictionA)TextView predictionA;
    @InjectView(R.id.predictionB)TextView predictionB;
    @InjectView(R.id.or)TextView or;
    @InjectView(R.id.desc)TextView desc;
    @InjectView(R.id.forTeamA)TextView forTeamA;
    @InjectView(R.id.forTeamB)TextView forTeamB;

    @InjectView(R.id.leftMenu)
    ImageView leftMenu;
    @InjectView(R.id.rightMenu)
    ImageView rightMenu;

    @OnClick(R.id.imgButton)
    void send() {
        sendMessage(editText.getText().toString());
        editText.setText("");
    }

    @OnClick(R.id.leftMenu)
    void openLeftMenu() {
        resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
    }

    @OnClick(R.id.rightMenu)
    void openRightMenu() {
        resideMenu.openMenu(ResideMenu.DIRECTION_RIGHT);
    }

    @OnClick(R.id.groupToolbarTextView)
    void openGroupSettings() {
        groupToolbarTextView.setTextColor(Color.parseColor("#000000"));
        Intent i = new Intent(getBaseContext(), GroupSettingsActivity.class);
        startActivity(i);
        overridePendingTransition(R.anim.pushdownin, R.anim.pushdownout);
    }

    ResideMenu resideMenu;
    Typeface defaultFont;
    Typeface defaultFontBold;

   // XMPPTCPConnection connection;
    boolean isConnected;

    ArrayList<String> stringArrayList = new ArrayList<>();

    // TODO: Use butterknife

    ListView activityMeChatListView;
    ArrayList<String> chatMessages = new ArrayList<>();
    ChatListAdapter chatListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);
        ButterKnife.inject(this);
        groupToolbarTextView.setTextColor(Color.parseColor("#000000"));

        // attach to current activity;
        resideMenu = new ResideMenu(this);
        resideMenu.attachToActivity(this);
        resideMenu.setScaleValue(0.5f);

        RelativeLayout layout = (RelativeLayout) findViewById(R.id.meLayout);
        layout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Log.e("clicked", "activity");

            }
        });

        defaultFont = Typeface.createFromAsset(getAssets(), "fonts/avonixorp.ttf");
        defaultFontBold = Typeface.createFromAsset(getAssets(), "fonts/avonixorpbold.ttf");

        groupToolbarTextView.setTypeface(defaultFont);
        editText.setTypeface(defaultFont);
        dateText1.setTypeface(defaultFont);
        uname1.setTypeface(defaultFontBold);
        uname2.setTypeface(defaultFontBold);
        uname3.setTypeface(defaultFontBold);
        uname4.setTypeface(defaultFontBold);

        time1.setTypeface(defaultFont);
        time2.setTypeface(defaultFont);
        time3.setTypeface(defaultFont);
        time4.setTypeface(defaultFont);

        fname1.setTypeface(defaultFont);
        fname2.setTypeface(defaultFontBold);
        fname3.setTypeface(defaultFont);

        predictionA.setTypeface(defaultFontBold);
        predictionB.setTypeface(defaultFontBold);
        or.setTypeface(defaultFont);
        desc.setTypeface(defaultFont);
        forTeamA.setTypeface(defaultFont);
        forTeamB.setTypeface(defaultFont);


        bo1.setTypeface(defaultFontBold);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable arg0) {
                boolean isReady = editText.getText().toString().length() > 0;
                if (isReady)
                    imgButton.setVisibility(View.VISIBLE);
                else
                    imgButton.setVisibility(View.INVISIBLE);

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }
        });

    }

    private void sendMessage(final String messageText) {
        new sendMessageAsync().execute();

        if (messageText.trim().length() == 0)
            return;
        chatMessages.add(messageText);
        Log.e("whatMessage", messageText);

        if (chatListAdapter != null)
            chatListAdapter.notifyDataSetChanged();

        activityMeChatListView = (ListView) findViewById(R.id.activityMeChatListView);
        chatListAdapter = new ChatListAdapter(getApplicationContext(), R.layout.chat_user_layout, chatMessages);
        activityMeChatListView.setAdapter(chatListAdapter);
    }

    private class sendMessageAsync extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {

            /*isConnected = false;

            XMPPTCPConnectionConfiguration.Builder config = XMPPTCPConnectionConfiguration.builder();
            config.setUsernameAndPassword("deepak@localhost"
                    , "bout123");
            config.setServiceName("localhost");
            config.setHost("boutchat.cloudapp.net");
            config.setPort(5222);
            config.setResource("test");
            config.setDebuggerEnabled(true);
            config.setSecurityMode(XMPPTCPConnectionConfiguration.SecurityMode.disabled);
            config.setCompressionEnabled(false);

            SASLDigestMD5Mechanism mechanism = new SASLDigestMD5Mechanism();
            SASLAuthentication.registerSASLMechanism(mechanism);
            SASLAuthentication.blacklistSASLMechanism("SCRAM-SHA-1");
            SASLAuthentication.blacklistSASLMechanism("DIGEST-MD5");
            SASLAuthentication.unBlacklistSASLMechanism("PLAIN");

            connection = new XMPPTCPConnection(config.build());

            ChatManager chatmanager = ChatManager.getInstanceFor(connection);

            MultiUserChatManager multiUserChatManager = MultiUserChatManager.getInstanceFor(connection);

            Message message = new Message();
            message.setBody("Howdy!");
            message.setType(Message.Type.groupchat);


            try {
                connection.connect();
                Log.e("connection status", "success");
                isConnected = true;
                connection.login();
                Log.e("Logged in?", connection.isAuthenticated() + "");

                *//*
                                    create a multi user chat room (group)
                 *//*
                MultiUserChat multiUserChat = multiUserChatManager.getMultiUserChat("testroom4@conference.localhost");
                multiUserChat.create("testroom4");
                multiUserChat.sendConfigurationForm(new Form(DataForm.Type.submit));

                *//*
                                    get info about a room
                 *//*
                *//*RoomInfo info = multiUserChatManager.getRoomInfo("testroom2@conference.localhost");
                Log.e("info", info.getOccupantsCount()+"");*//*

                *//*
                                    send messages to a room
                 *//*
                message.setTo("testroom4@conference.localhost");
                *//*multiUserChat.sendMessage(message);*//*
                Chat newChat = chatmanager.createChat("testroom4@conference.localhost", new ChatMessageListener() {
                    @Override
                    public void processMessage(Chat chat, Message message) {
                        try {
                            //Log.e("message", message.getBody());

                            //chat.sendMessage(message.getBody());
                        } catch (Exception e) {
                            Log.e("Exception", e.toString());
                        }
                    }
                });

                newChat.sendMessage(message);

                *//*
                                for debugging purposes only leave group to join again
                 *//*
                //multiUserChat.leave();

            } catch (Exception e) {
                connection = null;
                Log.e("Exception", e.toString());
            }
            //return isConnected;*/
            return null;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_me, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.pushupin, R.anim.pushupout);
    }
}
