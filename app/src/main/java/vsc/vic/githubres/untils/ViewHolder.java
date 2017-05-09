package vsc.vic.githubres.untils;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class ViewHolder extends RecyclerView.ViewHolder {

    private final ViewDataBinding mBinding;

    public ViewHolder(View itemView) {
        super(itemView);
        this.mBinding = DataBindingUtil.bind(itemView);
    }

    public ViewDataBinding getmBinding() {
        return mBinding;
    }
}
