#安卓ISO源生分享
####插件介绍
可以分享任意文件，分享任何文件！！！不限制文件类型，安卓调用的系统分享，ios因为微信直接跳转，可以引入源生的分享模块，然后配置APPID即可（也可以调用系统分享）
####源生插件的使用流程
1.购买插件，选择该插件绑定的项目。
2.在HBuilderX里找到项目，在manifest的app原生插件配置中勾选模块，如需要填写参数则参考插件作者的文档添加。
3.根据插件作者的提供的文档开发代码，在代码中引用插件，调用插件功能。
4.打包自定义基座，选择插件，得到自定义基座，然后运行时选择自定义基座，进行log输出测试。
5.开发完毕后正式云打包
付费原生插件目前不支持离线打包。
Android 离线打包原生插件另见文档 https://nativesupport.dcloud.net.cn/NativePlugin/offline_package/android
iOS 离线打包原生插件另见文档 https://nativesupport.dcloud.net.cn/NativePlugin/offline_package/ios

注意事项：使用HBuilderX2.7.14以下版本，如果同一插件且同一appid下购买并绑定了多个包名，提交云打包界面提示包名绑定不一致时，需要在HBuilderX项目中manifest.json->“App原生插件配置”->”云端插件“列表中删除该插件重新选择
####使用教程
#####1.引入插件
```
const FileShare= uni.requireNativePlugin('FileShare');
```
####2.调用
```
下面使用这个下载方式，亲测，如果用uniapp自身的ios中文乱码，下面可以重命名不会乱码，分享就是FileShare调用
	var dtask = plus.downloader.createDownload(url,  {filename:"_doc/pdf/"+name}, function(d, status){
						// 下载完成
				if(status == 200){ 							
					FileShare.render({
                        type:"QQ",//QQ为QQ，微信为WX，系统默认是SYSTEM，不填写默认SYSTEM
                        filePath:plus.io.convertLocalFileSystemURL(d.filename),
				}, result => {
											
				}
				);
			} else {
				console.log("Download failed: " + status); 
			}  
	});
dtask.start(); 
```
####3.在manifest.json—>App原生插件配置 勾选life-FileShare
隐私、权限声明
1. 本插件需要申请的系统权限列表：
内存读写权限，相册权限

2. 本插件采集的数据、发送的服务器地址、以及数据用途说明：
无

3. 本插件是否包含广告，如包含需详细说明广告表达方式、展示频率：
无
###源码项目地址
github：
（下载的同时别忘记star）
