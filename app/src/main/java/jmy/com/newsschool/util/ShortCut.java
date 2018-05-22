package jmy.com.newsschool.util;

import android.app.Activity;
import android.content.Intent;
import android.os.Parcelable;

/**
 * Created by Administrator on 2016/1/21.
 */
public class ShortCut {
    public static void createShortCut(Activity act, int iconResId, int appnameResId) {
        Intent intent = new Intent("com.android.launcher.action.INSTALL_SHORTCUT");
// 不允许重复创建
        intent.putExtra("duplicate", false);
// 需要现实的名称
        intent.putExtra(Intent.EXTRA_SHORTCUT_NAME, act.getString(appnameResId));
// 快捷图片
        Parcelable icon = Intent.ShortcutIconResource.fromContext(act.getApplicationContext(), iconResId);
        intent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, icon);
// 点击快捷图片，运行程序
        intent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, new Intent(act.getApplicationContext(), act.getClass()));
// 发送广播
        act.sendBroadcast(intent);

    }
}