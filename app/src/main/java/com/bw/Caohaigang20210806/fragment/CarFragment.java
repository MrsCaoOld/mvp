package com.bw.Caohaigang20210806.fragment;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.AuthTask;
import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.app.PayTask;
import com.bw.Caohaigang20210806.MainActivity;
import com.bw.Caohaigang20210806.R;
import com.bw.Caohaigang20210806.adapter.FoodAdapterGreenDao;
import com.bw.Caohaigang20210806.food.Food;
import com.bw.Caohaigang20210806.pay.AuthResult;
import com.bw.Caohaigang20210806.pay.H5PayDemoActivity;
import com.bw.Caohaigang20210806.pay.PayResult;
import com.bw.Caohaigang20210806.pay.util.OrderInfoUtil2_0;

import java.util.List;
import java.util.Map;

public class CarFragment extends Fragment {
    private View inflate;
    private RecyclerView recyclerView;
    private FoodAdapterGreenDao foodAdapterGreenDao;
    public static CheckBox rvCheck;
    public static int sum = 0;
    public static TextView text;
    public static Button and;


    /**
     * 用于支付宝支付业务的入参 app_id。
     */
    public static final String APPID = "2021000117666891";

    /**
     * 用于支付宝账户登录授权业务的入参 pid。
     */
    public static final String PID = "";

    /**
     * 用于支付宝账户登录授权业务的入参 target_id。
     */
    public static final String TARGET_ID = "";

