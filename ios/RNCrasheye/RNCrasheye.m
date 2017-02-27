
#import "RNCrasheye.h"
#import "Crasheye.h"

static NSString * const OptionAppKey = @"appKey";
static NSString * const OptionAppVersion = @"appVersion";
static NSString * const OptionUserIdentifier = @"userIdentifier";
static NSString * const OptionChannelID = @"channelID";
static NSString * const OptionFlushOnlyOverWifi = @"flushOnlyOverWiFi";
static NSString * const OptionLogging = @"logging";


@implementation RNCrasheye

- (dispatch_queue_t)methodQueue
{
    return dispatch_get_main_queue();
}
RCT_EXPORT_MODULE(RNCrasheye);

/**
 @brief:初始化
 */
RCT_EXPORT_METHOD(init:(NSDictionary*)options){
    if(options && [options isKindOfClass:[NSDictionary class]] && [options objectForKey:OptionAppKey]) {
        NSString* appKey =[options objectForKey:OptionAppKey];
        // 设置App版本号
        if([options objectForKey:OptionAppVersion]){
            [Crasheye setAppVersion:[options[OptionAppVersion] stringValue]];
        }
        
        // 设置网络上报方式，true：仅在wifi下上报；false：所有网络均上报。 默认值为false
        if([options objectForKey:OptionFlushOnlyOverWifi]) {
            BOOL value = [options[OptionFlushOnlyOverWifi] boolValue];
            if(value) {
                [Crasheye setFlushOnlyOverWiFi:1];
            } else {
                [Crasheye setFlushOnlyOverWiFi:0];
            }
            
        }
        
        // 初始化
        if([options objectForKey:OptionChannelID]){
            // 设置渠道
            [Crasheye initWithAppKey:[options[OptionAppKey] stringValue] withChannel:[options[OptionChannelID] stringValue]];
        } else {
            [Crasheye initWithAppKey:appKey];
        }
    }
}

/*
 @brief:获取SDK版本
 */
RCT_REMAP_METHOD(getSDKVersion, resolver1:(RCTPromiseResolveBlock)resolve rejecter1:(RCTPromiseRejectBlock)reject){
    NSString *sdkVersion = [Crasheye versionForCrasheye];
    resolve(sdkVersion);
}

/*
 @brief:获取设备唯一码
 */
RCT_REMAP_METHOD(getDeviceId, resolver2:(RCTPromiseResolveBlock)resolve rejecter2:(RCTPromiseRejectBlock)reject){
    NSString *deviceId = [Crasheye getDeviceID];
    resolve(deviceId);
}

/*
 @brief:添加面包屑（打点信息）
 @param:
 breadcrumb 内容
 */
RCT_EXPORT_METHOD(leaveBreadcrumb:(NSString*)breadcrumb){
    [Crasheye leaveBreadcrumb:breadcrumb];
}

/*
 @brief:主动上报脚本信息
 @param:
 errorTitle 错误脚本的标题
 exception 异常的内容
 */
RCT_EXPORT_METHOD(sendScriptException:(NSString*)errorTitle exception:(NSString*)exception) {
    [Crasheye sendScriptExceptionRequestWithTitle:errorTitle exception:exception file:nil language:@"Javascript"];
}

/*
 @brief:添加自定义数据
 @param:
 key 键
 value 值
 */
RCT_EXPORT_METHOD(addExtraData:(NSString*)key value:(NSString*)value){
    [Crasheye addExtraDataWithKey:key withValue:value];
}

@end









