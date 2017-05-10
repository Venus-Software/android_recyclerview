package vsc.vic.githubres.adapter;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import vsc.vic.githubres.BR;
import vsc.vic.githubres.R;
import vsc.vic.githubres.untils.Action;
import vsc.vic.githubres.untils.ActionItemView;
import vsc.vic.githubres.untils.CallAction;
import vsc.vic.githubres.untils.ViewHolder;
import vsc.vic.githubres.untils.doTouchCallBack;


/**
 *
 * 通用Adapter
 */
public class CommonAdapter<T> extends RecyclerView.Adapter<ViewHolder> implements doTouchCallBack {

    private List<T> mDataSet;
    private int mResource;
    private int mLoadMore;//1--需要，其他--不需要
    private boolean mIsGetClickView;//是否需要获取点击的view项
    private ActionItemView<Integer> onViewItemClick;//需要返回view
    private Action<Integer> mOnItemClick;//不要返回view
    private boolean isLoading;


    public CommonAdapter(int resource, List<T> mDataSet) {
        this.mResource = resource;
        this.mDataSet = mDataSet;
    }
    public CommonAdapter(int resource, List<T> mDataSet,int toLoadMore) {
        this.mResource = resource;
        this.mDataSet = mDataSet;
        this.mLoadMore = toLoadMore;
    }
    public CommonAdapter(int resource, List<T> mDataSet,boolean isGetClickView) {
        this.mResource = resource;
        this.mDataSet = mDataSet;
        this.mIsGetClickView = isGetClickView;
    }
    public CommonAdapter(int resource, List<T> mDataSet,int toLoadMore,boolean isGetClickView) {
        this.mResource = resource;
        this.mDataSet = mDataSet;
        this.mLoadMore = toLoadMore;
        this.mIsGetClickView = isGetClickView;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        Log.e("LOG_TAG","viewType"+viewType);
        View view  = inflater.inflate(viewType==-1 ? R.layout.item_load : mResource,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ViewDataBinding binding = holder.getmBinding();//获取binding
        binding.setVariable(BR.item,mDataSet.get(position));//设置数据源
        binding.executePendingBindings();//执行绑定
        if(mOnItemClick!=null){
            holder.itemView.setOnClickListener(v -> {
                if(mIsGetClickView){
                    mOnItemClick.onItemClick(position);
                }else{
                    onViewItemClick.onItemClick(v,position);
                }
            });

        }

    }

    @Override
    public int getItemViewType(int position) {
        if(mDataSet!=null){
            if(mDataSet.size()==position+1&&mDataSet.get(position)==null){
                return -1;
            }
        }
            return super.getItemViewType(position);


    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    //点击事件

    public void onItemClickLisener(Action<Integer> onItemClick) {
        this.mOnItemClick = onItemClick;
    }
    public void onItemClickLisener(ActionItemView<Integer> onItemClick) {this.onViewItemClick = onItemClick;}

    //加载更多
    public void setLoadMoreAction(RecyclerView recyclerView, CallAction action){

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int lastItem = 0;
                RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
                int itemCount = layoutManager.getItemCount();
                if(layoutManager instanceof GridLayoutManager){
                    GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
                    lastItem = gridLayoutManager.findLastVisibleItemPosition();
                }
                else if(layoutManager instanceof LinearLayoutManager){
                    LinearLayoutManager linearLayoutManager = (LinearLayoutManager) layoutManager;
                    lastItem = linearLayoutManager.findLastVisibleItemPosition();
                }
                if(!isLoading&&itemCount<=lastItem+1){
                    isLoading = true;
                    mDataSet.add(null);
                    notifyDataSetChanged();
                    new Handler().postDelayed(action::call,2000);
                }
            }
        });

    }

    public void reset() {
        isLoading = false;
        if (mDataSet.remove(null)) {
            notifyDataSetChanged();
        }
    }

    @Override
    public void doMoveAction(int fromPosiion, int toPosiion) {
        //进行滑动的时候具体操作
        Collections.swap(mDataSet,fromPosiion,toPosiion);
        notifyItemMoved(fromPosiion,toPosiion);
    }

    @Override
    public void doSwipAction(int posiion) {
        //进行拖拽的时候具体操作
        mDataSet.remove(posiion);
        notifyItemRemoved(posiion);
    }
}

