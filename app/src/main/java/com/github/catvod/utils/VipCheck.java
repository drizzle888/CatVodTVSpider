package com.github.catvod.utils;

import android.net.Uri;

public class VipCheck {
    public static boolean isVip(String url) {
        // 适配2.0.6的调用应用内解析列表的支持, 需要配合直连分析一起使用，参考cjt影视和极品直连
        try {
            boolean isVip = false;
            String host = Uri.parse(url).getHost();
            String[] vipWebsites = new String[]{"iqiyi.com", "v.qq.com", "youku.com", "le.com", "tudou.com", "mgtv.com", "sohu.com", "acfun.cn", "bilibili.com", "baofeng.com", "pptv.com"};
            for (int b = 0; b < vipWebsites.length; b++) {
                if (host.contains(vipWebsites[b])) {
                    if ("iqiyi.com".equals(vipWebsites[b])) {
                        //爱奇艺需要特殊处理
                        if (url.contains("iqiyi.com/a_") || url.contains("iqiyi.com/w_") || url.contains("iqiyi.com/v_")) {
                            isVip = true;
                            break;
                        }
                    } else {
                        isVip = true;
                        break;
                    }
                }
            }
            return isVip;
        } catch (Exception e) {
        }
        return false;
    }
}
