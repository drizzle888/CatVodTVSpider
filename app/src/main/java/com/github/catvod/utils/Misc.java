package com.github.catvod.utils;

import android.net.Uri;

import com.github.catvod.crawler.SpiderDebug;

import java.util.regex.Pattern;

public class Misc {
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

    private static Pattern snifferMatch = Pattern.compile("http((?!http).){26,}?\\.(m3u8|mp4)\\?.*|http((?!http).){26,}\\.(m3u8|mp4)|http((?!http).){26,}?/m3u8\\?pt=m3u8.*|http((?!http).)*?default\\.ixigua\\.com/.*|http((?!http).)*?cdn-tos[^\\?]*|http((?!http).)*?/obj/tos[^\\?]*|http.*?/player/m3u8play\\.php\\?url=.*|http.*?/player/.*?[pP]lay\\.php\\?url=.*|http.*?/playlist/m3u8/\\?vid=.*|http.*?\\.php\\?type=m3u8&.*|http.*?/download.aspx\\?.*|http.*?/api/up_api.php\\?.*|https.*?\\.66yk\\.cn.*");

    public static boolean isVideoFormat(String url) {
        return snifferMatch.matcher(url).find();
    }

    public static String fixUrl(String base, String src) {
        try {
            if (src.startsWith("//")) {
                Uri parse = Uri.parse(base);
                src = parse.getScheme() + ":" + src;
            } else if (!src.contains("://")) {
                Uri parse = Uri.parse(base);
                src = parse.getScheme() + "://" + parse.getHost() + src;
            }
        } catch (Exception e) {
            SpiderDebug.log(e);
        }
        return src;
    }
}
