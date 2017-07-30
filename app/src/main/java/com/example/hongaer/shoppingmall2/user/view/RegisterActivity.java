package com.example.hongaer.shoppingmall2.user.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hongaer.shoppingmall2.R;
import com.example.hongaer.shoppingmall2.app.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;

public class RegisterActivity extends Activity {

    @BindView(R.id.ib_login_back)
    ImageButton ibLoginBack;
    @BindView(R.id.et_sign_phone)
    EditText etSignPhone;
    @BindView(R.id.et_sign_pwd)
    EditText etSignPwd;
    @BindView(R.id.ib_login_visible)
    ImageButton ibLoginVisible;
    @BindView(R.id.btn_sign)
    Button btnSign;
    @BindView(R.id.tv_register)
    TextView tvRegister;
    @BindView(R.id.password_et)
    TextView passWorrd_et;
    @BindView(R.id.Message_btn)
    TextView messager_btn;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        Bmob.initialize(RegisterActivity.this,"90d09ab48f73eb4f3c73d5fc44dc001d");


}


    @OnClick({R.id.Message_btn,R.id.ib_login_back, R.id.ib_login_visible, R.id.btn_sign, R.id.tv_register})
    public void onClick(View view) {
        String phoneNumber=etSignPhone.getText().toString();
        String  newpassword=etSignPwd.getText().toString();
        String  verify=passWorrd_et.getText().toString();

        switch (view.getId()) {
            case R.id.ib_login_back:
                break;

            case R.id.ib_login_visible:
                break;
            case R.id.Message_btn:
                if(phoneNumber.length()==0){
                    Toast.makeText(this,"请输入手机号",Toast.LENGTH_SHORT).show();
                    return;
                }if(phoneNumber.length()!=11){
                Toast.makeText(this,"请输入有效手机号",Toast.LENGTH_SHORT).show();
                return;
            }
                  BmobSMS.requestSMSCode(phoneNumber, "注册模板", new QueryListener<Integer>() {
                      @Override
                      public void done(Integer integer, BmobException e) {
                          if(e==null){//验证码发送成功
                              messager_btn.setClickable(true);
                              messager_btn.setBackgroundColor(Color.parseColor("#FFD4D4D7"));

                              Toast.makeText(RegisterActivity.this, "验证码发送成功，请尽快使用", Toast.LENGTH_SHORT).show();

                              new CountDownTimer(30000, 1000) {
                                  @Override
                                  public void onTick(long millisUntilFinished) {
                                      //Message_btn.setBackgroundResource(R.drawable.button_shape02);
                                      messager_btn.setText("重新发送"+millisUntilFinished / 1000 + "秒");
                                  }

                                  @Override
                                  public void onFinish() {
                                      messager_btn.setClickable(true);
                                      //Message_btn.setBackgroundResource(R.drawable.button_shape);
                                      messager_btn.setText("重新发送");
                                  }
                              }.start();
                          }
                      else{
                              Toast.makeText(RegisterActivity.this, "验证码发送失败，请检查网络连接", Toast.LENGTH_SHORT).show();
                          }
                      }

                  });
                break;
            case R.id.btn_sign:

                //非空验证
                if(TextUtils.isEmpty(phoneNumber)||TextUtils.isEmpty(newpassword)||TextUtils.isEmpty(verify)){

                    Toast.makeText(this,"请输入手机号",Toast.LENGTH_SHORT).show();
                      return;

                }if(phoneNumber.length()<11){

                Toast.makeText(this,"请输入有效手机号",Toast.LENGTH_SHORT).show();
                return;

            }
                   BmobSMS.verifySmsCode(phoneNumber,verify, new UpdateListener() {
                        @Override
                        public void done(BmobException e) {
                            if (e==null){
                                Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                                //验证码正确 添加用户信息
                                String phoneNumber=etSignPhone.getText().toString();
                                String  newpassword=etSignPwd.getText().toString();
                                BmobUser user=new BmobUser();
                                user.setUsername(phoneNumber);
                                user.setPassword(newpassword);
                                user.signUp(new SaveListener< BmobUser>() {
                                    public void done( BmobUser s, BmobException e) {
                                        if(e==null){
                                           /* Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                                            finish();*/

                                        }else{

                                            //loge(e);
                                        /*    Toast.makeText(RegisterActivity.this, "注册失败", Toast.LENGTH_SHORT).show();*/
                                        }
                                    }
                                });
                                Intent intent=new Intent(RegisterActivity.this, LoginActivity.class);
                                  startActivity(intent);
                            }
                            else {
                                Toast.makeText(RegisterActivity.this, "验证码错误", Toast.LENGTH_SHORT).show();
                            }

                        }
                    });

                     break;

            case R.id.tv_register:

                break;
                }

                /*BmobUser user=new BmobUser();
                user.setUsername(phoneNumber);
                user.setPassword(newpassword);
                user.signUp(new SaveListener<User>() {
                    public void done( User s, BmobException e) {
                        if(e==null){
                            Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                            finish();
                        }else{
                            //loge(e);
                            Toast.makeText(RegisterActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });*/
               /* MyUser myUser=new MyUser();
                myUser.setNumber(phoneNumber);
                myUser.setPassnumber(newpassword);
                myUser.signUp(new SaveListener<MyUser>() {
                    public void done( MyUser s, BmobException e) {
                        if(e==null){
                            Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                        }else{
                            //loge(e);
                            Toast.makeText(RegisterActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                        }
                    }

                });*/
               /* myUser.save(new SaveListener<String>() {
                    @Override
                    public void done(String s, BmobException e) {
                        if(e==null){
                            Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                            RegisterActivity.this.finish();
                        }else{
                            Toast.makeText(RegisterActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                        }
                    }
                });*/
               /* myUser.signUp(this, new SaveListener() {
                    @Override
                    public void onSuccess() {
                        Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(int i, String s) {
                        Toast.makeText(RegisterActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                    }
                });
*/

        }
    }


