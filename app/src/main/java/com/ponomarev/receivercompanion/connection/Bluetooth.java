package com.ponomarev.receivercompanion.connection;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import static android.support.v4.app.ActivityCompat.startActivityForResult;

/**
 * Created by Ponomarev on 13.06.2016.
 */
public class Bluetooth {

    private static final int REQUEST_ENABLE_BT = 1;


    private final Context context;
    private final Activity activity;

    private final Bluetooth.BTBroadcastReceiver BTBroadcastReceiver = new BTBroadcastReceiver();


    public Bluetooth(Activity activity) {
        this.activity = activity;
        context = activity.getApplicationContext();

        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            // Device does not support Bluetooth
            Toast.makeText(context, "Device doesn't support bluetooth.", Toast.LENGTH_LONG).show();
            return;
        }

        if (!mBluetoothAdapter.isEnabled()) {
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(activity, enableBtIntent, REQUEST_ENABLE_BT, null);
        }
    }

    public static class BTBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if (BluetoothAdapter.ACTION_STATE_CHANGED.equals(action)) {
                int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, -1);

                switch (state) {
                    case BluetoothAdapter.STATE_OFF:
                    case BluetoothAdapter.STATE_ON:
                    case BluetoothAdapter.STATE_TURNING_OFF:
                    case BluetoothAdapter.STATE_TURNING_ON:


                        break;

                }
            }
        }
    }
}
