package com.demo.xposed;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import de.robv.android.xposed.XC_MethodHook;

import static de.robv.android.xposed.XposedHelpers.findAndHookMethod;


public class LoginBypass implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        XposedBridge.log("Running Xposed. Yay! .....");

        if(lpparam.packageName.equals("upc.master.asoler.vulnwatch")) {
            XposedBridge.log("Loaded app: " + lpparam.packageName);

            findAndHookMethod("upc.master.asoler.vulnwatch.MainActivity", lpparam.classLoader,
                    "checkPasswd", String.class, String.class,
                    new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            XposedBridge.log("Before Hooking...param.args[0] " + param.args[0]);
                            XposedBridge.log("Before Hooking...param.args[1] " + param.args[1]);
                            param.args[1] = param.args[0];
                        }

                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            XposedBridge.log("After Hooking...param.args[0] " + param.args[0]);
                            XposedBridge.log("After Hooking...param.args[1] " + param.args[1]);
                            param.args[1] = param.args[0];
                        }
                    });
        }
    }
}
