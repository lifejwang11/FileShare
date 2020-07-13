//
//  FileShareView.m
//  HBuilder-uniPlugin
//
//  Created by 王国港 on 4/14/20.
//  Copyright © 2020 DCloud. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "FileShareView.h"
#import "WXConvert.h"
#import "FileShare.h"


@implementation FileShareView

(strong, nonatomic)NSString *filePath;

(strong, nonatomic)NSString *type;

FileShareView *fileShareView;

/**
 解析数据
 
 @param option 相关设置信息
 */
- (void)parseOption:(NSDictionary *)option {
    
    if (option[@"position"]) {
        filePath = [WXConvert NSString:option[@"position"]];
    }
    
    if (option[@"title"]) {
        type = [WXConvert NSString:option[@"title"]];
    }

}


#pragma mark - Public method

+ (FileShareView *)alertWithOptions:(NSDictionary *)options callback:(DCAlertCallback)callback
{
    FileShareView *alert = [[FileShareView alloc] init];
    [alert parseOption:options];
    fileShareView = alert;
    return alert;
}

- (void)show
{
    TSShareHelper *tSShareHelper = [TSShareHelper shareHelper];
    [tSShareHelper shareWithType:TSShareHelperShareTypeOthers
                   andController:fileShareView
                     andFilePath:filePath
                   andCompletion:^(TSShareHelper *shareHelper, BOOL success) {
                       if (success) {
                           NSLog(@"成功的回调");
                       }else{
                           NSLog(@"失败的回调");
                       }
                   }];
}



- (void)dismiss {

}
@end
