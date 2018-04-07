package peiyu.person.sophix;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.taobao.sophix.PatchStatus;
import com.taobao.sophix.SophixManager;
import com.taobao.sophix.listener.PatchLoadStatusListener;


/**
 * Created by peiyu_wang on 2017/7/11.
 */

public class SophixApplication extends Application {


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }
    /**
     * 初始化SophixManager
     * 这个方法建议在attachBaseContext方法最前面或onCreate方法最前面执行
     */
    private void initSophixManager() {
        // initialize最好放在attachBaseContext最前面
        String appVersion = getAppVersion();
        SophixManager.getInstance().setContext(this)
                .setAppVersion(appVersion)
                .setAesKey("ccandroid2017712")
                .setEnableDebug(false)
                .setEnableFixWhenJit()
                .setPatchLoadStatusStub(new PatchLoadStatusListener() {
                    @Override
                    public void onLoad(final int mode, final int code, final String info, final int handlePatchVersion) {
                        // 补丁加载回调通知
                        if (code == PatchStatus.CODE_LOAD_SUCCESS) {
                            // 表明补丁加载成功
                            Toast.makeText(SophixApplication.this, "补丁加载成功", Toast.LENGTH_SHORT).show();
                        } else if (code == PatchStatus.CODE_LOAD_RELAUNCH) {
                            // 表明新补丁生效需要重启. 开发者可提示用户或者强制重启;
                            // 建议: 用户可以监听进入后台事件, 然后应用自杀
                            Toast.makeText(SophixApplication.this, "补丁需要应用重启才能生效", Toast.LENGTH_SHORT).show();
                            //让进程自杀
                            SophixManager.getInstance().killProcessSafely();
                        } else if (code == PatchStatus.CODE_LOAD_FAIL) {
                            // 内部引擎异常, 推荐此时清空本地补丁, 防止失败补丁重复加载
                            SophixManager.getInstance().cleanPatches();
                        } else {
                            // 其它错误信息, 查看PatchStatus类说明
                        }
                    }
                }).initialize();
    }
    /**
     * 获取当前app的versionName
     *
     * @return versionName
     */
    private String getAppVersion() {
        String appVersion;
        try {
            appVersion = this.getPackageManager().getPackageInfo(this.getPackageName(), 0).versionName;
        } catch (Exception e) {
            appVersion = "1.0";
        }
        return appVersion;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        initSophixManager();
    }

}
