package com.github.catvod.spider;

import com.github.catvod.crawler.SpiderDebug;
import com.github.catvod.crawler.SpiderReq;
import com.github.catvod.crawler.SpiderReqResult;
import com.github.catvod.crawler.SpiderUrl;

import java.util.HashMap;

public class XPathNfMov extends XPath {

    private String cookies = "";

    @Override
    protected HashMap<String, String> getHeaders(String url) {
        HashMap<String, String> hashMap = super.getHeaders(url);
        if (cookies.length() > 0)
            hashMap.put("Cookie", cookies);
        return hashMap;
    }

    @Override
    protected SpiderReqResult fetch(String webUrl) {
        SpiderDebug.log(webUrl);
        SpiderUrl su = new SpiderUrl(webUrl, getHeaders(webUrl));
        SpiderReqResult srr = SpiderReq.get(su);
        if (srr.content.contains("http-equiv=\"refresh\"")) {
            if (srr.headers.containsKey("set-cookie")) {
                cookies = srr.headers.get("set-cookie").get(0);
            }
            su = new SpiderUrl(webUrl, getHeaders(webUrl));
            srr = SpiderReq.get(su);
        }
        return srr;
    }
}
