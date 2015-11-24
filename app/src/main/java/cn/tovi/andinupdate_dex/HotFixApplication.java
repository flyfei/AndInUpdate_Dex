package cn.tovi.andinupdate_dex;

import android.app.Application;
import android.content.Context;

import java.io.File;

import cn.tovi.hotfixlib.HotFix;

/**
 * @author <a href='mailto:zhaotengfei9@gmail.com'>Tengfei Zhao</a>
 */
public class HotFixApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        File dexPath = new File(getDir("dex", Context.MODE_PRIVATE), "hackdex_dex.jar");
        DexUtils.prepareDex(this.getApplicationContext(), dexPath, "hackdex_dex.jar");
        HotFix.patch(this, dexPath.getAbsolutePath(), "cn.tovi.hackdex.AntilazyLoad");
        try {
            this.getClassLoader().loadClass("cn.tovi.hackdex.AntilazyLoad");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
