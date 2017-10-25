package com.tiandu.recruit.stud.base;

import android.app.Activity;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Jerome on 16/6/6.
 * Email :jeromekai8@gmail.com
 */
public class BaseAppManager {

    private static BaseAppManager instance = null;
    private static List<Activity> mActivities = new LinkedList<>();

    private BaseAppManager() {

    }

    public synchronized static BaseAppManager getInstance() {
        return AppMgrHolder.INSTANCE;
    }

    private static class AppMgrHolder{
        private static final BaseAppManager INSTANCE = new BaseAppManager();
    }

    public int size() {
        return mActivities.size();
    }

    public synchronized Activity getForwardActivity() {
        return size() > 0 ? mActivities.get(size() - 1) : null;
    }

    public synchronized void addActivity(Activity activity) {
        mActivities.add(activity);
    }

    public synchronized void removeActivity(Activity activity) {
        if (mActivities.contains(activity)) {
            mActivities.remove(activity);
        }
    }

    public synchronized void clearAll() {
        for (int i = mActivities.size() - 1; i > -1; i--) {
            Activity activity = mActivities.get(i);
            removeActivity(activity);
            activity.finish();
            i = mActivities.size();
        }
    }

    public synchronized void clearToTop() {
        Activity activity = null;
        for (int i = mActivities.size() - 2; i > -1; i--) {
            activity = mActivities.get(i);
            removeActivity(activity);
            activity.finish();
            i = mActivities.size() - 1;
        }
    }
}
