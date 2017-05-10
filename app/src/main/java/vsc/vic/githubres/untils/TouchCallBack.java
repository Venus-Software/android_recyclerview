package vsc.vic.githubres.untils;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

/**
 * Created by Administrator on 2017/5/10/010.
 * 实现左右滑动删除和上下滑动交换位置回调
 */

public class TouchCallBack extends ItemTouchHelper.Callback{
    doTouchCallBack mCallBack;

    public TouchCallBack(doTouchCallBack callBack){
    this.mCallBack = callBack;
    }
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int drop = ItemTouchHelper.UP|ItemTouchHelper.DOWN;
        int swip = ItemTouchHelper.RIGHT|ItemTouchHelper.LEFT;
        return makeMovementFlags(drop,swip);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        mCallBack.doMoveAction(viewHolder.getPosition(),target.getPosition());
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        mCallBack.doSwipAction(viewHolder.getPosition());
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return true;//设置可以长按
    }
}
