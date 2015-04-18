# Audio4Algoid

Audio4Algoid is a plugin for the Android application of [Algoid](http://www.algoid.net "lien vers Algoid").
Audio4Algoid add functions to play audios files in Algoid.

All the functions are in the audio object.

  function      | arguments      | description  
------------- |-------------| ---------
 playSound     |  path \( full path of the audio file\*. \)  | play the sound at path. **Only for little audio file, don't use it for music**  
 loadMusic      |   path \( full path of the audio file\*. \)  | load a song and return an id
 playMusic      | id : the id of the song |  play or continue the id song  
pauseMusic | id | pause the id song
stopMusic | id | stop the id song 
seekToInMusic | id, msec | seek to msec ( in milliseconde ) in the id song

\* : See the note

Note : If you don't start the path by a /, the begin if the path will be /sdcard/Algoid/raw.
Exemple : `audio.loadMusic("example.mp3")` will try to load /sdcard/Algoid/raw/example.mp3.
