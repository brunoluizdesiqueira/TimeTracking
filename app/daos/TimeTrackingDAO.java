package daos;

import com.avaje.ebean.Model;
import models.TimeTracking;

import java.util.List;
import java.util.Optional;

/**
 * Created by Bruno on 09/05/17.
 */
public class TimeTrackingDAO {
    private Model.Finder<Long, TimeTracking> timeTrackings = new Model.Finder<>(TimeTracking.class);

    public Optional<TimeTracking> comId(Long id) {
        TimeTracking timeTracking = timeTrackings.byId(id);
        return Optional.ofNullable(timeTracking);
    }
    public List<TimeTracking> todos() {
        return timeTrackings.all();
    }
}
