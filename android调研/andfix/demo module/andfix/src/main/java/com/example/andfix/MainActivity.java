package com.example.andfix;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import java.io.File;
import java.io.IOException;

public class MainActivity extends CheckPermissionsActivity
        implements View.OnClickListener {
    Button mbtnBug;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mbtnBug = (Button) findViewById(R.id.id_main_btn_bug);
        mbtnBug.setOnClickListener(this);
        fixBug();
    }
    /**
     * 判断andfix.apatch补丁包是否存在，如果存在便进行热修复
     */
    private void fixBug() {
        String fixBugApkName = "andfix.apatch";
        String patchPath = AndFixApplication.PATCH_PATH + fixBugApkName;
        File patchFile = new File(patchPath);
        if (patchFile != null) {
            try {
                AndFixApplication.patchManager.addPatch(patchPath);
            } catch (IOException e) {
                //文件找不到或者类型出错
            }
        }


    }
    /**
     * 测试mbtnBug点击事件
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        //测试bug，此时使用str会发生空指针异常
        String str = null;
        //修复bug，使用这个，并将上面的注释。
        //String str="andfix";
        if (!str.isEmpty()) {
            Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
        }
    }
}
