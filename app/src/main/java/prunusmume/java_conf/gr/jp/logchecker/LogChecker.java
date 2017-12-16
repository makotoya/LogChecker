package prunusmume.java_conf.gr.jp.logchecker;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by Makoto Yaguchi on 2017/12/14.
 */

public class LogChecker extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
