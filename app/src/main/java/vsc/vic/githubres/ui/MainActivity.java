package vsc.vic.githubres.ui;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import vsc.vic.githubres.R;
import vsc.vic.githubres.adapter.CommonAdapter;
import vsc.vic.githubres.databinding.ActivityMainBinding;
import vsc.vic.githubres.untils.ActionItemView;
import vsc.vic.githubres.untils.CallAction;
import vsc.vic.githubres.untils.TouchCallBack;
import vsc.vic.githubres.vo.RecycleViewVo;

public class MainActivity extends AppCompatActivity {

    public ActivityMainBinding mBinding;
    public boolean mLoadMore;//是否支持上拉加载
    private CommonAdapter adapter;
    private List<RecycleViewVo> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        createTestData();
        adapter = new CommonAdapter<>(R.layout.item_shoppingcart,mList);
        mBinding.recycleView.setAdapter(adapter);
        //添加分割线
        mBinding.recycleView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        adapter.onItemClickLisener(new ActionItemView<Integer>() {
            @Override
            public void onItemClick(View view, Integer integer) {

            }
        });
        adapter.setLoadMoreAction(mBinding.recycleView, new CallAction() {
            @Override
            public void call() {
                Toast.makeText(MainActivity.this,"加载更多",Toast.LENGTH_SHORT).show();
                adapter.reset();
            }
        });
        //上下滑动交换位置，左右滑动删除单项
        TouchCallBack callBack = new TouchCallBack(adapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callBack);
        itemTouchHelper.attachToRecyclerView(mBinding.recycleView);

    }




    private void createTestData(){
        if(mList==null){
            mList = new ArrayList<>();
        }
        RecycleViewVo vo = new RecycleViewVo();
        vo.setGiftAmount("10");
        vo.setGiftName("米桶");
        vo.setGiftPhoto("http://totole.chinacloudapp.cn/ttl_manager/data/upload/95979554-8ae7-444a-b9e6-09d9bde339c7.jpg");
        vo.setGiftPrice("50");
        mList.add(vo);
        RecycleViewVo vo1 = new RecycleViewVo();
        vo1.setGiftAmount("10");
        vo1.setGiftName("电子数码秤");
        vo1.setGiftPhoto("http://totole.chinacloudapp.cn/ttl_manager/data/upload/9038282f-8624-4151-acc5-2cfe644f1b7c.jpg");
        vo1.setGiftPrice("150");
        mList.add(vo1);
        RecycleViewVo vo2 = new RecycleViewVo();
        vo2.setGiftAmount("10");
        vo2.setGiftName("不锈钢保温焖烧杯");
        vo2.setGiftPhoto("http://totole.chinacloudapp.cn/ttl_manager/data/upload/9be30e87-69c0-441b-8b85-bee077517a58.jpg");
        vo2.setGiftPrice("20");
        mList.add(vo2);
        RecycleViewVo vo3 = new RecycleViewVo();
        vo3.setGiftAmount("10");
        vo3.setGiftName("不锈钢味斗菜盆五件套");
        vo3.setGiftPhoto("http://totole.chinacloudapp.cn/ttl_manager/data/upload/ed7c35bd-8f04-4035-a184-67052f54413d.jpg");
        vo3.setGiftPrice("50");
        mList.add(vo3);
        RecycleViewVo vo4 = new RecycleViewVo();
        vo4.setGiftAmount("10");
        vo4.setGiftName("烘焙套装17件套");
        vo4.setGiftPhoto("http://totole.chinacloudapp.cn/ttl_manager/data/upload/c2aacd0a-9df9-4252-84c6-2773dff87320.jpg");
        vo4.setGiftPrice("350");
        mList.add(vo4);
        RecycleViewVo vo5 = new RecycleViewVo();
        vo5.setGiftAmount("10");
        vo5.setGiftName("家用多功能厨房用品碗碟架");
        vo5.setGiftPhoto("http://totole.chinacloudapp.cn/ttl_manager/data/upload/f3abd7e6-2de1-48e7-aea1-f363e861f5a3.jpg");
        vo5.setGiftPrice("30");
        mList.add(vo5);
    }


}
