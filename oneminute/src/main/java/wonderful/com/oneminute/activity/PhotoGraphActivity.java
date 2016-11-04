package wonderful.com.oneminute.activity;

import android.app.Activity;
import android.os.Bundle;

import org.lasque.tusdk.core.TuSdkResult;
import org.lasque.tusdk.core.utils.hardware.CameraConfigs;
import org.lasque.tusdk.core.utils.hardware.CameraHelper;
import org.lasque.tusdk.impl.activity.TuFragment;
import org.lasque.tusdk.impl.components.camera.TuCameraFragment;
import org.lasque.tusdk.impl.components.camera.TuCameraOption;
import org.lasque.tusdk.impl.components.camera.TuFocusTouchView;
import org.lasque.tusdk.modules.components.TuSdkHelperComponent;

import wonderful.com.oneminute.utils.Multicomponent;


public class PhotoGraphActivity extends Activity implements TuCameraFragment.TuCameraFragmentDelegate {

    private TuCameraOption option;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 如果不支持摄像头显示警告信息
        if (CameraHelper.showAlertIfNotSupportCamera(getBaseContext())) return;
        initView();
        finish();
    }

    private void initView() {
        //获取相机控制器对象
        option = new TuCameraOption();
        //左下角显示相册入口的方法
        option.setDisplayAlbumPoster(true);
        //设置保存到系统相册
        option.setSaveToAlbum(true);
        // 是否保存到临时文件
        option.setSaveToTemp(false);
        //设置拍摄后自动释放相机
        option.setAutoReleaseAfterCaptured(true);
        //设置相机方向
        option.setAvPostion(CameraConfigs.CameraFacing.Back);
        //设置是否开启滤镜支持
        option.setEnableFilters(true);
        /*//是否默认显示滤镜视图
        option.setShowFilterDefault(true);*/
        //设置触摸聚焦视图ID
        option.setFocusTouchViewId(TuFocusTouchView.getLayoutId());
        /*//设置是否直接输出图片数据
        option.setOutputImageData(true);*/
        /*//设置需要显示的滤镜名称列表
        String[] filters = {"SkinNature", "SkinPink", "SkinJelly",
                "SkinNoir", "SkinRuddy", "SkinPowder", "SkinSugar"};
        option.setFilterGroup(Arrays.asList(filters));*/
        TuCameraFragment fragment = option.fragment();
        fragment.setDelegate(this);
        //启动相机并全屏显示
        TuSdkHelperComponent componentHelper = new TuSdkHelperComponent(this);
        componentHelper.presentModalNavigationActivity(fragment, true);
    }

    @Override
    public void onComponentError(TuFragment tuFragment, TuSdkResult tuSdkResult, Error error) {

    }

    @Override
    public void onTuCameraFragmentCaptured(TuCameraFragment tuCameraFragment, TuSdkResult tuSdkResult) {

    }

    @Override
    public boolean onTuCameraFragmentCapturedAsync(TuCameraFragment tuCameraFragment, TuSdkResult tuSdkResult) {
        return false;
    }

    //点击相册回调的方法
    @Override
    public void onTuAlbumDemand(TuCameraFragment tuCameraFragment) {
        //打开相册
        new Multicomponent().showSample(this);
    }
}