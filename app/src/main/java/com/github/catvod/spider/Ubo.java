package com.github.catvod.spider;

import android.content.Context;
import android.util.Base64;

import com.github.catvod.crawler.Spider;
import com.github.catvod.crawler.SpiderDebug;
import com.github.catvod.crawler.SpiderReq;
import com.github.catvod.crawler.SpiderReqResult;
import com.github.catvod.crawler.SpiderUrl;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * U播影视 弃用
 * <p>
 * Author: 小黄瓜
 */

public class Ubo extends Spider {


    private static final String siteUrl = "http://cs.youbohd.com/cs001rbmACJj6CZMG5crE.php/Kdtv/vod";
    private static final String siteHost = "cs.youbohd.com";

    protected JSONObject playerConfig = new JSONObject();

    protected HashMap<String, String> classifyMap = new HashMap<>();

    @Override
    public void init(Context context) {
        super.init(context);
        classifyMap.put("dianying", "电影");
        classifyMap.put("lianxuju", "连续剧");
        classifyMap.put("zongyi", "综艺");
        classifyMap.put("dongman", "动漫");
    }

    private HashMap<String, String> getHeaders(String url) {
        HashMap<String, String> headers = new HashMap<>();
        headers.put("Host", siteHost);
        headers.put("Connection", "Keep-Alive");
        headers.put("User-Agent", "Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0; BOIE9;ZHCN)");
        return headers;
    }

    @Override
    public String homeContent(boolean filter) {
        try {
            SpiderUrl su = new SpiderUrl(siteUrl + "?ac=flitter", getHeaders(siteUrl));
            SpiderReqResult srr = SpiderReq.get(su);
            JSONObject jsonObject = new JSONObject(srr.content);
            Iterator<String> keys = jsonObject.keys();
            JSONArray classes = new JSONArray();
            JSONObject filterConfig = new JSONObject();
            while (keys.hasNext()) {
                String key = keys.next();
                if (key.startsWith("classify")) {
                    continue;
                }
                String typeName = key;
                if (classifyMap.containsKey(key)) {
                    typeName = classifyMap.get(key);
                } else {
                    continue;
                }
                String typeId = key;
                JSONObject newCls = new JSONObject();
                newCls.put("type_id", typeId);
                newCls.put("type_name", typeName);
                classes.put(newCls);
                try {
                    JSONArray extendsAll = new JSONArray();
                    JSONArray typeExtendArray = jsonObject.getJSONArray(key);
                    for (int j = 0; j < typeExtendArray.length(); j++) {
                        JSONObject jObj = typeExtendArray.getJSONObject(j);
                        String typeExtendName = jObj.getString("name");
                        String typeExtendKey = null;
                        if (jObj.has("field")) {
                            typeExtendKey = jObj.getString("field");
                        }
                        if (typeExtendKey == null) {
                            switch (typeExtendName) {
                                case "类型":
                                    typeExtendKey = "type";
                                    break;
                                case "地区":
                                    typeExtendKey = "area";
                                    break;
                                case "语言":
                                    typeExtendKey = "lang";
                                    break;
                                case "年份":
                                    typeExtendKey = "year";
                                    break;
                            }
                        }
                        if (typeExtendKey == null) {
                            SpiderDebug.log(typeExtendName);
                            continue;
                        }
                        String[] typeExtendValues = jObj.getString("type").split(",");
                        if (typeExtendValues.length == 0)
                            continue;
                        JSONObject newTypeExtend = new JSONObject();
                        newTypeExtend.put("key", typeExtendKey);
                        newTypeExtend.put("name", typeExtendName);
                        JSONArray newTypeExtendKV = new JSONArray();
                        {
                            JSONObject kvAll = new JSONObject();
                            kvAll.put("n", "全部");
                            kvAll.put("v", "");
                            newTypeExtendKV.put(kvAll);
                        }
                        for (int k = 0; k < typeExtendValues.length; k++) {
                            String kk = typeExtendValues[k];
                            if (kk.trim().length() == 0)
                                continue;
                            JSONObject kv = new JSONObject();
                            kv.put("n", kk);
                            kv.put("v", kk);
                            newTypeExtendKV.put(kv);
                        }
                        if (newTypeExtendKV.length() == 1) {
                            continue;
                        }
                        newTypeExtend.put("value", newTypeExtendKV);
                        extendsAll.put(newTypeExtend);
                    }
                    if (extendsAll.length() > 0) {
                        filterConfig.put(typeId, extendsAll);
                    }
                } catch (Exception e) {
                    SpiderDebug.log(e);
                }

            }
            JSONObject result = new JSONObject();
            result.put("class", classes);
            if (filter) {
                result.put("filters", filterConfig);
            }
            return result.toString();
        } catch (Exception e) {
            SpiderDebug.log(e);
        }
        return "";
    }

