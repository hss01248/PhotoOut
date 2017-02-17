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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
       // PhotoUtil.init(getApplicationContext(),R.color.colorPrimaryDark,R.color.colorPrimary);
        PhotoUtil.init(getApplicationContext());
    }

    @OnClick({R.id.camera, R.id.pick1_crop, R.id.pic9, R.id.pick1})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.camera:
                PhotoUtil.cropAvatar(false)
                        .start(this, new PhotoCallback() {
                            @Override
                            public void onCancel() {
                                super.onCancel();
                            }

                            @Override
                            public void onFail(String msg, Throwable r) {
                                super.onFail(msg, r);
                            }

                            @Override
                            public void onSuccessSingle(String originalPath, String compressedPath) {
                                super.onSuccessSingle(originalPath, compressedPath);

                            }
                });
                break;
            case R.id.pick1_crop:
                PhotoUtil.begin()
                        .setFromCamera(false)
                        .setMaxSelectCount(1)
                        .setNeedCropWhenOne(true)
                        .setCropRatio(16,9)
                        .start(this, new PhotoCallback() {
                            @Override
                            public void onFail(String msg, Throwable r) {
                                super.onFail(msg, r);
                            }

                            @Override
                            public void onSuccessMulti(List<String> originalPaths, List<String> compressedPaths) {
                                super.onSuccessMulti(originalPaths, compressedPaths);
                            }

                            @Override
                            public void onSuccessSingle(String originalPath, String compressedPath) {
                                super.onSuccessSingle(originalPath, compressedPath);
                            }

                            @Override
                            public void onCancel() {
                                super.onCancel();
                            }
                        });
                break;
            case R.id.pic9:
                PhotoUtil.multiSelect(9)
                        .start(this, new PhotoCallback() {
                            @Override
                            public void onFail(String msg, Throwable r) {
                                super.onFail(msg, r);
                            }

                            @Override
                            public void onCancel() {
                                super.onCancel();
                            }

                            @Override
                            public void onSuccessMulti(List<String> originalPaths, List<String> compressedPaths) {
                                super.onSuccessMulti(originalPaths, compressedPaths);
                            }
                        });
                break;
            case R.id.pick1:
                PhotoUtil.begin()
                        .setFromCamera(false)
                        .setMaxSelectCount(5)
                        .setNeedCropWhenOne(false)
                        .setNeedCompress(true)
                        .setCropRatio(16,9)
                        .start(this, new PhotoCallback() {
                            @Override
                            public void onCancel() {
                                super.onCancel();
                            }

                            @Override
                            public void onFail(String msg, Throwable r) {
                                super.onFail(msg, r);
                            }

                            @Override
                            public void onSuccessMulti(List<String> originalPaths, List<String> compressedPaths) {
                                super.onSuccessMulti(originalPaths, compressedPaths);
                            }

                            @Override
                            public void onSuccessSingle(String originalPath, String compressedPath) {
                                super.onSuccessSingle(originalPath, compressedPath);
                            }
                        });
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        PhotoUtil.onActivityResult(this,requestCode,resultCode,data);
    }
}
