package vsc.vic.githubres.untils;

/**
 * Created by Administrator on 2017/5/10/010.
 * 具体实现上下滑动交换，左右滑动删除操作
 */

public interface doTouchCallBack {
    void doMoveAction(int fromPosiion,int toPosiion);
    void doSwipAction(int posiion);
}
