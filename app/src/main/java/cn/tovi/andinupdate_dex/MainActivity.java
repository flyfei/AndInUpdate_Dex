package cn.tovi.andinupdate_dex;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.io.File;

import cn.tovi.hotfixlib.HotFix;


/**
 * 注意:应用启动后，如果调用过某方法，然后再执行补丁程序，则不起作用
 * 2015-11-24 16:52:25
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private boolean hasUpdate = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_toast) {
            myToast(new BugClass().toString());
        }
    }

    private void myToast(String msg) {
        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
    }


    private void checkUpdate() {
        if (hasUpdate) {
            //准备补丁,从assert里拷贝到dex里
            File dexPath = new File(getDir("dex", Context.MODE_PRIVATE), "path_dex.jar");
            DexUtils.prepareDex(this.getApplicationContext(), dexPath, "path_dex.jar");

            HotFix.patch(this, dexPath.getAbsolutePath(), "cn.tovi.andinupdate_dex.BugClass");
        }
    }
}
