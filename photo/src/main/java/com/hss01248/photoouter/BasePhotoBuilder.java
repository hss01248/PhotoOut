package com.hss01248.photoouter;

import android.app.Activity;
import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import me.shaohui.advancedluban.Renameable;

/**
 * Created by Administrator on 2017/2/15 0015.
 * 输入:拍照或者图库
 * 输出:裁剪或未裁剪的,压缩或未压缩的图片文件的路径.
 */

public class BasePhotoBuilder {
    public Context context;
    public String rootDir = getRootDir();
    public String compressedDir = getCompressedDir();

    public BasePhotoBuilder setCompressRename(Renameable renameable) {
        this.renameable = renameable;
        return this;
    }

    public Renameable renameable;

    protected String getCompressedDir() {
        File file = new File(rootDir,"compressed");
        file.mkdirs();

        return file.getAbsolutePath();
    }


    private String getRootDir() {
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),"photoout");
        file.mkdirs();
        return file.getAbsolutePath();
    }


    public boolean fromCamera= false;//是不是拍照选择
    public ArrayList<String> selectedPaths;//已经选择的图片

    public boolean needCompress = true;//是否要压缩:默认需要

    public boolean needCropWhenOne = false;//只选择一张图时,是否需要裁剪,默认是需要的

    //裁剪的几个参数
    public int cropX = 1;
    public int cropY = 1;
    public  boolean isOval = false;//是否为椭圆
    public Object tag;//一个页面多个图片裁剪时,用于避免混淆
    public boolean showGridline = true;

    public String filePathToCrop;
    public String filePathToCamera;

    public boolean selectGif = false;

    public PhotoCallback callback;

    public int maxSelectCount = 9;

    //图片压缩参数
    public int maxWidth = 0;
    public int maxHeight = 0;
    public int quality = 80;
    public int requestCode;
    protected List<String> compressedPaths;

    public boolean remainExif = false;//不保留exif


    public BasePhotoBuilder setCropMuskOval(){
        this.isOval = true;
        return this;
    }

    public BasePhotoBuilder setFromCamera(boolean fromCamera){
        this.fromCamera = fromCamera;
        return this;
    }
    public BasePhotoBuilder setSelectedPaths(ArrayList<String> selectedPaths){
        this.selectedPaths = selectedPaths;
        return this;
    }

    public BasePhotoBuilder setNeedCompress(boolean needCompress){
        this.needCompress = needCompress;
        return this;
    }
    public BasePhotoBuilder setNeedCropWhenOne(boolean needCropWhenOne){
        this.needCropWhenOne = needCropWhenOne;
        return this;
    }
    public BasePhotoBuilder setCropRatio(int cropRatioX,int cropRatioY){
        this.cropX = cropRatioX;
        this.cropY = cropRatioY;
        return this;
    }

    public BasePhotoBuilder setSelectGif(){
        this.selectGif = true;
        return this;
    }
    public BasePhotoBuilder setMaxSelectCount(int maxSelectCount){
        this.maxSelectCount = maxSelectCount >0 ? maxSelectCount : 9;
        return this;
    }
    public BasePhotoBuilder setCompressMax(int maxWidth,int maxHeight){
        this.maxWidth = maxWidth;
        this.maxWidth = maxHeight;
        return this;
    }
    public BasePhotoBuilder setCompressQuality(int quality){
        this.quality = quality;
        return this;
    }
    public BasePhotoBuilder setCompressDir(String compressedDirPath){
        this.compressedDir = compressedDirPath;
        return this;
    }




    public void start(final Activity activity,int requestCode,PhotoCallback callback){
        this.callback = callback;
        this.requestCode = requestCode;

        File file = new File(compressedDir);
        if(!file.exists()){
            file.mkdirs();
        }
        File file1 = new File(file,".nomedia");
        if(!file1.exists()){
            try {
                file1.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



        if(fromCamera){
            PermissionUtils.askCamera(new PermissionUtils.PermissionListener() {
                @Override
                public void onGranted() {
                    MyTool.startCamera(activity,BasePhotoBuilder.this);
                }

                @Override
                public void onDenied(List<String> permissions) {

                }
            });

        }else {
            MyTool.startPicker()
                    .setPhotoCount(maxSelectCount)
                    .setShowGif(selectGif)
                    .setSelected(selectedPaths)
                    .start(activity,requestCode);
        }

    }


}
