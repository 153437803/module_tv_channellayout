package lib.kalu.tv.channel;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.widget.ScrollView;

import androidx.annotation.BoolRes;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.util.List;

import lib.kalu.tv.channel.model.ChannelModel;

@Keep
class ChannelScrollView extends ScrollView {
    public ChannelScrollView(@NonNull Context context,
                             @NonNull int itemGravity,
                             @NonNull int itemCount,
                             @NonNull int itemTextSize,
                             @NonNull int itemPaddingLeft,
                             @NonNull int itemPaddingRight) {
        super(context);
        init(itemGravity, itemCount, itemTextSize, itemPaddingLeft, itemPaddingRight);
    }

    @Override
    public void setOnLongClickListener(@Nullable OnLongClickListener l) {
//        super.setOnLongClickListener(l);
    }

    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
//        super.setOnClickListener(l);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
//        return super.dispatchTouchEvent(ev);
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        return super.onTouchEvent(event);
        return false;
    }

    private final void init(
            @NonNull int itemGravity,
            @NonNull int itemCount,
            @NonNull int itemTextSize,
            @NonNull int itemPaddingLeft,
            @NonNull int itemPaddingRight) {
        setClickable(false);
        setLongClickable(false);
        setFocusable(false);
        setFocusableInTouchMode(false);
        setFillViewport(true);
        setVerticalScrollBarEnabled(false);
        setHorizontalScrollBarEnabled(false);
//        setBackgroundColor(Color.parseColor("#00000000"));

        // step1
        removeAllViews();

        // step2
        ChannelLinearLayoutChild child = new ChannelLinearLayoutChild(getContext(), itemGravity, itemCount, itemTextSize, itemPaddingLeft, itemPaddingRight);
        ScrollView.LayoutParams params = new ScrollView.LayoutParams(ScrollView.LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        child.setLayoutParams(params);
        addView(child);
    }

    /********************/

    protected final void update(@NonNull List<ChannelModel> list) {

        View child = getChildAt(0);
        if (null == child)
            return;

        // update
        ((ChannelLinearLayoutChild) child).update(list, true);
    }

    protected final boolean nextUp() {
        try {
            ChannelLinearLayoutChild layoutChild = (ChannelLinearLayoutChild) getChildAt(0);
            int selectPosition = layoutChild.getSelectPosition();
            if (selectPosition == 0) {
                return false;
            } else {
                layoutChild.nextFocus(selectPosition, Channeldirection.NEXT_UP, true, true);
                return true;
            }
        } catch (Exception e) {
            return false;
        }
    }

    protected final boolean nextDown() {
        try {
            ChannelLinearLayoutChild layoutChild = (ChannelLinearLayoutChild) getChildAt(0);
            int count = layoutChild.getChildCount();
            int selectPosition = layoutChild.getSelectPosition();
            if (selectPosition + 1 >= count) {
                return false;
            } else {
                layoutChild.nextFocus(selectPosition, Channeldirection.NEXT_DOWN, true, true);
                return true;
            }
        } catch (Exception e) {
            return false;
        }
    }

    protected final void select(@Channeldirection.Value int direction, @NonNull int position, @NonNull boolean requestFocus, @NonNull boolean callback) {

        if (direction != Channeldirection.INIT)
            return;

        try {
            ChannelLinearLayoutChild layoutChild = (ChannelLinearLayoutChild) getChildAt(0);
            layoutChild.nextFocus(position, direction, requestFocus, callback);
        } catch (Exception e) {
        }

    }

//    protected final void resetTags() {
//        try {
//            ChannelLinearLayoutChild layoutChild = (ChannelLinearLayoutChild) getChildAt(0);
//            layoutChild.resetTags();
//        } catch (Exception e) {
//        }
//    }

    protected final void updateLeft() {
        try {
            ChannelLayout channelLayout = (ChannelLayout) getParent();
            int index = channelLayout.indexOfChild(this);
            if (index > 0) {
                ChannelScrollView scrollView = (ChannelScrollView) channelLayout.getChildAt(index - 1);
                ChannelLinearLayoutChild layoutChild = (ChannelLinearLayoutChild) scrollView.getChildAt(0);
                layoutChild.refresh();
            }
        } catch (Exception e) {
        }
    }

    private final void update() {
        try {

        } catch (Exception e) {
        }
    }

    protected final void focusAuto() {
        try {
            ChannelLinearLayoutChild layoutChild = (ChannelLinearLayoutChild) getChildAt(0);
            layoutChild.requestFocus();
            int selectPosition = layoutChild.getSelectPosition();
            layoutChild.setHighlightPosition(selectPosition, true);
        } catch (Exception e) {
        }
    }

//    protected final void resethighLight() {
//        try {
//            ChannelLinearLayoutChild layoutChild = (ChannelLinearLayoutChild) getChildAt(0);
//            layoutChild.resetHighlight();
//        } catch (Exception e) {
//        }
//    }

    protected final void clear() {
        try {
            ChannelLinearLayoutChild layoutChild = (ChannelLinearLayoutChild) getChildAt(0);
            layoutChild.clearFocus();
            layoutChild.removeAllViews();
            layoutChild.updateTags(null);
        } catch (Exception e) {
        }
    }

    /*************/

    protected final void callback(@NonNull int beforePosition, @NonNull int nextPosition, @NonNull int count, @Channeldirection.Value int direction, @NonNull ChannelModel value) {

        ChannelUtil.logE("callback22 => beforePosition = " + beforePosition + ", nextPosition = " + nextPosition + ", count = " + count + ", direction = " + direction + ", value = " + value);
        if (nextPosition < 0)
            return;

        ViewParent parent = getParent();
        if (null != parent && parent instanceof ChannelLayout) {
            int column = ((ChannelLayout) parent).indexOfChild(this);
            ((ChannelLayout) parent).callback(column, beforePosition, nextPosition, count, direction, value);
        }
    }
}
