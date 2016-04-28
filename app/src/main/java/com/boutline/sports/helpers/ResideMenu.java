package com.boutline.sports.helpers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.DisplayMetrics;
import android.view.*;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.boutline.sports.R;
import com.boutline.sports.activity.FixturesActivity;
import com.boutline.sports.activity.NewsFeedActivity;
import com.boutline.sports.activity.PredictionsActivity;
import com.boutline.sports.activity.ProfileSettingsActivity;
import com.boutline.sports.adapters.LeftMenuAdapter;
import com.boutline.sports.adapters.RightMenuAdapter;
import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.view.ViewHelper;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ResideMenu extends FrameLayout {

    private static final ScheduledExecutorService worker =
            Executors.newSingleThreadScheduledExecutor();

    public  static final int DIRECTION_LEFT  = 0;
    public  static final int DIRECTION_RIGHT = 1;
    private static final int PRESSED_MOVE_HORIZONTAL = 2;
    private static final int PRESSED_DOWN = 3;
    private static final int PRESSED_DONE = 4;
    private static final int PRESSED_MOVE_VERTICAL = 5;

    private ImageView imageViewShadow;
    private ImageView imageViewBackground;
    private LinearLayout layoutLeftMenu;
    private LinearLayout layoutRightMenu;
    private LinearLayout layoutMenu;

    private ListView leftMenuListView;
    private ListView rightMenuListView;

    private ScrollView scrollViewMenu;
    private Activity activity;
    private ViewGroup viewDecor;
    private TouchDisableView viewActivity;
    private boolean              isOpened;
    private float shadowAdjustScaleX;
    private float shadowAdjustScaleY;
    private List<View> ignoredViews;
    private List<ResideMenuItem> leftMenuItems;
    private List<ResideMenuItem> rightMenuItems;
    private DisplayMetrics displayMetrics = new DisplayMetrics();
    private OnMenuListener menuListener;
    private float lastRawX;
    private boolean isInIgnoredView = false;
    private int scaleDirection/* = DIRECTION_LEFT*/;
    private int pressedState   = PRESSED_DOWN;
    private List<Integer> disabledSwipeDirection = new ArrayList<Integer>();
    private float mScaleValue = 0.5f;

    ArrayList<String> mStringArrayListLeftMenu = new ArrayList<>();
    ArrayList<String> mStringArrayListRightMenu = new ArrayList<>();
    Context mContext;
    TextView resideMenuChannelTextView;
    View footerView;
    View headerView;

    Typeface defaultFont;
    Typeface defaultFontBold;

    public ResideMenu(Context context) {
        super(context);

        this.mContext = context;
        initViews(mContext);
    }

    private void initViews(Context context){
        LayoutInflater inflater = (LayoutInflater)
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.residemenu, this);
        defaultFont = Typeface.createFromAsset(mContext.getAssets(), "fonts/avonixorp.ttf");
        defaultFontBold = Typeface.createFromAsset(mContext.getAssets(), "fonts/avonixorpbold.ttf");
        //scrollViewLeftMenu = (ScrollView) findViewById(R.id.sv_left_menu);
        //scrollViewRightMenu = (ScrollView) findViewById(R.id.sv_right_menu);
        footerView = ((LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                .inflate(R.layout.left_menu_footer_view, null, false);
        headerView = ((LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE))
                .inflate(R.layout.groupsheader, null, false);
        imageViewShadow = (ImageView) findViewById(R.id.iv_shadow);
        layoutLeftMenu = (LinearLayout) findViewById(R.id.layout_left_menu);
        layoutRightMenu = (LinearLayout) findViewById(R.id.layout_right_menu);
        imageViewBackground = (ImageView) findViewById(R.id.iv_background);
        leftMenuListView = (ListView)findViewById(R.id.leftMenuListView);
        rightMenuListView = (ListView)findViewById(R.id.rightMenuListView);
        TextView headerlabel = (TextView)headerView.findViewById(R.id.headerlabel);
        headerlabel.setTypeface(defaultFontBold);
        TextView footerlabel = (TextView)footerView.findViewById(R.id.footerlabel);
        footerlabel.setTypeface(defaultFontBold);
        leftMenuListView.addFooterView(footerView);
        leftMenuListView.addHeaderView(headerView);

        footerView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        rightMenuListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position,
                long arg3)
                {
                    switch(position){
                        case 0:
                            closeMenu();
                            Intent i = new Intent(activity, PredictionsActivity.class);
                            activity.startActivity(i);
                            activity.overridePendingTransition(R.anim.pushleftin, R.anim.pushleftout);
                            break;

                        case 1:
                            closeMenu();
                            Intent i1 = new Intent(activity, NewsFeedActivity.class);
                            activity.startActivity(i1);
                            activity.overridePendingTransition(R.anim.pushleftin, R.anim.pushleftout);
                            break;
                        case 2:
                            closeMenu();
                            Intent i2 = new Intent(activity, FixturesActivity.class);
                            activity.startActivity(i2);
                            activity.overridePendingTransition(R.anim.pushleftin, R.anim.pushleftout);
                            break;
                        case 3:
                            closeMenu();
                            Intent i3 = new Intent(activity, ProfileSettingsActivity.class);
                            activity.startActivity(i3);
                            activity.overridePendingTransition(R.anim.pushleftin, R.anim.pushleftout);
                            break;
                        default:
                            closeMenu();
                            break;
                    }
                }

        });
    }

    @Override
    protected boolean fitSystemWindows(Rect insets) {
        // Applies the content insets to the view's padding, consuming that content (modifying the insets to be 0),
        // and returning true. This behavior is off by default and can be enabled through setFitsSystemWindows(boolean)
        // in api14+ devices.
        this.setPadding(viewActivity.getPaddingLeft() + insets.left, viewActivity.getPaddingTop() + insets.top,
                viewActivity.getPaddingRight() + insets.right, viewActivity.getPaddingBottom() + insets.bottom);
        insets.left = insets.top = insets.right = insets.bottom = 0;
        return true;
    }

    /**
     * Set up the activity;
     *
     * @param activity
     */
    public void attachToActivity(Activity activity){
        initValue(activity);
        setShadowAdjustScaleXByOrientation();
        viewDecor.addView(this, 0);
    }

    private void initValue(Activity activity){
        this.activity   = activity;
        leftMenuItems   = new ArrayList<ResideMenuItem>();
        rightMenuItems  = new ArrayList<ResideMenuItem>();
        ignoredViews    = new ArrayList<View>();
        viewDecor = (ViewGroup) activity.getWindow().getDecorView();
        viewActivity = new TouchDisableView(this.activity);
        /* Add left side menu items to an array list */
        mStringArrayListLeftMenu.add("You");
        mStringArrayListLeftMenu.add("ManchesterUnitedFans");
        mStringArrayListLeftMenu.add("IPL");
        mStringArrayListLeftMenu.add("RossiVsMarc");
        mStringArrayListLeftMenu.add("ISL");
        mStringArrayListLeftMenu.add("UEFA");
        mStringArrayListLeftMenu.add("EPL");
        mStringArrayListLeftMenu.add("ChelseaFanClub");
        mStringArrayListLeftMenu.add("GodOfCricketFan");
        mStringArrayListLeftMenu.add("CskVsKkr");
        mStringArrayListLeftMenu.add("MotoCrossMadness");
        mStringArrayListLeftMenu.add("Formula1");
        mStringArrayListLeftMenu.add("TigerWoodsFanClub");
        mStringArrayListLeftMenu.add("YouWeCan");
        /* Add left side menu items to an array list */
        mStringArrayListRightMenu.add("Predictions");
        mStringArrayListRightMenu.add("News");
        mStringArrayListRightMenu.add("Fixtures");
        mStringArrayListRightMenu.add("Settings");

        /* adapter for the listView */
        LeftMenuAdapter leftMenuAdapter = new LeftMenuAdapter(mContext, R.layout.left_menu_item, mStringArrayListLeftMenu);
        leftMenuListView.setAdapter(leftMenuAdapter);

        RightMenuAdapter rightMenuAdapter = new RightMenuAdapter(mContext, R.layout.right_menu_item, mStringArrayListRightMenu);
        rightMenuListView.setAdapter(rightMenuAdapter);

        View mContent   = viewDecor.getChildAt(0);
        viewDecor.removeViewAt(0);
        viewActivity.setContent(mContent);
        addView(viewActivity);

        ViewGroup parent = (ViewGroup) layoutLeftMenu.getParent();
        parent.removeView(layoutLeftMenu);
        parent.removeView(layoutRightMenu);

    }

    private void setShadowAdjustScaleXByOrientation(){
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            shadowAdjustScaleX = 0.034f;
            shadowAdjustScaleY = 0.12f;
        } else if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            shadowAdjustScaleX = 0.06f;
            shadowAdjustScaleY = 0.07f;
        }

    }

    /** Set the background image of menu;
     * @param imageResource */
    public void setBackground(int imageResource){
        imageViewBackground.setImageResource(imageResource);
    }

    /**
     * The visibility of the shadow under the activity;
     *
     * @param isVisible
     */
    public void setShadowVisible(boolean isVisible){
        if (isVisible) {
            imageViewShadow.setBackgroundResource(R.drawable.shadow);
        }
        else {
            imageViewShadow.setBackgroundResource(0);
        }
    }

    /**
     * Add a single item to the left menu;
     *
     * WARNING: It will be removed from v2.0.
     * @param menuItem
     */
    @Deprecated
    public void addMenuItem(ResideMenuItem menuItem){
        this.leftMenuItems.add(menuItem);
        layoutLeftMenu.addView(menuItem);
    }

    /**
     * Add a single item;
     *
     * @param menuItem
     * @param direction
     */
    public void addMenuItem(ResideMenuItem menuItem, int direction){
        if (direction == DIRECTION_LEFT){
            this.leftMenuItems.add(menuItem);
            layoutLeftMenu.addView(menuItem);
        }else{
            this.rightMenuItems.add(menuItem);
           layoutRightMenu.addView(menuItem);
        }
    }

    /**
     * WARNING: It will be removed from v2.0.
     * @param menuItems
     */
    @Deprecated
    public void setMenuItems(List<ResideMenuItem> menuItems){
        this.leftMenuItems = menuItems;
        rebuildMenu();
    }

    /**
     * Set menu items by a array;
     *
     * @param menuItems
     * @param direction
     */
    public void setMenuItems(List<ResideMenuItem> menuItems, int direction){
        if (direction == DIRECTION_LEFT)
            this.leftMenuItems = menuItems;
        else
            this.rightMenuItems = menuItems;
        rebuildMenu();
    }

    private void rebuildMenu(){

        layoutLeftMenu.removeAllViews();
        layoutRightMenu.removeAllViews();
        for (ResideMenuItem leftMenuItem : leftMenuItems) {
            layoutLeftMenu.addView(leftMenuItem);
        }
        for (ResideMenuItem rightMenuItem : rightMenuItems) {
            layoutRightMenu.addView(rightMenuItem);
        }

    }

    /**
     * WARNING: It will be removed from v2.0.
     * @return
     */
    @Deprecated
    public List<ResideMenuItem> getMenuItems() {
        return leftMenuItems;
    }

    /**
     * Return instances of menu items;
     *
     * @return
     */
    public List<ResideMenuItem> getMenuItems(int direction) {
        if ( direction == DIRECTION_LEFT)
            return leftMenuItems;
        else
            return rightMenuItems;
    }

    /**
     * If you need to do something on closing or opening menu,
     * set a listener here.
     *
     * @return
     */
    public void setMenuListener(OnMenuListener menuListener) {
        this.menuListener = menuListener;
    }


    public OnMenuListener getMenuListener() {
        return menuListener;
    }

    /**
     * Show the menu;
     */
    public void openMenu(int direction){
        setScaleDirection(direction);
        isOpened = true;
        AnimatorSet scaleDown_activity = buildScaleDownAnimation(viewActivity, mScaleValue, mScaleValue);
        AnimatorSet scaleDown_shadow = buildScaleDownAnimation(imageViewShadow,
        		mScaleValue + shadowAdjustScaleX, mScaleValue + shadowAdjustScaleY);
        AnimatorSet alpha_menu = buildMenuAnimation(layoutMenu, 1.0f);
        scaleDown_shadow.addListener(animationListener);
        scaleDown_activity.playTogether(scaleDown_shadow);
        scaleDown_activity.playTogether(alpha_menu);
        scaleDown_activity.start();
    }

    /**
     * Close the menu;
     */
    public void closeMenu(){

        isOpened = false;
        AnimatorSet scaleUp_activity = buildScaleUpAnimation(viewActivity, 1.0f, 1.0f);
       AnimatorSet scaleUp_shadow = buildScaleUpAnimation(imageViewShadow, 1.0f, 1.0f);
        AnimatorSet alpha_menu = buildMenuAnimation(layoutMenu, 0.0f);
        scaleUp_activity.addListener(animationListener);
        scaleUp_activity.playTogether(scaleUp_shadow);
        scaleUp_activity.playTogether(alpha_menu);
        scaleUp_activity.start();

    }

    @Deprecated
    public void setDirectionDisable(int direction){
        disabledSwipeDirection.add(direction);
    }

    public void setSwipeDirectionDisable(int direction){
        disabledSwipeDirection.add(direction);
    }

    private boolean isInDisableDirection(int direction){
        return disabledSwipeDirection.contains(direction);
    }

    private void setScaleDirection(int direction){

        int screenWidth = getScreenWidth();
        float pivotX;
        float pivotY = getScreenHeight() * 0.5f;

        if (direction == DIRECTION_LEFT){
            layoutMenu = layoutLeftMenu;
            pivotX  = screenWidth * 1.5f;
        }else{
            layoutMenu = layoutRightMenu;
            pivotX  = screenWidth * -0.5f;
        }

        ViewHelper.setPivotX(viewActivity, pivotX);
        ViewHelper.setPivotY(viewActivity, pivotY);
        ViewHelper.setPivotX(imageViewShadow, pivotX);
        ViewHelper.setPivotY(imageViewShadow, pivotY);
        scaleDirection = direction;

    }

    /**
     * return the flag of menu status;
     *
     * @return
     */
    public boolean isOpened() {
        return isOpened;
    }

    private OnClickListener viewActivityOnClickListener = new OnClickListener() {
        @Override
        public void onClick(View view) {
            if (isOpened()) closeMenu();

        }
    };

    private Animator.AnimatorListener animationListener = new Animator.AnimatorListener() {
        @Override
        public void onAnimationStart(Animator animation) {
            if (isOpened()){
                showScrollViewMenu(layoutMenu);
                if (menuListener != null)
                    menuListener.openMenu();
            }
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            // reset the view;
            if(isOpened()){
                viewActivity.setTouchDisable(true);
                viewActivity.setOnClickListener(viewActivityOnClickListener);
            }else{
                viewActivity.setTouchDisable(false);
                viewActivity.setOnClickListener(null);
                hideScrollViewMenu(layoutLeftMenu);
                hideScrollViewMenu(layoutRightMenu);
                if (menuListener != null)
                    menuListener.closeMenu();
            }
        }

        @Override
        public void onAnimationCancel(Animator animation) {

        }

        @Override
        public void onAnimationRepeat(Animator animation) {

        }
    };

    /**
     * A helper method to build scale down animation;
     *
     * @param target
     * @param targetScaleX
     * @param targetScaleY
     * @return
     */
    private AnimatorSet buildScaleDownAnimation(View target,float targetScaleX,float targetScaleY){

        AnimatorSet scaleDown = new AnimatorSet();
        scaleDown.playTogether(
                ObjectAnimator.ofFloat(target, "scaleX", targetScaleX),
                ObjectAnimator.ofFloat(target, "scaleY", targetScaleY)
        );

        scaleDown.setInterpolator(AnimationUtils.loadInterpolator(activity,android.R.interpolator.decelerate_cubic));
        scaleDown.setDuration(250);
        return scaleDown;
    }

    /**
     * A helper method to build scale up animation;
     *
     * @param target
     * @param targetScaleX
     * @param targetScaleY
     * @return
     */
    private AnimatorSet buildScaleUpAnimation(View target,float targetScaleX,float targetScaleY){

        AnimatorSet scaleUp = new AnimatorSet();
        scaleUp.playTogether(
                ObjectAnimator.ofFloat(target, "scaleX", targetScaleX),
                ObjectAnimator.ofFloat(target, "scaleY", targetScaleY)
        );

        scaleUp.setDuration(250);
        return scaleUp;
    }

    private AnimatorSet buildMenuAnimation(View target, float alpha){

        AnimatorSet alphaAnimation = new AnimatorSet();
        alphaAnimation.playTogether(
                ObjectAnimator.ofFloat(target, "alpha", alpha)
        );

        alphaAnimation.setDuration(250);
        return alphaAnimation;


    }

    /**
     * If there were some view you don't want reside menu
     * to intercept their touch event, you could add it to
     * ignored views.
     *
     * @param v
     */
    public void addIgnoredView(View v){
        ignoredViews.add(v);
    }

    /**
     * Remove a view from ignored views;
     * @param v
     */
    public void removeIgnoredView(View v){
        ignoredViews.remove(v);
    }

    /**
     * Clear the ignored view list;
     */
    public void clearIgnoredViewList(){
        ignoredViews.clear();
    }

    /**
     * If the motion event was relative to the view
     * which in ignored view list,return true;
     *
     * @param ev
     * @return
     */
    private boolean isInIgnoredView(MotionEvent ev) {
        Rect rect = new Rect();
        for (View v : ignoredViews) {
            v.getGlobalVisibleRect(rect);
            if (rect.contains((int) ev.getX(), (int) ev.getY()))
                return true;
        }
        return false;
    }

    private void setScaleDirectionByRawX(float currentRawX){
        if (currentRawX < lastRawX)
            setScaleDirection(DIRECTION_RIGHT);
        else
            setScaleDirection(DIRECTION_LEFT);
    }

    private float getTargetScale(float currentRawX){
        float scaleFloatX = ((currentRawX - lastRawX) / getScreenWidth()) * 0.75f;
        scaleFloatX = scaleDirection == DIRECTION_RIGHT ? - scaleFloatX : scaleFloatX;

        float targetScale = ViewHelper.getScaleX(viewActivity) - scaleFloatX;
        targetScale = targetScale > 1.0f ? 1.0f : targetScale;
        targetScale = targetScale < 0.5f ? 0.5f : targetScale;
        return targetScale;
    }

    private float lastActionDownX, lastActionDownY;

    @Override
    protected void dispatchSetPressed(boolean pressed) {
        closeMenu();
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        float currentActivityScaleX = ViewHelper.getScaleX(viewActivity);
        if (currentActivityScaleX == 1.0f)
            setScaleDirectionByRawX(ev.getRawX());

        switch (ev.getAction()){
            case MotionEvent.ACTION_DOWN:
                lastActionDownX = ev.getX();
                lastActionDownY = ev.getY();
                isInIgnoredView = isInIgnoredView(ev) && !isOpened();
                pressedState    = PRESSED_DOWN;
                break;

            case MotionEvent.ACTION_MOVE:
                if (isInIgnoredView || isInDisableDirection(scaleDirection))
                    break;

                if(pressedState != PRESSED_DOWN &&
                        pressedState != PRESSED_MOVE_HORIZONTAL)
                    break;

                int xOffset = (int) (ev.getX() - lastActionDownX);
                int yOffset = (int) (ev.getY() - lastActionDownY);

                if(pressedState == PRESSED_DOWN) {
                    if(yOffset > 25 || yOffset < -25) {
                        pressedState = PRESSED_MOVE_VERTICAL;
                        break;
                    }
                    if(xOffset < -50 || xOffset > 50) {
                        pressedState = PRESSED_MOVE_HORIZONTAL;
                        ev.setAction(MotionEvent.ACTION_CANCEL);
                    }
                } else if(pressedState == PRESSED_MOVE_HORIZONTAL) {
                    if (currentActivityScaleX < 0.95)
                        showScrollViewMenu(layoutLeftMenu);

                    float targetScale = getTargetScale(ev.getRawX());
                    ViewHelper.setScaleX(viewActivity, targetScale);
                    ViewHelper.setScaleY(viewActivity, targetScale);
                    ViewHelper.setScaleX(imageViewShadow, targetScale + shadowAdjustScaleX);
                    ViewHelper.setScaleY(imageViewShadow, targetScale + shadowAdjustScaleY);
                    ViewHelper.setAlpha(layoutMenu, (1 - targetScale) * 2.0f);

                    lastRawX = ev.getRawX();
                    return true;
                }

                break;

            case MotionEvent.ACTION_UP:

                if (isInIgnoredView) break;
                if (pressedState != PRESSED_MOVE_HORIZONTAL) break;

                pressedState = PRESSED_DONE;
                if (isOpened()){
                    if (currentActivityScaleX > 0.56f)
                        closeMenu();
                    else
                        openMenu(scaleDirection);
                }else{
                    if (currentActivityScaleX < 0.94f){
                        openMenu(scaleDirection);
                    }else{
                        closeMenu();
                    }
                }

                break;

        }
        lastRawX = ev.getRawX();
        return super.dispatchTouchEvent(ev);
    }


    public int getScreenHeight(){
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    public int getScreenWidth(){
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }
    
    public void setScaleValue(float scaleValue) {
        this.mScaleValue = scaleValue;
    }


    public interface OnMenuListener{

        /**
         * This method will be called at the finished time of opening menu animations.
         */
        public void openMenu();

        /**
         * This method will be called at the finished time of closing menu animations.
         */
        public void closeMenu();
    }

    private void showScrollViewMenu(LinearLayout layout){
        if (layout != null && layout.getParent() == null){
            addView(layout);
        }
    }

    private void hideScrollViewMenu(LinearLayout layout){
        if (layout != null && layout.getParent() != null){
            removeView(layout);
        }
    }

}
