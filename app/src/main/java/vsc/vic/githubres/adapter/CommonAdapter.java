package vsc.vic.githubres.adapter;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.os.Handler;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import vsc.vic.githubres.BR;
import vsc.vic.githubres.R;
import vsc.vic.githubres.untils.Action;
import vsc.vic.githubres.untils.ActionItemView;
import vsc.vic.githubres.untils.CallAction;
import vsc.vic.githubres.untils.ViewHolder;


/**
 *
 * 通用Adapter
 */
public class CommonAdapter<T> extends RecyclerView.Adapter<ViewHolder> {

    private List<T> mDataSet;
    private int mResource;
    private int mLoadMore;//1--需要，其他--不需要
    private boolean mIsGetClickView;//是否需要获取点击的view项
    private ActionItemView<Integer> onViewItemClick;//需要返回view
    private Action<Integer> mOnItemClick;//不要返回view


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
        View view  = inflater.inflate(mLoadMore==1 ? R.layout.item_load : mResource,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final int p = position;
        ViewDataBinding binding = holder.getmBinding();//获取binding
        binding.setVariable(BR.item,mDataSet.get(position));//设置数据源
        binding.executePendingBindings();//执行绑定
        if(mOnItemClick!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mIsGetClickView){
                        mOnItemClick.onItemClick(p);
                    }else{
                        onViewItemClick.onItemClick(v,p);
                    }
                }
            });

        }

    }

    @Override
    public int getItemViewType(int position) {
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
    public void onItemClickLisener(ActionItemView<Integer> onItemClick) {
        this.onViewItemClick = onItemClick;
    }

    //加载更多
    protected void setLoadMoreAction(RecyclerView recyclerView, final CallAction action){

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
                if(itemCount<=lastItem+1){
                    mDataSet.add(null);
                    notifyDataSetChanged();
                    new Handler().postDelayed(action::call,2000);
                }
            }
        });

    }



}

