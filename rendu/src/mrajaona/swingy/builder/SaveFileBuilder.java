package mrajaona.swingy.builder;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import mrajaona.swingy.data.GameMapData;
import mrajaona.swingy.data.SaveFileData;
import mrajaona.swingy.data.character.HeroData;

public class SaveFileBuilder {

    @Getter private long id;

    @NotNull
    @Getter private HeroData    hero;

    @NotNull
    @Getter private GameMapData map;

    public SaveFileBuilder setId(long id) {
        this.id = id;
        return (this);
    }

    public SaveFileBuilder setHero(HeroData hero) {
        this.hero = hero;
        return (this);
    }

    public SaveFileBuilder setMap(GameMapData map) {
        this.map = map;
        return (this);
    }

    public SaveFileData build() {

        //Create ValidatorFactory which returns validator
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

        //It validates bean instances
        Validator validator = factory.getValidator();

        //Validate bean
        Set<ConstraintViolation<SaveFileBuilder>> fileConstraintViolations = validator.validate(this);

        boolean error = false;

        //Show errors
        if (fileConstraintViolations.size() > 0) {
            // TODO : Exception
            System.out.println("Invalid save file");
            error = true;
        }

        if (error == true)
            return (null);
        else {
            System.out.println("Valid SaveFileData"); // DEBUG
            return (new SaveFileData(this));
        }

    }

    public SaveFileData newFile(HeroData hero, GameMapData map) {
        this.hero = hero;
        this.map  = map;

        return (build());
    }

    public SaveFileData loadFile(SaveFileData loaded) {
        if (loaded == null)
            return (null);

        HeroBuilder heroBuilder   = new HeroBuilder();
        GameMapBuilder mapBuilder = new GameMapBuilder();

        return (
            setId(loaded.getId())
            .setHero(
                heroBuilder.loadHero(
                    loaded.getHero()
                    ))
            .setMap(
                mapBuilder.loadMap(
                    loaded.getMap()
                    ))
            .build());
    }

}
