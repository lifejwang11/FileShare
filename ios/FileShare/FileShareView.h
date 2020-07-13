//
//  FileShareView.h
//  HBuilder-uniPlugin
//
//  Created by 王国港 on 4/14/20.
//  Copyright © 2020 DCloud. All rights reserved.
//

#import <UIKit/UIKit.h>

typedef void(^DCAlertCallback)(NSDictionary *result);

NS_ASSUME_NONNULL_BEGIN

@interface FileShareView : UIControl

+ (FileShareView *)alertWithOptions:(NSDictionary *)options callback:(DCAlertCallback)callback;

- (void)show;
- (void)dismiss;



@end

NS_ASSUME_NONNULL_END
