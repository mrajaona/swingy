package mrajaona.swingy.builder;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import mrajaona.swingy.data.GameMapData;
import mrajaona.swingy.data.SaveFileData;
import mrajaona.swingy.data.character.HeroData;
import mrajaona.swingy.exception.BuilderException;
import mrajaona.swingy.exception.InvalidCoordException;
import mrajaona.swingy.exception.InvalidViewTypeException;
import mrajaona.swingy.exception.SaveFileBuilderException;

public class SaveFileBuilder {

    @Getter private long id;

    @NotNull @Valid
    @Getter private HeroData    hero;

    @NotNull @Valid
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

    public SaveFileData build() throws BuilderException {

        //Create ValidatorFactory which returns validator
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

        //It validates bean instances
        Validator validator = factory.getValidator();

        //Validate bean
        Set<ConstraintViolation<SaveFileBuilder>> fileConstraintViolations = validator.validate(this);

        //Show errors
        if (fileConstraintViolations.size() > 0) {
            throw (new SaveFileBuilderException());
        }

        return (new SaveFileData(this));

    }

    public SaveFileData newFile(HeroData hero, GameMapData map) throws BuilderException {
        this.hero = hero;
        this.map  = map;

        return (build());
    }

    public SaveFileData loadFile(SaveFileData loaded) throws BuilderException, InvalidViewTypeException, InvalidCoordException {
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
