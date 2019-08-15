package com.highwayPlatform.util;

import java.util.Collection;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

public class JpushClientUtil {
	//盲样试验App
	private final static String APP_KEY = "29d168018fa522ee097585dc";
	private final static String MASTER_SECRET = "c7cef335c20b23584ef23846";
	
	private static JPushClient jPushClient = new JPushClient(MASTER_SECRET,APP_KEY,null,ClientConfig.getInstance());

	public static int pushMsg(Collection<String> alias, String title, String msgContent) {
		//JPushClient jPushClient = new JPushClient(MASTER_SECRET, APP_KEY, null, ClientConfig.getInstance());
		int result = 0;
		try {
			// PushPayload pushPayload=
//			 JpushClientUtil.buildPushObject_all_registrationId_alertWithTitle(registrationId,notification_title,msg_title,msg_content,extrasparam);
			// 别名推送
			PushPayload pushPayload = JpushClientUtil.buildPushObject_android_and_iosByAlias(alias, title, msgContent);
			System.out.println(pushPayload);
			PushResult pushResult = jPushClient.sendPush(pushPayload);
			if (pushResult.getResponseCode() == 200) {
				result = 1;
			}
			 Thread.sleep(5000);
			 // 请求结束后，调用 NettyHttpClient 中的 close 方法，否则进程不会退出。
			 jPushClient.close();
		} catch (APIConnectionException e) {
			e.printStackTrace();

		} catch (APIRequestException e) {
			e.printStackTrace();
		}catch(InterruptedException e) {
		    e.printStackTrace();
		}

		return result;
	}

	/**
	 * 别名推送
	 */
	public static PushPayload buildPushObject_android_and_iosByAlias(Collection<String> alias, String title, String content) {
		return PushPayload.newBuilder().setPlatform(Platform.android_ios()).setAudience(Audience.alias(alias))
				.setNotification(Notification.newBuilder().setAlert(content)
						.addPlatformNotification(AndroidNotification.newBuilder().setTitle(title).build())
						.addPlatformNotification(
								IosNotification.newBuilder().incrBadge(1).addExtra(title, content).build())
						.build())
				.setOptions(Options.newBuilder()
                        .setApnsProduction(false)//true-推送生产环境 false-推送开发环境（测试使用参数）
                        .setTimeToLive(90)//消息在JPush服务器的失效时间（测试使用参数）
                        .build())
				.build();
	}

}
