package com.hss01248.photoouter;

import android.util.Log;

import java.util.List;

/**
 * Created by Administrator on 2017/2/16 0016.
 */

public abstract class PhotoCallback {
    public  void onFail(String msg,Throwable r){
        if(PhotoUtil.isDebug)
        Log.e("onFail",msg);
        r.printStackTrace();

    }
    public  void onSuccessSingle(String originalPath,String compressedPath){
        if(PhotoUtil.isDebug)
        Log.e("onSuccessSingle",originalPath+"\n----compressedPath----\n"+compressedPath);

    }
    public  void onSuccessMulti(List<String> originalPaths,List<String> compressedPaths){
        if(PhotoUtil.isDebug)
        Log.e("onSuccessMulti",MyTool.getListStr(originalPaths)+"\n----compressedPaths----\n"+MyTool.getListStr(compressedPaths));

    }
    public  void onCancel(){
        if(PhotoUtil.isDebug)
        Log.e("onCancel","onCancel");
    }
}
