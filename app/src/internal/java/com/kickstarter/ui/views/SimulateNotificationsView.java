package com.kickstarter.ui.views;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.kickstarter.R;
import com.kickstarter.ui.activities.ProjectActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SimulateNotificationsView extends LinearLayout {
  public SimulateNotificationsView(@NonNull final Context context) {
    this(context, null);
  }

  public SimulateNotificationsView(@NonNull final Context context, @Nullable final AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public SimulateNotificationsView(@NonNull final Context context, @Nullable final AttributeSet attrs, final int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override
  protected void onFinishInflate() {
    super.onFinishInflate();
    ButterKnife.bind(this);
  }

  @OnClick(R.id.simulate_project_launch_button)
  public void simulateProjectLaunchButtonClick() {
    final Context context = getContext();

    final NotificationCompat.Builder builder =
      new NotificationCompat.Builder(context)
        .setSmallIcon(android.R.drawable.btn_default)
        .setContentTitle("Title")
        .setContentText("Text");

    final Intent resultIntent = new Intent(context, ProjectActivity.class)
      .putExtra(context.getString(R.string.intent_project_param), "double-fine-adventure");

    // The stack builder object will contain an artificial back stack for the
    // started Activity.
    // This ensures that navigating backward from the Activity leads out of
    // your application to the Home screen.
    TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
    // Adds the back stack for the Intent (but not the Intent itself)
    stackBuilder.addParentStack(ProjectActivity.class);
    // Adds the Intent that starts the Activity to the top of the stack
    stackBuilder.addNextIntent(resultIntent);
    PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
    builder.setContentIntent(resultPendingIntent);
    final NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    int id = 10009; // TODO: Extract, this is just a garbage id
    notificationManager.notify(id, builder.build());

    /*
    NotificationManager mNotificationManager =
      (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    // mId allows you to update the notification later on.
    mNotificationManager.notify(mId, mBuilder.build());
    Timber.d("launch");
    */
  }
}
