package com.hss01248.photoouter;

import android.Manifest;
import android.os.Build;

import com.mylhyl.acp.Acp;
import com.mylhyl.acp.AcpListener;
import com.mylhyl.acp.AcpOptions;

import java.util.List;

/**
 *
 * 需要添加依赖：
 *  compile 'com.mylhyl:acp:1.0.0'
 *
 * Created by Administrator on 2016/6/15 0015.
 */
public class PermissionUtils {


    /**
     *  compile 'com.mylhyl:acp:1.0.0'
     * @param listener
     * @param permission
     */
    private static void askPermission(final PermissionListener listener,String... permission){
        if (Build.VERSION.SDK_INT >= 23) {
            // Marshmallow+
            Acp.getInstance(PhotoUtil.context).request(new AcpOptions.Builder()
                            .setPermissions(permission)
//                .setDeniedMessage()
//                .setDeniedCloseBtn()
//                .setDeniedSettingBtn()
//                .setRationalMessage()
//                .setRationalBtn()
                            .build(),
                    new AcpListener() {
                        @Override
                        public void onGranted() {
                            listener.onGranted();
                        }

                        @Override
                        public void onDenied(List<String> permissions) {
                            listener.onDenied(permissions);
                           // MyToast.showFailToast("权限已经被拒绝");
                        }
                    });
        } else {
            // Pre-Marshmallow
            listener.onGranted();
        }
    }

    /**
     * group:android.permission-group.CALENDAR
     permission:android.permission.READ_CALENDAR
     permission:android.permission.WRITE_CALENDAR

     */
    public static void askCalendar(PermissionListener listener){
        askPermission(listener, Manifest.permission.READ_CALENDAR);
    }

    /**
     * group:android.permission-group.CAMERA
     permission:android.permission.CAMERA

     */
    public static void askCamera(PermissionListener listener){
        askPermission(listener, Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }


    /**
     * group:android.permission-group.STORAGE
     permission:android.permission.READ_EXTERNAL_STORAGE
     permission:android.permission.WRITE_EXTERNAL_STORAGE

     */
    public static void askExternalStorage(PermissionListener listener){
        askPermission(listener, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    }

    /**
     * group:android.permission-group.PHONE
     *
     permission:android.permission.READ_CALL_LOG
     permission:android.permission.READ_PHONE_STATE
     permission:android.permission.CALL_PHONE
     permission:android.permission.WRITE_CALL_LOG
     permission:android.permission.USE_SIP
     permission:android.permission.PROCESS_OUTGOING_CALLS
     permission:com.android.voicemail.permission.ADD_VOICEMAIL

     */
    public static void askPhone(PermissionListener listener){
        askPermission(listener, Manifest.permission.READ_PHONE_STATE);
    }
    public static void askCallPhone(PermissionListener listener){
        askPermission(listener, Manifest.permission.CALL_PHONE);
    }

    /**
     * group:android.permission-group.SMS
     *
     permission:android.permission.READ_SMS
     permission:android.permission.RECEIVE_WAP_PUSH
     permission:android.permission.RECEIVE_MMS
     permission:android.permission.RECEIVE_SMS
     permission:android.permission.SEND_SMS
     permission:android.permission.READ_CELL_BROADCASTS

     */
    public static void askSms(PermissionListener listener){
        askPermission(listener, Manifest.permission.SEND_SMS);
    }




    /**
     * group:android.permission-group.LOCATION
     permission:android.permission.ACCESS_FINE_LOCATION
     permission:android.permission.ACCESS_COARSE_LOCATION
     */
    public static void askLocationInfo(PermissionListener listener){
        askPermission(listener, Manifest.permission.ACCESS_COARSE_LOCATION);
    }


    /**
     * group:android.permission-group.MICROPHONE
     permission:android.permission.RECORD_AUDIO

     */
    public static void askRecord(PermissionListener listener){
        askPermission(listener, Manifest.permission.RECORD_AUDIO);
    }


    /**
     * group:android.permission-group.SENSORS
     permission:android.permission.BODY_SENSORS

     */
    public static void askSensors(PermissionListener listener){
        askPermission(listener, Manifest.permission.BODY_SENSORS);
    }

    /**
     * group:android.permission-group.CONTACTS
     permission:android.permission.WRITE_CONTACTS
     permission:android.permission.GET_ACCOUNTS
     permission:android.permission.READ_CONTACTS

     */
    public static void askContacts(PermissionListener listener){
        askPermission(listener, Manifest.permission.READ_CONTACTS);
    }

    public interface  PermissionListener{
        void onGranted();
        void onDenied(List<String> permissions);

    }


}
