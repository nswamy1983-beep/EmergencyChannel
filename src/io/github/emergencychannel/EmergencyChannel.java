package io.github.emergencychannel;

import com.google.appinventor.components.annotations.Extension;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.runtime.util.YailList;
import com.google.appinventor.components.runtime.ComponentContainer;
import com.google.appinventor.components.runtime.errors.YailRuntimeError;
import com.google.appinventor.components.runtime.AndroidNonvisibleComponent;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.media.AudioAttributes;
import android.media.RingtoneManager;
import android.os.Build;
@Extension(
        description = "Extension component for EmergencyChannel. Built with <3 and Rush.",
        icon = "icon.png"
)
public class EmergencyChannel extends AndroidNonvisibleComponent {
private Context context;
    public EmergencyChannel(ComponentContainer container) {
        super(container.$form());
        context = (Context) container.$context();
    }

    @SimpleFunction(description = "Returns the sum of the given list of integers.")
    public int SumAll(YailList listOfInts) {
        int sum = 0;

        for (final Object o : listOfInts.toArray()) {
            try {
                sum += Integer.parseInt(o.toString());
            } catch (NumberFormatException e) {
                throw new YailRuntimeError(e.toString(), "NumberFormatException");
            }
        }

        return sum;
    }
   @SimpleFunction(description = "Creates a high-importance emergency notification channel with sound and vibration.")
   public void CreateEmergencyChannel() {

       if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

               String channelId = "emergency_alerts_v2";

                       NotificationManager manager =
                                   (NotificationManager) context.getSystemService(
                                                   Context.NOTIFICATION_SERVICE);

                                                           if (manager.getNotificationChannel(channelId) == null) {

                                                                       NotificationChannel channel = new NotificationChannel(
                                                                                       channelId,
                                                                                                       "Emergency Alerts",
                                                                                                                       NotificationManager.IMPORTANCE_HIGH
                                                                                                                                   );

                                                                                                                                               channel.setDescription(
                                                                                                                                                               "Emergency alerts for fire, flood, earthquake and railway."
                                                                                                                                                                           );

                                                                                                                                                                                       channel.enableVibration(true);

                                                                                                                                                                                                   channel.setVibrationPattern(
                                                                                                                                                                                                                   new long[]{0, 500, 250, 500, 250, 700}
                                                                                                                                                                                                                               );

                                                                                                                                                                                                                                           AudioAttributes audioAttributes =
                                                                                                                                                                                                                                                           new AudioAttributes.Builder()
                                                                                                                                                                                                                                                                               .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                                                                                                                                                                                                                                                                                                   .build();

                                                                                                                                                                                                                                                                                                               channel.setSound(
                                                                                                                                                                                                                                                                                                                               RingtoneManager.getDefaultUri(
                                                                                                                                                                                                                                                                                                                                                   RingtoneManager.TYPE_NOTIFICATION),
                                                                                                                                                                                                                                                                                                                                                                   audioAttributes
                                                                                                                                                                                                                                                                                                                                                                               );

                                                                                                                                                                                                                                                                                                                                                                                           manager.createNotificationChannel(channel);
                                                                                                                                                                                                                                                                                                                                                                                                   }
                                                                                                                                                                                                                                                                                                                                                                                                       }
                                                                                                                                                                                                                                                                                                                                                                                                       } 
}
