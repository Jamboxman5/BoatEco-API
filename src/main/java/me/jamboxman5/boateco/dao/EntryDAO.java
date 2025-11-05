package me.jamboxman5.boateco.dao;

import me.jamboxman5.boateco.model.Entry;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface EntryDAO {

    @SqlQuery("SELECT * FROM gasEntries WHERE plate = :plate")
    @RegisterBeanMapper(Entry.class)
    List<Entry> findByPlate(@Bind("plate") String plate);

    @SqlUpdate("INSERT INTO gasEntries (month, day, year, miles, gallons, cost, plate) VALUES (" +
            ":month, " +
            ":day, " +
            ":year, " +
            ":miles, " +
            ":gallons, " +
            ":cost, " +
            ":plate)")
    void add(
            @Bind("month") int month,
            @Bind("day") int day,
            @Bind("year") int year,
            @Bind("miles") int miles,
            @Bind("gallons") double gallons,
            @Bind("cost") double cost,
            @Bind("plate") String plate);

    @SqlUpdate("delete from gasEntries where " +
            "month = :month and " +
            "day = :day and " +
            "year = :year and " +
            "miles = :miles and " +
            "gallons = :gallons and " +
            "cost = :cost and " +
            "plate = :plate")
    void delete(
            @Bind("month") int month,
            @Bind("day") int day,
            @Bind("year") int year,
            @Bind("miles") int miles,
            @Bind("gallons") double gallons,
            @Bind("cost") double cost,
            @Bind("plate") String plate);

    @SqlUpdate("delete from gasEntries where " +
            "month = :month and " +
            "day = :day and " +
            "year = :year and " +
            "plate = :plate")
    void delete(
            @Bind("month") int month,
            @Bind("day") int day,
            @Bind("year") int year,
            @Bind("plate") String plate);


}
