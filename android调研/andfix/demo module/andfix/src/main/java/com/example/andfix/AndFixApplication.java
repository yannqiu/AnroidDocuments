package com.example.andfix;

import android.app.Application;

import com.alipay.euler.andfix.patch.PatchManager;

/**
 * Created by peiyu_wang on 2017/7/11.
 */

public class AndFixApplication extends Application {


    public static  PatchManager patchManager;


    //默认的补丁路径
    public final static String PATCH_PATH = "/storage/emulated/0/Android/data/com.example.andfix/files/";


    @Override
    public void onCreate() {
        super.onCreate();
        initPatchManager();
    }

    private void initPatchManager() {
        patchManager = new PatchManager(this);
        //初始化版本
        patchManager.init("1.0");
        //You should load patch as early as possible, generally, in the initialization phase of your application(such as Application.onCreate()).
        patchManager.loadPatch();
        //path of the patch file that was downloaded，这个可以使用定时的判断服务器是否有可更新的补丁包进而下载，addPatch进行热修复
        //也可以使用服务器推送，接受推送并下载后，使用parchManager.addPath(String path)，传入下载的补丁包的路径即可实现热修复
        // patchManager.addPatch()
        // 用于生成这个文件夹
        this.getExternalFilesDir(null).getAbsolutePath();
    }
}
