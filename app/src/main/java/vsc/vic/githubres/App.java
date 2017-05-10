package vsc.vic.githubres;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

/**
 * Created by Administrator on 2017/5/10/010.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initFresco();
    }

    /**
     * init fresco lib
     */
    private void initFresco() {
        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(this)
                .setDownsampleEnabled(true)
                .build();
        Fresco.initialize(this, config);
//        FLog.setMinimumLoggingLevel(FLog.VERBOSE);
    }
}
