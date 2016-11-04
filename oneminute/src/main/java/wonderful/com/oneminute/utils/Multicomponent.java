package wonderful.com.oneminute.utils;

import android.app.Activity;

import org.lasque.tusdk.TuSdkGeeV1;
import org.lasque.tusdk.core.TuSdkResult;
import org.lasque.tusdk.core.utils.TLog;
import org.lasque.tusdk.core.utils.TuSdkWaterMarkOption;
import org.lasque.tusdk.impl.activity.TuFragment;
import org.lasque.tusdk.impl.components.TuEditMultipleComponent;
import org.lasque.tusdk.modules.components.TuSdkComponent;

public class Multicomponent {
    private Activity activity;

    public void showSample(Activity activity) {
        if (activity == null) return;

        TuSdkGeeV1.albumCommponent(activity, new TuSdkComponent.TuSdkComponentDelegate() {
            @Override
            public void onComponentFinished(TuSdkResult result, Error error, TuFragment lastFragment) {
                openEditMultiple(result, error, lastFragment);
            }
        }).showComponent();
        this.activity = activity;
    }

    /**
     * 开启照片美化组件
     */
    private void openEditMultiple(TuSdkResult result, Error error, TuFragment lastFragment) {
        if (result == null || error != null) return;

        // 组件委托
        TuSdkComponent.TuSdkComponentDelegate delegate = new TuSdkComponent.TuSdkComponentDelegate() {
            @Override
            public void onComponentFinished(TuSdkResult result, Error error, TuFragment lastFragment) {
                TLog.d("onEditMultipleComponentReaded: %s | %s", result, error);

                // 默认输出为 Bitmap  -> result.image

                // 如果保存到临时文件 (默认不保存, 当设置为true时, TuSdkResult.imageFile, 处理完成后将自动清理原始图片)
                // option.setSaveToTemp(true);  ->  result.imageFile

                // 保存到系统相册 (默认不保存, 当设置为true时, TuSdkResult.sqlInfo, 处理完成后将自动清理原始图片)
                // option.setSaveToAlbum(true);  -> result.image
            }
        };

        // 组件选项配置
        // @see-http://tusdk.com/docs/android/api/org/lasque/tusdk/impl/components/TuEditMultipleComponent.html
        TuEditMultipleComponent component = null;

        if (lastFragment == null) {
            component = TuSdkGeeV1.editMultipleCommponent(this.activity, delegate);
        } else {
            component = TuSdkGeeV1.editMultipleCommponent(lastFragment, delegate);
        }

        // @see-http://tusdk.com/docs/android/api/org/lasque/tusdk/impl/components/TuEditMultipleComponentOption.html
        // component.componentOption()

        // @see-http://tusdk.com/docs/android/api/org/lasque/tusdk/impl/components/edit/TuEditMultipleOption.html
        // component.componentOption().editMultipleOption()

        // 设置水印选项 (默认为空，如果设置不为空，则输出的图片上将带有水印)
        // component.componentOption().editMultipleOption().setWaterMarkOption(getWaterMarkOption(this.componentHelper.activity()));

        // @see-http://tusdk.com/docs/android/api/org/lasque/tusdk/impl/components/edit/TuEditCuterOption.html
        // component.componentOption().editCuterOption()

        // @see-http://tusdk.com/docs/android/api/org/lasque/tusdk/impl/components/filter/TuEditFilterOption.html
        // component.componentOption().editFilterOption()

        // @see-http://tusdk.com/docs/android/api/org/lasque/tusdk/impl/components/filter/TuEditSkinOption.html
        // component.componentOption().editSkinOption()

        // @see-http://tusdk.com/docs/android/api/org/lasque/tusdk/impl/components/sticker/TuEditStickerOption.html
        // component.componentOption().editStickerOption()

        // @see-http://tusdk.com/docs/android/api/org/lasque/tusdk/impl/components/filter/TuEditAdjustOption.html
        // component.componentOption().editAdjustOption()

        // @see-http://tusdk.com/docs/android/api/org/lasque/tusdk/impl/components/filter/TuEditSharpnessOption.html
        // component.componentOption().editSharpnessOption()

        // @see-http://tusdk.com/docs/android/api/org/lasque/tusdk/impl/components/filter/TuEditApertureOption.html
        // component.componentOption().editApertureOption()

        // @see-http://tusdk.com/docs/android/api/org/lasque/tusdk/impl/components/filter/TuEditVignetteOption.html
        // component.componentOption().editVignetteOption()

        // @see-http://tusdk.com/docs/android/api/org/lasque/tusdk/impl/components/smudge/TuEditSmudgeOption.html
        // component.componentOption().editSmudgeOption()

        // @see-http://tusdk.com/docs/android/api/org/lasque/tusdk/impl/components/filter/TuEditWipeAndFilterOption.html
        // component.componentOption().editWipeAndFilterOption()

        // 设置图片
        component.setImage(result.image)
                // 设置系统照片
                .setImageSqlInfo(result.imageSqlInfo)
                // 设置临时文件
                .setTempFilePath(result.imageFile)
                // 在组件执行完成后自动关闭组件
                .setAutoDismissWhenCompleted(true)
                // 开启组件
                .showComponent();
    }

    /**
     * 水印设置
     *
     * @return
     */
    public TuSdkWaterMarkOption getWaterMarkOption(Activity activity) {
        TuSdkWaterMarkOption option = new TuSdkWaterMarkOption();

        // 文字或者图片需要至少设置一个
        // 设置水印文字, 支持图文混排、图片或文字
        option.setMarkText("");

        // 设置文字颜色 (默认:#FFFFFF)
        option.setMarkTextColor("#FFFFFF");

        // 文字大小 (默认: 24 SP)
        option.setMarkTextSize(24);

        // 文字阴影颜色 (默认:#000000)
        option.setMarkTextShadowColor("#000000");

        // 设置水印图片, 支持图文混排、图片或文字
        //option.setMarkImage(BitmapHelper.getRawBitmap(activity, R.raw.sample_watermark));

        // 文字和图片顺序 (仅当图片和文字都非空时生效，默认: 文字在右)
        option.setMarkTextPosition(TuSdkWaterMarkOption.TextPosition.Right);

        // 设置水印位置 (默认: WaterMarkPosition.BottomRight)
        option.setMarkPosition(TuSdkWaterMarkOption.WaterMarkPosition.BottomRight);

        // 设置水印距离图片边距 (默认: 6dp)
        option.setMarkMargin(6);

        // 文字和图片间距 (默认: 2dp)
        option.setMarkTextPadding(5);

        return option;
    }
}
