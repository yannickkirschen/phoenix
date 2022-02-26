# Architecture of the code base

| Module                | Depends on                                           | Description                                                                      |
|-----------------------|------------------------------------------------------|----------------------------------------------------------------------------------|
| `phoenix-common`      | -                                                    | Utility classes, configuration model                                             |
| `phoenix-layouts`     | -                                                    | Swing layouts                                                                    |
| `phoenix-icons`       | -                                                    | Interfaces to work with icons                                                    |
| `phoenix-icons-jlfgr` | `phoenix-icons`                                      | Implementation of `phoenix-icons` for the Java look and feel Graphics Repository |
| `phoenix-icons-tango` | `phoenix-icons`                                      | Implementation of `phoenix-icons` for the Tango Desktop Project                  |
| `phoenix-components`  | `phoenix-common`, `phoenix-layouts`, `phoenix-icons` | Swing components                                                                 |
| `phoenix-core`        | `phoenix-components`, `phoenix-icons-jlfgr`          | Core application features (application context, view localization)               |
| `phoenix-showcase`    | `phoenix-core`                                       | Showcase                                                                         |
