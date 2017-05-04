package com.hss01248.photoouter;

import java.util.List;

/**
 * Created by Administrator on 2017/2/16 0016.
 */

public interface  PhotoCallback {
      void onFail(String msg,Throwable r,int requestCode);
      void onSuccessSingle(String originalPath,String compressedPath,int requestCode);
      void onSuccessMulti(List<String> originalPaths,List<String> compressedPaths,int requestCode);
      void onCancel(int requestCode);
}
