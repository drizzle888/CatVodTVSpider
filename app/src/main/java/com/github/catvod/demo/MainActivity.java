package com.github.catvod.demo;

import android.app.Activity;
import android.os.Bundle;

import com.github.catvod.spider.XPath;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Thread(new Runnable() {
            @Override
            public void run() {
                XPath aidi = new XPath();
                aidi.init(MainActivity.this, "{\n" +
                        "  \"ua\": \"\",\n" +
                        "  \"homeUrl\": \"http://www.9rmb.com\",\n" +
                        "  \"cateNode\": \"//ul[contains(@class,'navbar-nav')]/li/a[contains(@href, '.html') and not(contains(@href, '6'))]\",\n" +
                        "  \"cateName\": \"/text()\",\n" +
                        "  \"cateId\": \"/@href\",\n" +
                        "  \"cateIdR\": \"/type/(\\\\d+).html\",\n" +
                        "  \"cateManual\": {},\n" +
                        "  \"homeVodNode\": \"//div[@class='col-md-12 movie-item-out']//a[not(contains(@href, '6'))]/parent::*/parent::*/parent::*/div[contains(@class, 'movie-item-out') and position()<10]/div[@class='movie-item']/a\",\n" +
                        "  \"homeVodName\": \"/@title\",\n" +
                        "  \"homeVodId\": \"/@href\",\n" +
                        "  \"homeVodIdR\": \"/show/(\\\\w+).html\",\n" +
                        "  \"homeVodImg\": \"/img/@src\",\n" +
                        "  \"homeVodMark\": \"/button/text()\",\n" +
                        "  \"cateUrl\": \"http://www.9rmb.com/type/{cateId}/{catePg}.html\",\n" +
                        "  \"cateVodNode\": \"//div[@class='movie-item']/a\",\n" +
                        "  \"cateVodName\": \"/@title\",\n" +
                        "  \"cateVodId\": \"/@href\",\n" +
                        "  \"cateVodIdR\": \"/show/(\\\\w+).html\",\n" +
                        "  \"cateVodImg\": \"/img/@src\",\n" +
                        "  \"cateVodMark\": \"/button/text()\",\n" +
                        "  \"dtUrl\": \"http://www.9rmb.com/show/{vid}.html\",\n" +
                        "  \"dtNode\": \"//div[@class='container-fluid']\",\n" +
                        "  \"dtName\": \"//div[@class='col-md-9']//div[@class='col-md-4']//img/@alt\",\n" +
                        "  \"dtNameR\": \"\",\n" +
                        "  \"dtImg\": \"//div[@class='col-md-9']//div[@class='col-md-4']//img/@src\",\n" +
                        "  \"dtImgR\": \"\",\n" +
                        "  \"dtCate\": \"//div[@class='col-md-8']//span[@class='info-label' and contains(text(), '类型')]/parent::*/following-sibling::*/text()\",\n" +
                        "  \"dtCateR\": \"\",\n" +
                        "  \"dtYear\": \"//div[@class='col-md-8']//span[@class='info-label' and contains(text(), '日期')]/parent::*/following-sibling::*/text()\",\n" +
                        "  \"dtYearR\": \"\",\n" +
                        "  \"dtArea\": \"//div[@class='col-md-8']//span[@class='info-label' and contains(text(), '国家')]/parent::*/following-sibling::*/text()\",\n" +
                        "  \"dtAreaR\": \"\",\n" +
                        "  \"dtMark\": \"//div[@class='col-md-8']//span[@class='info-label' and contains(text(), '状态')]/parent::*/following-sibling::*/text()\",\n" +
                        "  \"dtMarkR\": \"\",\n" +
                        "  \"dtActor\": \"//div[@class='col-md-8']//span[@class='info-label' and contains(text(), '主演')]/parent::*/following-sibling::*/text()\",\n" +
                        "  \"dtActorR\": \"\",\n" +
                        "  \"dtDirector\": \"//div[@class='col-md-8']//span[@class='info-label' and contains(text(), '导演')]/parent::*/following-sibling::*/text()\",\n" +
                        "  \"dtDirectorR\": \"\",\n" +
                        "  \"dtDesc\": \"//p[@class='summary']/text()\",\n" +
                        "  \"dtDescR\": \"\",\n" +
                        "  \"dtFromNode\": \"//div[contains(@class,'resource-list')]/div[@class='panel-heading']/strong\",\n" +
                        "  \"dtFromName\": \"/text()\",\n" +
                        "  \"dtFromNameR\": \"\\\\S+\\\\.(\\\\S+) \\\\(\",\n" +
                        "  \"dtUrlNode\": \"//div[contains(@class,'resource-list')]/ul[@class='dslist-group']\",\n" +
                        "  \"dtUrlSubNode\": \"/li/a\",\n" +
                        "  \"dtUrlId\": \"@href\",\n" +
                        "  \"dtUrlIdR\": \"/play/(\\\\S+).html\",\n" +
                        "  \"dtUrlName\": \"/text()\",\n" +
                        "  \"dtUrlNameR\": \"\",\n" +
                        "  \"playUrl\": \"http://www.9rmb.com/play/{playUrl}.html\",\n" +
                        "  \"playUa\": \"\",\n" +
                        "  \"searchUrl\": \"http://www.9rmb.com/search?wd={wd}\",\n" +
                        "  \"scVodNode\": \"//div[@class='movie-item']/a\",\n" +
                        "  \"scVodName\": \"/@title\",\n" +
                        "  \"scVodId\": \"/@href\",\n" +
                        "  \"scVodIdR\": \"/show/(\\\\w+).html\",\n" +
                        "  \"scVodImg\": \"/img/@src\",\n" +
                        "  \"scVodMark\": \"/button/text()\"\n" +
                        "}\n");
                System.out.println(aidi.homeContent(true));
                System.out.println(aidi.homeVideoContent());
                System.out.println(aidi.categoryContent("2", "1", false, null));
                List<String> ids = new ArrayList<String>();
                ids.add("25603");
                System.out.println(aidi.detailContent(ids));
                System.out.println(aidi.playerContent("", "11111", new ArrayList<>()));
                System.out.println(aidi.searchContent("陪你一起", false));
            }
        }).start();
    }
}