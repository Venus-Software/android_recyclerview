package vsc.vic.githubres.ui;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import vsc.vic.githubres.R;
import vsc.vic.githubres.adapter.CommonAdapter;
import vsc.vic.githubres.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    public ActivityMainBinding mBinding;
    public boolean mLoadMore;//是否支持上拉加载

    private CommonAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
       /* adapter = new CommonAdapter<>();
        mBinding.recycleView.setAdapter(adapter);*/

    }

    public void setCommonAdapter(){



    }


}
