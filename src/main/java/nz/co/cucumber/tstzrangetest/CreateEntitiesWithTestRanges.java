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
        
        /** To reproduce either
         * Run once as is then try to view the repository via the REST endpoint at
         * localhost:8080/entityWithRanges.  
         * 
         * OR
         * 
         * Delete the DROP TABLE line from schema.sql and run twice.
         * The second time a document will exist by the name CROSSES_DST_NAME
         * and the exception will be thrown by the findByName(CROSSES_DST_NAME) call below
         */

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