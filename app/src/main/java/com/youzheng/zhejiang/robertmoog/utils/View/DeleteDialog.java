package com.youzheng.tongxiang.huntingjob.UI.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.youzheng.tongxiang.huntingjob.R;


/**
 * Created by yangyuqi on 2016/12/8.
 */

public class DeleteDialog extends Dialog {

    private TextView tvtitle,tvcontent;
    private TextView tv_ok,tv_no;

    private String strTitle,strContent,strConfirmbtn;

    private Context context;

    private boolean isDelete = false;

    private DeleteDialogInterface deleteDialogInterface;

    public DeleteDialog(Context context, String mtitle, String mContent, String btn) {
        super(context, R.style.DeleteDialogStyle);
        this.context = context;
        strTitle = mtitle;
        strContent = mContent;
        strConfirmbtn = btn;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    public TextView getTv_no() {
        return tv_no;
    }

    public void setTv_no(TextView tv_no) {
        this.tv_no = tv_no;
    }

    private void init() {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view =inflater.inflate(R.layout.confrim_dialog, null);
        setContentView(view);
        tv_ok=  (TextView) view.findViewById(R.id.tv_ok);
        tv_ok.setOnClickListener(new DeleteDialog.clickListener());
        tv_no = (TextView) view.findViewById(R.id.tv_no);
        tv_no.setOnClickListener(new DeleteDialog.clickListener());
        tvtitle = (TextView) findViewById(R.id.confrim_dialog_tv_title);
        tvcontent = (TextView) findViewById(R.id.confrim_dialog_tv_content);

        tvtitle.setText(strTitle);
        tvcontent.setText(strContent);
        tv_ok.setText(strConfirmbtn);

        Window dialogWindow = getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        DisplayMetrics d = context.getResources().getDisplayMetrics(); // 获取屏幕宽、高用
        lp.width = (int) (d.widthPixels * 0.65); // 高度设置为屏幕的0.6
        dialogWindow.setAttributes(lp);
    }

    private class clickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int id = v.getId();
            switch (id) {
                case R.id.tv_no:
                    dismiss();
                    break;
                case R.id.tv_ok:
                    isDelete = true;
                    deleteDialogInterface.isDelete(isDelete);
                    dismiss();
                    break;
            }
        }
    };

    public void OnDeleteBtn(DeleteDialogInterface mdeleteDialogInterface){
        deleteDialogInterface = mdeleteDialogInterface;
    }
}