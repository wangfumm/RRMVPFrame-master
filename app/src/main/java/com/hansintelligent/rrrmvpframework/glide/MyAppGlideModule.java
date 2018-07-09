package com.hansintelligent.rrrmvpframework.glide;

import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.module.AppGlideModule;

/**
 * MyAppGlideModule
 * Created by wangfu on 2018/5/21.
 */
@GlideModule
public class MyAppGlideModule extends AppGlideModule {


    /**
     * 禁用清单解析
     * 这样可以改善 Glide 的初始启动时间，并避免尝试解析元数据时的一些潜在问题。
     *
     * @return
     */
    @Override
    public boolean isManifestParsingEnabled() {
        return false;
    }
}
