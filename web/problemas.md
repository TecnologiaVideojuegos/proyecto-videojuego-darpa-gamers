---
layout: default
---

## Problemas encontrados y soluciones

Aquí vamos vamos a exponer todos los problemas que se han dado en la etapas de desarrollo del proyecto.

<br />
<div style="text-align: justify">
El mayor problema al que no hemos enfrentado ha sido a la hora de meter el audi oen el proyecto, ya que nos indicaba que no era
capaz de encontrar una librería que si que estaba en el proyecto. 
</div>
<br />

```bash

Tue May 21 19:15:27 CEST 2019 INFO:Slick Build #237
Tue May 21 19:15:27 CEST 2019 INFO:LWJGL Version: 2.9.2
Tue May 21 19:15:27 CEST 2019 INFO:OriginalDisplayMode: 1366 x 768 x 32 @60Hz
Tue May 21 19:15:27 CEST 2019 INFO:TargetDisplayMode: 1024 x 768 x 0 @0Hz
Tue May 21 19:15:28 CEST 2019 INFO:Starting display 1024x768
Tue May 21 19:15:28 CEST 2019 INFO:Use Java PNG Loader = true
WARNING: Found unknown Windows version: Windows 10
Attempting to use default windows plug-in.
Loading: net.java.games.input.DirectAndRawInputEnvironmentPlugin
Tue May 21 19:15:28 CEST 2019 INFO:Found 0 controllers
Tue May 21 19:15:29 CEST 2019 INFO:Initialising sounds..
Tue May 21 19:15:29 CEST 2019 ERROR:Sound initialisation failure.
Tue May 21 19:15:29 CEST 2019 ERROR:Could not locate OpenAL library.
org.lwjgl.LWJGLException: Could not locate OpenAL library.
at org.lwjgl.openal.AL.create(AL.java:156)
at org.lwjgl.openal.AL.create(AL.java:102)
at org.lwjgl.openal.AL.create(AL.java:206)
at org.newdawn.slick.openal.SoundStore$1.run(SoundStore.java:295)
at java.security.AccessController.doPrivileged(Native Method)
at org.newdawn.slick.openal.SoundStore.init(SoundStore.java:292)
at org.newdawn.slick.Music.<init>(Music.java:156)
at org.newdawn.slick.Music.<init>(Music.java:75)
at menu.MenuMainGame.init(MenuMainGame.java:37)
at org.newdawn.slick.state.StateBasedGame.init(StateBasedGame.java:171)
at org.newdawn.slick.AppGameContainer.setup(AppGameContainer.java:393)
at org.newdawn.slick.AppGameContainer.start(AppGameContainer.java:317)
at loop_game.MainGame.<init>(MainGame.java:28)
at loop_game.MainGame.main(MainGame.java:45)

```
<br />
<div style="text-align: justify">
Investigando nos dimos cuenta que si poniamos la variable de entorno de Java 
java.library.path con una rutá absoluta al la carpeta de slick funcionaba.Esto supuso un problema ya que cada uno tenía un path distinto, por lo que al inicio del juego se modifico de forma dinamica añadiendo esta linea.
</div>
<br />
```java
System.setProperty("java.library.path", new File("./slick").getAbsolutePath());

```

<br />


----

[Volver a la página principal](./)