    @Override
    public String homeVideoContent() {
        try {
            String url = siteUrl + "?ac=list&page=1";
            SpiderUrl su = new SpiderUrl(url, getHeaders(url));
            SpiderReqResult srr = SpiderReq.get(su);
            JSONObject jsonObject = new JSONObject(decryptResponse(srr.content));
            JSONArray jsonArray = jsonObject.getJSONArray("list");
            JSONArray videos = new JSONArray();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject vObj = jsonArray.getJSONObject(i);
                JSONObject v = new JSONObject();
                v.put("vod_id", vObj.getString("vod_id"));
                v.put("vod_name", vObj.getString("vod_name"));
                v.put("vod_pic", vObj.getString("vod_pic"));
                v.put("vod_remarks", vObj.getString("vod_remarks"));
                videos.put(v);
            }
            JSONObject result = new JSONObject();
            result.put("list", videos);
            return result.toString();
        } catch (Exception e) {
            SpiderDebug.log(e);
        }
        return "";
    }

    @Override
    public String categoryContent(String tid, String pg, boolean filter, HashMap<String, String> extend) {
        try {
            String url = siteUrl + "?ac=list";
            if (!extend.containsKey("class")) {
                url += "&class=" + URLEncoder.encode(tid);
            }
            Set<String> keys = extend.keySet();
            for (String key : keys) {
                String val = extend.get(key).trim();
                if (val.length() == 0)
                    continue;
                url += "&" + key + "=" + URLEncoder.encode(val);
            }
            url += "&page=" + pg;
            SpiderUrl su = new SpiderUrl(url, getHeaders(url));
            SpiderReqResult srr = SpiderReq.get(su);
            JSONObject dataObject = new JSONObject(decryptResponse(srr.content));
            JSONArray jsonArray = dataObject.getJSONArray("list");
            JSONArray videos = new JSONArray();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject vObj = jsonArray.getJSONObject(i);
                JSONObject v = new JSONObject();
                v.put("vod_id", vObj.getString("vod_id"));
                v.put("vod_name", vObj.getString("vod_name"));
                v.put("vod_pic", vObj.getString("vod_pic"));
                v.put("vod_remarks", vObj.getString("vod_remarks"));
                videos.put(v);
            }
            JSONObject result = new JSONObject();
            int limit = 20;
            int page = dataObject.getInt("page");
            int total = dataObject.getInt("total");
            int pageCount = dataObject.getInt("pagecount");
            result.put("page", page);
            result.put("pagecount", pageCount);
            result.put("limit", limit);
            result.put("total", total);
            result.put("list", videos);
            return result.toString();
        } catch (Exception e) {
            SpiderDebug.log(e);
        }
        return "";
    }

    @Override
    public String detailContent(List<String> ids) {
        try {
            String url = siteUrl + "?ac=detail&ids=" + ids.get(0);
            SpiderUrl su = new SpiderUrl(url, getHeaders(url));
            SpiderReqResult srr = SpiderReq.get(su);
            JSONObject dataObject = new JSONObject(decryptResponse(srr.content));
            JSONArray jsonArray = dataObject.getJSONArray("list");
            JSONObject result = new JSONObject();
            JSONArray list = new JSONArray();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject vObj = jsonArray.getJSONObject(i);
                JSONObject vodAtom = new JSONObject();
                vodAtom.put("vod_id", vObj.getString("vod_id"));
                vodAtom.put("vod_name", vObj.getString("vod_name"));
                vodAtom.put("vod_pic", vObj.getString("vod_pic"));
                vodAtom.put("type_name", vObj.getString("vod_class"));
                vodAtom.put("vod_year", vObj.getString("vod_year"));
                vodAtom.put("vod_area", vObj.getString("vod_area"));
                vodAtom.put("vod_remarks", vObj.getString("vod_remarks"));
                vodAtom.put("vod_actor", vObj.getString("vod_actor"));
                vodAtom.put("vod_director", vObj.getString("vod_director"));
                vodAtom.put("vod_content", vObj.getString("vod_content"));
                vodAtom.put("vod_play_from", vObj.getString("vod_play_from"));
                vodAtom.put("vod_play_url", vObj.getString("vod_play_url"));
                list.put(vodAtom);
            }
            result.put("list", list);
            return result.toString();
        } catch (Exception e) {
            SpiderDebug.log(e);
        }
        return "";
    }

    @Override
    public String playerContent(String flag, String id, List<String> vipFlags) {
        try {
            String url = "http://vip.youbohd.com/8CCmaSXKy2EfMyA11E.php?url=" + id;
            HashMap<String, String> headers = getHeaders(url);
            headers.put("Host", "vip.youbohd.com");
            SpiderUrl su = new SpiderUrl(url, headers);
            SpiderReqResult srr = SpiderReq.get(su);
            JSONObject playerData = new JSONObject(srr.content);
            JSONObject result = new JSONObject();
            result.put("parse", 0);
            result.put("header", "");
            result.put("playUrl", "");
            result.put("url", playerData.getString("url"));
            return result.toString();
        } catch (Exception e) {
            SpiderDebug.log(e);
        }
        return "";
    }

    @Override
    public String searchContent(String key, boolean quick) {
        try {
            if (quick)
                return "";
            String url = siteUrl + "?ac=list&zm=" + URLEncoder.encode(key) + "&page=1";
            SpiderUrl su = new SpiderUrl(url, getHeaders(url));
            SpiderReqResult srr = SpiderReq.get(su);
            JSONObject dataObject = new JSONObject(decryptResponse(srr.content));
            JSONArray jsonArray = dataObject.getJSONArray("list");
            JSONArray videos = new JSONArray();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject vObj = jsonArray.getJSONObject(i);
                JSONObject v = new JSONObject();
                v.put("vod_id", vObj.getString("vod_id"));
                v.put("vod_name", vObj.getString("vod_name"));
                v.put("vod_pic", vObj.getString("vod_pic"));
                v.put("vod_remarks", vObj.getString("vod_remarks"));
                videos.put(v);
            }
            JSONObject result = new JSONObject();
            result.put("list", videos);
            return result.toString();
        } catch (Exception e) {
            SpiderDebug.log(e);
        }
        return "";
    }

    protected String decryptResponse(String src) {
        return m1747RC4(src, m1983("/"));
    }

    public static String m1749MD5(byte[] bArr) {
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(bArr);
            byte[] array = instance.digest();
            char[] cArr2 = new char[(array.length * 2)];
            int i = 0;
            for (byte b : array) {
                int i2 = i + 1;
                cArr2[i] = cArr[(b >>> 4) & 15];
                i = i2 + 1;
                cArr2[i2] = cArr[b & 15];
            }
            return new String(cArr2);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String m1745RC4(String str, String str2) {
        if (!(str == null || str2 == null)) {
            try {
                byte[] RC4Base = RC4Base(str.getBytes("GBK"), str2);
                char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
                char[] cArr2 = new char[(RC4Base.length * 2)];
                int i = 0;
                for (byte b : RC4Base) {
                    int i2 = i + 1;
                    cArr2[i] = cArr[(b >>> 4) & 15];
                    i = i2 + 1;
                    cArr2[i2] = cArr[b & 15];
                }
                return new String(cArr2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "";
    }

    public static String m1983(String str) {
        return new SimpleDateFormat("yyyy" + str + "MM" + str + "dd").format(new Date(System.currentTimeMillis()));
    }

    public static String m1747RC4(String str, String str2) {
        if (!(str == null || str2 == null)) {
            try {
                return new String(RC4Base(HexString2Bytes(str), str2), "GBK");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "";
    }


    public static byte[] m1746RC42(byte[] bArr, String str) {
        if (bArr == null || str == null) {
            return new byte[0];
        }
        try {
            byte[] RC4Base = RC4Base(bArr, str);
            byte[] bArr2 = {48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 65, 66, 67, 68, 69, 70};
            byte[] bArr3 = new byte[(RC4Base.length * 2)];
            int i = 0;
            for (byte b : RC4Base) {
                int i2 = i + 1;
                bArr3[i] = bArr2[(b >>> 4) & 15];
                i = i2 + 1;
                bArr3[i2] = bArr2[b & 15];
            }
            return bArr3;
        } catch (Exception e) {
            e.printStackTrace();
            return new byte[0];
        }
    }

    public static byte[] m1748RC42(byte[] bArr, String str) {
        if (bArr == null || str == null) {
            return new byte[0];
        }
        try {
            return RC4Base(bArr, str);
        } catch (Exception e) {
            e.printStackTrace();
            return new byte[0];
        }
    }

    private static byte[] HexString2Bytes(String str) {
        try {
            int length = str.length();
            byte[] bArr = new byte[(length / 2)];
            byte[] bytes = str.getBytes("GBK");
            for (int i = 0; i < length / 2; i++) {
                int i2 = i * 2;
                bArr[i] = uniteBytes(bytes[i2], bytes[i2 + 1]);
            }
            return bArr;
        } catch (Exception e) {
            e.printStackTrace();
            return new byte[0];
        }
    }

    private static byte uniteBytes(byte b, byte b2) {
        return (byte) (((char) (((char) Byte.decode("0x" + new String(new byte[]{b})).byteValue()) << 4)) ^ ((char) Byte.decode("0x" + new String(new byte[]{b2})).byteValue()));
    }

    private static byte[] RC4Base(byte[] bArr, String str) {
        byte[] initKey = initKey(str);
        byte[] bArr2 = new byte[bArr.length];
        int i = 0;
        int b = 0;
        for (int i2 = 0; i2 < bArr.length; i2++) {
            i = (i + 1) & 255;
            b = ((initKey[i] & 255) + b) & 255;
            byte b2 = initKey[i];
            initKey[i] = initKey[b];
            initKey[b] = b2;
            bArr2[i2] = (byte) (initKey[((initKey[i] & 255) + (initKey[b] & 255)) & 255] ^ bArr[i2]);
        }
        return bArr2;
    }

    private static byte[] initKey(String str) {
        try {
            byte[] bytes = str.getBytes("GBK");
            byte[] bArr = new byte[256];
            for (int i = 0; i < 256; i++) {
                bArr[i] = (byte) i;
            }
            if (bytes != null) {
                if (bytes.length != 0) {
                    int i2 = 0;
                    int i3 = 0;
                    for (int i4 = 0; i4 < 256; i4++) {
                        i3 = ((bytes[i2] & 255) + (bArr[i4] & 255) + i3) & 255;
                        byte b = bArr[i4];
                        bArr[i4] = bArr[i3];
                        bArr[i3] = b;
                        i2 = (i2 + 1) % bytes.length;
                    }
                    return bArr;
                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String m1743DES(String str, String str2) throws Exception {
        try {
            IvParameterSpec ivParameterSpec = new IvParameterSpec(new byte[]{1, 2, 3, 4, 5, 6, 7, 8});
            SecretKeySpec secretKeySpec = new SecretKeySpec(str2.getBytes("GBK"), "DES");
            Cipher instance = Cipher.getInstance("DES/CBC/PKCS5Padding");
            instance.init(1, secretKeySpec, ivParameterSpec);
            return m1741BASE64(instance.doFinal(str.getBytes("GBK")));
        } catch (Exception unused) {
            return "";
        }
    }

    public static String m1744DES(String str, String str2) throws Exception {
        try {
            byte[] r6 = m1742BASE64(str);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(new byte[]{1, 2, 3, 4, 5, 6, 7, 8});
            SecretKeySpec secretKeySpec = new SecretKeySpec(str2.getBytes("GBK"), "DES");
            Cipher instance = Cipher.getInstance("DES/CBC/PKCS5Padding");
            instance.init(2, secretKeySpec, ivParameterSpec);
            return new String(instance.doFinal(r6), "GBK");
        } catch (Exception unused) {
            return "";
        }
    }

    public static String m1741BASE64(byte[] bArr) {
        return Base64.encodeToString(bArr, Base64.DEFAULT);
    }

    public static byte[] m1742BASE64(String str) {
        return Base64.decode(str, Base64.DEFAULT);
    }
}
