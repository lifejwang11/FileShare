//
//  FileShare.m
//  HBuilder-uniPlugin
//
//  Created by 王国港 on 4/14/20.
//  Copyright © 2020 DCloud. All rights reserved.
//


#import "FileShareProxy.h"

@implementation FileShareProxy
-(void)onCreateUniPlugin{
    NSLog(@"TestPlugin 有需要初始化的逻辑可以放这里！");
}

- (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions{
    NSLog(@"TestPlugin 有需要didFinishLaunchingWithOptions可以放这里！");
    return YES;
}

@end