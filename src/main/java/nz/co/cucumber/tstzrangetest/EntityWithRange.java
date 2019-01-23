package nz.co.cucumber.tstzrangetest;

import java.time.ZonedDateTime;
import java.util.UUID;

import javax.persistence.Entity;

import org.hibernate.annotations.TypeDef;
import org.springframework.data.jpa.domain.AbstractPersistable;

import com.vladmihalcea.hibernate.type.range.PostgreSQLRangeType;
import com.vladmihalcea.hibernate.type.range.Range;

@Entity
@TypeDef(name = "range", typeClass = PostgreSQLRangeType.class, defaultForType = Range.class)
public class EntityWithRange extends AbstractPersistable<UUID> {
    
    String name;
    
    Range<ZonedDateTime> range;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Range<ZonedDateTime> getRange() {
        return range;
    }

    public void setRange(Range<ZonedDateTime> range) {
        this.range = range;
    }

    @Override
    public String toString() {
        return "EntityWithRange [name=" + name + ", range=" + range + "]";
    }
}
