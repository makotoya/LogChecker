package prunusmume.java_conf.gr.jp.logchecker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.lang.ref.SoftReference;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Makoto Yaguchi on 2017/12/14.
 */

public class RecordAdapter extends BaseAdapter {
    private LayoutInflater mLayoutInflater = null;
    private List<Record> mRecordList;

    public RecordAdapter(Context context) {
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setRecordList(List<Record> recordList) {
        mRecordList = recordList;
    }

    @Override
    public int getCount() {
        return mRecordList.size();
    }

    @Override
    public Object getItem(int position) {
        return mRecordList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mRecordList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {                                  //↓ここをcustomlist_recordにしたい
            convertView = mLayoutInflater.inflate(R.layout.customlist_record, null);
        }

        TextView mTextListField, mTextListDestination, mTextListSpecies, mTextListLength,mTextListNumSum,mTextListVolSum,mTextListDate;

        mTextListField = (TextView) convertView.findViewById(R.id.textRecField);
        mTextListDestination = (TextView) convertView.findViewById(R.id.textRecDestination);
        mTextListSpecies = (TextView) convertView.findViewById(R.id.textRecSpecies);
        mTextListLength  = (TextView) convertView.findViewById(R.id.textRecLength);
        mTextListNumSum  = (TextView) convertView.findViewById(R.id.textRecNumSum);
        mTextListVolSum = (TextView) convertView.findViewById(R.id.textRecVolSum);
        mTextListDate = (TextView) convertView.findViewById(R.id.textDate);

        mTextListField.setText(mRecordList.get(position).getRecField());
        mTextListDestination.setText(mRecordList.get(position).getRecDestination());
        mTextListSpecies.setText(mRecordList.get(position).getRecSpecies());
        mTextListLength.setText(String.valueOf(mRecordList.get(position).getRecLength()));
        mTextListNumSum.setText(String.valueOf(mRecordList.get(position).getRecNumSum()));
        mTextListVolSum.setText(String.valueOf(mRecordList.get(position).getRecVolSum()));

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.JAPANESE);
        Date date = mRecordList.get(position).getDate();
        mTextListDate.setText(simpleDateFormat.format(date));

        return convertView;
    }
}
