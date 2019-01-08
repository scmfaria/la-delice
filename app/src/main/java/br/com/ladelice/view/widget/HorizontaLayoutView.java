package br.com.ladelice.view.widget;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;

public class HorizontaLayoutView extends LinearLayoutManager {

    private boolean isScrollEnabled = true;

    public HorizontaLayoutView(Context context) {
        super(context);
    }

    public HorizontaLayoutView(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public HorizontaLayoutView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setScrollEnabled(boolean res) {
        this.isScrollEnabled = res;
    }

    @Override
    public boolean canScrollHorizontally() {
        return isScrollEnabled && super.canScrollHorizontally();
    }
}
