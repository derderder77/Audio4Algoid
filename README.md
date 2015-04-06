# Audio4Algoid

Audio4Algoid is a plugin for the Android application of [Algoid](http://www.algoid.net "lien vers Algoid").
Audio4Algoid add functions to play audios files in Algoid.

All the functions are in the audio object.

  function      | arguments      | description  
------------- |-------------| ---------
 playSound     |  path \( full path of the audio file\*. \)  | play the sound at path. **Only for little audio file, don't use it for music**  
 loadMusic      |   path \( full path of the audio file\*. \)  | load a song to  uses with other music functions 
 playMusic      | nothing            |  play or continue the loaded song  
pauseMusic | nothing | pause the loaded song
stopMusic | nothing | stop the loaded song 

\* : See the note

Note : If you don't start the path by a /, the begin if the path will be /sdcard/Algoid/raw.
Exemple : `audio\.loadMusic\("example.mp3"\)` will try to load /sdcard/Algoid/raw/example\.mp3 \.
