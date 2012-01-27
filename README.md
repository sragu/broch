# Broch

Bootstrap build environment, and allow sharing of ant build scripts across
projects.

## Tasks
* Allow sharing of ant scripts by bootstraping them. (done)
* Include sample projects for usage, and run broch against them. (done)
* Managing build scripts as a project dependency. (done)
* Extract only the dependent common scripts, dont unzip the world
* Structure the common ant scripts as pluggable, isolated units.
* Embedded ivy to fetch jars needed for common build scripts, such as (ant-contrib, sonar, cobertura, etc.)
