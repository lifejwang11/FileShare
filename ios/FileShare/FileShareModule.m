//
//  FileShareModule.m
//  HBuilder-uniPlugin
//
//  Created by 王国港 on 4/14/20.
//  Copyright © 2020 DCloud. All rights reserved.
//

#import "FileShareModule.h"
#import "WXUtility.h"
#import "FileShareView.h"

@interface FileShareModule ()
@property (nonatomic, weak) FileShareView *alertView;
@end

@implementation FileShareModule

@synthesize weexInstance;

WX_EXPORT_METHOD(@selector(show:callback:))
WX_EXPORT_METHOD(@selector(dismiss))

- (void)dealloc
{
    [[NSNotificationCenter defaultCenter] removeObserver:self];
}

- (instancetype)init
{
    if (self = [super init]) {
        /* 监听App停止运行事件，如果alert存在，调一下dismiss方法移除 */
        [[NSNotificationCenter defaultCenter] addObserver:self selector:@selector(dismiss) name:@"PDRCoreAppDidStopedKey" object:nil];
    }
    return self;
}

- (void)_show:(NSDictionary *)options callback:(WXModuleKeepAliveCallback)callback
{
    FileShareView *alertView = [FileShareView alertWithOptions:options
                                                          callback:^(NSDictionary *result) {
                                                              if (callback) {
                                                                  callback(result,YES);
                                                              }
                                                          }];
    self.alertView = alertView;
    [alertView show];
}


#pragma mark - Export Method

- (void)show:(NSDictionary *)options callback:(WXModuleKeepAliveCallback)callback
{
    [self _show:options callback:callback];
}

- (void)dismiss
{
    [self.alertView dismiss];
}

@end
