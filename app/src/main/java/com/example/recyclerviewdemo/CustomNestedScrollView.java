package com.example.recyclerviewdemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

/**
 * A NestedScrollView with our custom nested scrolling behavior.
 */
public class CustomNestedScrollView extends NestedScrollView{
    public CustomNestedScrollView(@NonNull Context context) {
        super(context);
    }

    public CustomNestedScrollView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomNestedScrollView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    // The NestedScrollView should steal the scroll/fling events away from
    // the RecyclerView if: (1) the user is dragging their finger down and
    // the RecyclerView is scrolled to the top of its content, or (2) the
    // user is dragging their finger up and the NestedScrollView is not
    // scrolled to the bottom of its content.

    @Override
    public void onNestedPreScroll(View target, int dx, int dy, int[] consumed) {
        final RecyclerView rv = (RecyclerView) target;
        if ((dy < 0 && isRvScrolledToTop(rv)) || (dy > 0 && !isNsvScrolledToBottom(this))) {
            // Scroll the NestedScrollView's content and record the number of pixels consumed
            // (so that the RecyclerView will know not to perform the scroll as well).
            scrollBy(0, dy);
            consumed[1] = dy;
            return;
        }
        super.onNestedPreScroll(target, dx, dy, consumed);
    }

    @Override
    public boolean onNestedPreFling(View target, float velX, float velY) {
        final RecyclerView rv = (RecyclerView) target;
        if ((velY < 0 && isRvScrolledToTop(rv)) || (velY > 0 && !isNsvScrolledToBottom(this))) {
            // Fling the NestedScrollView's content and return true (so that the RecyclerView
            // will know not to perform the fling as well).
            fling((int) velY);
            return true;
        }
        return super.onNestedPreFling(target, velX, velY);
    }

    /**
     * Returns true iff the NestedScrollView is scrolled to the bottom of its
     * content (i.e. if the card's inner RecyclerView is completely visible).
     */
    private static boolean isNsvScrolledToBottom(NestedScrollView nsv) {
        return !nsv.canScrollVertically(1);
    }

    /**
     * Returns true iff the RecyclerView is scrolled to the top of its
     * content (i.e. if the RecyclerView's first item is completely visible).
     */
    private static boolean isRvScrolledToTop(RecyclerView rv) {
        final LinearLayoutManager lm = (LinearLayoutManager) rv.getLayoutManager();
        return lm.findFirstVisibleItemPosition() == 0
                && lm.findViewByPosition(0).getTop() == 0;
    }
}
