package nz.co.cucumber.tstzrangetest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.vladmihalcea.hibernate.type.range.Range;

@Component
public class CreateEntitiesWithTestRanges implements ApplicationRunner {
    Logger log = LoggerFactory.getLogger(this.getClass());

    static final String NO_DST_NAME = "NO DST";
    static final String CROSSES_DST_NAME = "CROSSES DST";

    @Autowired
    EntityWithRangeRepository repo;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        EntityWithRange rangeNoDST = repo.findByName(NO_DST_NAME);
        if (rangeNoDST == null) {
            rangeNoDST = new EntityWithRange();
            rangeNoDST.setName(NO_DST_NAME);
            rangeNoDST.setRange(Range.zonedDateTimeRange("[2018-05-28T16:00:02+12,2018-08-28T16:00:05+12]"));
            repo.save(rangeNoDST);
        }

        // If such a document exists, the exception will be thrown by the next line.
        // To see this, comment out/delete the DROP TABLE line from the schema.sql in resources.
        
        // If the document is newly created you can see the error by viewing the repo from the browser
        // localhost:8080/entityWithRanges
        EntityWithRange rangeCrossesDST = repo.findByName(CROSSES_DST_NAME);
        if (rangeCrossesDST == null) {
            rangeCrossesDST = new EntityWithRange();
            rangeCrossesDST.setName(CROSSES_DST_NAME);
            
            // DST 2018 in NZ started end of September
            // second part of range is in December, so the range crosses DST
            rangeCrossesDST.setRange(Range.zonedDateTimeRange("[2018-05-28T16:00:02+12,2018-12-28T16:00:05+12]"));
            repo.save(rangeCrossesDST);
        }
    }
}