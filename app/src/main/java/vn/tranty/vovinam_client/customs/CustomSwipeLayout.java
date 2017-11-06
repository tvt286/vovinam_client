package vn.tranty.vovinam_client.customs;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TRUC-SIDA on 12/5/2016.
 */

public class CustomSwipeLayout extends RecyclerView.OnScrollListener {
    private List<RecyclerView.OnScrollListener> mScrollListeners = new ArrayList<RecyclerView.OnScrollListener>();
    private int mExpectedVisiblePosition = 0;
    private SwipeRefreshLayout mSwipeLayout;

    public CustomSwipeLayout(SwipeRefreshLayout swipeLayout) {
        mSwipeLayout = swipeLayout;
    }
    public void addScrollListener(RecyclerView.OnScrollListener listener){
        mScrollListeners.add(listener);
    }
    public boolean removeScrollListener(RecyclerView.OnScrollListener listener){
        return mScrollListeners.remove(listener);
    }
    public void setExpectedFirstVisiblePosition(int position){
        mExpectedVisiblePosition = position;
    }
    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        notifyScrollStateChanged(recyclerView,newState);
        LinearLayoutManager llm = (LinearLayoutManager) recyclerView.getLayoutManager();
        int firstVisible = llm.findFirstCompletelyVisibleItemPosition();
        if(firstVisible != RecyclerView.NO_POSITION)
            mSwipeLayout.setEnabled(firstVisible == mExpectedVisiblePosition);

    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        notifyOnScrolled(recyclerView, dx, dy);
    }
    private void notifyOnScrolled(RecyclerView recyclerView, int dx, int dy){
        for(RecyclerView.OnScrollListener listener : mScrollListeners){
            listener.onScrolled(recyclerView, dx, dy);
        }
    }
    private void notifyScrollStateChanged(RecyclerView recyclerView, int newState){
        for(RecyclerView.OnScrollListener listener : mScrollListeners){
            listener.onScrollStateChanged(recyclerView, newState);
        }
    }
}