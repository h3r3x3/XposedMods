package io.github.h3r3x3.xposedmods;

import android.webkit.WebView;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * Created by h3r3x3 on 2016/4/1.
 * QQ未做检验
 */
public class QQWebViewHook implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        if (!loadPackageParam.packageName.equals("com.tencent.mobileqq")) {
            return;
        }
        XposedBridge.hookAllConstructors(WebView.class, new XC_MethodHook() {
            @Override
            protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                XposedHelpers.callStaticMethod(WebView.class, "setWebContentsDebuggingEnabled", true);
            }
        });
        Class clazz = XposedHelpers.findClass("com.tencent.smtt.sdk.WebView", loadPackageParam.classLoader);
        if (clazz == null) {
            return;
        }
        XposedBridge.hookAllConstructors(clazz,
                new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        XposedHelpers.callStaticMethod(WebView.class, "setWebContentsDebuggingEnabled", true);
                    }
                });
    }
}
