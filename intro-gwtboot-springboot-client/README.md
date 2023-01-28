# Your GWT App with DominoUI and Dagger2

## Compile GWT

```
mvn clean verify
```

## Compile GWT with Profile Mock (development-mock)
```
mvn clean verify -Pdevelopment-mock
```

## Run GWT devmode

```
mvn gwt:generate-module gwt:devmode
```

## Run GWT devmode with Profile Mock (development-mock)

```
mvn gwt:generate-module gwt:devmode -Pdevelopment-mock
```
