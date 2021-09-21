package com.github.catvod.demo;

import android.app.Activity;
import android.os.Bundle;

import com.github.catvod.spider.Aidi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Thread(new Runnable() {
            @Override
            public void run() {
                Aidi aidi = new Aidi();
                aidi.init(MainActivity.this);
                System.out.println(aidi.homeContent(true));
                HashMap<String, String> extend = new HashMap<>();
                extend.put("0", "dongzuopian");
                System.out.println(aidi.categoryContent("dianying", "1", false, extend));
                List<String> ids = new ArrayList<String>();
                ids.add("1902");
                System.out.println(aidi.detailContent(ids));
                System.out.println(aidi.searchContent("陪你一起", false));
            }
        }).start();
    }
}