# Icons

Phoenix lets you choose the icons you like. Yet, it requires some default icons
to exist. For that reason, Phoenix provides a dynamic mechanism: all required
icons can be found in the enum `icons.DefaultIcon`. Paths to all these icons can
be retrieved by using the interface `icons.IconSet`. Phoenix provides two
reference implementations, one for
the [Java look and feel Graphics Repository](https://www.oracle.com/java/technologies/java-look-and-feel-graphics-repository.html)
and one for
the [Tango Desktop Project](http://tango.freedesktop.org/Tango_Desktop_Project).

## Set and use an icon set

You can set the icon set by setting `phoenix.laf.iconSet` in
your `phoenix.properties`. The default
is `sh.yannick.phoenix.icons.JLFGRIconSet`.

If you want to use your own icon set, you have to implement `icons.IconSet` and
provide paths to all default icons.

You can find the default icons and the icon set interface in `phoenix-icons`:

```xml

<dependency>
    <groupId>sh.yannick.phoenix</groupId>
    <artifactId>phoenix-icons</artifactId>
    <version>VERSION</version>
</dependency>
```
