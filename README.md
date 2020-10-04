# Dto 2 Json Parser

- [Installation](#installation)
- [Features](#features)

---

## Installation

- Clone repository
- `mvn clean install`
- `mvn clean compile assembly:single`
- If you want a convenient method to call the jar, add an alias to your ~/.bashrc:

    `alias dto2json='java -jar <YOURPATH>/dto-to-json-parser/target/dto-to-json-parser-1.0-SNAPSHOT-jar-with-dependencies.jar'`

## Usage

- If you want to generate JSON for any Dto, navigate into the working directory of that project and make sure the specific `.class` file
 has been built

> Show required parameters

```shell
$ dto2json
```

> Generate JSON Output

```shell
$ dto2json -f=TestclassDto.class -p=com.ggpstudio.parser
```

---

## Features
For future releases there is Javax Validation Annotation compliant random Output intended