    /**
     *  pkcs8 格式的商户私钥。
     *
     * 	如下私钥，RSA2_PRIVATE 或者 RSA_PRIVATE 只需要填入一个，如果两个都设置了，本 Demo 将优先
     * 	使用 RSA2_PRIVATE。RSA2_PRIVATE 可以保证商户交易在更加安全的环境下进行，建议商户使用
     * 	RSA2_PRIVATE。
     *
     * 	建议使用支付宝提供的公私钥生成工具生成和获取 RSA2_PRIVATE。
     * 	工具地址：https://doc.open.alipay.com/docs/doc.htm?treeId=291&articleId=106097&docType=1
     */
    public static final String RSA2_PRIVATE = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCBdQfh0jueIOoEC+iIEcc85hVcjiGcbrje4h3zbB+dH2qPGmiVrNfobQ/GwvaKakP82RqeFx2W4SvCm+Aa5ciiigUC/obMWWG4ZYXL/K4BOsCgr+/7rs+9gZ7EmsMtaWAbBsTLV2WWQpAQeMYzcdDBQVdlWPiJJpk+r9xLp22n1Yeb0gbKFJnbfEulFNU/oVAyqJsrLbJq2UwLuoM13cV0jDKBHDfc/HRhlC+RNXjsve1Cr8JNHOC30J74iEj03LiqcPRNx5kVy1M+Td2jazL40P8alkJXIhMrqBOEwTNGPD5BwZTD4FgH6BtcDfSsvMgkezxmXuI0FMrWr/pro0IJAgMBAAECggEAT3w61bWOc13EMa3aHUnFKigfL4/5JSJ6kicFusNFeeSjObA+TMAuZQApsqFcXMo4alQm2rBYs54Xj0rDw+AYW2wg/aCnxWqP7HcxOnkvoYOgaEj+MWs5FCM1B4P6mLRdlNqIFE3aCvNdny94ine0dCQZdBm3qAYyC2JRAYaQdn5tC6XWX2B2mntk5Ynu9jT2ZIujOq6QOrUyZ/ybskZec/451v28wbVBXznAkF71wgWI4akYeFCAg/4pEC5Wjs/UeRkxduR/OJNgXE4njwMzJaDOGIjzGiK4w25OvpCxWyw+8pZcrw9EG8Y94YVOz9JOL/ud64GCOrrO4QJMquGjgQKBgQDKkhU+BONYvLsYA5Chs7Bcz91HyZ0gnrzfIFFD+yye4QdGMCzmWCCTeSrhqfByxq1SvZPeEDZYm5Ob4IptPNHXzwakOdxoweDNEU3KWp7qC1ULGzQm1M437zujjEGks0rszzyngnU64vCST+OUy36oNAHWRspcYor/FXwFzGEg2QKBgQCjmjMnvz0WeeCgozw3+UQd/so7nfd86mDv3f/9a+7wplVrUbHRltz9y2mhTupw9C9dSnu/LTzLgYx10Tfk3DH4j/OWkw6RK2SPZxacQCJueInX1LaNa4B64fzLzonGWBJEIFvLeIA1iTeGwjtO2rbrdR3U2lvoug/ygBfek6BssQKBgQDHVZ0FnHXDWmSZKtjl5hZEHl/XRnQSJ2IgBoMxbDbqWPF5heqI+GuF8xPZYAq2Rv9Djj21pSp1YxKLrxdIizLLNHOKo01a4H3ADakI2MfL8z7KYQietYX/0LBVLa1izvOJuBwtkdieC7dDos2GHAEUr6inHJ4A7w9sF84lPorNcQKBgDnBVg3SFV655/LNrZSVCtcL+5jQS25tz4redGCimdea4MziKkQzXhC6nq+zjFAT5CSrBsSW07+EHHH7SiAhHAMDzpp5j8rv5rA5VGIUwhL9uqvXDBsguFqpLPVGFJ1QuiISylUqVRiROGj6aLWNwMWesslRzOB7zuVtEwAgXaIBAoGAZ7l8dfsJhbvkQCXbDcMSsohpQ2sHPEWvJ/uwp9R+rKo93ul38YTWtkLYxwRm7ybFAC13HQz1X8IeYXC4SqOTH3SYsnonokJHA9RFnknVTczNHmF31xEaNz6H3X5rU3q+8mcVlPOkNPx6OFEBOxBcHNfUnQmg5xZEW9EgIs656fI=";
    public static final String RSA_PRIVATE = "";

    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     * 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        Toast.makeText(getContext(), "支付成功", Toast.LENGTH_SHORT).show();
                        MainActivity.foodDao.deleteAll();
                        List<Food> foods = MainActivity.foodDao.loadAll();
                        foodAdapterGreenDao = new FoodAdapterGreenDao(R.layout.item_food_car,foods);
                        recyclerView.setAdapter(foodAdapterGreenDao);
                        text.setText("合计：0元");
                        showAlert(getContext(), getString(R.string.pay_success) + payResult);
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        showAlert(getContext(), getString(R.string.pay_failed) + payResult);
                    }
                    break;
                }
                case SDK_AUTH_FLAG: {
                    @SuppressWarnings("unchecked")
                    AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
                    String resultStatus = authResult.getResultStatus();

                    // 判断resultStatus 为“9000”且result_code
                    // 为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
                    if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {
                        // 获取alipay_open_id，调支付时作为参数extern_token 的value
                        // 传入，则支付账户为该授权账户
                        showAlert(getContext(), getString(R.string.auth_success) + authResult);
                    } else {
                        // 其他状态值则为授权失败
                        showAlert(getContext(), getString(R.string.auth_failed) + authResult);
                    }
                    break;
                }
                default:
                    break;
            }
        };
    };
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        inflate = inflater.inflate(R.layout.fragment_car, container, false);
        initView();

        return inflate;
    }

    private void initView() {
        recyclerView = inflate.findViewById(R.id.rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        rvCheck = (CheckBox) inflate.findViewById(R.id.rv_check);
        text = (TextView) inflate.findViewById(R.id.text);
        and = (Button) inflate.findViewById(R.id.and);
    }

    @Override
    public void onResume() {
        super.onResume();
        List<Food> foods = MainActivity.foodDao.loadAll();
        foodAdapterGreenDao = new FoodAdapterGreenDao(R.layout.item_food_car,foods);
        recyclerView.setAdapter(foodAdapterGreenDao);

        //全选
        rvCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rvCheck.isChecked()){
                    for (Food food : foods) {
                        food.setCheck("1");
                        int num = food.getNum();
                        sum+=num;
                    }
                }else{
                    for (Food food : foods) {
                        food.setCheck("2");
                        int num = food.getNum();
                        sum-=num;
                    }
                }
                text.setText("合计："+sum+"元");
                foodAdapterGreenDao.notifyDataSetChanged();
            }
        });
        //结算
        and.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);
                payV2(v);
            }
        });
    }


    /**
     * 支付宝支付业务示例
     */
    public void payV2(View v) {
        if (TextUtils.isEmpty(APPID) || (TextUtils.isEmpty(RSA2_PRIVATE) && TextUtils.isEmpty(RSA_PRIVATE))) {
            showAlert(getContext(), getString(R.string.error_missing_appid_rsa_private));
            return;
        }

        /*
         * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
         * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
         * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
         *
         * orderInfo 的获取必须来自服务端；
         */
        boolean rsa2 = (RSA2_PRIVATE.length() > 0);
        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID, rsa2);
        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);

        String privateKey = rsa2 ? RSA2_PRIVATE : RSA_PRIVATE;
        String sign = OrderInfoUtil2_0.getSign(params, privateKey, rsa2);
        final String orderInfo = orderParam + "&" + sign;

        final Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(getActivity());
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Log.i("msp", result.toString());

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    /**
     * 支付宝账户授权业务示例
     */
    public void authV2(View v) {
        if (TextUtils.isEmpty(PID) || TextUtils.isEmpty(APPID)
                || (TextUtils.isEmpty(RSA2_PRIVATE) && TextUtils.isEmpty(RSA_PRIVATE))
                || TextUtils.isEmpty(TARGET_ID)) {
            showAlert(getContext(), getString(R.string.error_auth_missing_partner_appid_rsa_private_target_id));
            return;
        }

        /*
         * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
         * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
         * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
         *
         * authInfo 的获取必须来自服务端；
         */
        boolean rsa2 = (RSA2_PRIVATE.length() > 0);
        Map<String, String> authInfoMap = OrderInfoUtil2_0.buildAuthInfoMap(PID, APPID, TARGET_ID, rsa2);
        String info = OrderInfoUtil2_0.buildOrderParam(authInfoMap);

        String privateKey = rsa2 ? RSA2_PRIVATE : RSA_PRIVATE;
        String sign = OrderInfoUtil2_0.getSign(authInfoMap, privateKey, rsa2);
        final String authInfo = info + "&" + sign;
        Runnable authRunnable = new Runnable() {

            @Override
            public void run() {
                // 构造AuthTask 对象
                AuthTask authTask = new AuthTask(getActivity());
                // 调用授权接口，获取授权结果
                Map<String, String> result = authTask.authV2(authInfo, true);

                Message msg = new Message();
                msg.what = SDK_AUTH_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        // 必须异步调用
        Thread authThread = new Thread(authRunnable);
        authThread.start();
    }

    /**
     * 获取支付宝 SDK 版本号。
     */
    public void showSdkVersion(View v) {
        PayTask payTask = new PayTask(getActivity());
        String version = payTask.getVersion();
        showAlert(getContext(), getString(R.string.alipay_sdk_version_is) + version);
    }

    /**
     * 将 H5 网页版支付转换成支付宝 App 支付的示例
     */
    public void h5Pay(View v) {
        WebView.setWebContentsDebuggingEnabled(true);
        Intent intent = new Intent(getContext(), H5PayDemoActivity.class);
        Bundle extras = new Bundle();

        /*
         * URL 是要测试的网站，在 Demo App 中会使用 H5PayDemoActivity 内的 WebView 打开。
         *
         * 可以填写任一支持支付宝支付的网站（如淘宝或一号店），在网站中下订单并唤起支付宝；
         * 或者直接填写由支付宝文档提供的“网站 Demo”生成的订单地址
         * （如 https://mclient.alipay.com/h5Continue.htm?h5_route_token=303ff0894cd4dccf591b089761dexxxx）
         * 进行测试。
         *
         * H5PayDemoActivity 中的 MyWebViewClient.shouldOverrideUrlLoading() 实现了拦截 URL 唤起支付宝，
         * 可以参考它实现自定义的 URL 拦截逻辑。
         */
        String url = "https://m.taobao.com";
        extras.putString("url", url);
        intent.putExtras(extras);
        startActivity(intent);
    }

    private static void showAlert(Context ctx, String info) {
        showAlert(ctx, info, null);
    }

    private static void showAlert(Context ctx, String info, DialogInterface.OnDismissListener onDismiss) {
        new AlertDialog.Builder(ctx)
                .setMessage(info)
                .setPositiveButton(R.string.confirm, null)
                .setOnDismissListener(onDismiss)
                .show();
    }

    private static void showToast(Context ctx, String msg) {
        Toast.makeText(ctx, msg, Toast.LENGTH_LONG).show();
    }

    private static String bundleToString(Bundle bundle) {
        if (bundle == null) {
            return "null";
        }
        final StringBuilder sb = new StringBuilder();
        for (String key: bundle.keySet()) {
            sb.append(key).append("=>").append(bundle.get(key)).append("\n");
        }
        return sb.toString();
    }
}