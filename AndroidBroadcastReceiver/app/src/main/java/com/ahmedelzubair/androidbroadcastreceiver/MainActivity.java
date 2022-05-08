package com.ahmedelzubair.androidbroadcastreceiver;

import android.content.IntentFilter;
import android.net.wifi.WifiManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private WifiBroadcastReceiver wifiBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        registerNetworkChangeReceiver();

    }

    private void registerNetworkChangeReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(WifiManager.SUPPLICANT_CONNECTION_CHANGE_ACTION);

        wifiBroadcastReceiver = new WifiBroadcastReceiver();
        registerReceiver(wifiBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(wifiBroadcastReceiver);
    }

}