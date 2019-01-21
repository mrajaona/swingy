package mrajaona.swingy.util;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcPooledConnectionSource;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import com.j256.ormlite.table.TableUtils;

import mrajaona.swingy.builder.GameMapBuilder;
import mrajaona.swingy.builder.SaveFileBuilder;
import mrajaona.swingy.data.GameData;
import mrajaona.swingy.data.GameMapData;
import mrajaona.swingy.data.SaveFileData;
import mrajaona.swingy.data.artifact.ArtifactData;
import mrajaona.swingy.data.character.HeroData;
import mrajaona.swingy.model.SaveFileModel;

/*
** Singleton
** Manages saved Hero data
*/

public class SaveManager {

    private static SaveManager          manager     = new SaveManager();
    private Dao<HeroData, Long>         heroDao     = null;
    private Dao<ArtifactData, Long>     artifactDao = null;
    private Dao<GameMapData, Long>      mapDao      = null;
    private Dao<SaveFileData, Long>     saveDao     = null;

    private SaveManager() {}

    public static SaveManager getManager() {
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

        TableUtils.createTableIfNotExists(connectionSource, GameMapData.class);
        mapDao = DaoManager.createDao(connectionSource, GameMapData.class);

        TableUtils.createTableIfNotExists(connectionSource, SaveFileData.class);
        saveDao = DaoManager.createDao(connectionSource, SaveFileData.class);

    }

    private void closeConnection() throws IOException {
        connectionSource.close();
        connectionSource = null;
    }

    // ----- Edit db

    public void save() throws SQLException, IOException {
        HeroData     hero     = GameData.getData().getHero();
        if (hero == null)
            return ;

        GameMapData  map      = GameData.getData().getMap();
        if (map == null) {
        	GameMapBuilder builder = new GameMapBuilder();
        	map = builder.newMap();
        }

        SaveFileData saveFile = GameData.getData().getSaveFile();
        if (saveFile == null) {
        	SaveFileBuilder builder = new SaveFileBuilder();
            saveFile = builder.newFile(hero, map);
        } else {
            SaveFileModel.updateFile(hero, map);
        }

        openConnection();

        Dao.CreateOrUpdateStatus statusHelm   = artifactDao.createOrUpdate(hero.getHelm());
        Dao.CreateOrUpdateStatus statusArmor  = artifactDao.createOrUpdate(hero.getArmor());
        Dao.CreateOrUpdateStatus statusWeapon = artifactDao.createOrUpdate(hero.getWeapon());

        Dao.CreateOrUpdateStatus statusHero   = heroDao.createOrUpdate(hero);
        // DEBUG
        System.out.println(
            "Hero" + System.lineSeparator() +
            "created: " + statusHero.isCreated() + System.lineSeparator() +
            "updated: " + statusHero.isUpdated() + System.lineSeparator() +
            "modified: " + statusHero.getNumLinesChanged()
            );

        Dao.CreateOrUpdateStatus statusMap    = mapDao.createOrUpdate(map);
        // DEBUG
        System.out.println(
            "Map" + System.lineSeparator() +
            "created: " + statusMap.isCreated() + System.lineSeparator() +
            "updated: " + statusMap.isUpdated() + System.lineSeparator() +
            "modified: " + statusMap.getNumLinesChanged()
            );
        Dao.CreateOrUpdateStatus statusSave   = saveDao.createOrUpdate(saveFile);
        // DEBUG
        System.out.println(
            "Save" + System.lineSeparator() +
            "created: " + statusSave.isCreated() + System.lineSeparator() +
            "updated: " + statusSave.isUpdated() + System.lineSeparator() +
            "modified: " + statusSave.getNumLinesChanged()
            );

        closeConnection();
    }

    public void delete(long id) throws SQLException, IOException {
        openConnection();

        SaveFileData saveFile = saveDao.queryForId(id);

        if (saveFile == null) {
            return ;
        }

        HeroData hero = saveFile.getHero();

        if (hero != null) {
            artifactDao.delete(hero.getHelm());
            artifactDao.delete(hero.getArmor());
            artifactDao.delete(hero.getWeapon());

            heroDao.delete(hero);
        }

        GameMapData map = saveFile.getMap();
        if (map != null) {
            mapDao.delete(map);
        }

        saveDao.delete(saveFile);

        closeConnection();
    }

    // ----- Find in db

    public List<HeroData> listHeroes() throws SQLException, IOException {
        openConnection();
        // For medium sized or large tables, this may load a lot of objects into memory so you should consider using the iterator() method instead.
        // List<HeroData> heroList = heroDao.iterator();
        List<HeroData> heroList = heroDao.queryForAll();

        Iterator<HeroData> heroIterator = heroList.iterator();
        while (heroIterator.hasNext()) {
            HeroData hero = heroIterator.next();

            artifactDao.refresh(hero.getHelm());
            artifactDao.refresh(hero.getArmor());
            artifactDao.refresh(hero.getWeapon());
        }

        closeConnection();
        return (heroList);
    }

    public SaveFileData load(long heroId) throws SQLException, IOException {
        openConnection();

        QueryBuilder<SaveFileData, Long> queryBuilder = saveDao.queryBuilder();

        Where<SaveFileData, Long> query = queryBuilder.where()
          .eq("hero_id", heroId);

        List<SaveFileData> result = queryBuilder.query();

        if (result == null || result.size() > 1)  {
            // Exception
            return (null);
        } else if (result.size() == 0) {
        	// Wrong id ; possible in console view ;
            return (null);
        }


        // java.lang.IndexOutOfBoundsException: Index 0 out of bounds for length 0

        SaveFileData saveFile = result.get(0);

        if (saveFile == null) {
            // Wrong id ; possible in console view ;
            return (null);
        }

        HeroData hero = saveFile.getHero();
        heroDao.refresh(hero);

        if (hero != null) {
            artifactDao.refresh(hero.getHelm());
            artifactDao.refresh(hero.getArmor());
            artifactDao.refresh(hero.getWeapon());
        } else {
            // Exception
        }

        GameMapData map = saveFile.getMap();
        mapDao.refresh(map);

        if (map != null) {
            // update enemies
        } else {
            // Exception
        }

        closeConnection();
        return (saveFile);
    }

}
