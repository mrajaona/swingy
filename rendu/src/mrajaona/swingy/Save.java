package mrajaona.swingy;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.table.TableUtils;

import mrajaona.swingy.data.character.HeroData;

/*
** Singleton
** Manages saved Hero data
*/

public class Save {

    private static Save manager     = new Save();
    private Dao<HeroData, Long> heroDao = null;

    private Save() {}

    public static Save getManager() {
        return (manager);
    }

    // ----- Connect / disconnect from db

    private final static String DATABASE_URL = "jdbc:sqlite:src/main/resources/swingy.db";
    private static JdbcPooledConnectionSource connectionSource = null;

    public void openConnection() throws SQLException {
        if (connectionSource == null)
            connectionSource = new JdbcPooledConnectionSource(DATABASE_URL);

        TableUtils.createTableIfNotExists(connectionSource, HeroData.class);
        heroDao = DaoManager.createDao(connectionSource, HeroData.class);
    }

    public void closeConnection() throws IOException {
        connectionSource.close();
        connectionSource = null;
    }

    // ----- Edit db

    public void save(HeroData hero) throws SQLException {
        Dao.CreateOrUpdateStatus status = heroDao.createOrUpdate(hero);
        System.out.println(
            "created: " + status.isCreated() + System.lineSeparator() +
            "updated: " + status.isUpdated() + System.lineSeparator() +
            "modified: " + status.getNumLinesChanged()
            );

    }

    public void delete(HeroData hero) throws SQLException {
        heroDao.delete(hero);
    }

    // ----- Find in db

    public List<HeroData> listHeroes() throws SQLException {
        // For medium sized or large tables, this may load a lot of objects into memory so you should consider using the iterator() method instead.
        List<HeroData> heroList = heroDao.queryForAll();
        return (heroList);
    }

    public HeroData load(long id) throws SQLException {
        return (heroDao.queryForId(id));
    }

}
