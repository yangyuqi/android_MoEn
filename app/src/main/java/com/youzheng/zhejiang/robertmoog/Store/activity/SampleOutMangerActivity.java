package com.youzheng.zhejiang.robertmoog.Store.activity;


import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yalantis.ucrop.UCrop;
import com.yalantis.ucrop.UCropActivity;
import com.youzheng.zhejiang.robertmoog.Base.BaseActivity;
import com.youzheng.zhejiang.robertmoog.R;
import com.youzheng.zhejiang.robertmoog.Store.utils.Bimp;
import com.youzheng.zhejiang.robertmoog.Store.utils.PermissionUtils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 出样管理界面
 */
public class SampleOutMangerActivity extends BaseActivity implements View.OnClickListener {

    private ImageView btnBack;
    /**  */
    private TextView textHeadTitle;
    /**  */
    private TextView textHeadNext;
    private ImageView iv_next;
    private RelativeLayout layout_header;
    private LinearLayout lin_commit;
    private LinearLayout lin_history;
    private LinearLayout inflate;
    private Dialog dialog;
    /**
     * 拍摄
     */
    private TextView tv_take_photo;
    /**
     * 从手机相册选择
     */
    private TextView tv_find_from;
    /**
     * 取消
     */
    private TextView tv_cancel;
    protected Bitmap mBitmap;
    protected File file;
    protected int degree = 0;
    protected int maxChoiceNumber = 9;
    public static final int TAKE_PHOTO_WITH_DATA = 1;

    public static final int RESULT_LOAD_IMAGE = 2;
    /**
     * 是否需要拍照后裁剪
     */
    protected boolean isTailor = false;

