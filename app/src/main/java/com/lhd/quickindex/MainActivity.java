package com.lhd.quickindex;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends Activity
{

    private IndexView iv_main_words;
    private TextView tv_main_word;
    private ListView lv_main_persons;
    /**
     * ListView的数据集合
     */
    private List<Person> data;
    private Handler handler = new Handler()
    {
        public void handleMessage(Message msg)
        {
            switch (msg.what)

            {
                case 0:
                    tv_main_word.setVisibility(View.GONE);
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv_main_words = (IndexView) findViewById(R.id.iv_main_words);
        tv_main_word = (TextView) findViewById(R.id.tv_main_word);
        lv_main_persons = (ListView) findViewById(R.id.lv_main_persons);
        initData();
        iv_main_words.setOnIndexChangeListener(new MyOnIndexChangeListener());
        MyListAdapter adapter = new MyListAdapter(this, data);
        lv_main_persons.setAdapter(adapter);
    }

    class MyOnIndexChangeListener implements IndexView.OnIndexChangeListener

    {
        @Override
        public void onIndexChange(String word)
        {
            updateWord(word);
            for (int i = 0; i < data.size(); i++)
            {
                Person person = data.get(i);
                if (word.equals(person.getPinYin().substring(0,1)))
                {
                    lv_main_persons.setSelection(i);
                    return;
                }
            }

        }
    }

    private void updateWord(String word)
    {
        tv_main_word.setVisibility(View.VISIBLE);
        tv_main_word.setText(word);
        handler.removeMessages(0);
        handler.sendEmptyMessageDelayed(0, 2000);
    }

    private void initData()
    {

        data = new ArrayList<>();

        data.add(new Person("张晓飞"));
        data.add(new Person("杨光福"));
        data.add(new Person("胡继群"));
        data.add(new Person("刘畅"));

        data.add(new Person("钟泽兴"));
        data.add(new Person("尹革新"));
        data.add(new Person("安传鑫"));
        data.add(new Person("张骞壬"));

        data.add(new Person("温松"));
        data.add(new Person("李凤秋"));
        data.add(new Person("刘甫"));
        data.add(new Person("娄全超"));
        data.add(new Person("张猛"));

        data.add(new Person("王英杰"));
        data.add(new Person("李振南"));
        data.add(new Person("孙仁政"));
        data.add(new Person("唐春雷"));
        data.add(new Person("牛鹏伟"));
        data.add(new Person("姜宇航"));

        data.add(new Person("刘挺"));
        data.add(new Person("张洪瑞"));
        data.add(new Person("张建忠"));
        data.add(new Person("侯亚帅"));
        data.add(new Person("刘帅"));

        data.add(new Person("乔竞飞"));
        data.add(new Person("徐雨健"));
        data.add(new Person("吴亮"));
        data.add(new Person("王兆霖"));

        data.add(new Person("阿三"));

        //排序
        Collections.sort(data, new Comparator<Person>()
        {
            @Override
            public int compare(Person lhs, Person rhs)
            {
                return lhs.getPinYin().substring(0, 1).compareTo(rhs.getPinYin().substring(0, 1));

            }
        });
    }
}
