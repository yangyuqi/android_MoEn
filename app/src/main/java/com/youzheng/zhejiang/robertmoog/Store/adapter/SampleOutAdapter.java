package com.youzheng.zhejiang.robertmoog.Store.adapter;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.youzheng.zhejiang.robertmoog.R;
import com.youzheng.zhejiang.robertmoog.Store.activity.SampleOutInformationActivity;
import com.youzheng.zhejiang.robertmoog.Store.bean.CommitRequest;
import com.youzheng.zhejiang.robertmoog.Store.bean.SampleOutList;

import java.util.ArrayList;
import java.util.List;

public class SampleOutAdapter extends BaseAdapter {
    private List<SampleOutList.SampleResDataBean.SampleSingleDataListBean> list;
    private Context context;
    private LayoutInflater layoutInflater;
    private Boolean isappear = false;
    public static List<CommitRequest.ProductSampleDataBean> text=new ArrayList<>();//
    private List<EditText> editTextList;

    public SampleOutAdapter(List<SampleOutList.SampleResDataBean.SampleSingleDataListBean> list, Context context) {
        this.list = list;
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        editTextList = new ArrayList<>();
    }

    public void setUI(List<SampleOutList.SampleResDataBean.SampleSingleDataListBean> list) {
        this.list = list;
        notifyDataSetChanged();

    }

    public void setAppear(Boolean isappear) {
        this.isappear = isappear;
        editTextList.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_sample_out, parent, false);
            viewHolder = new ViewHolder(convertView, position);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        SampleOutList.SampleResDataBean.SampleSingleDataListBean bean = list.get(position);
        viewHolder.tv_content.setText(bean.getSampleName());
        viewHolder.tv_number.setText(bean.getSampleQuantity() + "");

        int sampleId = list.get(position).getSampleId();
        viewHolder.et_number.setTag(sampleId);

        if (isappear == true) {
            viewHolder.tv_number.setVisibility(View.GONE);
            viewHolder.et_number.setVisibility(View.VISIBLE);
//            viewHolder.et_number.setText(bean.getSampleQuantity()+"");
        } else {
            viewHolder.tv_number.setVisibility(View.VISIBLE);
            viewHolder.et_number.setVisibility(View.GONE);
        }

        editTextList.add(viewHolder.et_number);

        return convertView;
    }

    public List<EditText> getEdList(){

        return editTextList;
    }

    class ViewHolder {
        private TextView tv_content, tv_number;
        private EditText et_number;

        public ViewHolder(View view, int pisition) {
            tv_content = view.findViewById(R.id.tv_content);
            tv_number = view.findViewById(R.id.tv_number);
            et_number = view.findViewById(R.id.et_number);
//            et_number.setTag(pisition);//存tag值
//            et_number.addTextChangedListener(new TextSwitcher(this));
        }
    }

    class TextSwitcher implements TextWatcher {
        private ViewHolder mHolder;

        public TextSwitcher(ViewHolder mHolder) {
            this.mHolder = mHolder;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

//            List<CommitRequest.ProductSampleDataBean> requests=new ArrayList<>();
//            CommitRequest.ProductSampleDataBean bean=new CommitRequest.ProductSampleDataBean();
//            bean.setSampleId(list.get(position).getSampleId());
//            bean.setSampleQuantity(list.get(position).getSampleQuantity());
//            requests.add(bean);
        }

        @Override
        public void afterTextChanged(Editable s) {
//            if (!TextUtils.isEmpty(s)){
//
//                int position = (int) mHolder.et_number.getTag();//取tag值
////                ((SampleOutInformationActivity) context).getEditData(position, s.toString());
//                CommitRequest.ProductSampleDataBean commit=new CommitRequest.ProductSampleDataBean();
//                for (int i = 0; i <list.size() ; i++) {
//                    commit.setSampleQuantity(Integer.parseInt(mHolder.et_number.getText().toString()));
//                    commit.setSampleId(list.get(position).getSampleId());
//                    text.add(commit);
//                }


        }
    }
}
