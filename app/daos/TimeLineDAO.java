package daos;

import com.avaje.ebean.Model;
import models.TimeLine;

import java.util.List;
import java.util.Optional;

/**
 * Created by Bruno on 09/05/17.
 */
public class TimeLineDAO {
    private Model.Finder<Long, TimeLine> timeLines = new Model.Finder<>(TimeLine.class);

    public Optional<TimeLine> comId(Long id) {
        TimeLine timeLine = timeLines.byId(id);
        return Optional.ofNullable(timeLine);
    }
    public List<TimeLine> todos() {
        return timeLines.all();
    }
}
