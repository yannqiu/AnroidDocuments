package peiyu.person.sophix;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.taobao.sophix.SophixManager;

public class MainActivity extends CheckPermissionsActivity
        implements View.OnClickListener {
    Button mbtnBug;
    Button mbtnLoadPatch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mbtnBug = (Button) findViewById(R.id.id_main_btn_bug);
        mbtnLoadPatch= (Button) findViewById(R.id.id_main_load_patch);
        mbtnBug.setOnClickListener(this);
        mbtnLoadPatch.setOnClickListener(this);
    }
    /**
     * 测试mbtnBug点击事件
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.id_main_btn_bug:
                //测试bug，此时使用str会发生空指针异常
               // String str = null;
                //修复bug，使用这个，并将上面的注释。
                String str="sophix";
                if (!str.isEmpty()) {
                    Toast.makeText(MainActivity.this, str, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.id_main_load_patch:
                SophixManager.getInstance().queryAndLoadNewPatch();
                break;
            default:
                break;
        }

    }
}
