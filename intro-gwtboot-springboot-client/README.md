# Your GWT App with DominoUI and Dagger2

## Compile GWT

```
mvn clean verify
```

## Compile GWT with Profile Mock
```
mvn clean verify -Pdevelopment-mock
```

## Run GWT devmode

```
mvn gwt:generate-module gwt:devmode
```

## Run GWT devmode with Profile Mock

```
mvn gwt:generate-module gwt:devmode -Pdevelopment-mock
```