    /**
     * 选择本地相册是否需要多选
     */
    protected boolean isChoice = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample_out_manger);
        initView();
    }

    private void initView() {
        btnBack = (ImageView) findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);
        textHeadTitle = (TextView) findViewById(R.id.textHeadTitle);
        textHeadTitle.setText("出样发布");
        textHeadNext = (TextView) findViewById(R.id.textHeadNext);
        iv_next = (ImageView) findViewById(R.id.iv_next);
        layout_header = (RelativeLayout) findViewById(R.id.layout_header);
        lin_commit = (LinearLayout) findViewById(R.id.lin_commit);
        lin_commit.setOnClickListener(this);
        lin_history = (LinearLayout) findViewById(R.id.lin_history);
        lin_history.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btnBack:
                finish();
                break;
            case R.id.lin_commit:
                showDialog();
                break;
            case R.id.lin_history:
                startActivity(new Intent(this,SampleOutDetailActivity.class));
                break;
            case R.id.tv_take_photo:
                skipCamera();
                break;
            case R.id.tv_find_from:
                startActivity(new Intent(this,UpPhotoActivity.class));
                dialog.dismiss();
                break;
            case R.id.tv_cancel:
                if (dialog.isShowing()){
                    dialog.dismiss();
                }

                break;
        }
    }


    private void showDialog() {
        dialog = new Dialog(SampleOutMangerActivity.this, R.style.ActionDialogStyle);
        //填充对话框的布局
        inflate = (LinearLayout) LayoutInflater.from(SampleOutMangerActivity.this).inflate(R.layout.layout_photo, null);
        //初始化控件
        tv_take_photo = (TextView) inflate.findViewById(R.id.tv_take_photo);
        tv_take_photo.setOnClickListener(this);
        tv_find_from = (TextView) inflate.findViewById(R.id.tv_find_from);
        tv_find_from.setOnClickListener(this);
        tv_cancel = (TextView) inflate.findViewById(R.id.tv_cancel);
        tv_cancel.setOnClickListener(this);
        //将布局设置给Dialog
        dialog.setContentView(inflate);
        //获取当前Activity所在的窗体
        Window dialogWindow = dialog.getWindow();
        //设置Dialog从窗体底部弹出
        dialogWindow.setGravity(Gravity.BOTTOM);
        //获得窗体的属性
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = LinearLayout.LayoutParams.MATCH_PARENT;
//        lp.y = 20;//设置Dialog距离底部的距离
////       将属性设置给窗体
        dialogWindow.setAttributes(lp);
        dialog.show();//显示对话框
    }

    protected void skipCamera(){

        if (Build.VERSION.SDK_INT >= 23) {

            if (PermissionUtils.permissionIsOpen(this, Manifest.permission.CAMERA)
                    && PermissionUtils.permissionIsOpen(this, Manifest.permission.WRITE_EXTERNAL_STORAGE
            ) ){

                camera();

            } else {

                if (!shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)
                        | !shouldShowRequestPermissionRationale(Manifest.permission.WRITE_EXTERNAL_STORAGE
                )){
                    PermissionUtils.showCameraDialog(getString(R.string.content_str_camera) + getString(R.string.content_str_read)
                            ,this);
                } else {

                    List<String> list = new ArrayList<>();

                    list.add(Manifest.permission.CAMERA);
                    list.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);

                    PermissionUtils.openMultiPermission(this ,list , PermissionUtils.CODE_MULTI);

                }

            }

        }else {
            camera();
        }

    }

    // 调用系统拍照
    protected void camera() {

        try {
            destoryBimap();
            String state = Environment.getExternalStorageState();
            if (state.equals(Environment.MEDIA_MOUNTED)) {
                // 获取 SD 卡根目录
                String saveDir = Environment.getExternalStorageDirectory() + "/repair_box/";
                // 新建目录
                File dir = new File(saveDir);
                if (!dir.exists())
                    dir.mkdir();
                // 生成文件名
                SimpleDateFormat t = new SimpleDateFormat("yyyyMMddssSSS");

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    file = new File(Environment.getExternalStorageDirectory().getAbsolutePath()
                            + "/buff/" + System.currentTimeMillis() + ".jpg");
                    file.getParentFile().mkdirs();

                    //改变Uri  注意和xml中的一致
                    Uri uri = FileProvider.getUriForFile(this, "huizhi_buff_com.buff.fileprovider", file);
                    //添加权限
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

                    intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                    startActivityForResult(intent, TAKE_PHOTO_WITH_DATA);

                }else {
                    Intent getImageByCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    file = new File(saveDir, "MT" + (t.format(new Date())) + ".jpg");
                    getImageByCamera.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                    startActivityForResult(getImageByCamera, TAKE_PHOTO_WITH_DATA);
                }
            } else {

                showToast(getString(R.string.null_sd));
            }
        } catch (Exception e) {
        }

    }

    /**
     * 销毁图片文件
     */
    protected void destoryBimap() {
        try {
            if (mBitmap != null && !mBitmap.isRecycled()) {
                mBitmap.recycle();
                mBitmap = null;
            }
        } catch (Exception e) {
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("111",resultCode +"");
        Log.i("121",requestCode + "");
        //裁剪成功后的uri
        if (requestCode == UCrop.REQUEST_CROP) {
            final Uri resultUri = UCrop.getOutput(data);
           // setTailorHeadIvEvenSendMine(resultUri);
        } else if (resultCode == UCrop.RESULT_ERROR) {
            final Throwable cropError = UCrop.getError(data);
        }

        switch (requestCode) {

            case TAKE_PHOTO_WITH_DATA:

                if (!file.canRead() || !file.canWrite()) {
                    return;
                }

                String picturePath1 = file.getPath();

                Log.i("person", picturePath1);
                try {

                    Bitmap bm = Bimp.revitionImageSize(picturePath1);
                    degree = readPictureDegree(picturePath1);
                    Bitmap bm1 = rotaingImageView(degree, bm);
                    Uri uri1 = bitmap2Uri(bm1, this);

                    //拍照后需要裁剪走
                    if (isTailor){
                        startUcrop(uri1);
                    }else {
                        //setHeadIvEvenSendMine(bm1 ,picturePath1);
                    }

                    Bimp.drr.clear();


                } catch (Exception e) {

                }

                break;
            case RESULT_LOAD_IMAGE :
                if (data != null) {// 如果返回的数据不等空，就添加图片,防止空指向异常
                    // 得到系统返回的地址 并Uri格式
                    Uri selectedImage = data.getData();
                    // 要查找的类型 字段
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    // 用个cursor把Uri指向的图片信息查找（query）出来
                    Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                    cursor.moveToFirst();
                    // 查找完成后 将其取出 得到图片地址
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String picturePath = cursor.getString(columnIndex);
                    Log.i("", picturePath);
                    // 关闭cursor
                    cursor.close();
                    try {
                        Bitmap bm = Bimp.revitionImageSize(picturePath);
                        Uri uri1 = bitmap2Uri(bm, this);
                        //setHeadIvEvenSendMine(bm ,picturePath);

                        Bimp.drr.clear();

                    } catch (Exception e) {
                    }
                }
                break;

        }
    }


    private void startUcrop(Uri uri) {
        File newFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath()
                + "/buff/" + System.currentTimeMillis() + ".jpg");
        newFile.getParentFile().mkdirs();
        //改变Uri  注意和xml中的一致
        Uri destinationUri = FileProvider.getUriForFile(this, "huizhi_buff_com.buff.fileprovider", newFile);
        UCrop uCrop = UCrop.of(uri, destinationUri);
        UCrop.Options options = new UCrop.Options();
        //设置裁剪图片可操作的手势
        options.setAllowedGestures(UCropActivity.SCALE , UCropActivity.NONE, UCropActivity.NONE);
        //设置隐藏底部容器，默认显示
//        options.setHideBottomControls(true);
        //设置toolbar颜色
        options.setToolbarColor(ActivityCompat.getColor(this, R.color.colorPrimary));
        //设置状态栏颜色
        options.setStatusBarColor(ActivityCompat.getColor(this, R.color.colorPrimary));
        //是否能调整裁剪框
//        options.setFreeStyleCropEnabled(true);
        uCrop.withOptions(options);
        uCrop.start(this);
    }



    public static Uri bitmap2Uri(Bitmap bitmap, Context context) {
        Uri uri = Uri.parse(MediaStore.Images.Media.insertImage(context.getContentResolver(), bitmap, null, null));
        return uri;
    }

    /**
     * 读取图片属性：旋转的角度
     * @param path 图片绝对路径
     * @return degree旋转的角度
     */
    protected static int readPictureDegree(String path) {
        int degree = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_NORMAL);

            switch (orientation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    degree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    degree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    degree = 270;
                    break;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return degree;
    }

    /*
     * 旋转图片
     */
    protected static Bitmap rotaingImageView(int angle, Bitmap bitmap) {
        // 旋转图片 动作
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        // 创建新的图片
        Bitmap resizedBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return resizedBitmap;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case PermissionUtils.CODE_SINGLE://获取单个权限

                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && permissions[0].equals(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {//权限被打开
                    camera();
                }

                break;

            case PermissionUtils.CODE_MULTI://获取多个权限

                List<String> list = new ArrayList<>();

                if (grantResults.length > 0) {
                    for (int i = 0; i < grantResults.length; i++) {
                        int grantResult = grantResults[i];
                        if (grantResult == PackageManager.PERMISSION_DENIED) {//权限被拒绝

                            list.add(permissions[i]);

                        }
                    }
                }

                if (list.size() == 0){
                    camera();
                }

                break;

        }

    }
}
