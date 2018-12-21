package mrajaona.swingy;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.table.TableUtils;

import mrajaona.swingy.data.GameData;
import mrajaona.swingy.data.artifact.ArtifactData;
import mrajaona.swingy.data.character.HeroData;

/*
** Singleton
** Manages saved Hero data
*/

public class Save {

    private static Save manager                 = new Save();
    private Dao<HeroData, Long> heroDao         = null;
    private Dao<ArtifactData, Long> artifactDao = null;

    private Save() {}

    public static Save getManager() {
        return (manager);
    }

    // ----- Connect / disconnect from db

    private final static String DATABASE_URL = "jdbc:sqlite:src/main/resources/swingy.db";
    private static JdbcPooledConnectionSource connectionSource = null;

    private void openConnection() throws SQLException {
        if (connectionSource == null)
            connectionSource = new JdbcPooledConnectionSource(DATABASE_URL);

        TableUtils.createTableIfNotExists(connectionSource, HeroData.class);
        heroDao = DaoManager.createDao(connectionSource, HeroData.class);

        TableUtils.createTableIfNotExists(connectionSource, ArtifactData.class);
        artifactDao = DaoManager.createDao(connectionSource, ArtifactData.class);

    }

    private void closeConnection() throws IOException {
        connectionSource.close();
        connectionSource = null;
    }

    // ----- Edit db

    public void save() throws SQLException, IOException {
        HeroData hero = GameData.getHero();

        openConnection();

        Dao.CreateOrUpdateStatus statusHelm   = artifactDao.createOrUpdate(hero.getHelm());
        Dao.CreateOrUpdateStatus statusArmor  = artifactDao.createOrUpdate(hero.getArmor());
        Dao.CreateOrUpdateStatus statusWeapon = artifactDao.createOrUpdate(hero.getWeapon());

        Dao.CreateOrUpdateStatus status = heroDao.createOrUpdate(hero);
        System.out.println(
            "created: " + status.isCreated() + System.lineSeparator() +
            "updated: " + status.isUpdated() + System.lineSeparator() +
            "modified: " + status.getNumLinesChanged()
            );
        closeConnection();
    }

    public void delete(HeroData hero) throws SQLException, IOException {
        openConnection();

        artifactDao.delete(hero.getHelm());
        artifactDao.delete(hero.getArmor());
        artifactDao.delete(hero.getWeapon());

        heroDao.delete(hero);
        closeConnection();
    }

    // ----- Find in db

    public List<HeroData> listHeroes() throws SQLException, IOException {
        openConnection();
        // For medium sized or large tables, this may load a lot of objects into memory so you should consider using the iterator() method instead.
        List<HeroData> heroList = heroDao.queryForAll();
        closeConnection();
        return (heroList);
    }

    public HeroData load(long id) throws SQLException, IOException {
        openConnection();
        HeroData hero = heroDao.queryForId(id);

        artifactDao.refresh(hero.getHelm());
        artifactDao.refresh(hero.getArmor());
        artifactDao.refresh(hero.getWeapon());

        closeConnection();
        return (hero);
    }

}
