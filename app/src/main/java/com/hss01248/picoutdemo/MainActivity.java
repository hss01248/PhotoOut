package com.hss01248.picoutdemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.hss01248.photoouter.PhotoCallback;
import com.hss01248.photoouter.PhotoUtil;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends Activity {

    @Bind(R.id.camera)
    Button camera;
    @Bind(R.id.pick1_crop)
    Button pick1Crop;
    @Bind(R.id.pic9)
    Button pic9;
    @Bind(R.id.pick1)
    Button pick1;
    @Bind(R.id.activity_main)
    LinearLayout activityMain;
    PhotoCallback callback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
       // PhotoUtil.init(getApplicationContext(),R.color.colorPrimaryDark,R.color.colorPrimary);


        callback = ProxyTools.getShowMethodInfoProxy(new PhotoCallback() {
            @Override
            public void onFail(String msg, Throwable r, int requestCode) {

            }

            @Override
            public void onSuccessSingle(String originalPath, String compressedPath, int requestCode) {

            }

            @Override
            public void onSuccessMulti(List<String> originalPaths, List<String> compressedPaths, int requestCode) {

            }

            @Override
            public void onCancel(int requestCode) {

            }
        });
    }

    @OnClick({R.id.camera, R.id.pick1_crop, R.id.pic9, R.id.pick1})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.camera:
                PhotoUtil.cropAvatar(true)
                        .start(this, 23, callback);
                break;
            case R.id.pick1_crop:
                PhotoUtil.begin()
                        .setFromCamera(false)
                        .setMaxSelectCount(1)
                        .setNeedCropWhenOne(true)
                        .setCropRatio(16,9)
                        .start(this, 77, callback);
                break;
            case R.id.pic9:
                PhotoUtil.multiSelect(9)
                        .start(this, 55, callback);
                break;
            case R.id.pick1:
                PhotoUtil.begin()
                        .setNeedCropWhenOne(true)
                        .setNeedCompress(true)
                        .setMaxSelectCount(1)
                        .setCropMuskOval()
                        .setSelectGif()
                        /*.setFromCamera(false)
                        .setMaxSelectCount(5)
                        .setNeedCropWhenOne(false)
                        .setNeedCompress(true)
                        .setCropRatio(16,9)*/
                        .start(this, 33, callback);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        PhotoUtil.onActivityResult(this,requestCode,resultCode,data);
    }
}
