# Look and Feel

Phoenix requires the current LAF to provide some properties, such as the primary
color. Unfortunately, there is no standardized way to retrieve those properties.

Therefore, each LAF is wrapped by a `common.laf.LookAndFeelWrapper`, that
provides abstract methods to retrieve required properties. Implementations are
available for each of Swing's built-in LAF and an instance of the appropriate
wrapper can be retrieved by calling `LookAndFeelWrapper.defaultWrapper()`.

When you use a different LAF than the defaults in Swing, you first have to
register a wrapper for that LAF by
calling `LookAndFeelWrapper.addWrapper(WRAPPER.class)`.
