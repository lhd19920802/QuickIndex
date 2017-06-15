package com.lhd.quickindex;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by lihuaidong on 2017/6/15 16:24.
 * 微信：lhd520ssp
 * QQ:414320737
 * 作用：
 */
public class MyListAdapter extends BaseAdapter
{
    private final Context mContext;
    private final List<Person> data;

    public MyListAdapter(Context mContext, List<Person> data)
    {
        this.mContext = mContext;
        this.data = data;
    }

    @Override
    public int getCount()
    {
        return data.size();
    }

    @Override
    public Object getItem(int position)
    {
        return null;
    }

    @Override
    public long getItemId(int position)
    {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        ViewHolder viewHolder;
        if (convertView == null)
        {
            viewHolder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.item_main, null);
            viewHolder.tv_item_name = (TextView) convertView.findViewById(R.id.tv_item_name);
            viewHolder.tv_item_word = (TextView) convertView.findViewById(R.id.tv_item_word);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Person person = data.get(position);

        viewHolder.tv_item_name.setText(person.getName());
        viewHolder.tv_item_word.setText(person.getPinYin().substring(0, 1));
        if (position == 0)
        {
            viewHolder.tv_item_word.setVisibility(View.VISIBLE);
        }
        else
        {
            Person prePerson = data.get(position - 1);
            if (person.getPinYin().substring(0, 1).equals(prePerson.getPinYin().substring(0, 1)))
            {

                viewHolder.tv_item_word.setVisibility(View.GONE);
            }

        }
        return convertView;


    }

    static class ViewHolder
    {
        TextView tv_item_word;
        TextView tv_item_name;
    }
}
