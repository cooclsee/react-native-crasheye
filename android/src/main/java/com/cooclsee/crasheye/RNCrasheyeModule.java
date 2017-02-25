
package com.cooclsee.crasheye;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.xsj.crasheye.Crasheye;

/**
 * http://www.crasheye.cn/help/androidsdkuse#overview-android-sdk-oftenused-api
 */
public class RNCrasheyeModule extends ReactContextBaseJavaModule {

    private final ReactApplicationContext reactContext;

    private static final String OPTION_APPKEY = "appKey";
    private static final String OPTION_CHANNELID = "channelID";
    private static final String OPTION_APP_VERSION = "appVersion";
    private static final String OPTION_FLUSH_ONLY_OVER_WIFI = "flushOnlyOverWiFi";
    private static final String OPTION_USER_IDENTIFIER = "userIdentifier";
    private static final String OPTION_LOGGING = "logging";


    public RNCrasheyeModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "RNCrasheye";
    }

    /**
     * 初始化
     *
     * @param options
     */
    @ReactMethod
    public void init(ReadableMap options) {
        if (options != null && options.hasKey(OPTION_APPKEY)) {
            // 设置渠道ID
            if (options.hasKey(OPTION_CHANNELID)) {
                Crasheye.setChannelID(options.getString(OPTION_CHANNELID));
            }
            // 设置App版本
            if (options.hasKey(OPTION_APP_VERSION)) {
                Crasheye.setAppVersion(options.getString(OPTION_APP_VERSION));
            }
            // 设置网络上报方式，true：仅在wifi下上报；false：所有网络均上报。 默认值为false
            if (options.hasKey(OPTION_FLUSH_ONLY_OVER_WIFI)) {
                Crasheye.setFlushOnlyOverWiFi(options.getBoolean(OPTION_FLUSH_ONLY_OVER_WIFI));
            }
            // 设置用户标识
            if (options.hasKey(OPTION_USER_IDENTIFIER)) {
                Crasheye.setUserIdentifier(options.getString(OPTION_USER_IDENTIFIER));
            }
            // 设置收集log日志
            if (options.hasKey(OPTION_LOGGING)) {
                ReadableMap map = options.getMap(OPTION_LOGGING);
                if (map != null) {
                    final String keyLines = "lines";
                    final String keyFilter = "filter";
                    if (map.hasKey(keyLines) && map.hasKey(keyFilter)) {
                        Crasheye.setLogging(map.getInt(keyLines), map.getString(keyFilter));
                    } else if (map.hasKey(keyLines)) {
                        Crasheye.setLogging(map.getInt(keyLines));
                    } else if (map.hasKey(keyFilter)) {
                        Crasheye.setLogging(map.getString(keyFilter));
                    }
                }
                Crasheye.setLogging(options.getString(OPTION_USER_IDENTIFIER));
            }

            // 初始化
            Crasheye.init(reactContext, options.getString(OPTION_APPKEY));

        }
    }

    /**
     * 获取SDK版本
     *
     * @param promise
     */
    @ReactMethod
    public void getSDKVersion(Promise promise) {
        String sdkVersion = Crasheye.getSDKVersion();
        promise.resolve(sdkVersion);
    }

    /**
     * 获取设备UUID
     */
    @ReactMethod
    public void getDeviceId(Promise promise) {
        String uuid = Crasheye.getCrasheyeUUID();
        promise.resolve(uuid);
    }

    /**
     * 添加面包屑（打点信息）
     *
     * @param breadcrumb
     */
    @ReactMethod
    public void leaveBreadcrumb(String breadcrumb) {
        Crasheye.leaveBreadcrumb(breadcrumb);
    }

    /**
     * 主动上报脚本信息
     *
     * @param errorTitle 错误脚本的标题
     * @param exception 堆栈信息
     */
    @ReactMethod
    public void sendScriptException(String errorTitle, String exception) {
        Crasheye.sendScriptException(errorTitle, exception, "Javascript");
    }

    /**
     * 添加自定义数据
     *
     * @param key
     * @param value
     */
    @ReactMethod
    public void addExtraData(String key, String value) {
        Crasheye.addExtraData(key, value);
    }
}